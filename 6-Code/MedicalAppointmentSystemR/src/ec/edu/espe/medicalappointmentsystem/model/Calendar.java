package ec.edu.espe.medicalappointmentsystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<List<Appointment>> appointments;

    public Calendar() {
        appointments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            appointments.add(new ArrayList<>(10));
            for (int j = 0; j < 10; j++) {
                appointments.get(i).add(null);
            }
        }
    }

    public LocalDate getDate(int dayIndex) {
        return LocalDate.now().plusDays(dayIndex);
    }

    public Appointment getAppointment(int dayIndex, int timeSlot) {
        return appointments.get(dayIndex).get(timeSlot);
    }

    public void setAppointment(int dayIndex, int timeSlot, Appointment appointment) {
        appointments.get(dayIndex).set(timeSlot, appointment);
    }
}



