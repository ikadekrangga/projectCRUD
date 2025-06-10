package loginPage;
import koneksi.koneksiDB;
import parent.user;
import java.sql.*;

public class loginUser extends user {
    private login Login;
    public int idUser; // Ini akan menyimpan idUser setelah login berhasil

    public loginUser(String name, String email, String password) {
        super(name, email, password);
        this.Login = new login();
        this.idUser = -1; // Default value
    }

    public boolean userLogin() {
        var scanner = new java.util.Scanner(System.in);

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // 1. Verifikasi login
        boolean isLoggedIn = Login.loginUser(email, password);
        
        if (isLoggedIn) {
            // 2. Dapatkan idUser dari database
            this.idUser = getIdUserByEmail(email); // Perbaikan penting!
            
            if (this.idUser != -1) {
                System.out.println("Welcome, " + getName() + " (User).");
                return true;
            } else {
                System.out.println("Error: User ID not found!");
                return false;
            }
        } else {
            System.out.println("Login failed!");
            return false;
        }
    }
    
    private int getIdUserByEmail(String email) {
        String query = "SELECT idUser FROM users WHERE email = ?";
        try (Connection connection = koneksiDB.getConnection()) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, email);
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idUser"); // Return actual ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 jika gagal
    }
}