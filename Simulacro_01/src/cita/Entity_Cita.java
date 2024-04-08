package cita;

import java.sql.Time;

public class Entity_Cita {

    int id;

    String date;

    Time hour;

    String motivo;

    int idPaciente;

    int idMedico;

    public Entity_Cita(int id, String date, Time hour, String motivo, int idPaciente, int idMedico) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.motivo = motivo;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
    }

    public Entity_Cita() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    @Override
    public String toString() {
        return "Entity_Cita{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", hour=" + hour +
                ", motivo='" + motivo + '\'' +
                ", idPaciente=" + idPaciente +
                ", idMedico=" + idMedico +
                '}';
    }
}
