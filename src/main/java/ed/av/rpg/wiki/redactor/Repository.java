package ed.av.rpg.wiki.redactor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    public final static String URL = "jdbc:sqlite:sample.db";

    public static void createTable(String name) {

        String sql = """
                CREATE TABLE IF NOT EXISTS world_entity (
                    id INTEGER,
                    parent_id INTEGER,
                    name VARCHAR(30),
                    description TEXT 
                );
                """;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица создана успешно.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveData(List<WorldEntity> worldEntityList) {

        String sql = "INSERT INTO world_entity(id, parent_id, name, description) VALUES(?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
                conn.setAutoCommit(false);

                for (int i = 0; i < worldEntityList.size(); i++) {
                    pstmt.setInt(1, worldEntityList.get(i).getId());
                    pstmt.setInt(2, worldEntityList.get(i).getParentId());
                    pstmt.setString(3, worldEntityList.get(i).getName());
                    pstmt.setString(4, worldEntityList.get(i).getDescription());
                    pstmt.addBatch();

                    if (i % 100 == 0) {
                        pstmt.executeBatch();
                    }
                }
                pstmt.executeBatch();
                conn.commit();

                System.out.println("Данные успешно добавлены.");
            }
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Соединение с БД закрыто");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public static List<WorldEntity> readData() {

        String sql = "SELECT * FROM world_entity";

        try (Connection conn = DriverManager.getConnection(URL)) {
            try(PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

                var worldEntities = new ArrayList<WorldEntity>();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int parentId = rs.getInt("parent_id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    worldEntities.add(new WorldEntity(id, parentId, name, description));
                }
                System.out.println("Данные успешно загружены.");
                return worldEntities;

            } catch (SQLException e) {
                System.out.println("Ошибка при выполнении запроса: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
        return null;
    }

    public static void deleteAllData() {

        String sql = "DELETE FROM world_entity";

        try (Connection conn = DriverManager.getConnection(URL)) {
            try(Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Данные успешно удалены.");

            } catch (SQLException e) {
                System.out.println("Ошибка при выполнении запроса: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }
}
