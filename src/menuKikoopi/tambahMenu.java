package menuKikoopi;
import java.sql.*;
import koneksi.koneksiDB;
import java.util.Scanner;


public class tambahMenu {
    
    public void addMenu(){
        var scanner = new Scanner(System.in);
        
        System.out.println("Masukkan nama menu : ");
        String namaMenu = scanner.nextLine();
        
        System.out.println("Masukkan kategori menu (food/drink) : ");
        String kategoriMenu = scanner.nextLine();
        
        System.out.println("Masukkan Harga menu : ");
        int hargaMenu = scanner.nextInt();
        

        
        addMenuToDatabase(namaMenu, hargaMenu, kategoriMenu);
    }
    
    private void addMenuToDatabase(String namaMenu, int hargaMenu, String kategoriMenu){
        String query = "INSERT into menu (kategoriMenu,namaMenu,hargaMenu) VALUES (?,?,?)";
        
        try(Connection connection = koneksiDB.getConnection();
            PreparedStatement st = connection.prepareStatement(query)){
            
            st.setString(1, kategoriMenu);
            st.setString(2,namaMenu);
            st.setInt(3, hargaMenu);
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Menu berhasil ditambahkan!");
            } else {
                System.out.println("Gagal menambahkan menu.");
            }
            st.close();
        }catch(Exception e){
             System.out.println("Terjadi kesalahan saat menambahkan menu.");
        }
    }
}
