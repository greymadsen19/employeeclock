/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeclock;

import java.awt.Color;
import java.awt.HeadlessException;
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
import javax.print.PrintException;
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
public class ClockStation extends javax.swing.JFrame
{

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
            
            //Get the last non null starting from the end of the clock array
            for(int j = (clock.size()-1); j >= 0; j--)
            {
                if(clock.get(j) != null)
                {
                    lastNonNull = clock.get(j);
                    break;
                }
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

        jPanel1 = new javax.swing.JPanel();
        progTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtScrollPane = new javax.swing.JScrollPane();
        txtInputBox = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        submButton = new javax.swing.JButton();
        printLabel = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clock Station");

        jPanel1.setBackground(new java.awt.Color(220, 220, 220));

        progTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        progTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        progTitle.setText("Employee Clock Station");

        jPanel2.setBackground(new java.awt.Color(220, 220, 220));

        txtScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        txtScrollPane.setToolTipText("");
        txtScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtInputBox.setColumns(20);
        txtInputBox.setRows(5);
        txtInputBox.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtScrollPane.setViewportView(txtInputBox);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Enter your Employee ID");

        submButton.setText("Submit");
        submButton.setToolTipText("Click here to clock in or out");
        submButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(submButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        printLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        printLabel.setText("<html>Print<br>Clock<br>Times</html>");
        printLabel.setToolTipText("Click here to print the ClockTimes.txt file");
        printLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printLabelMouseClicked(evt);
            }
        });

        lblMessage.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(progTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(printLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(printLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

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
                dataFile.println("             Employee_ID Employee_Name Hourly_Pay Day_Of_Week Date       Time");
                dataFile.println("            ----------- ------------- ---------- ----------- ----       ----");
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
        catch(HeadlessException | IOException | PrintException e)
        {
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
        try
        {
            for(int i = 0; i < 100; i++)
            {
                if(lastNonNull.getClockType() == ClockType.IN)
                {
                    return true;
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
    public static void main(String args[])
    {
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
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ClockStation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel printLabel;
    private javax.swing.JLabel progTitle;
    private javax.swing.JButton submButton;
    private javax.swing.JTextArea txtInputBox;
    private javax.swing.JScrollPane txtScrollPane;
    // End of variables declaration//GEN-END:variables
}
