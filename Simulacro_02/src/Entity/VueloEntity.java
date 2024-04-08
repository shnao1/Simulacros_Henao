package Entity;

import java.sql.Time;
import java.util.Date;

public class VueloEntity {

    int id;

    String destiny;

    Date year;

    Time hour;

    int idAvion;

    public VueloEntity(int id, String destiny, Date year, Time hour, int idAvion) {
        this.id = id;
        this.destiny = destiny;
        this.year = year;
        this.hour = hour;
        this.idAvion = idAvion;
    }

    public VueloEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    @Override
    public String toString() {
        return "VueloEntity{" +
                "id=" + id +
                ", destiny='" + destiny + '\'' +
                ", year=" + year +
                ", hour=" + hour +
                ", idAvion=" + idAvion +
                '}';
    }
}
