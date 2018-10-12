package nl.han.dea.ricky.entity;

public class Account {
    private String name;
    private String password;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Account() {
    }

    public String getUser() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
