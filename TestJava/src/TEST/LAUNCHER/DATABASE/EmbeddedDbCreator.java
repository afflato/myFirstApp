package TEST.LAUNCHER.DATABASE;

import java.sql.*;
import java.util.*;

/** Creates "myDatabase" DB and "employees" table.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate/JPA, and Java programming</a>.
 */

public class EmbeddedDbCreator {
  // Driver class not needed in JDBC 4.0 (Java SE 6)
  private String driver = "oracle.jdbc.driver.OracleDriver";
  private String protocol = "jdbc:oracle:thin:@localhost:1521/";
  private String username = "hiberdba";
  private String password = "Admin123";
  private String dbName = "hibernatedb";
  private String tableName = "employees";
  private Properties userInfo;
  
  public EmbeddedDbCreator() {
    userInfo = new Properties();
    userInfo.put("user", username);
    userInfo.put("password", password);
  }
  
  public void createDatabase() {
    Employee[] employees = {
      new Employee(1, "Larry", "Ellison", "CEO", 1234567890),
      new Employee(2, "Charles", "Phillips", "President", 23456789),
      new Employee(3, "Safra", "Catz", "President", 32654987),
      new Employee(4, "Keith", "Block", "VP", 1234567),
      new Employee(5, "Thomas", "Kurian", "VP", 2431765),
      new Employee(6, "Joe", "Hacker", "Peon", 23456),
      new Employee(7, "Jane", "Hacker", "Peon", 32654),
      new Employee(8, "David", "Developer", "Peon", 21555),
      new Employee(9, "Danielle", "Developer", "Peon", 21333),
      new Employee(10, "Cody", "Coder", "Peon", 19842),
      new Employee(11, "Cathy", "Coder", "Peon", 18944),
      new Employee(12, "Gary", "Grunt", "Gofer", 7777),
      new Employee(13, "Gabby", "Grunt", "Gofer", 8888)
    };
    try {
    	Class.forName(driver);
      String dbUrl = protocol + dbName ;//+ ";create=true";
      Connection connection = 
        DriverManager.getConnection(dbUrl, userInfo);
      Statement statement = connection.createStatement();
      String format = "VARCHAR(20)";
      String tableDescription = 
        String.format
          ("CREATE TABLE %s" +
              "(id INT, firstname %s, lastname %s, " +
        		    "position %s, salary INT)",
        	tableName, format, format, format);
      //tableDescription=String.format("DROP TABLE %s",tableName);
      statement.execute(tableDescription);
      String template =
        String.format("INSERT INTO %s VALUES(?, ?, ?, ?, ?)",
                      tableName);
      PreparedStatement inserter = 
        connection.prepareStatement(template);
      for(Employee e: employees) {
        inserter.setInt(1, e.getEmployeeID());
        inserter.setString(2, e.getFirstName());
        inserter.setString(3, e.getLastName());
        inserter.setString(4, e.getPosition());
        inserter.setInt(5, e.getSalary());
        inserter.executeUpdate();
        System.out.printf("Inserted %s %s.%n", 
                          e.getFirstName(), 
                          e.getLastName());
      }
      inserter.close();
      connection.close();
    } catch (SQLException sqle) {
      // If table already exists, then skip everything else
    } catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
  }
  
  public void showTable() {  
    try {
      String dbUrl = protocol + dbName;
      Connection connection;
      connection = DriverManager.getConnection(dbUrl, userInfo);
      Statement statement = connection.createStatement();
      String query =
        String.format("SELECT * FROM %s", tableName);
      ResultSet resultSet = statement.executeQuery(query);
      while(resultSet.next()) {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("firstname");
        String lastName = resultSet.getString("lastname");
        String position = resultSet.getString("position");
        int salary = resultSet.getInt("salary");
        System.out.printf("%s %s (%s, id=%s) earns $%,d per year.%n",
                          firstName, lastName, position, id, salary);
      }
      connection.close();
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }  
  }
  
  public static void main(String[] args) {
    EmbeddedDbCreator tester = new EmbeddedDbCreator();
    tester.createDatabase();
    tester.showTable();
  }
}
