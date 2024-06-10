/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Reminder Model
 * 
 * Author: Luis Vaca, TheJavaBandits, DCCO-ESPE
 */
public class Reminder {
    private Date appointmentDate;
    private Date appointmentTime;
    private String recipient;

    public Reminder(Date appointmentDate, Date appointmentTime, String recipient) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.recipient = recipient;
    }

    // Getters and Setters
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    // Operations
    public void sendReminder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println("Sending reminder to " + recipient +
            " for appointment at " + sdf.format(appointmentDate) + " " + sdf.format(appointmentTime));
    }

    public void confirmAppointment() {
        System.out.println("Appointment confirmed for " + recipient +
            " on " + new SimpleDateFormat("yyyy-MM-dd").format(appointmentDate) +
            " at " + new SimpleDateFormat("HH:mm").format(appointmentTime));
    }

    public void updateReminderDetails(Date newDate, Date newTime) {
        setAppointmentDate(newDate);
        setAppointmentTime(newTime);
        System.out.println("Reminder details updated for " + recipient);
    }
}
