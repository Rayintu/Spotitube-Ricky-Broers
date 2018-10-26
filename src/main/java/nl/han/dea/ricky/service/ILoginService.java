package nl.han.dea.ricky.service;

import nl.han.dea.ricky.entity.LoginCredentials;
import nl.han.dea.ricky.entity.UserToken;
import nl.han.dea.ricky.exception.LoginException;

public interface ILoginService {
    UserToken login(LoginCredentials creds) throws LoginException;
}
