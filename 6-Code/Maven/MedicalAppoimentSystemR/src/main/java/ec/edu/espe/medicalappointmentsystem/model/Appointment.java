package ec.edu.espe.medicalappointmentsystem.model;

import ec.edu.espe.medicalappointmentsystem.util.DateValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Appointment {
    private String id;
    private LocalDate dateAppointment;
    private int timeSlot;
    private Doctor doctor;
    private Patient patient;
    private boolean emailSent;
    private String hourToAppointment;

    public Appointment() {
    }

    public Appointment(LocalDate dateAppointment, int timeSlot, Doctor doctor, Patient patient) {
        this.id = UUID.randomUUID().toString(); // Generar un ID único
        this.dateAppointment = dateAppointment;
        this.timeSlot = timeSlot;
        this.doctor = doctor;
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", dateAppointment='" + dateAppointment + '\'' +
                ", timeSlot=" + timeSlot +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", emailSent=" + emailSent +
                ", hourToAppointment=" + hourToAppointment +
                '}';
    }

    public String getHourToAppointment() {
        return hourToAppointment;
    }

    public void setHourToAppointment(String hourToAppointment) {
        this.hourToAppointment = hourToAppointment;
    }
    

    public boolean getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(LocalDate dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public static Doctor inputDoctorData(List<Doctor> doctors) {
        Scanner input = new Scanner(System.in);
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


            if (selectedDoctor == null) {
                System.out.println("Doctor not found. Please enter a valid ID.");
            }

            System.out.println("Do you want to select another doctor (yes/no):");
            continueInput = input.nextLine();
        } while (continueInput.equalsIgnoreCase("yes"));

        return selectedDoctor;
    }

    public static Appointment createAppointment(List<Doctor> doctors, Patient patient) {
        LocalDate appointmentDate = DateValidator.getValidAppointmentDate();
        int timeSlot = DateValidator.getValidAppointmentTime();
        Doctor doctor = inputDoctorData(doctors);

        if (doctor != null) {
            return new Appointment(appointmentDate, timeSlot, doctor, patient);
        } else {
            System.out.println("Error: No se seleccionó un doctor válido.");
            return null;
        }
    }
}
