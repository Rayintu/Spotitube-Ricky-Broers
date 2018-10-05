package nl.han.dea.ricky.LocalStorage;

import nl.han.dea.ricky.Response.UserToken;

public class LocalTokenStorage {
    private UserToken token;

    public LocalTokenStorage(UserToken token) {
        this.token = token;
    }

    public UserToken getToken() {
        return token;
    }

    public void setToken(UserToken token) {
        this.token = token;
    }

    public String getTokenName() {
        return token.getUser();
    }

    public String getTokenNumber() {
        return token.getToken();
    }
}
