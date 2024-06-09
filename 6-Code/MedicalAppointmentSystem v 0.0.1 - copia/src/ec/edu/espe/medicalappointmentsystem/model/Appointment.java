package ec.edu.espe.medicalappointmentsystem.model;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import ec.edu.espe.medicalappointmentsystem.util.DateValidator;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author CommitCrew
 */
public class Appointment {

    private int id;
    private LocalDate dateAppointment;
    private Doctor doctor;
    private Patient patient;
    private String patientID;

    public Appointment(int id, LocalDate dateAppointment, Doctor doctor, Patient patient, String patientID) {
        this.id = id;
        this.dateAppointment = dateAppointment;
        this.doctor = doctor;
        this.patient = patient;
        this.patientID = patientID;
    }

    public static void addAppointment() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Appointment id: ");
            int id = input.nextInt();
            input.nextLine();

            // Get current date and generate patient ID
            LocalDate dateAppointment = LocalDate.now();
            String patientID = DateValidator.generatePatientID(dateAppointment);

            // Get doctor information
            Doctor doctor = Doctor.inputDoctorData();

            // Get patient information
            Patient patient = Patient.inputPatientData();

            // Create appointment
            Appointment appointment = new Appointment(id, dateAppointment, doctor, patient, patientID);

            // Save appointment to file
            FileManager.save(appointment.toString(), "appointments");

            System.out.println("Appointment created successfully.");
        }
    }

    @Override
    public String toString() {
        return "Appointment{"
                + "id=" + id
                + ", dateAppointment=" + dateAppointment
                + ", doctor=" + doctor
                + ", patient=" + patient
                + ", patientID='" + patientID + '\''
                + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(LocalDate dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public static Doctor inputDoctorData(List<Doctor> doctors) {
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

                // Buscar el doctor seleccionado en la lista
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
