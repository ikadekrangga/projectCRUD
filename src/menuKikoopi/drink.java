package menuKikoopi;

import java.sql.*;
import koneksi.koneksiDB;

public class drink extends menu {
    String kategori = "drink";

    public drink(String nama, int harga, String kategori) {
        super(nama, harga);
        this.kategori = kategori;
    }

    /**
     *
     * @return
     */
    @Override
   public int getId(){
        return idMenu;
    }

    public boolean showMenu() {
        String query = "SELECT * FROM menu WHERE kategoriMenu = ?";

        try (Connection connection = koneksiDB.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

            st.setString(1, kategori);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
