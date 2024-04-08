package medico;



import especialidad.ControllerEspecialidad;
import especialidad.Entity_Especialidad;
import especialidad.ModelEspecialidad;

import javax.swing.*;
import java.util.List;

public class MedicoController {

    public static void getAll(){
        MedicoModel objMedicoModel = new MedicoModel();
        List<Object> listMedicos = objMedicoModel.findAll();

        if (listMedicos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pacientes para listar", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String listMedicosText = "Lista de medicos \n";
            for (Object iterador : listMedicos) {
                Medico objMedico = (Medico) iterador;
                listMedicosText += objMedico.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, listMedicosText);
        }
    }

    public static String getAllString(){
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedicos = "Lista de medicos \n";

        for (Object iterador : objMedicoModel.findAll()){
            Medico objMedico = (Medico) iterador;
            listMedicos += objMedico.toString() + "\n";
        }
        return listMedicos;
    }

    public static void create(){

        try {
            MedicoModel objMedicoModel = new MedicoModel();
            String nombre = JOptionPane.showInputDialog("Inserte el nombre del medico");
            String apellidos = JOptionPane.showInputDialog("Inserte el/los apellidos de los medicos");
            int especialidad_id = Integer.parseInt(JOptionPane.showInputDialog(ControllerEspecialidad.getAllString() + "\n Ingrese el id de la especialidad del medico"));

            ModelEspecialidad objEspecialidadModel = new ModelEspecialidad();
            Entity_Especialidad objEspecialidad = objEspecialidadModel.findById(especialidad_id);

            if (objEspecialidad == null){
                JOptionPane.showMessageDialog(null, "Especialidad no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                Medico objMedico = new Medico();
                objMedico.setNombreMedico(nombre);
                objMedico.setApellidosMedico(apellidos);
                objMedico.setIdEspecialidadMedico(especialidad_id);

                objMedico = (Medico) objMedicoModel.insert(objMedico);

                JOptionPane.showMessageDialog(null, objMedico.toString());
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id de la especialidad debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void delete(){
            MedicoModel objMedicoModel = new MedicoModel();
        try {
            String listMedicos = getAllString();

            int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listMedicos + "\n Ingresa el id de el medico a eliminar"));
            Medico objMedico = objMedicoModel.findMedicoById(idDelete);
            int confirm = 1;

            if (objMedico == null){
                JOptionPane.showMessageDialog(null, "Medico no encontrado", "Error", JOptionPane.ERROR_MESSAGE);

            }else{
                confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de borrar este medico? \n" +
                        "Ten en cuenta que con el se borraran las citas agendadas a este. \n" + objMedico.toString());

                if (confirm == 0){
                    objMedicoModel.delete(objMedico);
                }
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id de el medico debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void update(){
        try {
            MedicoModel objMedicoModel = new MedicoModel();
            String listMedicos = getAllString();
            int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listMedicos + "\n Ingresa el id de el medico a actualizar"));

            Medico objMedico = objMedicoModel.findMedicoById(idUpdate);

            if (objMedico == null){
                JOptionPane.showMessageDialog(null, "Medico no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }else {
                String nombre = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre de el medico: ",
                        objMedico.getNombreMedico());
                String apellidos = JOptionPane.showInputDialog(null, "Ingrese el/los nuevo(s) apellido(s) del medico: ",
                        objMedico.getApellidosMedico());
                int especialidad_id = Integer.parseInt(JOptionPane.showInputDialog(ControllerEspecialidad.getAllString() + "\n Ingrese el id de la nueva especialidad del medico: ",
                        objMedico.getIdEspecialidadMedico()));

                ModelEspecialidad objEspecialidadModel = new ModelEspecialidad();
                Entity_Especialidad objEspecialidad = objEspecialidadModel.findById(especialidad_id);

                 if (objEspecialidad == null) {
                    JOptionPane.showMessageDialog(null, "Especialidad no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                objMedico.setNombreMedico(nombre);
                objMedico.setApellidosMedico(apellidos);
                objMedico.setIdEspecialidadMedico(especialidad_id);

                objMedicoModel.update(objMedico);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id de el medico y de la especialidad debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void FindByEspecialidad(){
        MedicoModel objMedicoModel = new MedicoModel();
        ModelEspecialidad objEspecialidadModel = new ModelEspecialidad();
        try {
        String listMedicos = "Lista de medicos \n";
        int especialidad_id = Integer.parseInt(JOptionPane.showInputDialog(ControllerEspecialidad.getAllString() + "\n Ingrese el id de la especialidad por la que buscara los medicos: "));

        Entity_Especialidad objEspecialidad = objEspecialidadModel.findById(especialidad_id);

        if (objEspecialidad != null){
            for (Object iterador : objMedicoModel.findMedicosByEspecialidad(especialidad_id)){
                Medico objMedico = (Medico) iterador;
                listMedicos += objMedico.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, "Los medicos con esta especialidad son: \n" + listMedicos + "\n");
        }else {
            JOptionPane.showMessageDialog(null, "Especialidad no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }


        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id de la especialidad debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
