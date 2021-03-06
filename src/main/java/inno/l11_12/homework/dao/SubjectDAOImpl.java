package inno.l11_12.homework.dao;


import inno.l11_12.homework.entities.Person;
import inno.l11_12.homework.entities.Subject;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class SubjectDAOImpl implements SubjectDAO {

    private static final Logger LOGGER =
            Logger.getLogger(PersonDAOImpl.class);

    public static final String SELECT_ALL_FROM_SUBJECTS = "select * from \"InnoEduSchema\".subject";
    public static final String SELECT_DESCRIPTIONS_FROM_SUBJECTS = "select description from \"InnoEduSchema\".\"subject\"";
    public static final String SELECT_DESCRIPTION_ON_ID = "select description from \"InnoEduSchema\".subject where id_subject=?";
    public static final String SELECT_PERSONS_ON_SUBJECT_ID = "select id_person, name, birthdate from \"InnoEduSchema\".\"Person\" a, \"InnoEduSchema\".schedule b where b.id_p=a.id_person and b.id_s=?";
    public static final String INSERT_SUBJECT = "insert into \"InnoEduSchema\".subject (description) values (?)";
    public static final String UPDATE_SUBJECT_ON_ID = "update \"InnoEduSchema\".subject set description=? where id_person=?";
    public static final String DELETE_SUBJECT_ON_ID = "delete from \"InnoEduSchema\".subject where id_subject=?";
    private final Connection connection;

    public SubjectDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Subject> getAllRecordsOnSubjects() throws SQLException {
        Collection<Subject> result = new ArrayList<>();
        LOGGER.info("Preparing to execute \"get all records\" query on \"Subject\" table");
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_SUBJECTS)) {
            ResultSet rs = statement.executeQuery();
            LOGGER.info("Query executed successfully");
            while (rs.next() == true) {
                int id_subj = rs.getInt("id_subject");
                String description = rs.getString("description");
                Subject subj = new Subject();
                subj.setId(id_subj);
                subj.setDescription(description);
                result.add(subj);
            }
        }
        LOGGER.info("Query results pushing to collection completed");
        return result;
    }

    @Override
    public Collection<String> getAllDescriptions() throws SQLException {
        Collection<String> result = new ArrayList<>();
        LOGGER.info("Preparing to execute \"get all descriptions\" query on \"Subject\" table");
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DESCRIPTIONS_FROM_SUBJECTS)) {
            ResultSet rs = statement.executeQuery();
            LOGGER.info("Query executed successfully");
            while (rs.next() == true) {
                result.add(rs.getString("description"));
            }
        }
        LOGGER.info("Query results pushing to collection completed");
        return result;
    }

    @Override
    public String getDescription(Subject subject) throws SQLException {
        LOGGER.info("Preparing to execute \"get Description on Subject\" query on \"Subject\" table");
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DESCRIPTION_ON_ID)) {
            statement.setInt(1, subject.getId());
            ResultSet rs = statement.executeQuery();
            LOGGER.info("Query executed successfully");
            LOGGER.info("Query result stored into String");
            return rs.getString("description");
        }
    }

    @Override
    public Collection<Person> getPupilsOnSubject(Subject subject) throws SQLException {
        Collection<Person> result = new ArrayList<>();
        LOGGER.info("Preparing to execute \"get Persons on Subject\" query on \"Schedule\" table");
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSONS_ON_SUBJECT_ID)) {
            statement.setInt(1, subject.getId());
            ResultSet rs = statement.executeQuery();
            LOGGER.info("Query executed successfully");
            while (rs.next() == true) {
                int id = rs.getInt("id_person");
                String name = rs.getString("name");
                long birthdate = rs.getTimestamp("birthdate").getTime();
                Person person = new Person();
                result.add(person);
            }
        }
        LOGGER.info("Query results pushing to collection completed");
        return result;
    }

    @Override
    public void createSubject(Subject subject) throws SQLException {
        LOGGER.info("Creating Person:" + subject.getDescription() + " " + subject.getId());
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT)) {
            statement.setString(1, subject.getDescription());
            statement.execute();
        }
        LOGGER.info("Subject successfully created");
    }

    @Override
    public void updateSubject(Subject subject) throws SQLException {
        LOGGER.info("Updating Person:" + subject.getDescription() + " " + subject.getId());
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT_ON_ID)) {
            statement.setString(1, subject.getDescription());
            statement.setInt(2, subject.getId());
            statement.execute();
        }
        LOGGER.info("Person successfully updated");
    }

    @Override
    public void deleteSubject(Subject subject) throws SQLException {
        LOGGER.info("Deleting Person:" + subject.getDescription() + " " + subject.getId());
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_ON_ID)) {
            statement.setInt(1, subject.getId());
            statement.execute();
        }
        LOGGER.info("Person successfully deleted");
    }
}
