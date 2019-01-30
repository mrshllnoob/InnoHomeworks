package inno.l11_12.homework.entities;

import java.util.Objects;

public class Subject {

    private int id;
    private String description;

    @Override
    public String toString() {
        return "entities.Subject{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id &&
                Objects.equals(description, subject.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
