package inno.l11_12.homework.dao;


import inno.l11_12.homework.TestDrive;
import inno.l11_12.homework.entities.Person;
import inno.l11_12.homework.entities.Subject;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PersonDAOImpl implements PersonDAO {

    private static final Logger LOGGER =
            Logger.getLogger(PersonDAOImpl.class);

    public static final String INSERT_PERSON_SQL_TEMPLATE =
                            "insert into \"InnoEduSchema\".\"Person\" (name, birthdate) values (?, ?)";
    public static final String SELECT_ALL_FROM_PERSON =
                            "select * from \"InnoEduSchema\".\"Person\"";
    public static final String SELECT_NAMES_FROM_PERSON = "select name from \"InnoEduSchema\".\"Person\"";
    public static final String SELECT_BIRTHDATE_FROM_PERSON = "select birthdate from \"InnoEduSchema\".\"Person\"";
    public static final String SELECT_NAME_BY_ID = "select name from \"InnoEduSchema\".\"Person\" where id_person=?";
    public static final String SELECT_BIRTHDATE_BY_ID = "select birthdate from \"InnoEduSchema\".\"Person\" where id_person=?";
    public static final String UPDATE_PERSON_ON_ID = "update \"InnoEduSchema\".\"Person\" set name=?. bitrhdate=? where id_person=?";
    public static final String DELETE_PERSON_ON_ID = "delete from \"InnoEduSchema\".\"Person\" where id_person=?";
    public static final String GET_ALL_SUBJECTS_ON_USER = "select id_subject, description " +
            "from \"InnoEduSchema\".subject a, " +
            "\"InnoEduSchema\".schedule b " +
            "where b.id_p=? and id_s=id_subject";
    private final Connection connection;

    public PersonDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Person> getAllRecordsOnPerson() throws SQLException {
        Collection<Person> result = new ArrayList<>();
        LOGGER.info("Preparing to execute \"get all records\" query on \"Person\" table");
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_PERSON)) {
            ResultSet rs = statement.executeQuery();
            LOGGER.info("Query executed successfully");
            while (rs.next()==true) {
                int personID = rs.getInt("id_person");
                String name = rs.getString("name");
                long birthdate = rs.getTimestamp("birthdate").getTime();
                Person person = new Person();
                person.setId(personID);
                person.setName(name);
                person.setBirthdate(birthdate);
                result.add(person);
            }
        }
        LOGGER.info("Query results pushing to collection completed");
        return result;
    }

    @Override
    public Collection<String> getAllNames() throws SQLException {
        Collection<String> result = new ArrayList<>();
        LOGGER.info("Preparing to execute \"get all Names\" query on \"Person\" table");
        try (PreparedStatement statement = connection.prepareStatement(SELECT_NAMES_FROM_PERSON)) {
            ResultSet rs = statement.executeQuery();
            LOGGER.info("Query executed successfully");
            while(rs.next()==true) {
                String name = rs.getString("name");
                result.add(name);
            }
        }
        LOGGER.info("Query results pushing to collection completed");
        return result;
    }

    @Override
    public Collection<Long> getAllBirths() throws SQLException {
        Collection<Long> result = new ArrayList<>();
        LOGGER.info("Preparing to execute \"get all Names\" query on \"Person\" table");
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BIRTHDATE_FROM_PERSON)) {
            ResultSet rs = statement.executeQuery();
            LOGGER.info("Query executed successfully");
            while(rs.next()==true) {
                Long birthdate = rs.getTimestamp("birthdate").getTime();
                result.add(birthdate);
            }
        }
        LOGGER.info("Query results pushing to collection completed");
        return result;
    }

    @Override
    public String getNameByPerson(Person person) throws SQLException {
        LOGGER.info("Preparing to execute \"get Name of Person\" query on \"Person\" table");
        try(PreparedStatement statement = connection.prepareStatement(SELECT_NAME_BY_ID)) {
            statement.setInt(1, person.getId());
            ResultSet rs = statement.executeQuery();
            LOGGER.info("Query executed successfully");
            LOGGER.info("Query result stored into String");
            return rs.getString(0);
        }
    }

    @Override
    public Long getBirthByPerson(Person person) throws SQLException {
        LOGGER.info("Preparing to execute \"get Birth of Person\" query on \"Person\" table");
        try(PreparedStatement statement = connection.prepareStatement(SELECT_BIRTHDATE_BY_ID)) {
            statement.setInt(1, person.getId());
            ResultSet rs = statement.executeQuery();
            LOGGER.info("Query executed successfully");
            LOGGER.info("Query result stored into Long");
            return rs.getTimestamp(0).getTime();
        }
    }

    @Override
    public Collection<Subject> getPersonsSubjects(Person person) throws SQLException {
        Collection<Subject> result = new ArrayList<>();
        LOGGER.info("Preparing to execute \"get all Subjects of Person\" query on \"Schedule\" table");
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_SUBJECTS_ON_USER)) {
            statement.setInt(1,person.getId());
            ResultSet rs = statement.executeQuery();
            LOGGER.info("Query executed successfully");
            while (rs.next() == true) {
                int subj_id = rs.getInt("id_subject");
                String desc = rs.getString("description");
                Subject subject = new Subject();
                subject.setId(subj_id);
                subject.setDescription(desc);
                result.add(subject);
            }
        }
        LOGGER.info("Query results pushing to collection completed");
        return result;
    }

    @Override
    public void createPerson(Person person) throws SQLException {
        LOGGER.info("Creating Person:" + person.getName() + " " + person.getId());
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PERSON_SQL_TEMPLATE)) {
            statement.setString(1, person.getName());
            statement.setTimestamp(2, new Timestamp(person.getBirthdate()));
            statement.execute();
        }
        LOGGER.info("Person successfully created");
    }

    @Override
    public void updatePerson(Person person) throws SQLException {
        LOGGER.info("Updating Person:" + person.getName() + " " + person.getId());
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON_ON_ID)) {
            statement.setString(1, person.getName());
            statement.setTimestamp(2, new Timestamp(person.getBirthdate()));
            statement.setInt(3, person.getId());
            statement.execute();
        }
        LOGGER.info("Person successfully updated");
    }

    @Override
    public void deletePerson(Person person) throws SQLException {
        LOGGER.info("Deleting Person:" + person.getName() + " " + person.getId());
        try (PreparedStatement statement
                     = connection.prepareStatement(DELETE_PERSON_ON_ID)) {
            statement.setInt(1, person.getId());
            statement.execute();
        }
        LOGGER.info("Person successfully deleted");
    }
}
