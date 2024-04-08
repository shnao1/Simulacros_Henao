package Entity;

import java.util.Date;

public class ReservacionEntity {

    int id;

    int idPasajero;

    int idVuelo;

    Date year;

    String asiento;

    public ReservacionEntity(int id, int idPasajero, int idVuelo, Date year, String asiento) {
        this.id = id;
        this.idPasajero = idPasajero;
        this.idVuelo = idVuelo;
        this.year = year;
        this.asiento = asiento;
    }

    public ReservacionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    @Override
    public String toString() {
        return "ReservacionEntity{" +
                "id=" + id +
                ", idPasajero=" + idPasajero +
                ", idVuelo=" + idVuelo +
                ", year=" + year +
                ", asiento='" + asiento + '\'' +
                '}';
    }
}
