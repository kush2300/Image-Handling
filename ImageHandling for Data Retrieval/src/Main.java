//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "kushmaster230";
        String folder_path = "C:\\Users\\Kush Churausia\\Downloads\\Images\\";

        String query = "SELECT image_data FROM image_table WHERE image_id = (?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded succesfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection establish succesfully");

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, 1);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                byte[] image_data = resultSet.getBytes("image_data");
                String image_path = folder_path + "extractedImage.jpg";
                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(image_data);
                System.out.println("Image Successfully Extracted....!!!!");
            }
            else {
                System.out.println("Image Not Found");
            }


        }catch(SQLException e){
                throw new RuntimeException(e);
            } catch(FileNotFoundException e){
                throw new RuntimeException(e);
            } catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    }
