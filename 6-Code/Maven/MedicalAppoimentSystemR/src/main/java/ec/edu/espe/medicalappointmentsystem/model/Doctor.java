package ec.edu.espe.medicalappointmentsystem.model;

import java.util.Scanner;

public class Doctor {

    private int id;
    private String name;
    private String specialty;
    private String schedule;

    public Doctor(int id, String name, String specialty, String schedule) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.schedule = schedule;
    }

    public Doctor() {
    }

    @Override
    public String toString() {
        return String.format("Doctor { ID: %d, Nombre: %s, Especialidad: %s, Horario: %s }",
                id, name, specialty, schedule);
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public static Doctor inputDoctorData() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("==========================================");
            System.out.println("|       Ingrese los datos del Doctor       |");
            System.out.println("==========================================");

            System.out.print("| Ingrese el ID del doctor: ");
            int id = input.nextInt();
            input.nextLine();

            System.out.print("| Ingrese el nombre del doctor: ");
            String name = input.nextLine();

            System.out.print("| Ingrese la especialidad del doctor: ");
            String specialty = input.nextLine();

            System.out.print("| Ingrese el horario del doctor: ");
            String schedule = input.nextLine();

            System.out.println("==========================================");

            return new Doctor(id, name, specialty, schedule);
        }
    }

    public void printDoctorInfo() {
        System.out.println("==========================================");
        System.out.println("|          Información del Doctor         |");
        System.out.println("==========================================");
        System.out.printf("| ID:        %-30d |\n", id);
        System.out.printf("| Nombre:    %-30s |\n", name);
        System.out.printf("| Especialidad: %-30s |\n", specialty);
        System.out.printf("| Horario:   %-30s |\n", schedule);
        System.out.println("==========================================");
    }

}
