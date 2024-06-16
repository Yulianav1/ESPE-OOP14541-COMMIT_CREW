package ec.edu.espe.medicalappointmentsystem.model;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author CommitCrew
 */
public class Patient {

    public int id;
    public String name;
    public int age;
    public String email;
    
    public Patient() {
        // Constructor por defecto
    }

    public Patient(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
            System.out.println("Ingrese el id (cedula) del paciente:");
            int id = input.nextInt();
            input.nextLine();

            System.out.println("Ingrese el nombre del paciente:");
            String name = input.nextLine();

            System.out.println("Ingrese la edad del paciente:");
            int age = input.nextInt();
            input.nextLine();
            System.out.println("Ingrese el correo electronico al que se le enviara un recordatorio");
            String email = input.nextLine();
            return new Patient(id, name, age, email);
        } catch (Exception e) {
            System.out.println("Un error ha ocurrido: " + e.getMessage());
            return null;
        }
    }

    public void printPatientInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: "+ email);
    }
}
