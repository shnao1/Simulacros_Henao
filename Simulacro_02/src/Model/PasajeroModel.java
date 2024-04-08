package Model;

import Database.ConfigDB;
import Entity.PasajeroEntity;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel {
    public List<Object> findAll(){

        Connection objConnection = ConfigDB.openConection();

        List<Object> listPasajero = new ArrayList<>();

        try {

            String sql = "select * from pasajero;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                PasajeroEntity objPasajero = new PasajeroEntity();

                objPasajero.setId(objResult.getInt("id"));
                objPasajero.setName(objResult.getString("nombre"));
                objPasajero.setLastName(objResult.getString("apellido"));
                objPasajero.setDni(objResult.getString("dni"));

                listPasajero.add(objPasajero);

            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return listPasajero;
    }

    public Object insert(Object obj){

        Connection objConnection = ConfigDB.openConection();

        PasajeroEntity objPasajero = (PasajeroEntity) obj;

        try{

            String sql = "INSERT INTO pasajero (nombre,apellido,dni) VALUES (?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objPasajero.getName());
            objPrepare.setString(2,objPasajero.getLastName());
            objPrepare.setString(3,objPasajero.getDni());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objPasajero.setId(objResult.getInt(1));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objPasajero;
    }

    public boolean delete(Object obj){

        Connection objConnection = ConfigDB.openConection();

        PasajeroEntity objPasajero = (PasajeroEntity) obj;

        try {

            String sql = "DELETE FROM  pasajero WHERE id=? ;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objPasajero.getId());

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

        PasajeroEntity objPasajero = (PasajeroEntity) obj;

        try {

            String sql = "UPDATE pasajero SET name=?,apellido=?,dni =? WHERE id=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objPasajero.getName());
            objPrepare.setString(2,objPasajero.getLastName());
            objPrepare.setString(3,objPasajero.getDni());
            objPrepare.setInt(4,objPasajero.getId());

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

    public PasajeroEntity findById (int id){

        Connection objConnection = ConfigDB.openConection();

        PasajeroEntity objPasajero = null;

        try{

            String sql = "SELECT * FROM pasajero WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objPasajero = new PasajeroEntity();
                objPasajero.setName(objResult.getString("nombre"));
                objPasajero.setLastName(objResult.getString("apellido"));
                objPasajero.setDni(objResult.getString("dni"));
                objPasajero.setId(objResult.getInt("id"));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        ConfigDB.closeConnection();
        return objPasajero;
    }
}
