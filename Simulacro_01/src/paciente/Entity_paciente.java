package paciente;

import java.util.Date;

public class Entity_paciente {

    int id;

    String name;

    String lastName;

    String DateOfBirth;

    String dni;

    public Entity_paciente(int id, String name, String lastName, String dateOfBirth, String dni) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        DateOfBirth = dateOfBirth;
        this.dni = dni;
    }

    public Entity_paciente() {
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

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "entity_paciente{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", DateOfBirth='" + DateOfBirth + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
