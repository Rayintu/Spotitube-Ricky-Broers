package nl.han.dea.ricky.persistence;

import nl.han.dea.ricky.LoginCredentials;
import nl.han.dea.ricky.entity.Account;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.response.UserToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private ConnectionFactory connectionFactory;

    public AccountDAO() {
        connectionFactory = new ConnectionFactory();
    }

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

//            if (resultSet.getString("user").equals(creds.getUser()) && resultSet.getString("password").equals(creds.getPassword())) {
//                return true;
//            } else {
//                throw new LoginException("Incorrect credentials");
//            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserToken getToken(LoginCredentials creds) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT accounts.full_name, t.token FROM accounts JOIN tokens t on accounts.user = t.user WHERE accounts.user = ?");
        ) {
            statement.setString(1, creds.getUser());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new UserToken(resultSet.getString("token"), resultSet.getString("full_name"));
            } else {
                throw new SQLException();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
