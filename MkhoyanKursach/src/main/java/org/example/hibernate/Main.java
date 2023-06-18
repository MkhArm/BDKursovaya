package org.example.hibernate;


import org.example.repository.DatabaseManagementSystems;

public class Main {
    private static final String URL_LOCALE_NAME = "localhost/";         // ваш компьютер
    private static final String DATABASE_NAME = "airport_db";   // имя базы
    public static final String USER_NAME = "postgres";          // имя пользователя
    public static final String DATABASE_PASS = "221199";         // пароль базы данных
    private static final boolean DEBUG = true;
    private static final String URL = URL_LOCALE_NAME;
    public static final DatabaseManagementSystems DATABASE = DatabaseManagementSystems.POSTGRES_SQL;
    public static final String DATABASE_URL = DATABASE.getProtocol() + URL + DATABASE_NAME;
    public static void log(Object obj, String msg){
        if (DEBUG) System.out.println(obj.getClass().getSimpleName() + ": " + msg);
    }

}