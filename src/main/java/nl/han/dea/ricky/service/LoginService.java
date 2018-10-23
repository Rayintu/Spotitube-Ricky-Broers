package nl.han.dea.ricky.service;

import nl.han.dea.ricky.LoginCredentials;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.persistence.IAccountDAO;
import nl.han.dea.ricky.response.UserToken;

import javax.inject.Inject;

public class LoginService implements ILoginService {

    @Inject
    IAccountDAO IAccountDAO;

    public LoginService() {
    }

    @Override
    public UserToken login(LoginCredentials creds) throws LoginException {
        if (IAccountDAO.login(creds)) {
            return IAccountDAO.getToken(creds);

        } else {
            throw new LoginException("Incorrect authenticate credentials.");
        }
    }
}
