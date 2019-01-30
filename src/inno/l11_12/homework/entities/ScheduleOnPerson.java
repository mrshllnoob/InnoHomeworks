package inno.l11_12.homework.entities;

import java.util.Collection;
import java.util.Objects;

public class ScheduleOnPerson {

    private Person person;
    private Collection<Subject> subjects;

    @Override
    public String toString() {
        return "entities.ScheduleOnPerson{" +
                "person=" + person +
                ", subjects=" + subjects +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleOnPerson that = (ScheduleOnPerson) o;
        return Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Collection<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<Subject> subjects) {
        this.subjects = subjects;
    }
}
