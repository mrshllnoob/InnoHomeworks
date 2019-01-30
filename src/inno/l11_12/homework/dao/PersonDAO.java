package inno.l11_12.homework.dao;


import inno.l11_12.homework.entities.Person;
import inno.l11_12.homework.entities.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface PersonDAO {

    Collection<Person> getAllRecordsOnPerson() throws SQLException;

    Collection<String> getAllNames() throws SQLException;

    Collection<Long> getAllBirths() throws SQLException;

    String getNameByPerson(Person person) throws SQLException;

    Long getBirthByPerson(Person person) throws SQLException;

    Collection<Subject> getPersonsSubjects(Person person) throws SQLException;

    void createPerson(Person person) throws SQLException;

    void updatePerson(Person person) throws SQLException;

    void deletePerson(Person person) throws SQLException;

}
