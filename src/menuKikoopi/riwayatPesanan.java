package menuKikoopi;

import java.sql.*;
import koneksi.koneksiDB;

public class riwayatPesanan {
    public void tampilkanPesanan() {
        System.out.println("==== Pesanan ====");
        riwayatPesanan();
    }

    private void riwayatPesanan() {
        String query = "SELECT p.idPesanan, u.idUser, t.namaMenu " +
                       "FROM pesanan p " +
                       "JOIN users u ON p.idUser = u.idUser " +
                       "JOIN transaksi t ON u.idTransaksi = t.idTransaksi";

        try (Connection connection = koneksi.koneksiDB.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String idPesanan = rs.getString("idPesanan");
                String idUser = rs.getString("idUser");
                String statusPesanan = rs.getString("statusPesanan");
                System.out.println("ID: " + idPesanan + " | ID USER: " + idUser + " | Harga: Rp" + statusPesanan);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Tidak ada transaksi!");
        }
    }
}
