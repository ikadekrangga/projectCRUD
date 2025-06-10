package loginPage;

import java.sql.*;
import koneksi.koneksiDB;
import java.util.Scanner;

public class login {

    public boolean loginUser(String email, String password) {
        if (validateLogin(email, password)) {
            System.out.println("Login Successful!");
            return true;
        } else {
            System.out.println("Invalid email or password!");
            return false;
        }
    }

    private boolean validateLogin(String email, String password) {
        try (Connection connection = koneksiDB.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ? and password = ?")) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                return rs.next();  // Jika ada hasil, berarti login berhasil
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
