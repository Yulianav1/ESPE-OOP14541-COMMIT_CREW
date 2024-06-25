package ec.edu.espe.medicalappointmentsystem.model;

import ec.edu.espe.medicalappointmentsystem.util.IdValidator;
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
        return "Patient{" + "id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + '}';
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
        int id = 0;
        String name = "";
        int age = 0;
        String email = "";

        try {
            System.out.println("==========================================");
            System.out.println("|      Ingrese los datos del Paciente     |");
            System.out.println("==========================================");

            while (true) {
                try {
                    System.out.print("| Ingrese el ID del paciente (cedula): ");
                    id = Integer.parseInt(input.nextLine());
                    String numId = Integer.toString(id);
                    IdValidator.idValidator(numId);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Entrada inválida. Ingrese un número válido para el ID.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Ingreso del nombre del paciente
            while (true) {
                try {
                    System.out.print("| Ingrese el nombre del paciente: ");
                    name = input.nextLine();
                    if (name.isEmpty()) {
                        throw new IllegalArgumentException("El nombre no puede estar vacío.");
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            
            while (true) {
                try {
                    System.out.print("| Ingrese la edad del paciente: ");
                    age = Integer.parseInt(input.nextLine());
                    if (age <= 0) {
                        throw new IllegalArgumentException("La edad debe ser un número positivo.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Entrada inválida. Ingrese un número válido para la edad.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Ingreso del correo electrónico del paciente
            while (true) {
                try {
                    System.out.print("| Ingrese el correo electrónico del paciente: ");
                    email = input.nextLine();
                    if (!email.contains("@") || !email.contains(".")) {
                        throw new IllegalArgumentException("El correo electrónico no es válido.");
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

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

