package ec.edu.espe.medicalappointmentsystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Author: CommitCrew
 */
public class Calendar {
 private static List<List<Appointment>> calendar;

    public Calendar() {
        calendar = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Appointment> day = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                day.add(null);
            }
            calendar.add(day);
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