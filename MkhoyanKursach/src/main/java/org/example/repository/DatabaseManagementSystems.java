package org.example.repository;

import java.sql.SQLException;

public enum DatabaseManagementSystems {

    POSTGRES_SQL("jdbc:postgresql://", "org.postgresql.Driver");

    private String protocol = "jdbc:default:connection:";
    private String driver;

    DatabaseManagementSystems(String protocol, String driver) {
        this.protocol = protocol;
        this.driver = driver;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getDriver() {
        return driver;
    }

    public boolean isDuplicateInsert (SQLException sql){
        if (sql.getSQLState().startsWith("23")) return true;
        return false;
    }
}