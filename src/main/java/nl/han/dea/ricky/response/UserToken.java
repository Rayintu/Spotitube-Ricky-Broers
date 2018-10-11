package nl.han.dea.ricky.response;

public class UserToken {
    private String token;
    private String user;

    public UserToken(String token, String user) {
        this.token = token;
        this.user = user;

    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
