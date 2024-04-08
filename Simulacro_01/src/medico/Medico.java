package medico;

import especialidad.Entity_Especialidad;

public class Medico {
    private int idMedico;
    private String nombreMedico;
    private String apellidosMedico;
    private int idEspecialidadMedico;

    private Entity_Especialidad objEspecialidad;

    public Medico() {
    }

    public Medico(int idMedico, String nombreMedico, String apellidosMedico, int idEspecialidadMedico) {
        this.idMedico = idMedico;
        this.nombreMedico = nombreMedico;
        this.apellidosMedico = apellidosMedico;
        this.idEspecialidadMedico = idEspecialidadMedico;
    }



    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidosMedico() {
        return apellidosMedico;
    }

    public void setApellidosMedico(String apellidosMedico) {
        this.apellidosMedico = apellidosMedico;
    }

    public int getIdEspecialidadMedico() {
        return idEspecialidadMedico;
    }

    public void setIdEspecialidadMedico(int idEspecialidadMedico) {
        this.idEspecialidadMedico = idEspecialidadMedico;
    }

    public Entity_Especialidad getObjEspecialidad() {
        return null;
    }

    public void setObjEspecialidad(Entity_Especialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }

    @Override
    public String toString() {
        return "Id medico: " + idMedico +
                ", Nombre medico: '" + nombreMedico + '\'' +
                ", Apellidos medico: '" + apellidosMedico + '\'' +
                ", Especialidad del medico: " + objEspecialidad;
    }
}
