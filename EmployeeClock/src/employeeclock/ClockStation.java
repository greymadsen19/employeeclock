/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeclock;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 * The ClockStation program
 * is a Clock-in\out station
 * for employees that allows the
 * employee to enter their employee id
 * and will clock them in if they
 * haven't clocked in yet or
 * clocks them out as well as saving to a file.
 * If they enter an invalid ID then
 * an error message is displayed.
 * There is also an area in the program that
 * the user can click to open a print
 * dialog so that they can print out
 * the text file where the clock times for 
 * each employee are stored.
 * @author Jacob Madsen
 */
public class ClockStation extends javax.swing.JFrame {

    private Employee employees[] = new Employee[100];
    
    private Clock[] timeClockedOut = new Clock[100];
    private Clock[] timeClockedIn = new Clock[100];
    private ArrayList<Clock> clock = new ArrayList<>();
    private Clock lastNonNull = new Clock();
    
    private LocalDateTime[] time = new LocalDateTime[100];
    
    private File file;
    private FileWriter fwriter;
    private PrintWriter dataFile;
    
    /**
     * Creates new form ClockStation
     */
    public ClockStation() {
        initComponents();
        
        employees[0] = new Employee("56849175", "John", 10.99);
        employees[1] = new Employee("56393847", "Sarah", 15.75);
        employees[2] = new Employee("56820361", "Robert", 10.99);
        employees[3] = new Employee("56409021", "Jacob", 20.59);
        employees[4] = new Employee("56886677", "Bob", 25.45);
        try {
            // TODO add your handling code here:
            file = new File("ClockTimes.txt");
            Scanner inputFile = new Scanner(file);
            String lines = "";
            String[] splitTokens;
            int i = 0;
            
            //Ints to store the first two lines
            int currentLine = 0;
            int skipLine = 1;
            
            while(inputFile.hasNext())
            {
                
                lines = inputFile.nextLine();
                
                //Split the lines of a file with two delimeters: Space and tab
                splitTokens = lines.split("\\s+|\t");
                
                //To skip the first two lines to avoid an error when parsing to clock and emp
                if(currentLine++ <= skipLine)
                {
                    continue;
                }
                
                //Create an employee from the file and set the format for the date and time
                Employee emp = new Employee(splitTokens[1],
                        splitTokens[2], Double.parseDouble(splitTokens[3]));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                
                //Create a new clock using the tokens read from the file
                clock.add(i ,new Clock(emp, LocalDateTime.parse(splitTokens[5] + " " + splitTokens[6], formatter), splitTokens[4]));
                
                //Check if even to determine if the ClockType enum is IN or OUT
                if(i%2 == 0)
                {
                    timeClockedIn[i] = clock.get(i);
                    clock.get(i).setClockType(ClockType.IN);
                }
                else
                {
                    timeClockedOut[i] = clock.get(i);
                    clock.get(i).setClockType(ClockType.OUT);
                }
                ++i;
            }
            inputFile.close();
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Each time this
     * method is run it will
     * create a new timer
     * which schedules a task
     * to set the text
     * of the clockin\out\error
     * message to blank after a certain 
     * number of seconds
     */
    public void startTimer()
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                lblMessage.setText(" ");
            }
        }, 3*1000);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        submButton = new javax.swing.JButton();
        progTitle = new javax.swing.JLabel();
        txtScrollPane = new javax.swing.JScrollPane();
        txtInputBox = new javax.swing.JTextArea();
        lblMessage = new javax.swing.JLabel();
        printLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clock Station");

        submButton.setText("Clock In");
        submButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submButtonActionPerformed(evt);
            }
        });

        progTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        progTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        progTitle.setText("Employee Clock Station");

        txtScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        txtScrollPane.setToolTipText("");
        txtScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtInputBox.setColumns(20);
        txtInputBox.setRows(5);
        txtScrollPane.setViewportView(txtInputBox);

        lblMessage.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        printLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        printLabel.setText("<html>Print<br>Clock<br>Times</html>");
        printLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(printLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(txtScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(submButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(txtScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submButton)
                .addGap(16, 16, 16))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * When the submit button
     * is clicked it
     * will clock the employee
     * in or out depending on
     * if they have clocked in yet.
     * The information of the employee and
     * time clocked in\out is written
     * to a text file.
     * @param evt The event generated by clicking the button
     */
    private void submButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submButtonActionPerformed
        // TODO add your handling code here:
        String userInput = "";
        Employee employee = null;
        try
        {
            userInput = txtInputBox.getText();
            fwriter = new FileWriter("ClockTimes.txt", true);
            dataFile = new PrintWriter(fwriter);
            
            //Check if the file has no data so that a header can be added
            if(file.length() == 0)
            {
                dataFile.println("             Employee_ID Employee_Name Hourly_Pay Day_Of_Week Date      Time");
                dataFile.println("            ----------- ------------- ---------- ----------- ----       ----");
            }
            
            //Get the last non null starting from the end of the clock array
            for(int j = (clock.size()-1); j >= 0; j--)
            {
                if(clock.get(j) != null)
                {
                    lastNonNull = clock.get(j);
                    break;
                }
            }
            
            for(int i = 0; i < 100; i++)
            {
                if(employees[i].getEmployeeID().equals(userInput))
                {
                    employee = employees[i];
                    time[i] = LocalDateTime.now();
                    
                    //Check if the employee has not clocked to clock them in
                    //Else clock them out if the isClockedIn method returns true
                    if(isClockedIn(employee.getEmployeeID()) == false)
                    {
                        timeClockedIn[i] = new Clock(employee, time[i]);
                        dataFile.println("Clocked_In" + "  " + employee.toString() + " " + timeClockedIn[i].toString());
                        lblMessage.setText(employee.getEmployeeName() + ", you have clocked in");
                        lastNonNull.setClockType(ClockType.IN);
                        startTimer();
                    }
                    else
                    {
                        timeClockedOut[i] = new Clock(employee, time[i]);
                        dataFile.println("Clocked_Out" + " " + employee.toString() + " " + timeClockedOut[i].toString());
                        lblMessage.setText(employee.getEmployeeName() + ", you have clocked out");
                        lastNonNull.setClockType(ClockType.OUT);
                        startTimer();
                    }
                    
                    
                    dataFile.close();
                    lblMessage.setForeground(Color.BLACK);
                    txtInputBox.setText("");
                    break;
                }
                else
                {
                    //If the employee id is invalid display an error message
                    lblMessage.setText("Invalid Employee ID!");
                    lblMessage.setForeground(Color.RED);
                    txtInputBox.setText("");
                    startTimer();
                }
            }
                
        }
        catch(IOException | NullPointerException e)
        {
            //Display an error message if an exception is thrown
            // When the Employee ID is entered
            lblMessage.setText("Invalid Employee ID!");
            lblMessage.setForeground(Color.RED);
            txtInputBox.setText("");
            startTimer();
        }
    }//GEN-LAST:event_submButtonActionPerformed

    private void printLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printLabelMouseClicked
        // TODO add your handling code here:
        try
        {
            //Create an input stream and read from the text file
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.read();
            
            //With docflavof use autosense to set the input stream for the printer
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            
            
            //Create a Print reqeust attribute set
            PrintRequestAttributeSet asset = new HashPrintRequestAttributeSet();
            
            //Create print services for the default service and the rest of the print services available
            PrintService[] pservices = PrintServiceLookup.lookupPrintServices(flavor, asset);
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
            
            //Create a new SimpleDoc from the input stream that reads the file and the doc flavor
            Doc doc = new SimpleDoc(inputStream, flavor, null);
            
            if(pservices.length > 0)
            {
                //Set a new print service from a selection using the Service UI print dialog
                PrintService selectPrinter = ServiceUI.printDialog(null, 50, 50, pservices, defaultService, flavor, asset);
                if(selectPrinter != null)
                {
                    //Create a print job for the selected print service and print the document
                    DocPrintJob pj = selectPrinter.createPrintJob();
                    pj.print(doc, asset);
                }
            }
            
            inputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_printLabelMouseClicked

    /**
     * Th isClockedIn method checks if
     * the employee id that was clocked in matches
     * the id given. It then checks if that
     * employee has clocked in and returns true
     * else it returns false.
     * @param id The id of the employee clocking in
     * @return return false if not clocked in
     */
    public boolean isClockedIn(String id)
    {
        try {
            for(int i = 0; i < 100; i++)
            {
                if (timeClockedIn[i].getEmployee().getEmployeeID().equals(id))
                {
                    if(lastNonNull.getClockType() == ClockType.IN)
                    {
                        return true;
                    }
                }
            }

            return false;
        }
        catch(NullPointerException ex)
        {
            return false;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClockStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClockStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClockStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClockStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClockStation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel printLabel;
    private javax.swing.JLabel progTitle;
    private javax.swing.JButton submButton;
    private javax.swing.JTextArea txtInputBox;
    private javax.swing.JScrollPane txtScrollPane;
    // End of variables declaration//GEN-END:variables
}
