package menuKikoopi;
import java.sql.*;
import koneksi.koneksiDB;
import java.util.Scanner;

public class updateMenu {
    private int idMenu;  // variabel idMenu diubah menjadi instance variable

    // Metode utama untuk edit menu
    public void editMenu() {
        var scanner = new Scanner(System.in);
        
        // Menampilkan semua menu yang tersedia
        showMenu tampilkan = new showMenu();
        tampilkan.tampilkanSemuaMenu();

        // Meminta user untuk memasukkan idMenu yang ingin diedit
        System.out.println("Masukkan idMenu :");
        idMenu = scanner.nextInt();  // Simpan input idMenu yang dimasukkan oleh user
        scanner.nextLine();  // Membersihkan buffer scanner setelah nextInt()
        
        // Menampilkan data menu yang ingin diedit
        updateMenuited();  // Memanggil metode untuk menampilkan menu yang dipilih
        
        // Meminta user untuk memasukkan informasi baru menu
        System.out.println("==== Edit menu ====");
        System.out.println("Masukkan nama menu : ");
        String namaMenu = scanner.nextLine();
        
        System.out.println("Masukkan kategori menu (food/drink) : ");
        String kategoriMenu = scanner.nextLine();
        
        System.out.println("Masukkan Harga menu : ");
        int hargaMenu = scanner.nextInt();
        
        // Memperbarui menu ke dalam database
        editMenuToDatabase(namaMenu, hargaMenu, kategoriMenu);
    }

    // Metode untuk mengupdate data menu ke dalam database
    private void editMenuToDatabase(String namaMenu, int hargaMenu, String kategoriMenu) {
        String query = "UPDATE menu SET namaMenu = ?, kategoriMenu = ?, hargaMenu = ? WHERE idMenu = ?";

        try (Connection connection = koneksiDB.getConnection()) {
            PreparedStatement st = connection.prepareStatement(query);
            // Set parameter query sesuai urutan
            st.setString(1, namaMenu);  // Nama menu
            st.setString(2, kategoriMenu);  // Kategori menu
            st.setInt(3, hargaMenu);  // Harga menu
            st.setInt(4, idMenu);  // idMenu yang akan diupdate
            
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Menu berhasil diperbarui di database.");
            } else {
                System.out.println("Gagal memperbarui menu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat memperbarui menu.");
        }
    }

    // Metode untuk menampilkan data menu yang ingin diedit berdasarkan idMenu
    public void updateMenuited() {
        String query = "SELECT * FROM menu WHERE idMenu = ?";
        try (Connection connection = koneksiDB.getConnection()) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, idMenu);  // Set parameter untuk idMenu
            
            ResultSet rs = st.executeQuery();
            
            // Menampilkan data menu yang ditemukan
            if (rs.next()) {
                // Ambil data dari ResultSet
                String namaMenu = rs.getString("namaMenu");
                String kategoriMenu = rs.getString("kategoriMenu");
                int hargaMenu = rs.getInt("hargaMenu");
                
                System.out.println("ID: " + idMenu + " | Nama: " + namaMenu + " | Kategori: " + kategoriMenu + " | Harga: Rp" + hargaMenu);
            } else {
                System.out.println("Menu dengan idMenu " + idMenu + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat mengambil data menu.");
        }
    }
}
