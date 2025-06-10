package loginPage;

import java.sql.*;
import koneksi.koneksiDB;
import java.util.Scanner;

public class register {

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (insertUserIntoDatabase(name, email, password)) {
            System.out.println("Registration successful! You can now log in.");
        } else {
            System.out.println("Error during registration. Please try again.");
        }
    }

    private boolean insertUserIntoDatabase(String name, String email, String password) {
        try (Connection connection = koneksiDB.getConnection()) {
            String query = "INSERT INTO users (namaUser, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, name);
                st.setString(2, email);
                st.setString(3, password);

                int result = st.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
