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
    
    public Employee getEmployee()
    {
        return this.employee;
    }
    
    @Override
    public String toString()
    {
        String str = "";
        str += dayOfWeek + " ";
        str += time.getMonthValue() + "-";
        str += time.getDayOfMonth() + "-";
        str += time.getYear() + " ";
        str += time.getHour() + ":";
        str += time.getMinute();
        
        return str;
    }
}
