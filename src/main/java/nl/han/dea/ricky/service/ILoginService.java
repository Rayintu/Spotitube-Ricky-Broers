package nl.han.dea.ricky.service;

import nl.han.dea.ricky.LoginCredentials;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.response.UserToken;

public interface ILoginService {
    UserToken login(LoginCredentials creds) throws LoginException;
}
