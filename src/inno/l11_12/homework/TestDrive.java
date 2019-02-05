package inno.l11_12.homework;


import inno.l11_12.homework.dao.PersonDAOImpl;
import inno.l11_12.homework.entities.Person;
import org.apache.log4j.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TestDrive {

    private static final Logger LOGGER =
            Logger.getLogger(TestDrive.class);

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String serverURL = "jdbc:postgresql://localhost:5432/postgres";
        String login = "postgres";
        String pass = "postgres";
        Connection conn = DriverManager.getConnection(serverURL,login,pass);
        LOGGER.info("Connected to pgsql on localhost");
        LOGGER.info(conn);

        PersonDAOImpl personDAO = new PersonDAOImpl(conn);
        Person person = new Person();
        person.setName("John Smith");
        person.setBirthDate(System.currentTimeMillis());
        personDAO.createPerson(person);
    }

}
