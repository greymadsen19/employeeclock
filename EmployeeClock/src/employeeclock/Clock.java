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
 *
 * @author Jacob Madsen
 */
public class Clock {
    private Employee employee;
    private LocalDateTime time;
    private String dayOfWeek;
    
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
        this.dayOfWeek = Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
    }
    
    public Clock(Employee emp, LocalDateTime time, String dayOfWeek)
    {
        this.dayOfWeek = dayOfWeek;
        this.employee = emp;
        this.time = time;
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
        
        str += dayOfWeek + " ";
        str += String.format("%02d", time.getDayOfMonth()) + "/";
        str += String.format("%02d", time.getMonthValue()) + "/";
        str += time.getYear() + " ";
        str += String.format("%02d", time.getHour()) + ":";
        str += String.format("%02d", time.getMinute()) + ":";
        str += String.format("%02d", time.getSecond());
        
        return str;
    }
}
