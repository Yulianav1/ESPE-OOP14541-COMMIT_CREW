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

    public Calendar() {
        this.workingDays = new ArrayList<>();
        this.availableSlots = new ArrayList<>();
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
