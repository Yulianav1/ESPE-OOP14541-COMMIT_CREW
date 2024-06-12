package ec.edu.espe.medicalappointmentsystem.controller;

import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.util.DateValidator;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import ec.edu.espe.medicalappointmentsystem.model.Patient;
import ec.edu.espe.medicalappointmentsystem.view.Menu;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author CommitCrew
 */
public class AppointmentController {

 public static void addAppointment(List<Doctor> doctors, List<Appointment> appointments, List<Patient> patients) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter appointment ID:");
            int id = input.nextInt();
            input.nextLine();

            // Get appointment date
            System.out.println("Enter Appointment Date (yyyy-MM-dd): ");
            String dateStr = input.nextLine();
            LocalDate dateAppointment = LocalDate.parse(dateStr);

            // Get doctor information
            Doctor selectedDoctor = inputDoctorData(doctors);

            // Get patient information
            Patient patient = Patient.inputPatientData();

            // Create appointment
            Appointment appointment = new Appointment(id, dateAppointment, selectedDoctor, patient);

            appointments.add(appointment); // Add the appointment to the list of appointments

            FileManager.save(appointment.toString(), "appointments");

            System.out.println("Appointment created successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while creating the appointment: " + e.getMessage());
        }
    }

    private static Doctor inputDoctorData(List<Doctor> doctors) {
        try (Scanner input = new Scanner(System.in)) {
            String continueInput;
            Doctor selectedDoctor = null;

            System.out.println("Doctors List:");
            for (Doctor doctor : doctors) {
                doctor.printDoctorInfo();
            }

            do {
                System.out.println("Enter doctor's ID:");
                int selectedId = input.nextInt();
                input.nextLine();

                // Search for the selected doctor in the list
                for (Doctor doctor : doctors) {
                    if (doctor.getId() == selectedId) {
                        selectedDoctor = doctor;
                        break;
                    }
                }

                if (selectedDoctor == null) {
                    System.out.println("Doctor not found. Please enter a valid ID.");
                }

                System.out.println("Do you want to select another doctor (yes/no):");
                continueInput = input.nextLine();
            } while (continueInput.equalsIgnoreCase("yes"));

            return selectedDoctor;
        }
    }

}
