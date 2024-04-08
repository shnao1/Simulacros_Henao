package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

        static Connection objConection = null;

        public static Connection openConection(){
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                String url = "jdbc:mysql://uxz7jqdyj6qayna8:jNHTZWOeHc7XxgXz88jP@bvhkve8p6us7jb3ealha-mysql.services.clever-cloud.com:3306/bvhkve8p6us7jb3ealha";

                String user = "uxz7jqdyj6qayna8";

                String paswword = "jNHTZWOeHc7XxgXz88jP";

                objConection = (Connection) DriverManager.getConnection(url,user,paswword);


            }catch (ClassNotFoundException e){
                System.out.println("ERROR >> Driver no Instalado"+e.getMessage());
            }catch (SQLException e){
                System.out.println("ERROR >> error al conectar con la base de datos "+e.getMessage());
            }
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
