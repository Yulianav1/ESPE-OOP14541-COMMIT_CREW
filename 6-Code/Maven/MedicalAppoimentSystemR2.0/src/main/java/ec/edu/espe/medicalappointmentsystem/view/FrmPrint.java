/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.view;

import ec.edu.espe.medicalappointmentsystem.controller.AppointmentController;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.util.Printer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexis Viteri DCO-ESPE
 */
public class FrmPrint extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrint
     */
    private Appointment selectedAppointment;
    private DefaultTableModel model;
    public FrmPrint() {
         initComponents();
        model = (DefaultTableModel) jTableAppointments.getModel(); // Asegúrate de que 'model' esté correctamente inicializado
        showAppointments(); 
        
    }
    
    private void showAppointments() {
    try {
        // Limpia la tabla antes de agregar nuevos datos
        model.setRowCount(0);

        // Obtener las citas desde el controlador
        List<Appointment> appointments = AppointmentController.loadAppointments();
        System.out.println("Número de citas recuperadas: " + appointments.size());

        // Recorrer la lista de citas y agregarlas al modelo de la tabla
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Recorrer la lista de citas y agregarlas al modelo de la tabla
        for (Appointment appointment : appointments) {
            // Convertir Date a LocalDate
            LocalDate localDate = appointment.getDateAppointment().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();

            // Formatear la fecha
            String formattedDate = localDate.format(formatter);

            // Asegúrate de que los datos se obtengan correctamente
            Object[] row = {
                formattedDate,  // Usar la fecha formateada
                appointment.getPatient().getName(),
                appointment.getPatient().getId(),
                appointment.getDoctor().getName(),
                appointment.getTimeSlot()
            };
            model.addRow(row); // Agrega la fila al modelo
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar las citas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
private void printAppointment(Appointment appointment) {
        // Aquí debes implementar la lógica para imprimir la cita.
        // Puedes usar la información del objeto 'appointment' para generar un informe de impresión.
        System.out.println("Imprimiendo cita: " + appointment);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAppointments = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ButtonPrint = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Paciente", "Cedula", "Doctor", "Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAppointments.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableAppointments.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTableAppointments.setGridColor(new java.awt.Color(204, 255, 255));
        jTableAppointments.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTableAppointments.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTableAppointments.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableAppointments.setShowHorizontalLines(true);
        jTableAppointments.setUpdateSelectionOnSort(false);
        jTableAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAppointmentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAppointments);
        if (jTableAppointments.getColumnModel().getColumnCount() > 0) {
            jTableAppointments.getColumnModel().getColumn(0).setResizable(false);
            jTableAppointments.getColumnModel().getColumn(0).setHeaderValue("Fecha");
            jTableAppointments.getColumnModel().getColumn(1).setResizable(false);
            jTableAppointments.getColumnModel().getColumn(1).setHeaderValue("Paciente");
            jTableAppointments.getColumnModel().getColumn(2).setResizable(false);
            jTableAppointments.getColumnModel().getColumn(2).setHeaderValue("Cedula");
            jTableAppointments.getColumnModel().getColumn(3).setResizable(false);
            jTableAppointments.getColumnModel().getColumn(3).setHeaderValue("Doctor");
            jTableAppointments.getColumnModel().getColumn(4).setResizable(false);
            jTableAppointments.getColumnModel().getColumn(4).setHeaderValue("Hora");
        }
        jTableAppointments.getAccessibleContext().setAccessibleParent(jTableAppointments);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 630, 350));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Impresion de Citas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(424, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 50));

        ButtonPrint.setBackground(new java.awt.Color(33, 150, 255));
        ButtonPrint.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ButtonPrint.setForeground(new java.awt.Color(255, 255, 255));
        ButtonPrint.setText("Imprimir");
        ButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPrintActionPerformed(evt);
            }
        });
        jPanel1.add(ButtonPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 130, 40));

        btnReturn.setBackground(new java.awt.Color(33, 150, 243));
        btnReturn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(255, 255, 255));
        btnReturn.setText("Volver");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });
        jPanel1.add(btnReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 450, 120, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        FrmMenu frmMenu = new FrmMenu();
        this.setVisible(false);
        frmMenu.setVisible(true);
    }//GEN-LAST:event_btnReturnActionPerformed
    private void jTableAppointmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAppointmentsMouseClicked
        int row = jTableAppointments.getSelectedRow(); // Obtén la fila seleccionada
    if (row != -1) {
        // Obtén los datos de la fila seleccionada
        String dateString = (String) model.getValueAt(row, 0); 
        String patientName = (String) model.getValueAt(row, 1);
        String patientId = (String) model.getValueAt(row, 2);
        String doctorName = (String) model.getValueAt(row, 3);
        String timeSlot = (String) model.getValueAt(row, 4);
        
        // Convertir la cadena de fecha a LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        // Convertir LocalDate a Date
        ZoneId zoneId = ZoneId.systemDefault(); // O usa otro ZoneId si es necesario
        Date date = Date.from(localDate.atStartOfDay(zoneId).toInstant());

        
        // Aquí deberías buscar la cita correspondiente en tu lista de citas
        for (Appointment appointment : AppointmentController.loadAppointments()) {
            if (appointment.getDateAppointment().equals(date) &&
                appointment.getPatient().getName().equals(patientName) &&
                appointment.getPatient().getId().equals(patientId) &&
                appointment.getDoctor().getName().equals(doctorName) &&
                appointment.getTimeSlot().equals(timeSlot)) {
                selectedAppointment = appointment;
                break;
            }
        }
        
        // Verifica que la cita seleccionada no sea nula
        if (selectedAppointment == null) {
            System.out.println("No se encontró una cita que coincida con la selección.");
        }
    }
    }//GEN-LAST:event_jTableAppointmentsMouseClicked

    private void ButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPrintActionPerformed
       // Verifica si se ha seleccionado una cita
    if (selectedAppointment != null) {
        // Llama al método para imprimir la cita
        Printer.printAppointment(selectedAppointment);
    } else {
        // Muestra un mensaje de advertencia si no se ha seleccionado ninguna cita
        JOptionPane.showMessageDialog(this, "Por favor, selecciona una cita para imprimir.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_ButtonPrintActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrint().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonPrint;
    private javax.swing.JButton btnReturn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAppointments;
    // End of variables declaration//GEN-END:variables
}
