package menuKikoopi;

import java.sql.*;
import koneksi.koneksiDB;

public class menu {
    String nama;
    int harga;
    int idMenu;

    public menu(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public int getharga() {
        return harga;
    }

    public int getId() {
        return idMenu;
    }
}
