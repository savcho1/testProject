package db;

import bd.DatabaseConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class TestDB{
   String sqlQuery = "SELECT * FROM test";

   @Test
   public void getUser1Name() throws SQLException {
      Map<Integer, String> users = new HashMap<>();
      try (Connection connection =  DatabaseConnection.connect()){
         System.out.println("Connected to the PostgreSQL database.");
         try (Statement statement = connection.createStatement();
              ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
               users.put(resultSet.getInt("id"), resultSet.getString("name"));
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               System.out.println("ID: " + id + ", Name: " + name);
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

      Assert.assertEquals(users.get(1), "Test1");
      Assert.assertEquals(users.get(2),"Test2");
   }

}
