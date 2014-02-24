package TEST.LAUNCHER.DATABASE;

/** Simple representation of an employee. Used to seed database
 *  initially. Immutable.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate/JPA, and Java programming</a>.
 */

public class Employee {
  private int employeeID, salary;
  private String firstName, lastName, position;

  public Employee(int employeeID, String firstName,
                  String lastName, String position,
                  int salary) {
    this.employeeID = employeeID;
    this.salary = salary;
    this.firstName = firstName;
    this.lastName = lastName;
    this.position = position;
  }
  
  public int getEmployeeID() {
    return(employeeID);
  }

  public String getFirstName() {
    return(firstName);
  }
  
  public String getLastName() {
    return(lastName);
  }
  
  public String getPosition() {
    return(position);
  }

  public int getSalary() {
    return(salary);
  }
}
