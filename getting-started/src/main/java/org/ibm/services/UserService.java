package org.ibm.services;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@ApplicationScoped
public class UserService {

    @Inject
    AgroalDataSource defaultDataSource;

    @Inject
    @DataSource("users")
    AgroalDataSource usersDataSource;

    @PostConstruct
    public void init() {
        System.out.println("H2- db init is called");
        try {
            Connection connection = defaultDataSource.getConnection();
            System.out.println(connection);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE TABLE1(id INT NOT NULL,  title VARCHAR(50))");
            stmt.executeUpdate("INSERT INTO TABLE1 " + "VALUES (100, 'Subramanian')");
            ResultSet rs = stmt.executeQuery("select * from table1");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2));
            }
            stmt.close();
            connection.close();

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
    public String getUser(){
        try {
            Connection connection = usersDataSource.getConnection();
            System.out.println(connection);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE user(id INT NOT NULL,  title VARCHAR(50))");
            stmt.executeUpdate("INSERT INTO user " + "VALUES (100, 'Subramanian')");
            ResultSet rs = stmt.executeQuery("select * from user");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2));
            }
            stmt.close();
            connection.close();

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return  "User";
    }
}
