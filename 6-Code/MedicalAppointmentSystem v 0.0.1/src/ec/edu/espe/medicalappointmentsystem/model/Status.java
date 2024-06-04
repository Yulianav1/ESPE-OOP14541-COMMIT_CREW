/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;

import java.util.Date;

/**
 * Status Model
 * 
 * Author: Luis Vaca, TheJavaBandits, DCCO-ESPE
 */
public class Status {
    private Date currentDate;
    private Date currentTime; 
    private boolean isAvailable;
    private int appointmentCount;

    public Status() {
        this.currentDate = new Date(); 
        this.currentTime = new Date(); 
        this.isAvailable = true;
        this.appointmentCount = 0;
    }

    // Getters and Setters
    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getAppointmentCount() {
        return appointmentCount;
    }

    public void setAppointmentCount(int appointmentCount) {
        this.appointmentCount = appointmentCount;
    }

    // Operations
    public boolean checkAvailability() {
        return isAvailable && (appointmentCount < 10); 
    }

    public void updateStatus(boolean isAvailable, int appointmentCount) {
        setAvailable(isAvailable);
        setAppointmentCount(appointmentCount);
        System.out.println("Updated status to: Available=" + isAvailable + ", AppointmentCount=" + appointmentCount);
    }

    public Date getNextAvailableSlot() {
        // This is a simple placeholder for getting the next available slot
        if (isAvailable()) {
            System.out.println("Next available slot is at " + getCurrentTime());
            return getCurrentTime();
        } else {
            System.out.println("No available slots currently.");
            return null;
        }
    }
}
