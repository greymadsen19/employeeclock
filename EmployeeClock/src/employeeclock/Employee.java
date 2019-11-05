/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeclock;

/**
 *
 * @author Jacob Madsen
 */
public class Employee {
    private int employeeID;
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
    public Employee(int employeeID, String employeeName, double hourPay)
    {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.hourPay = hourPay;
    }
    
    public int getEmployeeID()
    {
        return employeeID;
    }
    
    public String getEmployeeName()
    {
        return employeeName;
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
        str += employeeID + " ";
        str += employeeName + " ";
        str += hourPay;
        return str;
    }
}
