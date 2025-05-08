package ed.av.rpg.wiki.redactor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteUtil {

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Не удалось загрузить драйвер SQLite: " + e.getMessage());
            return;
        }
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:wiki_data.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Соединение с базой данных установлено.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
