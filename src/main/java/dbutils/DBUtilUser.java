package dbutils;

/**
 * Created by Мадина on 22.11.2014.
 */

import models.User;
import java.sql.*;
import java.util.ArrayList;

public class DBUtilUser {
    public static String url = "jdbc:derby://localhost:1527/MyDbTest";
    public static String username = "app";
    public static String password = "app";

    public ArrayList<User> findAll() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            ArrayList<User> array = new ArrayList<User>();
            if (conn != null) { // Connected
                String sql = "SELECT * FROM users";

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    array.add(new User(result.getString("email")));
                }
                return array;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

