package nl.han.dea.ricky.Service;

import nl.han.dea.ricky.LoginCredentials;
import nl.han.dea.ricky.LoginException;
import nl.han.dea.ricky.Response.UserToken;

public class LoginService {
    public LoginService() {
    }

    public UserToken login(LoginCredentials creds) throws LoginException {
        if (creds.getUser().equals("ricky") && creds.getPassword().equals("yeet")) {
            UserToken loginToken = new UserToken("1234-1234-1234", "Ricky Broers");
            return loginToken;
        } else {
            throw new LoginException("Incorrect login credentials.");
        }
    }
}
