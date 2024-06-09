package ec.edu.espe.medicalappointmentsystem.model;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
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
    public Doctor doctor;
    private Patient patient;

    public Appointment(int id, LocalDate dateAppointment, Doctor doctor, Patient patient) {
        this.id = id;
        this.dateAppointment = dateAppointment;
        this.doctor = doctor;
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Appointment{"
                + "id=" + id
                + ", dateAppointment=" + dateAppointment
                + ", doctor=" + doctor
                + ", patient=" + patient
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

    public String getTime() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
