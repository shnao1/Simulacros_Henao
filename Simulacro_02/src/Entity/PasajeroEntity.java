package Entity;

public class PasajeroEntity {
    int id;

    String name;

    String lastName;

    String dni;

    public PasajeroEntity(int id, String name, String lastName, String dni) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
    }

    public PasajeroEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "pasajeroEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
