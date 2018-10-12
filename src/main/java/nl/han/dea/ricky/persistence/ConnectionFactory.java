package nl.han.dea.ricky.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final String username = "root";
    private final String password = "yeet";
    private Properties databaseProperties;


    public ConnectionFactory() {
        //mogelijk kan deze try catch weg even testen
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        if (databaseProperties == null) {
            databaseProperties = readProperties();
        }
        try {
//            return DriverManager.getConnection("jdbc:mysql://localhost:3306/spotitube",
//                    username, password);
//
            return DriverManager.getConnection(
                    databaseProperties.getProperty("db.url"),
                    databaseProperties.getProperty("db.user"),
                    databaseProperties.getProperty("db.password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties readProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/database.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
