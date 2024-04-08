package Entity;

public class AvionEntity    {

    int id;

    String model;

    int capacity;

    public AvionEntity(int id, String model, int capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
    }

    public AvionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "AvionEntity{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
