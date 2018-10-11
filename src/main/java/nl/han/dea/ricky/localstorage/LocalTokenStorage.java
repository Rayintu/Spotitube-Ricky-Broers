package nl.han.dea.ricky.localstorage;

import nl.han.dea.ricky.response.UserToken;

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
