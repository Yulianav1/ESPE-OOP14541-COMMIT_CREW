/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.view;

import com.toedter.calendar.JDateChooser;
import ec.edu.espe.medicalappointmentsystem.controller.AppointmentController;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yuliana Valencia DCCO-ESPE
 */
public class FrmCalendar extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrint
     */
    private DefaultTableModel model;
    private JDateChooser dateChooser;
    private Appointment selectedAppointment;

    public FrmCalendar() {
        initComponents();
        // Inicializar el modelo de la tabla y JDateChooser
        model = (DefaultTableModel) jTableAppointments.getModel();
        dateChooser = DateAppointment;
    }

    private void loadAppointmentsForDate(LocalDate date) {
        try {
            // Limpiar la tabla antes de agregar nuevos datos
            model.setRowCount(0);

            // Obtener todas las citas desde el controlador
            List<Appointment> appointments = AppointmentController.loadAppointments();

            // Recorrer la lista de citas y agregar las que coinciden con la fecha seleccionada
            for (Appointment appointment : appointments) {
                LocalDate appointmentDate = appointment.getDateAppointment().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();

                if (appointmentDate.equals(date)) {
                    // Crear una fila con los datos necesarios
                    Object[] row = {
                        appointment.getPatient().getName(),
                        appointment.getPatient().getId(),
                        appointment.getTimeSlot()
                    };

                    // Agregar la fila al modelo de la tabla
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que ocurra
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las citas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAppointments = new javax.swing.JTable();
        ButtonCarry = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        btnReturn = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        DateAppointment = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Paciente", "Cedula", "Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
            jTableAppointments.getColumnModel().getColumn(1).setResizable(false);
            jTableAppointments.getColumnModel().getColumn(2).setResizable(false);
        }
        jTableAppointments.getAccessibleContext().setAccessibleParent(jTableAppointments);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 580, 220));

        ButtonCarry.setBackground(new java.awt.Color(33, 150, 255));
        ButtonCarry.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ButtonCarry.setForeground(new java.awt.Color(255, 255, 255));
        ButtonCarry.setText("Cargar");
        ButtonCarry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCarryActionPerformed(evt);
            }
        });
        jPanel1.add(ButtonCarry, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, 130, 40));

        jPanel4.setBackground(new java.awt.Color(33, 150, 243));

        jLabel16.setFont(new java.awt.Font("RomanD", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Calendario");

        btnReturn.setText("Home");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 90));

        jLabel17.setFont(new java.awt.Font("RomanD", 1, 18)); // NOI18N
        jLabel17.setText("Calendario");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        jPanel1.add(DateAppointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 180, -1));

        jLabel2.setText("Fecha:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 40, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableAppointmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAppointmentsMouseClicked
        int selectedRow = jTableAppointments.getSelectedRow();  // Obtener la fila seleccionada

        if (selectedRow >= 0) {
            // Obtener los valores de la fila seleccionada
            String patientName = (String) model.getValueAt(selectedRow, 0);
            String patientId = (String) model.getValueAt(selectedRow, 1);
            String timeSlot = (String) model.getValueAt(selectedRow, 2);

            // Ahora puedes buscar la cita correspondiente en la lista de citas, si la tienes almacenada
            List<Appointment> appointments = AppointmentController.loadAppointments();

            for (Appointment appointment : appointments) {
                if (appointment.getPatient().getName().equals(patientName)
                        && appointment.getPatient().getId().equals(patientId)
                        && appointment.getTimeSlot().equals(timeSlot)) {

                    // Cita encontrada, la puedes almacenar en selectedAppointment para su posterior uso
                    selectedAppointment = appointment;
                    break;
                }
            }

        }


    }//GEN-LAST:event_jTableAppointmentsMouseClicked
  

    private void ButtonCarryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCarryActionPerformed
         // Obtener la fecha seleccionada del JDateChooser
        Date selectedDate = dateChooser.getDate();

        if (selectedDate != null) {
            // Convertir Date a LocalDate
            LocalDate localDate = selectedDate.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();

            // Llamar al método que actualiza la tabla con las citas para la fecha seleccionada
            loadAppointmentsForDate(localDate);
        } else {
            // Mostrar un mensaje de advertencia si no se selecciona una fecha
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una fecha.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_ButtonCarryActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        FrmMenu frmMenu = new FrmMenu();
        this.setVisible(false);
        frmMenu.setVisible(true);
    }//GEN-LAST:event_btnReturnActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCalendar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCarry;
    private com.toedter.calendar.JDateChooser DateAppointment;
    private javax.swing.JButton btnReturn;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAppointments;
    // End of variables declaration//GEN-END:variables
}
