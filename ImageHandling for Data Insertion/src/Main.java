import java.io.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "kushmaster230";
        String image_path = "C:\\Users\\Kush Churausia\\Downloads\\IMAGE.jpg";

        String query = "INSERT INTO image_table(image_data) VALUES(?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded succesfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection establish succesfully");
            FileInputStream fileInputStream = new FileInputStream(image_path);
            byte[] imageData = new byte[fileInputStream.available()];
            fileInputStream.read(imageData);

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setBytes(1, imageData);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows > 0){
                System.out.println(" INSERTED DATA SUCCESSFULLY..!! ");
            }
            else{
                System.out.println(" IMAGE NOT INSERTED..!! ");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
