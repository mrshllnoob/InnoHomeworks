package inno.l11_12.homework;


import inno.l11_12.homework.dao.PersonDAOImpl;
import inno.l11_12.homework.entities.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDrive {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String serverURL = "jdbc:postgresql://localhost:5432/postgres";
        String login = "postgres";
        String pass = "postgres";
        Connection conn = DriverManager.getConnection(serverURL,login,pass);
        System.out.println("Connected to pgsql on localhost");
        System.out.println(conn);

        PersonDAOImpl personDAO = new PersonDAOImpl(conn);
        Person person = new Person();
        person.setName("John Smith");
        person.setBirthDate(System.currentTimeMillis());
        personDAO.createPerson(person);
    }

}
