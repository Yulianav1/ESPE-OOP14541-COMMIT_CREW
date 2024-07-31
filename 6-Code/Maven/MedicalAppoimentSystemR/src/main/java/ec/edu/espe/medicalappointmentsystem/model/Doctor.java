package ec.edu.espe.medicalappointmentsystem.model;

import java.util.ArrayList;
import java.util.Scanner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Doctor {

    private String id;
    private String name;
    private String specialty;
    private String schedule;
    private String education;
    private String email;
    private String cellphone;
    private int appointmentsCount; // Nuevo atributo

    public Doctor() {
    }

    public Doctor(String id, String name, String specialty, String schedule, String education, String email, String cellphone) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.schedule = schedule;
        this.education = education;
        this.email = email;
        this.cellphone = cellphone;
        this.appointmentsCount = 0; // Inicializar el conteo en 0
    }

    public Doctor(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
        this.appointmentsCount = 0; // Inicializar el conteo en 0
    }

    public Doctor(String name1, String specialty1, String workingHours) {
        // Considera agregar el constructor con inicialización para appointmentsCount
    }

    @Override
    public String toString() {
        return String.format("Doctor { ID: %d, Nombre: %s, Especialidad: %s, Horario: %s, Citas: %d }",
                getId(), getName(), getSpecialty(), getSchedule(), getAppointmentsCount());
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public int getAppointmentsCount() {
        return appointmentsCount;
    }

    public void setAppointmentsCount(int appointmentsCount) {
        this.appointmentsCount = appointmentsCount;
    }

    public void printDoctorInfo() {
        System.out.println("==========================================");
        System.out.println("|          Información del Doctor         |");
        System.out.println("==========================================");
        System.out.printf("| ID:        %-30d |\n", getId());
        System.out.printf("| Nombre:    %-30s |\n", getName());
        System.out.printf("| Especialidad: %-30s |\n", getSpecialty());
        System.out.printf("| Horario:   %-30s |\n", getSchedule());
        System.out.printf("| Citas:     %-30d |\n", getAppointmentsCount()); // Imprimir conteo de citas
        System.out.println("==========================================");
    }
}