package especialidad;

public class Entity_Especialidad {

    int id;

    String name;

    String description;

    public Entity_Especialidad(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Entity_Especialidad() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Entity_Especialidad{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
