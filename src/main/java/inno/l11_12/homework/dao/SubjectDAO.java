package inno.l11_12.homework.dao;


import inno.l11_12.homework.entities.Person;
import inno.l11_12.homework.entities.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface SubjectDAO {

    Collection<Subject> getAllRecordsOnSubjects() throws SQLException;

    Collection<String> getAllDescriptions() throws SQLException;

    String getDescription(Subject subject) throws SQLException;

    Collection<Person> getPupilsOnSubject(Subject subject) throws SQLException;

    void createSubject(Subject subject) throws SQLException;

    void updateSubject(Subject subject) throws SQLException;

    void deleteSubject(Subject subject) throws SQLException;

}
