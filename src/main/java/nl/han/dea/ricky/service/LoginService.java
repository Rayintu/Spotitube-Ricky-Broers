package nl.han.dea.ricky.service;

import nl.han.dea.ricky.LoginCredentials;
import nl.han.dea.ricky.StorageFactory;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.localstorage.LocalTokenStorage;
import nl.han.dea.ricky.response.UserToken;

public class LoginService {
    StorageFactory storageFactory = new StorageFactory();
    LocalTokenStorage tokenStorage;

    public LoginService() {
    }

    public UserToken login(LoginCredentials creds) throws LoginException {
        if (creds.getUser().equals("ricky") && creds.getPassword().equals("yeet")) {
            tokenStorage = storageFactory.getInstanceOf();
            return new UserToken("1234-1234-1234", "Ricky Broers");

        } else {
            throw new LoginException("Incorrect authenticate credentials.");
        }
    }
}
