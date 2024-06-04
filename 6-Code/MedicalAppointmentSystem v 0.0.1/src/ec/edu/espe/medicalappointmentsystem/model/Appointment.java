package ec.edu.espe.medicalappointmentsystem.model;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author CommitCrew
 */
public class Appointment {

    private int id;
    private Date dateAppointment;

    public Appointment(int id, Date dateAppointment) {
        this.id = id;
        this.dateAppointment = dateAppointment;
    }

    @Override
    public String toString() {
        return "Appointment{" + "id=" + id + ", dateAppointment=" + dateAppointment + '}';
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the dateAppointment
     */
    public Date getDateAppointment() {
        return dateAppointment;
    }

    /**
     * @param dateAppointment the dateAppointment to set
     */
    public void setDateAppointment(Date dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public static void inputDoctorData() {
        try (Scanner input = new Scanner(System.in)) {
            String continueInput;

            do {
                int id;
                String name;
                String specialty;
                String schedule;

                System.out.println("Enter doctor's the ID:");
                String idStr = input.nextLine();
                id = Integer.parseInt(idStr);

                System.out.println("Enter the doctor's name:");
                name = input.nextLine();

                System.out.println("Select the specialty:");
                specialty = input.nextLine();

                System.out.println("Enter the doctor's schedule:");
                schedule = input.nextLine();

                Doctor doctor = new Doctor(id, name, specialty, schedule);

                System.out.println("New doctor added --> \n" + doctor);

                System.out.println("Do you want to enter another doctor (yes/no):");
                continueInput = input.nextLine();
            } while (continueInput.equalsIgnoreCase("yes"));

        }

    }

}
