/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;

/**
 * Receptionist Model
 * 
 * Author: Luis Vaca, TheJavaBandits, DCCO-ESPE
 */
public class Receptionist {
    private int id;
    private String name;
    private String schedule;

    public Receptionist(int id, String name, String schedule) {
        this.id = id;
        this.name = name;
        this.schedule = schedule;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    // Operations
    public void checkDoctorAvailability() {
        System.out.println("Checking doctor's availability...");
    }

    public void scheduleAppointment() {
        System.out.println("Scheduling an appointment for a patient...");
    }

    public void sendAppointmentReminders() {
        System.out.println("Sending appointment reminders...");
    }

    public void updateSchedule(String newSchedule) {
        setSchedule(newSchedule);
        System.out.println("Updated receptionist's schedule to: " + newSchedule);
    }
}
