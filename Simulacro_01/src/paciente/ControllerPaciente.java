package paciente;


import javax.swing.*;
import java.util.List;

public class ControllerPaciente {
    public static void getAll() {
        ModelPaciente objModel = new ModelPaciente();
        List<Object> listaPacientes = objModel.finAll();

        if (listaPacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pacientes para listar", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String listPacientesText = "Lista de Pacientes \n";
            for (Object iterador : listaPacientes) {
                Entity_paciente objPacient = (Entity_paciente) iterador;
                listPacientesText += objPacient.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, listPacientesText);
        }
    }

    public static String getAllString() {
        ModelPaciente objModel = new ModelPaciente();
        List<Object> listaPacientes = objModel.finAll();

        if (listaPacientes.isEmpty()) {
            return "No hay pacientes para listar";
        } else {
            String listPacientesText = "Lista de Pacientes \n";
            for (Object iterador : listaPacientes) {
                Entity_paciente objPacient = (Entity_paciente) iterador;
                listPacientesText += objPacient.toString() + "\n";
            }
            return listPacientesText;
        }
    }

    public static void create(){
        ModelPaciente objModel = new ModelPaciente();

        String nombre = JOptionPane.showInputDialog("Inserte el nombre del paciente");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del paciente");
        String fechaNacimiento = JOptionPane.showInputDialog("Ingresa la fecha de nacimiento del paciente YYYY-MM-DD");
        String documentoIdentidad = JOptionPane.showInputDialog("Ingrese el documento de identidad del paciente.\nDebe tener exactamente 10 dígitos.");

        if (documentoIdentidad.length() != 10) {
            JOptionPane.showMessageDialog(null, "El documento de identidad debe tener exactamente 10 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Entity_paciente pacienteExistente = objModel.findByDocument(Integer.parseInt(documentoIdentidad));
            if (pacienteExistente != null) {
                JOptionPane.showMessageDialog(null, "El documento ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Entity_paciente objPacient = new Entity_paciente();
            objPacient.setName(nombre);
            objPacient.setLastName(apellidos);
            objPacient.setDateOfBirth(fechaNacimiento);
            objPacient.setDni(documentoIdentidad);

            objModel.insert(objPacient);

            JOptionPane.showMessageDialog(null, "Paciente agregado correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El documento de identidad debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void delete(){
        ModelPaciente objModel = new ModelPaciente();
        String listPacientes = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPacientes + "\n Ingresa el id de el paciente a eliminar"));
        Entity_paciente objPacient = objModel.findById(idDelete);
        int confirm = 1;

        if (objPacient == null){
            JOptionPane.showMessageDialog(null, "Paciente no encontrado", "Error", JOptionPane.ERROR_MESSAGE );
        }else{
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de borrar este paciente?. Con el se borraran todas sus citas. \n"
                    + objPacient.toString());

            if (confirm == 0){
                objModel.delete(objPacient);
            }
        }
    }

    public static void update(){

        ModelPaciente objModel = new ModelPaciente();
        try {

            String listPacientes = getAllString();

            int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPacientes + "\nIngresa el id del paciente a actualizar"));
            Entity_paciente objPacient = objModel.findById(idUpdate);

            if (objPacient == null){
                JOptionPane.showMessageDialog(null, "Paciente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String nombre = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del paciente:", objPacient.getName());
                String apellidos = JOptionPane.showInputDialog(null, "Ingrese los nuevos apellidos del paciente:", objPacient.getLastName());
                String fechaNacimiento = JOptionPane.showInputDialog(null, "Ingrese la nueva fecha de nacimiento del paciente (YYYY-MM-DD):", objPacient.getDateOfBirth());
                String documentoIdentidad = JOptionPane.showInputDialog(null, "Ingrese el nuevo documento de identidad del paciente\nDebe tener exactamente 10 dígitos:", objPacient.getDni());

                if (documentoIdentidad.length() != 10) {
                    JOptionPane.showMessageDialog(null, "El documento de identidad debe tener exactamente 10 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Entity_paciente pacienteExistente = objModel.findByDocument(Integer.parseInt(documentoIdentidad));
                    if (pacienteExistente != null && pacienteExistente.getId() != objPacient.getId()) {
                        JOptionPane.showMessageDialog(null, "El documento ya existe en otro paciente.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    objPacient.setName(nombre);
                    objPacient.setLastName(apellidos);
                    objPacient.setDateOfBirth(fechaNacimiento);
                    objPacient.setDni(documentoIdentidad);

                    objModel.update(objPacient);

                    JOptionPane.showMessageDialog(null, "Paciente actualizado correctamente.");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El documento de identidad debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id de el paciente debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void findByDocument() {
        ModelPaciente objModel = new ModelPaciente();

        try {
            int documento = Integer.parseInt(JOptionPane.showInputDialog("Inserte el documento de identidad del paciente a buscar: "));

            Entity_paciente objPacient = objModel.findByDocument(documento);

            if (objPacient != null) {
                JOptionPane.showMessageDialog(null, "El paciente con este documento es: \n" + objPacient);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un paciente con el documento " + documento, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número de documento válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
