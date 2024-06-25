package ec.edu.espe.medicalappointmentsystem.controller;

import ec.edu.espe.medicalappointmentsystem.util.DateValidator;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import ec.edu.espe.medicalappointmentsystem.model.Patient;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AppointmentController {

    public static Appointment addAppointment(List<Doctor> doctors, List<Patient> patients, Scanner input) {
        LocalDate appointmentDate = DateValidator.getValidAppointmentDate();
        int timeSlot = DateValidator.getValidAppointmentTime();
        Doctor selectedDoctor = inputDoctorData(doctors, input);
        Patient patient = Patient.inputPatientData(input);

        if (patient != null) {
            LocalDate formattedDate = LocalDate.parse(appointmentDate.toString());
            Appointment appointment = new Appointment(formattedDate, timeSlot, selectedDoctor, patient);
            FileManager.addAndSaveAppointment(appointment);
            System.out.println("Cita creada exitosamente.");
            return appointment;  // Devolver la cita creada
        } else {
            System.out.println("No se pudo crear la cita. Los datos del paciente no eran válidos.");
            return null;  // Devolver null si no se pudo crear la cita
        }
    }

    public static void viewAppointments() {
        List<Appointment> appointments = FileManager.loadAppointments();
        System.out.println("Viendo las citas:");
        if (appointments.isEmpty()) {
            System.out.println("Sin citas registradas.");
        } else {
            for (Appointment apt : appointments) {
                System.out.println("Appointment ID: " + apt.getId());
                System.out.println("Doctor: " + apt.getDoctor().getName());
                System.out.println("Especialidad: " + apt.getDoctor().getSpecialty());
                System.out.println("Fecha: " + apt.getDateAppointment());
                System.out.println("Hora: " + getTimeSlotString(apt.getTimeSlot()));  // Imprimir la franja horaria
                System.out.println("Paciente: " + apt.getPatient().getName());
                System.out.println("-------------------");
            }
        }
    }

    private static Doctor inputDoctorData(List<Doctor> doctors, Scanner input) {
        System.out.println("Seleccionar un doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i).getName() + " - " + doctors.get(i).getSpecialty());
        }
        int choice;
        do {
            System.out.print("Ingrese uno de los números: ");
            while (!input.hasNextInt()) {
                System.out.println("Entrada inválida, ingrese un número correspondiente a un doctor:");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine();
        } while (choice < 1 || choice > doctors.size());

        return doctors.get(choice - 1);
    }

    private static String getTimeSlotString(int timeSlot) {
        switch (timeSlot) {
            case 1: return "7:00 am - 8:30 am";
            case 2: return "8:30 am - 10:00 am";
            case 3: return "10:00 am - 11:30 am";
            case 4: return "11:30 am - 1:00 pm";
            case 5: return "1:00 pm - 2:30 pm";
            default: return "Hora no válida";
        }
    }
}
