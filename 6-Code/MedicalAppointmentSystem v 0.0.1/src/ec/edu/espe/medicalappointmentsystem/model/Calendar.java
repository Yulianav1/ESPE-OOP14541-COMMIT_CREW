/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Calendar Model
 * 
 * Author: Luis Vaca, TheJavaBandits, DCCO-ESPE
 */
public class Calendar {
    private List<String> workingDays;
    private List<String> availableSlots;
    private List<List<Appointment>> calendar;
    public Calendar() {
        this.calendar = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Appointment> day = new ArrayList<>();
            for (int j = 0; j < 10; j++) { // 10 slots representando los intervalos de una hora y media desde las 7:00 hasta la 1:30
                day.add(null); // Inicializar con citas nulas
            }
            this.calendar.add(day);
        }
    }

    public void addAppointment(int dayOfWeek, int timeSlot, Appointment appointment) {
        if (dayOfWeek >= 0 && dayOfWeek < 5 && timeSlot >= 0 && timeSlot < 10) {
            calendar.get(dayOfWeek).set(timeSlot, appointment);
        } else {
            System.out.println("Error: Day of week or time slot out of range.");
        }
    }

    public Appointment getAppointment(int dayOfWeek, int timeSlot) {
        if (dayOfWeek >= 0 && dayOfWeek < 5 && timeSlot >= 0 && timeSlot < 10) {
            return calendar.get(dayOfWeek).get(timeSlot);
        } else {
            System.out.println("Error: Day of week or time slot out of range.");
            return null;
        }
    }

    // Getters and Setters
    public List<String> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<String> workingDays) {
        this.workingDays = new ArrayList<>(workingDays);
        System.out.println("Working days updated to: " + workingDays);
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<String> availableSlots) {
        this.availableSlots = new ArrayList<>(availableSlots);
        System.out.println("Available slots updated to: " + availableSlots);
    }

    // Operations
    public void manageAvailability() {
        System.out.println("Managing availability...");
    }

    public boolean checkAvailableSlots() {
        // Check if there are available slots
        boolean hasAvailableSlots = !availableSlots.isEmpty();
        System.out.println("Checking available slots: " + hasAvailableSlots);
        return hasAvailableSlots;
    }

    public void updateWorkingDays(List<String> newWorkingDays) {
        setWorkingDays(newWorkingDays);
    }

    public void updateAvailableSlots(List<String> newAvailableSlots) {
        setAvailableSlots(newAvailableSlots);
    }
}
