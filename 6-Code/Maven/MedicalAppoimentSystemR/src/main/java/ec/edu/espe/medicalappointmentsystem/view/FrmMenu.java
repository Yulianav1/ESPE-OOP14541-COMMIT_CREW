/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.view;

/**
 *
 * @author usuar
 */
public class FrmMenu extends javax.swing.JFrame {

    /**
     * Creates new form FrmMenu
     */
    public FrmMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        itmLogout = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        itmExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itmAddAppointment = new javax.swing.JMenu();
        itmRescheduleAppointment = new javax.swing.JMenu();
        cancelAppointment = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        itmCalendar = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        itmAddDoctor = new javax.swing.JMenuItem();
        itmViewDoctor = new javax.swing.JMenuItem();
        itmDeleteDoctor = new javax.swing.JMenu();
        itmSpecialty = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        itmLogout.setText("Medical Appointment System");

        jMenuItem3.setText("Logout");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        itmLogout.add(jMenuItem3);

        itmExit.setText("Exit");
        itmExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmExitActionPerformed(evt);
            }
        });
        itmLogout.add(itmExit);

        jMenuBar1.add(itmLogout);

        jMenu2.setText("Citas");

        itmAddAppointment.setText("Agregar Cita");
        jMenu2.add(itmAddAppointment);

        itmRescheduleAppointment.setText("Reagendar Cita");
        jMenu2.add(itmRescheduleAppointment);

        cancelAppointment.setText("Cancelar Cita");
        jMenu2.add(cancelAppointment);

        jMenuBar1.add(jMenu2);

        jMenu10.setText("Calendario");

        itmCalendar.setText("Ver Calendario");
        itmCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmCalendarActionPerformed(evt);
            }
        });
        jMenu10.add(itmCalendar);

        jMenuBar1.add(jMenu10);

        jMenu5.setText("Doctor");

        itmAddDoctor.setText("Añadir Doctor");
        jMenu5.add(itmAddDoctor);

        itmViewDoctor.setText("Ver Doctores");
        jMenu5.add(itmViewDoctor);

        itmDeleteDoctor.setText("Eliminar Doctor");
        jMenu5.add(itmDeleteDoctor);

        jMenuBar1.add(jMenu5);

        itmSpecialty.setText("Reporte");

        jMenu11.setText("Especialidades");
        itmSpecialty.add(jMenu11);

        jMenuItem9.setText("Doctores");
        itmSpecialty.add(jMenuItem9);

        jMenuBar1.add(itmSpecialty);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(352, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void itmExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmExitActionPerformed

    private void itmCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmCalendarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmCalendarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cancelAppointment;
    private javax.swing.JMenu itmAddAppointment;
    private javax.swing.JMenuItem itmAddDoctor;
    private javax.swing.JMenuItem itmCalendar;
    private javax.swing.JMenu itmDeleteDoctor;
    private javax.swing.JMenuItem itmExit;
    private javax.swing.JMenu itmLogout;
    private javax.swing.JMenu itmRescheduleAppointment;
    private javax.swing.JMenu itmSpecialty;
    private javax.swing.JMenuItem itmViewDoctor;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
