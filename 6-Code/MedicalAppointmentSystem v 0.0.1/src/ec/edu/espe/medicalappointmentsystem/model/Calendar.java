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
 * Author:
 */
public class Calendar {
<<<<<<< HEAD

    private static List<List<Appointment>> calendar;

    public Calendar() {
        calendar = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Appointment> day = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                day.add(null);
            }
            calendar.add(day);
=======
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

    public void addAppointment(int dayOfWeek, int timeSlot, Appointment appointment) {
        if (dayOfWeek >= 0 && dayOfWeek < 5 && timeSlot >= 0 && timeSlot < 10) {
            calendar.get(dayOfWeek).set(timeSlot, appointment);
        } else {
            System.out.println("Error: Day of week or time slot out of range.");
        }
    }

    public static Appointment getAppointment(int dayOfWeek, int timeSlot) {
        if (dayOfWeek >= 0 && dayOfWeek < 5 && timeSlot >= 0 && timeSlot < 10) {
            return calendar.get(dayOfWeek).get(timeSlot);
        } else {
            System.out.println("Error: Day of week or time slot out of range.");
            return null;
        }
    }

}
