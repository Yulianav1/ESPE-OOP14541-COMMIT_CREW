package ec.edu.espe.medicalappointmentsystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<List<Appointment>> appointments;

    public Calendar() {
        appointments = new ArrayList<>();
        for (int i = 0; i < 365; i++) { // Soporte para citas durante todo el año
            List<Appointment> dailyAppointments = new ArrayList<>(10);
            for (int j = 0; j < 10; j++) {
                dailyAppointments.add(null);
            }
            appointments.add(dailyAppointments);
        }
    }

    public LocalDate getDate(int dayIndex) {
        return LocalDate.now().plusDays(dayIndex);
    }

    public Appointment getAppointment(int dayIndex, int timeSlot) {
        if (dayIndex < 0 || dayIndex >= appointments.size() || timeSlot < 0 || timeSlot >= 10) {
            return null;
        }
        return appointments.get(dayIndex).get(timeSlot);
    }

    public void setAppointment(int dayIndex, int timeSlot, Appointment appointment) {
        if (dayIndex >= 0 && dayIndex < appointments.size() && timeSlot >= 0 && timeSlot < 10) {
            appointments.get(dayIndex).set(timeSlot, appointment);
        }
    }
}
