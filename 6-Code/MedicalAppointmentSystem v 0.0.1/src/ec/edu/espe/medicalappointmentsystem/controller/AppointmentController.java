package ec.edu.espe.medicalappointmentsystem.controller;

import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.util.DateValidator;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import ec.edu.espe.medicalappointmentsystem.model.Patient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author CommitCrew
 */
public class AppointmentController {

    private static List<Appointment> appointments = new ArrayList<>();

    public static void addAppointment(List<Doctor> doctors) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter appointment ID:");
            int id = input.nextInt();
            input.nextLine();

            LocalDate appointmentDate = DateValidator.getValidAppointmentDate();

            Doctor selectedDoctor = Appointment.inputDoctorData(doctors);

            Patient patient = Patient.inputPatientData();

            Appointment appointment = new Appointment(id, appointmentDate, selectedDoctor, patient);

            appointments.add(appointment);

            FileManager.save(appointment.toString(), "appointments");

            System.out.println("Appointment created successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while creating the appointment: " + e.getMessage());
        }
    }

}
