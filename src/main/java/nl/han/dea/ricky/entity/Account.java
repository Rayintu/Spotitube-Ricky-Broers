package nl.han.dea.ricky.entity;

public class Account {
    private String user;
    private String password;

    public Account(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Account() {
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
