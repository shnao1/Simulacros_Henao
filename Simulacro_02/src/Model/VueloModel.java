package Model;

import Database.ConfigDB;
import Entity.VueloEntity;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueloModel {
    public List<Object> findAll(){

        Connection objConnection = ConfigDB.openConection();

        List<Object> listVuelo = new ArrayList<>();

        try {

            String sql = "select * from vuelo;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                VueloEntity objVuelo = new VueloEntity();

                objVuelo.setId(objResult.getInt("id"));
                objVuelo.setDestiny(objResult.getString("destino"));
                objVuelo.setYear(objResult.getString("fecha"));
                objVuelo.setHour(objResult.getString("hora"));
                objVuelo.setIdAvion(objResult.getInt("id_avion"));

                listVuelo.add(objVuelo);

            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return listVuelo;
    }

    public Object insert(Object obj){

        Connection objConnection = ConfigDB.openConection();

        VueloEntity objVuelo = (VueloEntity) obj;

        try{

            String sql = "INSERT INTO vuelo (destino,fecha,hora) VALUES (?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objVuelo.getDestiny());
            objPrepare.setString(2,objVuelo.getYear());
            objPrepare.setString(3,objVuelo.getHour());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objVuelo.setId(objResult.getInt(1));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objVuelo;
    }

    public boolean delete(Object obj){

        Connection objConnection = ConfigDB.openConection();

        VueloEntity objVuelo = (VueloEntity) obj;

        try {

            String sql = "DELETE FROM  vuelo WHERE id=? ;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objVuelo.getId());

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

        VueloEntity objVuelo = (VueloEntity) obj;

        try {

            String sql = "UPDATE vuelo SET destino=?,fecha=?,hora =? WHERE id=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objVuelo.getDestiny());
            objPrepare.setString(2,objVuelo.getYear());
            objPrepare.setString(3,objVuelo.getYear());
            objPrepare.setInt(4,objVuelo.getId());

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

    public VueloEntity findById (int id){

        Connection objConnection = ConfigDB.openConection();

        VueloEntity objVuelo = null;

        try{

            String sql = "SELECT * FROM vuelo WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objVuelo = new VueloEntity();
                objVuelo.setDestiny(objResult.getString("destino"));
                objVuelo.setYear(objResult.getString("fecha"));
                objVuelo.setHour(objResult.getString("hora"));
                objVuelo.setId(objResult.getInt("id"));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }
}
