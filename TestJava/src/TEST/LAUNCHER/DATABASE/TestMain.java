package TEST.LAUNCHER.DATABASE;
import java.sql.*;
import java.util.*;

public class TestMain{
//	public static void main(String[] args){
//		String url="jdbc:derby:myDatabase";
//		Properties userInfo= new Properties();
//		userInfo.put("user", "someUser");
//		userInfo.put("password", "somePassword");
//		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
//		showSalaries(url,userInfo,driver);
//		//createDatabase();
//	}

	private static void showSalaries(String url, Properties userInfo,
			String driverClass) {
		try{
			//Load the driver. Not needed in Java6
			//Class.forName(driverClass);
			//Establish network connection to database
			Connection connection= DriverManager.getConnection(url,userInfo);
			System.out.println("Employees\n==================");
			//Create statement for executing queries
			Statement statement = connection.createStatement();
			String query="SELECT * FROM employees ORDER BY salary";
			//Send query to database and store results
			ResultSet resultSet= statement.executeQuery(query);
			while (resultSet.next()) {
				int id= resultSet.getInt("id");
				String firstName=resultSet.getString("firstname");
				String lastName=resultSet.getString("lastname");
				String position=resultSet.getString("position");
				int salary= resultSet.getInt("salary");
				System.out.printf("%s %s (%s, id=%d) earns $ %d, per year. %n",
						firstName, lastName, position,id,salary);
			}
				
		}
		catch (Exception e) {
			System.err.println("Error with connection "+e);
		}
	}


	public static void createDatabase() {
		Employee[] employees = {
			new Employee(1, "Larry", "Ellison", "CEO",1234567890),
			new Employee(2, "Charles", "Phillips", "President",23456789),
			new Employee(3, "Safra", "Catz", "President",32654987)
		};
		try {
			String dbUrl = "jdbc:derby:" + "myDatabase" + ";create=true";
			Properties userInfo= new Properties();
			userInfo.put("user", "someUser");
			userInfo.put("password", "somePassword");
			Connection connection =
			DriverManager.getConnection(dbUrl, userInfo);
			Statement statement = connection.createStatement();
			String format = "VARCHAR(20)";
			String tableDescription =String.format
						("CREATE TABLE %s" +
						"(id INT, firstname %s, lastname %s, " +
						"position %s, salary INT)","employees", format, format, format);
			statement.execute(tableDescription);

			String template =
			String.format("INSERT INTO %s VALUES(?, ?, ?, ?, ?)",
			"employees");
			PreparedStatement inserter =connection.prepareStatement(template);
			for(Employee e: employees) {
				inserter.setInt(1, e.getEmployeeID());
				inserter.setString(2, e.getFirstName());
				inserter.setString(3, e.getLastName());
				inserter.setString(4, e.getPosition());
				inserter.setInt(5, e.getSalary());
				inserter.executeUpdate();
				System.out.printf("Inserted %s %s.%n",	e.getFirstName(),e.getLastName());
			}
			inserter.close();
			connection.close();		
		}catch(Exception e){}		
	}
	
	static class Employee{
		private int employeeID;
		private String firstName;
		private String lastName;
		private String position;
		private int salary;
		
		public Employee(int i, String f, String l, String p, int s){
			setEmployeeID(i);
			setFirstName(f);
			setLastName(l);
			setPosition(p);
			setSalary(s);
		}

		public int getSalary() {
			return salary;
		}

		public void setSalary(int salary) {
			this.salary = salary;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public int getEmployeeID() {
			return employeeID;
		}

		public void setEmployeeID(int id) {
			this.employeeID = id;
		}
	}
}