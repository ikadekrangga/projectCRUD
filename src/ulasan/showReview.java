package ulasan;
import koneksi.koneksiDB;
import java.util.Scanner;
import java.sql.*;
import loginPage.loginAdmin;


public class showReview {
    public loginAdmin user;
    public void tampilkanUlasan(){
        System.out.println("==== ULASAN ====");
        tampilkanUlasanUser();
    }
    
    private void tampilkanUlasanUser(){
        String query = "SELECT * FROM ulasan";
        
        try(Connection connection = koneksiDB.getConnection()){
            var st = connection.prepareStatement(query);
            
            var rs = st.executeQuery();
            
            while(rs.next()){
                int idUlasan = rs.getInt("idUlasan");
                int idUser = rs.getInt("idUser");
                String ulasan = rs.getString("ulasan");
                System.out.println("----NO----|----idUser----|Ulasan");
                System.out.println(idUlasan +  idUser +  ulasan);
            }
            rs.close();
            st.close();
        }catch(Exception e){
            System.out.println("Belum ada ulasan!");
        }
    }
}
