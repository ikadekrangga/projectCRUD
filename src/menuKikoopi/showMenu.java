package menuKikoopi;

import java.sql.*;
import koneksi.koneksiDB;

public class showMenu {

    public void tampilkanSemuaMenu() {
        System.out.println("==== MENU FOOD ====");
        tampilkanMenuByKategori("food");

        System.out.println("\n==== MENU DRINK ====");
        tampilkanMenuByKategori("drink");
    }

    private void tampilkanMenuByKategori(String kategori) {
        String query = "SELECT * FROM menu WHERE kategoriMenu = ?";

        try (Connection connection = koneksiDB.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

            st.setString(1, kategori);
            ResultSet rs = st.executeQuery();

            // Memeriksa jika hasil query kosong
            if (!rs.isBeforeFirst()) {
                System.out.println("Tidak ada menu untuk kategoriMenu " + kategori);
            }

            while (rs.next()) {
                String idMenu = rs.getString("idMenu");
                String kategoriMenu = rs.getString("kategoriMenu");
                String namaMenu = rs.getString("namaMenu");
                int hargaMenu = rs.getInt("hargaMenu");
                System.out.println("ID: " + idMenu + " | Nama: " + namaMenu + " | Harga: Rp" + hargaMenu);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Menampilkan rincian error
            System.out.println("Gagal menampilkan menu kategori " + kategori);
        }
    }
}
