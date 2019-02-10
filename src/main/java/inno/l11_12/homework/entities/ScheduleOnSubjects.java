package inno.l11_12.homework.entities;

import java.util.Collection;
import java.util.Objects;

public class ScheduleOnSubjects {

    private Collection<Person> persons;
    private Subject subject;

    public Collection<Person> getPersons() {
        return persons;
    }

    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "entities.ScheduleOnSubjects{" +
                "persons=" + persons +
                ", subject=" + subject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleOnSubjects that = (ScheduleOnSubjects) o;
        return Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}
