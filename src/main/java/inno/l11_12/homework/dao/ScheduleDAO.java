package inno.l11_12.homework.dao;

import inno.l11_12.homework.entities.Person;
import inno.l11_12.homework.entities.Subject;

import java.util.Collection;

public interface ScheduleDAO {
    Collection<Subject> getAllSubjects();

    Collection<Person> getAllPersons();

    Collection<Person> getPersonsBySubject(Subject subject);

    Collection<Subject> getSubjectsByPerson(Person person);

    void linkToCourse(Person person, Subject subject);

    void linkToCourse(Subject subject, Person person);

}
