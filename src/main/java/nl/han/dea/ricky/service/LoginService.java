package nl.han.dea.ricky.service;

import nl.han.dea.ricky.LoginCredentials;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.persistence.AccountDAO;
import nl.han.dea.ricky.response.UserToken;

public class LoginService {

    AccountDAO accountDAO = new AccountDAO();

    public LoginService() {
    }

    public UserToken login(LoginCredentials creds) throws LoginException {
        if (accountDAO.login(creds)) {
            return accountDAO.getToken(creds);

        } else {
            throw new LoginException("Incorrect authenticate credentials.");
        }
    }
}
