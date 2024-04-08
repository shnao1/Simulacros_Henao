package especialidad;

import javax.swing.*;

public class ControllerEspecialidad {

    //HACER VALIDACIONES

    public static void getAll(){
        ModelEspecialidad objModel = new ModelEspecialidad();
        String listEspecialidad = "Lista de especialidades \n";

        for (Object iterador : objModel.getAll()){
            Entity_Especialidad objEspecialidad = (Entity_Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listEspecialidad);
    }

    public static String getAllString(){
        ModelEspecialidad objModel = new ModelEspecialidad();
        String listEspecialidad = "Lista de especialidades \n";

        for (Object iterador : objModel.getAll()){
            Entity_Especialidad objEspecialidad = (Entity_Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }
        return listEspecialidad;
    }

    public static void create(){
        ModelEspecialidad objModel = new ModelEspecialidad();

        String nombre = JOptionPane.showInputDialog("Inserte el nombre de la especialidad");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la especialidad");

        Entity_Especialidad objEspecialidad = new Entity_Especialidad();
        objEspecialidad.setName(nombre);
        objEspecialidad.setDescription(descripcion);

        objEspecialidad = (Entity_Especialidad) objModel.insert(objEspecialidad);

        JOptionPane.showMessageDialog(null, objEspecialidad.toString());
    }

    public static void delete(){
        ModelEspecialidad objModel = new ModelEspecialidad();
        String listEspecialidades = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidades + "\n Ingresa el id de la especialidad a eliminar"));
        Entity_Especialidad objEspecialidad = objModel.findById(idDelete);
        int confirm = 1;

        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null, "Especialidad no encontrada");
        }else{
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de borrar esta especialidad?. \n" +
                    "Ten en cuenta que se eliminaran todos los medicos que incluye esta especialidad y con el medico" +
                    " las citas de este. \n" + objEspecialidad.toString());

            if (confirm == 0){
                objModel.delete(objEspecialidad);
            }
        }
    }
    public static void update(){
        ModelEspecialidad objModel = new ModelEspecialidad();
        String listEspecialidad = getAllString();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidad + "\n Ingresa el id de la especialidad a actualizar"));

        Entity_Especialidad objEspecialidad = objModel.findById(idUpdate);

        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null, "Especialidad no encontrada");
        }else {
            String nombre = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre de la especialidad: ",
                    objEspecialidad.getName());
            String descripcion = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la especialidad: ",
                    objEspecialidad.getDescription());

            objEspecialidad.setName(nombre);
            objEspecialidad.setDescription(descripcion);

            objModel.update(objEspecialidad);
        }
    }public  static  void insert(){
        String nombre =JOptionPane.showInputDialog("Ingrese el nombre de la espcialidad");
        String description =JOptionPane.showInputDialog("Ingrese la discripcion de la especialidad");

        instanceModel().insert(new Entity_Especialidad(nombre,description));
    }

    public static ModelEspecialidad instanceModel(){
        return new ModelEspecialidad();
    }

}
