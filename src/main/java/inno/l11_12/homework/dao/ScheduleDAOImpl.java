package inno.l11_12.homework.dao;


import inno.l11_12.homework.entities.Person;
import inno.l11_12.homework.entities.Subject;

import java.sql.Connection;
import java.util.Collection;

public class ScheduleDAOImpl implements ScheduleDAO{

    public static final String INSERT_PERSON_SQL_TEMPLATE =
            "insert into person (name, birth_date) values (?, ?)";
    private final Connection connection;

    public ScheduleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Subject> getAllSubjects() {
        return null;
    }

    @Override
    public Collection<Person> getAllPersons() {
        return null;
    }

    @Override
    public Collection<Person> getPersonsBySubject(Subject subject) {
        return null;
    }

    @Override
    public Collection<Subject> getSubjectsByPerson(Person person) {
        return null;
    }

    @Override
    public void linkToCourse(Person person, Subject subject) {

    }

    @Override
    public void linkToCourse(Subject subject, Person person) {

    }

}
