package by.painter.model;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

abstract public class DBConnectorTool {

    protected final static Logger LOGGER = Logger.getLogger("log");
    private static String host;
    private static String user;
    private static String password;
    private static Connection connection;
    private static Statement statement;
    private static boolean isConnectionActive;


    public static void connect() {
        try {
            LOGGER.info("Подключение к БД");
            parseConfigs();
            connection = DriverManager.getConnection(host, user, password);
            statement = connection.createStatement();
            isConnectionActive = true;
        } catch (SQLException ex) {
            LOGGER.error("Ошибка подключения к базе данных: " + ex);
            JOptionPane.showMessageDialog(null, "Ошибка подключения к базе данных.", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void disconnect() {
        try {
            LOGGER.info("Отключение от БД");
            if (!isConnectionActive) {
                return;
            }
            statement.close();
            connection.close();
            isConnectionActive = false;
        } catch (SQLException ex) {
            LOGGER.error("Ошибка разрыва соединения с базой дланных: " + ex);
            JOptionPane.showMessageDialog(null, "Ошибка разрыва подключения с БД", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ResultSet executeSQL(String sql) {
        try {
            LOGGER.info("Выполнение SQL");
            if (!isConnectionActive || statement == null) {
                LOGGER.error("Ошибка выполнения SQL-запроса");
                return null;
            }
            LOGGER.info("Executing SQL: " + sql);
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
            LOGGER.error("Ошибка выполнения SQL-запроса: " + ex);
            JOptionPane.showMessageDialog(null, "Ошибка выполнения SQL-запроса.", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static int updateSQL(String sql) {
        try {
            LOGGER.info("Выполнение SQL");
            if (!isConnectionActive || statement == null) {
                LOGGER.error("Ошибка выполнения SQL-запроса");
                return 0;
            }
            LOGGER.info("Executing SQL: " + sql);
            return statement.executeUpdate(sql);
        } catch (SQLException ex) {
            LOGGER.error("Ошибка выполнения SQL-запроса: " + ex);
            JOptionPane.showMessageDialog(null, "Ошибка выполнения SQL-запроса.", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private static void parseConfigs() {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/db.properties");
            property.load(fis);
            host = property.getProperty("db.host");
            user = property.getProperty("db.login");
            password = property.getProperty("db.password");
            fis.close();
            LOGGER.info("DB properties: " + host + " " + user + " " + password);
        } catch (IOException exception) {
            LOGGER.error("Ошибка чтения конфигурационного файла" + exception);
        }

    }


}
