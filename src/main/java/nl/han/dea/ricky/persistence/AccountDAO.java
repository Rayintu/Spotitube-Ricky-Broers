package nl.han.dea.ricky.persistence;

import nl.han.dea.ricky.entity.Account;
import nl.han.dea.ricky.entity.LoginCredentials;
import nl.han.dea.ricky.entity.UserToken;
import nl.han.dea.ricky.exception.LoginException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AccountDAO implements IAccountDAO {

    private ConnectionFactory connectionFactory;

    public AccountDAO() {
        connectionFactory = new ConnectionFactory();
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM ACCOUNTS")
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("user");
                String password = resultSet.getString("password");
                accounts.add(new Account(username, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public void persistAccount(Account account) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO ACCOUNT (user,password) VALUES (?,?);");
        ) {
            statement.setString(1, account.getUser());
            statement.setString(2, account.getPassword());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean login(LoginCredentials creds) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE user = ? AND password = ?")
        ) {
            statement.setString(1, creds.getUser());
            statement.setString(2, creds.getPassword());

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new LoginException("Incorrect credentials");
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserToken getToken(LoginCredentials creds) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String currentDate = sdf.format(date);
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT accounts.full_name,t.user, t.token, t.valid_until FROM accounts JOIN tokens t on accounts.user = t.user WHERE accounts.user = ?");
                PreparedStatement newUserStatement = connection.prepareStatement("SELECT * FROM accounts WHERE user = ?")
        ) {
            statement.setString(1, creds.getUser());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String fullName = resultSet.getString("full_name");
                if (sdf.parse(currentDate).before(resultSet.getDate("valid_until")) && resultSet.getString("token") != null) {
                    return new UserToken(resultSet.getString("token"), fullName);
                } else {
                    return new UserToken(createNewTokenForUserAndUpdateItInDatabase(resultSet.getString("user")), fullName);
                }
            } else {
                newUserStatement.setString(1, creds.getUser());
                ResultSet newUserResultset = newUserStatement.executeQuery();
                if (newUserResultset.next()) {
                    return new UserToken(createNewTokenForNewUserAndInsertItIntoDatabase(newUserResultset.getString("user")), newUserResultset.getString("full_name"));
                } else {
                    throw new SQLException("Something went wrong");
                }
            }


        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String createNewTokenForNewUserAndInsertItIntoDatabase(String user) {
        UUID uuid = UUID.randomUUID();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO tokens (user, token, valid_until) VALUES (?,?,DATE_ADD(curdate(), INTERVAL 1 DAY))");
        ) {
            statement.setString(1, user);
            statement.setString(2, uuid.toString());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return uuid.toString();
    }

    private String createNewTokenForUserAndUpdateItInDatabase(String username) {
        UUID uuid = UUID.randomUUID();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE tokens SET token= ?, valid_until = DATE_ADD(curdate(), INTERVAL 1 DAY) WHERE user = ?");

        ) {
            statement.setString(1, uuid.toString());
            statement.setString(2, username);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return uuid.toString();
    }
}
