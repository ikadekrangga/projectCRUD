package koneksi;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class koneksiDB {
    private static Connection conn;
    
    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {  // Pastikan koneksi baru hanya dibuat jika tidak ada koneksi terbuka
            try {
                String url = "jdbc:mysql://localhost:3306/dbpbo";
                String username = "root";
                String pass = "";
            
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                conn = DriverManager.getConnection(url, username, pass);
            } catch (SQLException e) {
                Logger.getLogger(koneksiDB.class.getName()).log(Level.SEVERE, null, e);
                throw e;  // Lempar ulang jika gagal
            }
        }
        return conn;
    }
}
