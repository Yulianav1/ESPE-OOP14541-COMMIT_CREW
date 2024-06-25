package ec.edu.espe.medicalappointmentsystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<List<Appointment>> calendar;

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
            System.out.println("Error: Día de la semana o franja horaria fuera de rango.");
        }
    }

    public Appointment getAppointment(int dayOfWeek, int timeSlot) {
        if (dayOfWeek >= 0 && dayOfWeek < 5 && timeSlot >= 0 && timeSlot < 10) {
            return calendar.get(dayOfWeek).get(timeSlot);
        } else {
            System.out.println("Error: Día de la semana o franja horaria fuera de rango.");
            return null;
        }
    }

    public String getDate(int index) {
        // Lógica para obtener la fecha formateada en el índice proporcionado
        LocalDate date = LocalDate.now().plusDays(index);
        return date.toString(); // Ejemplo básico, puedes formatear la fecha según tu necesidad
    }

    // Otros métodos del calendario
}

