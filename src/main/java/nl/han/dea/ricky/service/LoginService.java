package nl.han.dea.ricky.service;

import nl.han.dea.ricky.entity.LoginCredentials;
import nl.han.dea.ricky.entity.UserToken;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.persistence.IAccountDAO;

import javax.inject.Inject;

public class LoginService implements ILoginService {

    @Inject
    private IAccountDAO accountDAO;

    public LoginService() {

    }

    @Override
    public UserToken login(LoginCredentials creds) throws LoginException {
        if (accountDAO.login(creds)) {
            return accountDAO.getToken(creds);

        } else {
            throw new LoginException("Incorrect authenticate credentials.");
        }
    }

    @Inject
    public void setAccountDAO(nl.han.dea.ricky.persistence.IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
}
