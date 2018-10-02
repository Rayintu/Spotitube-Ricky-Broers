package nl.han.dea.ricky;

public class LoginReturnObject {
    private String token;
    private String user;

    public LoginReturnObject(String token, String user) {
        this.token = token;
        this.user = user;

    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }


}
