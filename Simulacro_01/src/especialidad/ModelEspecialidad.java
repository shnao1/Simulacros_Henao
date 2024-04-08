package especialidad;

import database.ConfigDB;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModelEspecialidad {

    public List<Object> getAll(){

        Connection objConnection = ConfigDB.openConection();

        List<Object> listEspciality = new ArrayList<>();

        try{

            String sql = "select * from especialidad;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                Entity_Especialidad objEspeciality = new Entity_Especialidad();

                objEspeciality.setId(objResult.getInt("id"));
                objEspeciality.setName(objResult.getString("name"));
                objEspeciality.setDescription(objResult.getString("description"));

                listEspciality.add(objEspeciality);

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listEspciality;
    }



    public Object insert(Object obj){

        Connection objConnection = ConfigDB.openConection();

        Entity_Especialidad objEspecialidad = (Entity_Especialidad) obj;

        try{

            String sql = "INSERT INTO especialidad (name,description) VALUES (?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objEspecialidad.getName());
            objPrepare.setString(2,objEspecialidad.getDescription());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objEspecialidad.setId(objResult.getInt(1));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objEspecialidad;
    }

    public boolean delete(Object obj){

        Connection objConnection = ConfigDB.openConection();

        Entity_Especialidad objEspecialidad = (Entity_Especialidad) obj;

        try {

            String sql = "DELETE FROM  especialidad WHERE id=? ;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objEspecialidad.getId());

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

        Entity_Especialidad objEspecialidad = (Entity_Especialidad) obj;

        try {

            String sql = "UPDATE especialidad SET name=?,description=? WHERE id=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objEspecialidad.getName());
            objPrepare.setString(2,objEspecialidad.getDescription());
            objPrepare.setInt(3,objEspecialidad.getId());

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

    public Entity_Especialidad findById (int id){

        Connection objConnection = ConfigDB.openConection();

        Entity_Especialidad objEspecialidad = null;

        try{

            String sql = "SELECT * FROM especialidad WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objEspecialidad = new Entity_Especialidad();
                objEspecialidad.setDescription(objResult.getString("description"));
                objEspecialidad.setName(objResult.getString("name"));
                objEspecialidad.setId(objResult.getInt("id"));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        ConfigDB.closeConnection();
        return objEspecialidad;
    }

}
