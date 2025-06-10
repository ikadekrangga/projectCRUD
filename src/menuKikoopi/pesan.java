package menuKikoopi;

import java.sql.*;
import koneksi.koneksiDB;
import loginPage.loginUser;
import menuKikoopi.menu;

public class pesan {
    private String namaMenu;
    private int hargaMenu;
    private int idUser;
    private int idMenu;
    private loginUser user;
    private menu menu;

    // Menambahkan namaMenu di konstruktor
    public pesan(String namaMenu, loginUser user) {
        this.namaMenu = namaMenu;
        this.hargaMenu = hargaMenu(namaMenu);
        this.idUser = user.idUser;  // Get the logged-in user's id
        // Menambahkan pemanggilan idMenu untuk mendapatkan idMenu dari database
        this.idMenu = idMenu(namaMenu);
    }

    // Metode untuk memesan makanan atau minuman
    public void orderFoodOrDrink(String kategori) {
        System.out.println("id user :" + idUser);

        if (kategori.equalsIgnoreCase("food")) {
            food makanan = new food(namaMenu, hargaMenu);
            if (makanan.showMenu()) {
                System.out.println("Makanan " + namaMenu + " berhasil dipesan!");
                insertPesanan(makanan);  // Call insertPesanan for food
            } else {
                System.out.println("Makanan tidak tersedia.");
            }
        } else if (kategori.equalsIgnoreCase("drink")) {
            drink minuman = new drink(namaMenu, hargaMenu, kategori);
            if (minuman.showMenu()) {
                System.out.println("Minuman " + namaMenu + " berhasil dipesan!");
                insertPesanan(minuman);  // Call insertPesanan for drink
            } else {
                System.out.println("Minuman tidak tersedia.");
            }
        } else {
            System.out.println("Kategori tidak dikenal.");
        }
    }

    // Menyimpan pesanan ke dalam database
    private void insertPesanan(menu menu) {
        try (Connection connection = koneksiDB.getConnection()) {
            String query = "INSERT INTO transaksi (idUser, idMenu, namaMenu, hargaMenu) VALUES (?, ?, ?, ?)";

            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setInt(1, idUser);  // idUser yang valid sudah ada di objek pesanan
                st.setInt(2, idMenu);   // Gunakan idMenu yang sudah didapat dari metode idMenu
                st.setString(3, namaMenu);
                st.setInt(4, hargaMenu);  // Ambil hargaMenu dari objek menu (food atau drink)

                int rowsAffected = st.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Pesanan berhasil dimasukkan ke database.");
                } else {
                    System.out.println("Gagal menambahkan pesanan.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat memasukkan pesanan.");
        }
    }

    // Menampilkan menu yang sudah dipesan
    public void showOrderedMenu() {
        System.out.println("Menu yang dipesan: " + namaMenu + " - Harga: Rp" + hargaMenu);
    }

    // Mendapatkan idMenu dari database berdasarkan namaMenu
    public int idMenu(String namaMenu) {
        String query = "SELECT idMenu FROM menu WHERE namaMenu = ?";
        int idMenu = -1; // Nilai default, misalnya -1, untuk menunjukkan bahwa tidak ditemukan

        try (Connection connection = koneksiDB.getConnection()) {
            var st = connection.prepareStatement(query);
            st.setString(1, namaMenu);
            ResultSet rs = st.executeQuery();

            // Mengecek apakah ada hasil dari query
            if (rs.next()) {
                // Ambil nilai idMenu dari ResultSet dan set ke variabel idMenu
                idMenu = rs.getInt("idMenu"); // Ambil hasilnya, tipe data disesuaikan (getInt untuk int)
                System.out.println("ID Menu: " + idMenu); // Menampilkan idMenu untuk debugging
            } else {
                System.out.println("Menu tidak ditemukan.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Cetak error untuk debugging
        }

        // idMenu bisa digunakan di sini setelah query dijalankan
        return idMenu; // Kembalikan nilai idMenu
    }
    public int hargaMenu(String namaMenu) {
        String query = "SELECT hargaMenu FROM menu WHERE namaMenu = ?";
        int hargaMenu = -1; // Nilai default, misalnya -1, untuk menunjukkan bahwa tidak ditemukan

        try (Connection connection = koneksiDB.getConnection()) {
            var st = connection.prepareStatement(query);
            st.setString(1, namaMenu);
            ResultSet rs = st.executeQuery();

            // Mengecek apakah ada hasil dari query
            if (rs.next()) {
                // Ambil nilai idMenu dari ResultSet dan set ke variabel idMenu
                hargaMenu = rs.getInt("hargaMenu"); // Ambil hasilnya, tipe data disesuaikan (getInt untuk int)
                System.out.println("Harga Menu: " + hargaMenu); // Menampilkan idMenu untuk debugging
            } else {
                System.out.println("Menu tidak ditemukan.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Cetak error untuk debugging
        }

        // idMenu bisa digunakan di sini setelah query dijalankan
        return hargaMenu; // Kembalikan nilai idMenu
    }
}
