package latihan.praktikum2.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Cakes {
    public static void main(String[] x) throws Exception{
        //1.Buat variabel bertiper FileReader
        String namafile = "src/main/resources/cake.csv";
        FileReader fr = new FileReader(namafile);

        //2.Bungkus dalam BufferedReader supaya ada method readLine
        BufferedReader reader = new BufferedReader(fr);

        //3.Looping, baca data, dan tampilkan 
        String data = reader.readLine(); //header, ignore 
        data = reader.readLine();
        
         //4. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/cakes";
        String dbUser = "root";
        String dbPass = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        
        while(data != null){
            System.out.println("========================");
            String[] cake = data.split(",");
            String sql = "INSERT INTO produk (id_produk,nama_produk,harga,id_kategori) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cake[0]);
            ps.setString(2, cake[1]);
            ps.setString(3, cake[2]);
            ps.setString(4, cake[3]);
            ps.executeUpdate();
            data = reader.readLine();
        }
        //4. Tutup file
        reader.close();
        conn.close();
    }
}
