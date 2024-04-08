package paciente;

import database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModelPaciente {

    public List<Object> finAll(){

        Connection objConnection = ConfigDB.openConection();

        List<Object> listPaciente = new ArrayList<>();

        try {

            String sql = "SELECT * FROM paciente;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Entity_paciente objPaciente = new Entity_paciente();
                objPaciente.setId(objResult.getInt("id"));
                objPaciente.setName(objResult.getString("name"));
                objPaciente.setDni(objResult.getString("dni"));
                objPaciente.setLastName(objResult.getString("lastname"));
                objPaciente.setDateOfBirth(objResult.getString("fecha"));

                listPaciente.add(objPaciente);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }


        return listPaciente;
    }


    public Object insert(Object obj){

        Entity_paciente objpaciente = (Entity_paciente) obj;

        Connection objConnection = ConfigDB.openConection();


        try {

            String sql = "INSERT INTO paciente(name,lastname,fecha,dni) VALUES(?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objpaciente.getName());


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }


        return false;
    }

    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConection();
        Entity_paciente objPaciente = (Entity_paciente) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE paciente SET name = ?, lastname = ?, fecha = ?, dni = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objPaciente.getName());
            objPrepare.setString(2, objPaciente.getLastName());
            objPrepare.setString(3, objPaciente.getDateOfBirth());
            objPrepare.setString(4, objPaciente.getDni());
            objPrepare.setInt(5, objPaciente.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "El paciente se actualizo correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdate;
    }

    public boolean delete(Object obj) {

        Entity_paciente objPaciente = (Entity_paciente) obj;
        Connection objConnection = ConfigDB.openConection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM paciente WHERE id_paciente = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objPaciente.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "El paciente se elimino correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());

        }

        ConfigDB.closeConnection();
        return isDelete;
    }

    public Entity_paciente findById(int id_paciente){
        Connection objConnection = ConfigDB.openConection();
        Entity_paciente objPaciente = null;

        try {
            String sql = "SELECT * FROM paciente WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id_paciente);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objPaciente = new Entity_paciente();
                objPaciente.setId(objResult.getInt("id"));
                objPaciente.setName(objResult.getString("name"));
                objPaciente.setLastName(objResult.getString("lastname"));
                objPaciente.setDateOfBirth(objResult.getString("fecha"));
                objPaciente.setDni(objResult.getString("dni"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return objPaciente;
    }

    public Entity_paciente findByDocument(int document_paciente){
        Connection objConnection = ConfigDB.openConection();
        Entity_paciente objPaciente = null;

        try {
            String sql = "SELECT * FROM paciente WHERE dni = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, document_paciente);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objPaciente = new Entity_paciente();
                objPaciente.setId(objResult.getInt("id"));
                objPaciente.setName(objResult.getString("name"));
                objPaciente.setLastName(objResult.getString("lastname"));
                objPaciente.setDateOfBirth(objResult.getString("fecha"));
                objPaciente.setDni(objResult.getString("dni"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return objPaciente;
    }

}
