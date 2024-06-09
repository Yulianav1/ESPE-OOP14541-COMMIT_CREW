package ec.edu.espe.medicalappointmentsystem.controller;

import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.util.DateValidator;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import ec.edu.espe.medicalappointmentsystem.model.Patient;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author CommitCrew
 */
public class AppointmentController {

    public static void addAppointment(List<Doctor> doctors, List<Appointment> appointments, List<Patient> patients, Scanner input) {
        try {
            System.out.println("Enter appointment ID:");
            int id = input.nextInt();
            input.nextLine();

            LocalDate appointmentDate = DateValidator.getValidAppointmentDate();

            Doctor selectedDoctor = inputDoctorData(doctors);

            Patient patient = Patient.inputPatientData(input);

            if (patient != null) {
                Appointment appointment = new Appointment(id, appointmentDate, selectedDoctor, patient);

                appointments.add(appointment);

                FileManager.save(appointment.toString(), "appointments");

                System.out.println("Appointment created successfully.");
            } else {
                System.out.println("Failed to create appointment. Patient data was invalid.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while creating the appointment: " + e.getMessage());
        }
    }

    public static void viewAppointment(List<Appointment> appointments) {
        System.out.println("Viewing Appointments:");
        if (appointments.isEmpty()) {
            System.out.println("No appointments registered.");
        } else {
            for (Appointment apt : appointments) {
                System.out.println("Appointment ID: " + apt.getId());
                System.out.println("Doctor: " + apt.getDoctor().getName());
                System.out.println("Specialty: " + apt.getDoctor().getSpecialty());
                System.out.println("Date: " + apt.getDateAppointment());
                System.out.println("Patient: " + apt.getPatient().getName());
                System.out.println("-------------------");
            }
        }
    }

    private static Doctor inputDoctorData(List<Doctor> doctors) {
        Scanner input = new Scanner(System.in);
        System.out.println("Select a doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i).getName() + " - " + doctors.get(i).getSpecialty());
        }
        int choice;
        do {
            System.out.print("Enter your choice: ");
            while (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number corresponding to a doctor:");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine();
        } while (choice < 1 || choice > doctors.size());

        return doctors.get(choice - 1);
    }

  
}
