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

    public Patient(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", name=" + name + ", age=" + age + '}';
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

    public static Patient inputPatientData(Scanner input) {
        try {
            System.out.println("Enter patient ID:");
            int id = input.nextInt();
            input.nextLine();

            System.out.println("Enter patient name:");
            String name = input.nextLine();

            System.out.println("Enter patient age:");
            int age = input.nextInt();
            input.nextLine();

            return new Patient(id, name, age);
        } catch (Exception e) {
            System.out.println("An error occurred while entering patient data: " + e.getMessage());
            return null;
        }
    }

    public void printPatientInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}
