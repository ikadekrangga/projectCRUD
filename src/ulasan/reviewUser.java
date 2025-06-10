package ulasan;

import loginPage.loginUser;
import java.sql.*;
import java.util.Scanner;
import koneksi.koneksiDB;

public class reviewUser {
    public String ulasan;
    public loginUser user;  // Menggunakan loginUser untuk menyimpan data pengguna yang login
    public int idUser;

    // Konstruktor dengan loginUser
    public reviewUser(loginUser user) {
        this.user = user;  // Set user yang sedang login
    }
    public int getIdUser(){
        return this.idUser=user.idUser ;
    }

    // Method untuk menambahkan ulasan
    public void tambahkanUlasan() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan ulasan anda!");
        this.ulasan = scanner.nextLine();
        
        if (insertReviewIntoDatabase(this.ulasan)) {
            System.out.println("Berhasil memberikan ulasan");
        } else {
            System.out.println("Gagal memberikan Ulasan!");
        }
    }

    // Method untuk memasukkan ulasan ke database
    private boolean insertReviewIntoDatabase(String ulasan) {
        // Gunakan try-with-resources untuk memastikan koneksi dan statement ditutup otomatis
        try (Connection connection = koneksiDB.getConnection()) {
            String query = "INSERT INTO ulasan (idUser, ulasan) VALUES (?,?)";
            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setInt(1, idUser);  // Menggunakan ID pengguna dari loginUser
                st.setString(2, ulasan);     // Menggunakan ulasan yang diberikan pengguna

                int rowsAffected = st.executeUpdate();
                return rowsAffected > 0;  // Jika ada baris yang terpengaruh, berarti berhasil
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
