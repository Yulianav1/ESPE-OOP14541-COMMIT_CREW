package ec.edu.espe.medicalappointmentsystem.controller;

import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.util.DateValidator;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import ec.edu.espe.medicalappointmentsystem.model.Patient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class AppointmentController {

public static void addAppointment(List<Doctor> doctors, List<Appointment> appointments, List<Patient> patients, Scanner input) {
        try {
          
            LocalDate appointmentDate = DateValidator.getValidAppointmentDate();

            Doctor selectedDoctor = inputDoctorData(doctors);

            Patient patient = Patient.inputPatientData(input);

            if (patient != null) {
                // Convertir LocalDate a String antes de crear la cita
                String formattedDate = appointmentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
                Appointment appointment = new Appointment(formattedDate, selectedDoctor, patient); 

                appointments.add(appointment);

                FileManager.save(appointment.toString(), "appointments");

                System.out.println("Cita creada exitosamente.");
            } else {
                System.out.println("No se pudo crear la cita. Los datos del paciente no eran válidos.");
            }
        } catch (Exception e) {
            System.out.println("Se produjo un error al crear la cita: " + e.getMessage());
        }
    }
    public static void viewAppointment(List<Appointment> appointments) {
        System.out.println("Viendo las citas:");
        if (appointments.isEmpty()) {
            System.out.println("Sin citas registradas.");
        } else {
            for (Appointment apt : appointments) {
                System.out.println("Appointment ID: " + apt.getId());
                System.out.println("Doctor: " + apt.getDoctor().getName());
                System.out.println("Especialidad: " + apt.getDoctor().getSpecialty());
                System.out.println("Fecha: " + apt.getDateAppointment());
                System.out.println("Paciente: " + apt.getPatient().getName());
                System.out.println("-------------------");
            }
        }
    }

    private static Doctor inputDoctorData(List<Doctor> doctors) {
        Scanner input = new Scanner(System.in);
        System.out.println("Seleccionar un doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i).getName() + " - " + doctors.get(i).getSpecialty());
        }
        int choice;
        do {
            System.out.print("Ingrese uno de los nuemros: ");
            while (!input.hasNextInt()) {
                System.out.println("Entrada invalida, ingrese un numero correspondiente a un doctor:");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine();
        } while (choice < 1 || choice > doctors.size());

        return doctors.get(choice - 1);
    }
}
