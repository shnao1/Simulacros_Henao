package Model;

import Database.ConfigDB;
import Entity.AvionEntity;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvionModel {

    public List<Object> findAll(){

        Connection objConnection = ConfigDB.openConection();

        List<Object> listAvion = new ArrayList<>();

        try {

            String sql = "select * from avion;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                AvionEntity objAvion = new AvionEntity();

                objAvion.setId(objResult.getInt("id"));
                objAvion.setModel(objResult.getString("modelo"));
                objAvion.setCapacity(objResult.getInt("capacidad"));

                listAvion.add(objAvion);

            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return listAvion;
    }

    public Object insert(Object obj){

        Connection objConnection = ConfigDB.openConection();

        AvionEntity objAvion = (AvionEntity) obj;

        try{

            String sql = "INSERT INTO avion (model,capacidad) VALUES (?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objAvion.getModel());
            objPrepare.setInt(2,objAvion.getCapacity());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objAvion.setId(objResult.getInt(1));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objAvion;
    }

    public boolean delete(Object obj){

        Connection objConnection = ConfigDB.openConection();

        AvionEntity objAvion = (AvionEntity) obj;

        try {

            String sql = "DELETE FROM  avion WHERE id=? ;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objAvion.getId());

            int afected = objPrepare.executeUpdate();

            if (afected > 0){
                JOptionPane.showMessageDialog(null,"The update was successful");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }finally {
            ConfigDB.closeConnection();
        }
        return true;
    }

    public boolean update (Object obj){

        Connection objConnection = ConfigDB.openConection();

        AvionEntity objAvion = (AvionEntity) obj;

        try {

            String sql = "UPDATE especialidad SET name=?,description=? WHERE id=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objAvion.getModel());
            objPrepare.setInt(2,objAvion.getCapacity());
            objPrepare.setInt(3,objAvion.getId());

            int afected = objPrepare.executeUpdate();

            if (afected > 0){
                JOptionPane.showMessageDialog(null,"The update was successful");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }finally {
            ConfigDB.closeConnection();
        }
        return true;
    }

    public AvionEntity findById (int id){

        Connection objConnection = ConfigDB.openConection();

        AvionEntity objAvion = null;

        try{

            String sql = "SELECT * FROM especialidad WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objAvion = new AvionEntity();
                objAvion.setCapacity(objResult.getString("capacidad"));
                objAvion.setModel(objResult.getInt("modelo"));
                objAvion.setId(objResult.getInt("id"));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        ConfigDB.closeConnection();
        return objAvion;
    }

}
