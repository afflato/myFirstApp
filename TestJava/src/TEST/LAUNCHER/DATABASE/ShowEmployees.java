package TEST.LAUNCHER.DATABASE;

import java.sql.*;
import java.util.*;

/** A JDBC example that connects to an embedded Derby 
 *  (aka Java DB) database, issues a simple SQL query for the
 *  employee table, and prints the results.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate/JPA, and Java programming</a>.
 */

public class ShowEmployees {
//  public static void main(String[] args) {
//    String url = "jdbc:derby:myDatabase";
//    Properties userInfo = new Properties();
//    // Any username/password allowed for embedded Derby.
//    userInfo.put("user", "someuser");
//    userInfo.put("password", "somepassword");
//    String driver = "org.apache.derby.jdbc.EmbeddedDriver";
//    showSalaries(url, userInfo, driver);
//  }

  /** Query the employee table and print the first names, 
   *  last names, and salaries.
   */
   
  public static void showSalaries(String url, 
                                  Properties userInfo,
                                  String driverClass) {
    try {
      // Load the driver. NOT NEEDED in Java 6!
      // Class.forName(driverClass); 
      
      // Establish network connection to database.
      Connection connection =
        DriverManager.getConnection(url, userInfo);
      System.out.println("Employees\n==========");
      // Create a statement for executing queries.
      Statement statement = connection.createStatement();
      String query =
        "SELECT * FROM employees ORDER BY salary";
      // Send query to database and store results.
      ResultSet resultSet = statement.executeQuery(query);
      // Print results.
      while(resultSet.next()) {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("firstname");
        String lastName = resultSet.getString("lastname");
        String position = resultSet.getString("position");
        int salary = resultSet.getInt("salary");
        System.out.printf
            ("%s %s (%s, id=%s) earns $%,d per year.%n",
             firstName, lastName, position, id, salary);
      }
      connection.close();
    } catch(Exception e) {
      System.err.println("Error with connection: " + e);
    }
  }
}   
