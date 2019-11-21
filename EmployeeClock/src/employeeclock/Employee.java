/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeclock;

/**
 * The employee class
 * contains the
 * information of an employee:
 * Their ID, their hourly pay, and
 * their name.
 * The class is capable of taking in
 * no arguments or taking in those
 * three parameters as arguments.
 * @author Jacob Madsen
 */
public class Employee {
    private String employeeID;
    private double hourPay;
    private String employeeName;
    
    /**
     * Default constructor for the Employee class
     * To be used for creating an empty
     * object to be initialized later.
     */
    public Employee()
    {
        super();
    }
    
    /**
     * Constructor that accepts values for
     * the Employee classes fields
     * in order to initialize additional
     * employee objects
     * @param employeeID The employees ID
     * @param employeeName The Employees name
     * @param hourPay The Employees hourly pay
     */
    public Employee(String employeeID, String employeeName, double hourPay)
    {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.hourPay = hourPay;
    }
    
    /**
     * Getter method to
     * return the employee
     * ID
     * @return The employee ID
     */
    public String getEmployeeID()
    {
        return employeeID;
    }
    
    /**
     * Getter method that
     * returns the name
     * of an employee
     * @return Name of employee
     */
    public String getEmployeeName()
    {
        return employeeName;
    }
    
    /**
     * This method takes in an
     * argument for an employee ID
     * to set the value in the
     * employeeID parameter.
     * @param employeeID The employees ID
     */
    public void setEmployeeID(String employeeID)
    {
        this.employeeID = employeeID;
    }
    
    /**
     * This is a toString method for the
     * Employee class that will return
     * the information of an employee object in the
     * form of a string.
     * @return The parameters of an employee object
     */
    @Override
    public String toString()
    {
        String str = "";
        str += employeeID + "    ";
        str += employeeName + "\t";
        str += hourPay + "     ";
        return str;
    }
}
