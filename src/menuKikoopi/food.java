package menuKikoopi;

import java.sql.*;
import koneksi.koneksiDB;

public class food extends menu {
    public food(String nama, int harga) {
        super(nama, harga);
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

            st.setString(1, "food");  // Kategori 'food' langsung dimasukkan
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

