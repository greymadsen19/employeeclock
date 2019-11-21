/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeclock;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

/**
 * The clock class
 * contains the information
 * for a clock time
 * clocked by an employee.
 * This being the day of week,
 * type of clock time(IN or OUT),
 * and the LocalDateTime containing
 * the date and time they have being
 * recorded of clocking.
 * The class accepts an employee and a LocalDateTime
 * as its parameters and also is able to accept the
 * day of the week.
 * @author Jacob Madsen
 */
public class Clock {
    
    private Employee employee;
    private LocalDateTime time;
    private String dayOfWeek;
    private ClockType clockType;

    /**
     * Constructor for the
     * clock class to 
     * use the default with no parameters
     */
    public Clock()
    {
        super();
    }
    
    /**
     * Constructor that builds a
     * clock time for an
     * employee
     * @param employee The employee
     * @param time The time clockedin\out
     */
    public Clock(Employee employee, LocalDateTime time)
    {
        this.employee = employee;
        this.time = time;
        this.dayOfWeek = Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
    }
    
    /**
     * This constructor builds the
     * clock time with the employee and time
     * as well as the day of the week.
     * @param emp The employee
     * @param time The time clocked in\out
     * @param dayOfWeek The day of the week when clocking in\out
     */
    public Clock(Employee emp, LocalDateTime time, String dayOfWeek)
    {
        this.dayOfWeek = dayOfWeek;
        this.employee = emp;
        this.time = time;
    }
    
    /**
     * Getter method to return 
     * the employee that has a clock in\out
     * time.
     * @return The employee
     */
    public Employee getEmployee() {
        return employee;
    }
    
    /**
     * Getter method to return the
     * value in the Enum called ClockType
     * @return The type of clock time being IN/OUT
     */
    public ClockType getClockType() {
        return clockType;
    }

    /**
     * Setter method to define what
     * value the ClockType of the clock
     * is being IN/OUT
     * @param clockType The type of clock time being IN/OUT
     */
    public void setClockType(ClockType clockType) {
        this.clockType = clockType;
    }
    
    /**
     * To string method
     * to return the information
     * of the clock
     * from date time
     * @return The date and time as a string
     */
    @Override
    public String toString()
    {
        String str = "";
        str += dayOfWeek + "         ";
        str += String.format("%02d", time.getDayOfMonth()) + "/";
        str += String.format("%02d", time.getMonthValue()) + "/";
        str += time.getYear() + " ";
        str += String.format("%02d", time.getHour()) + ":";
        str += String.format("%02d", time.getMinute()) + ":";
        str += String.format("%02d", time.getSecond());
        
        return str;
    }

    
}
