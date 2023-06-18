package org.example.repository;

import org.example.hibernate.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.example.hibernate.Main.*;
public class JDBCRepository{
    private static JDBCRepository INSTANCE;
    private JDBCRepository() {
        checkDriver();
    }

    public static synchronized JDBCRepository getInstance() {
        if (INSTANCE == null){
            INSTANCE = new JDBCRepository();
        }
        return INSTANCE;
    }
    private void checkDriver () {
        try {
            Class.forName(Main.DATABASE.getDriver());
            Main.log(this, "Connected JDBC Driver - " + Main.DATABASE.getDriver());
        } catch (ClassNotFoundException e) {
            Main.log(this, "JDBC Driver is not found. Include it in your library path ");
            throw new RuntimeException(e);
        }
    }

    private Connection openConnection () throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER_NAME, DATABASE_PASS);
    }

}
