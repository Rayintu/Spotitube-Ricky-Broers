package nl.han.dea.ricky;

public class LoginCredentials {
    private String user;
    private String password;

    public LoginCredentials() {

    }

    public LoginCredentials(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user.toLowerCase();
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password.toLowerCase();
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
