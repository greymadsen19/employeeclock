/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeclock;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Jacob Madsen
 */
public class ClockStation extends javax.swing.JFrame {

    private Employee employees[] = new Employee[100];
    private Clock[] timeClockedOut = new Clock[100];
    private Clock[] timeClockedIn = new Clock[100];
    private Clock clock[] = new Clock[100];
    private LocalDateTime[] time = new LocalDateTime[100];
    private FileWriter fwriter;
    private PrintWriter dataFile;
    
    /**
     * Creates new form ClockStation
     */
    public ClockStation() {
        initComponents();
        
        employees[0] = new Employee(56849175, "John", 10.99);
        employees[1] = new Employee(56393847, "Sarah", 15.75);
        employees[2] = new Employee(56820361, "Robert", 10.99);
        employees[3] = new Employee(56409021, "Jacob", 20.59);
        employees[4] = new Employee(56886677, "Bob", 25.45);
        employees[5] = new Employee(56755266, "Donald", 15.99);
        employees[6] = new Employee(56957129, "Steve", 16.85);
        employees[7] = new Employee(56510413, "Vanessa", 19.99);
        employees[8] = new Employee(56001704, "Jenn", 14.99);
        employees[9] = new Employee(56065281, "Sam", 15.99);
        
        try {
            // TODO add your handling code here:
            File file = new File("ClockTimes.txt");
            Scanner inputFile = new Scanner(file);
            String lines = "";
            String[] splitTokens;
            int i = 0;
            while(inputFile.hasNext())
            {
                lines = inputFile.nextLine();
                splitTokens = lines.split(" ");
                
                Employee emp = new Employee(Integer.parseInt(splitTokens[0]),
                        splitTokens[1], Double.parseDouble(splitTokens[2]));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                clock[i] = new Clock(emp, LocalDateTime.parse(splitTokens[4] + " " + splitTokens[5], formatter), splitTokens[3]);
                
                if(i%2 == 0)
                {
                    timeClockedIn[i] = clock[i];
                }
                else
                {
                    timeClockedOut[i] = clock[i];
                }
                ++i;
            }
            inputFile.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clock Station");

        submButton.setText("SUBMIT");
        submButton.setActionCommand("SUBMIT");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                .addComponent(progTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        int userInput = 0;
        Employee employee = null;
        try
        {
            userInput = Integer.parseInt(txtInputBox.getText());
            fwriter = new FileWriter("ClockTimes.txt", true);
            dataFile = new PrintWriter(fwriter);
            for(int i = 0; i < 100; i++)
            {
                if(employees[i].getEmployeeID() == userInput)
                {
                    employee = employees[i];
                    time[i] = LocalDateTime.now();
                    
                    if(isClockedIn(employee.getEmployeeID()) == false)
                    {
                        timeClockedIn[i] = new Clock(employee, time[i]);
                        dataFile.println(employee.toString() + " " + timeClockedIn[i].toString());
                        lblMessage.setText(employee.getEmployeeName() + ", you have clocked in");
                        timeClockedOut[i] = null;
                    }
                    else
                    {
                        timeClockedOut[i] = new Clock(employee, time[i]);
                        dataFile.println(employee.toString() + " " + timeClockedOut[i].toString());
                        lblMessage.setText(employee.getEmployeeName() + ", you have clocked out");
                        timeClockedIn[i] = null;
                    }
                    
                    
                    dataFile.close();
                    lblMessage.setForeground(Color.BLACK);
                    txtInputBox.setText("");
                    break;
                }
                else
                {
                    lblMessage.setText("Invalid Employee ID!");
                    lblMessage.setForeground(Color.RED);
                    txtInputBox.setText("");
                }
            }
                
        }
        catch(IOException | NumberFormatException e)
        {
            e.printStackTrace();
            lblMessage.setText("Invalid Employee ID!");
            lblMessage.setForeground(Color.RED);
            txtInputBox.setText("");
        }
    }//GEN-LAST:event_submButtonActionPerformed

    public boolean isClockedIn(int id)
    {
        try {
            for(int i = 0; i < 100; i++)
            {
                if (timeClockedIn[i].getEmployee().getEmployeeID() == id)
                {
                    if(timeClockedIn[i] != null)
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
    private javax.swing.JLabel progTitle;
    private javax.swing.JButton submButton;
    private javax.swing.JTextArea txtInputBox;
    private javax.swing.JScrollPane txtScrollPane;
    // End of variables declaration//GEN-END:variables
}
