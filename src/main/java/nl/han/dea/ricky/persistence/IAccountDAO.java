package nl.han.dea.ricky.persistence;

import nl.han.dea.ricky.entity.Account;
import nl.han.dea.ricky.entity.LoginCredentials;
import nl.han.dea.ricky.entity.UserToken;

import java.util.List;

public interface IAccountDAO {
    List<Account> getAllAccounts();

    void persistAccount(Account account);

    boolean login(LoginCredentials creds);

    UserToken getToken(LoginCredentials creds);
}
