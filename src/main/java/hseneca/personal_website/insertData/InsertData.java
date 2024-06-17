package hseneca.personal_website.insertData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class InsertData {
    private static final String URL = "jdbc:postgresql://localhost:5432/yourdatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Random random = new Random();

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(false);
            String sql = "INSERT INTO large_dataset (name, value) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);

            for (int i = 1; i <= 100_000_000; i++) {
                pstmt.setString(1, "Name " + i);
                pstmt.setInt(2, random.nextInt(1000));
                pstmt.addBatch();

                if (i % 10_000 == 0) {
                    pstmt.executeBatch();
                    conn.commit();
                    System.out.println(i + " records inserted.");
                }
            }

            pstmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
