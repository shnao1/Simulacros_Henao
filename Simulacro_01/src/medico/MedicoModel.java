package medico;
import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;
import entity.Medico;
import especialidad.Entity_Especialidad;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD{

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConection();
        Medico objMedico = (Medico) obj;

        try {
            String sql = "INSERT INTO Medico (name, lastname, id) VALUES( ?, ?, ?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objMedico.getNombreMedico());
            objPrepare.setString(2, objMedico.getApellidosMedico());
            objPrepare.setInt(3, objMedico.getIdEspecialidadMedico());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objMedico.setIdMedico(objResult.getInt(1));
                JOptionPane.showMessageDialog(null, "Medico insertado correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return findMedicoById(objMedico.getIdMedico());
    }

    @Override
    public List<Object> findAll() {

        List<Object> listMedicos = new ArrayList<>();
        Connection objConnection = ConfigDB.openConection();

        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON especialidad.id = medico.id ORDER BY medico.id ASC;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Medico objMedico = new Medico();
                Entity_Especialidad objEspecialidad = new Entity_Especialidad();

                objMedico.setIdMedico(objResult.getInt("medico.id"));
                objMedico.setNombreMedico(objResult.getString("medico.name"));
                objMedico.setApellidosMedico(objResult.getString("medico.lastName"));
                objMedico.setIdEspecialidadMedico(objResult.getInt("medico.id"));

                objEspecialidad.setName(objResult.getString("especialidad.name"));
                objEspecialidad.setDescription(objResult.getString("especialidad.description"));
                objEspecialidad.setId(objResult.getInt("especialidad.id"));

                objMedico.setObjEspecialidad(objEspecialidad);

                listMedicos.add(objMedico);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listMedicos;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConection();
        Medico objMedico = (Medico) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE medico SET name = ?, lastName = ?, id = ? WHERE idMedico = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objMedico.getNombreMedico());
            objPrepare.setString(2, objMedico.getApellidosMedico());
            objPrepare.setInt(3, objMedico.getIdEspecialidadMedico());
            objPrepare.setInt(4, objMedico.getIdMedico());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "El medico se actualizo correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdate;
    }


    @Override
    public boolean delete(Object obj) {

        Medico objMedico = (Medico) obj;
        Connection objConnection = ConfigDB.openConection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM medico WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objMedico.getIdMedico());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "El medico se elimino correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return isDelete;
    }

    public Medico findMedicoById(int id_medico){
        Connection objConnection = ConfigDB.openConection();
        Medico objMedico = null;

        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON especialidad.id_especialidad = medico.id_especialidad WHERE medico.id_medico = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id_medico);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objMedico = new Medico();
                Entity_Especialidad objEspecialidad = new Entity_Especialidad();
                objMedico.setIdMedico(objResult.getInt("id"));
                objMedico.setNombreMedico(objResult.getString("name"));
                objMedico.setApellidosMedico(objResult.getString("lastName"));
                objMedico.setIdEspecialidadMedico(objResult.getInt("idEspecialidad"));

                objEspecialidad.setName(objResult.getString("especialidad.name"));
                objEspecialidad.setDescription(objResult.getString("especialidad.description"));
                objEspecialidad.setId(objResult.getInt("especialidad.id"));

                objMedico.setObjEspecialidad(objEspecialidad);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return objMedico;
    }

    public List<Object> findMedicosByEspecialidad(int especialidad_id) {

        List<Object> listMedicos = new ArrayList<>();
        Connection objConnection = ConfigDB.openConection();

        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON especialidad.id_especialidad = ? WHERE medico.id_especialidad = especialidad.id_especialidad;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, especialidad_id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Medico objMedico = new Medico();
                Entity_Especialidad objEspecialidad = new Entity_Especialidad();

                objMedico.setIdMedico(objResult.getInt("medico.id"));
                objMedico.setNombreMedico(objResult.getString("medico.name"));
                objMedico.setApellidosMedico(objResult.getString("medico.lastName"));
                objMedico.setIdEspecialidadMedico(objResult.getInt("medico.idEspecialidad"));

                objEspecialidad.setName(objResult.getString("especialidad.name"));
                objEspecialidad.setDescription(objResult.getString("especialidad.description"));
                objEspecialidad.setId(objResult.getInt("especialidad.idEspecialidad"));

                objMedico.setObjEspecialidad(objEspecialidad);

                listMedicos.add(objMedico);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listMedicos;
    }

}
