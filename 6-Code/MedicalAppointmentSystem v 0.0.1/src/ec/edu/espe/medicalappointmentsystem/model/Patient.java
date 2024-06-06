package ec.edu.espe.medicalappointmentsystem.model;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author CommitCrew
 */
public class Patient {
    private int id;
    private String name;
    private int age;
    private Date bornOnDate;

    public Patient(int id, String name, int age, Date bornOnDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bornOnDate = bornOnDate;
    }
    
    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", name=" + name + ", age=" + age + ", bornOnDate=" + bornOnDate + '}';
    }
    
    public static Patient inputPatientData() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter patient ID:");
            int id = input.nextInt();
            input.nextLine(); // Consume newline

            System.out.println("Enter patient name:");
            String name = input.nextLine();

            System.out.println("Enter patient age:");
            int age = input.nextInt();
            input.nextLine(); // Consume newline

            return new Patient(id, name, age, null);
        } catch (Exception e) {
            System.out.println("An error occurred while entering patient data: " + e.getMessage());
            return null;
        }
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the bornOnDate
     */
    public Date getBornOnDate() {
        return bornOnDate;
    }

    /**
     * @param bornOnDate the bornOnDate to set
     */
    public void setBornOnDate(Date bornOnDate) {
        this.bornOnDate = bornOnDate;
    }
}
