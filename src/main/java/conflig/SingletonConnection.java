package conflig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;
    private SingletonConnection() {}

    // đặt tên database financial_management cho đồng bộ nhé
    private static String jdbcURL = "jdbc:mysql://localhost:3306/financial_management_system?useSSL=false";
    private static String jdbcUsername = "root";
    //Tự điền password nha anh em, làm luôn cho tiện chỉ việc thay password thôi;
    private static String jdbcPassword = "duclap123";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                //load driver và đăng kí nó với ứng dụng (Đăng ký gọi phương thức Class.forName("driverName")
                Class.forName("com.mysql.jdbc.Driver");
                //tạo kết nối (connection)
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                System.out.println("success");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
