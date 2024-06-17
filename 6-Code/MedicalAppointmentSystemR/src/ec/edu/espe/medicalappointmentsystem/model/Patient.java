package ec.edu.espe.medicalappointmentsystem.model;

import java.util.Scanner;

public class Patient {

    private int id;
    private String name;
    private int age;
    private String email;

    public Patient(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Patient() {
        // Constructor por defecto
    }

    @Override
    public String toString() {
        return String.format("Patient { ID: %d, Name: %s, Age: %d, Email: %s }", id, name, age, email);
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Patient inputPatientData(Scanner input) {
        try {
            System.out.println("==========================================");
            System.out.println("|      Ingrese los datos del Paciente     |");
            System.out.println("==========================================");

            System.out.print("| Ingrese el ID del paciente (cedula): ");
            int id = input.nextInt();
            input.nextLine(); // Limpiar el buffer

            System.out.print("| Ingrese el nombre del paciente: ");
            String name = input.nextLine();

            System.out.print("| Ingrese la edad del paciente: ");
            int age = input.nextInt();
            input.nextLine(); // Limpiar el buffer
            
            System.out.print("| Ingrese el correo electrónico del paciente: ");
            String email = input.nextLine();

            System.out.println("==========================================");

            return new Patient(id, name, age, email);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void printPatientInfo() {
        System.out.println("==========================================");
        System.out.println("|        Información del Paciente         |");
        System.out.println("==========================================");
        System.out.printf("| ID:         %-30d |\n", id);
        System.out.printf("| Nombre:     %-30s |\n", name);
        System.out.printf("| Edad:       %-30d |\n", age);
        System.out.printf("| Correo:     %-30s |\n", email);
        System.out.println("==========================================");
    }
}
