package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    static Connection objConection = null;

    public static Connection openConection(){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://ux1goxnamij3b0iw:ytn4OklgjyX6gB1hDZsQ@bvhd3krzi1e9amozyksm-mysql.services.clever-cloud.com:3306/bvhd3krzi1e9amozyksm";

            String user = "ux1goxnamij3b0iw";

            String paswword = "ytn4OklgjyX6gB1hDZsQ";

            objConection = (Connection) DriverManager.getConnection(url,user,paswword);


        }catch (ClassNotFoundException e){
            System.out.println("ERROR >> Driver no Instalado"+e.getMessage());
        }catch (SQLException e){
            System.out.println("ERROR >> error al conectar con la base de datos "+e.getMessage());
        }
        System.out.println("Conectada");

        return objConection;

    }

    public static void closeConnection(){
        try{
            if (objConection != null){
                objConection.close();
                System.out.println("Se finalizo la conexion con exito");
            }
        }catch (SQLException e){
            System.out.println("ERROR >> error al desconectar la base de datos "+e.getMessage());
        }
    }
}