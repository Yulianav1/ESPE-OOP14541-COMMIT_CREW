package ec.edu.espe.medicalappointmentsystem.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Doctor {

    private String id;
    private String name;
    private String specialty;
    private String schedule;
    private String education;
    private String email;
    private String cellphone;

    public Doctor(String id, String name, String specialty, String schedule, String education, String email, String cellphone) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.schedule = schedule;
        this.education = education;
        this.email = email;
        this.cellphone = cellphone;
    }
    
        public Doctor(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }


    public Doctor(String name1, String specialty1, String workingHours) {
    }

    @Override
    public String toString() {
        return String.format("Doctor { ID: %d, Nombre: %s, Especialidad: %s, Horario: %s }", getId(), getName(), getSpecialty(), getSchedule());
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
        this.setSchedule(schedule);
    }


    public void printDoctorInfo() {
        System.out.println("==========================================");
        System.out.println("|          Información del Doctor         |");
        System.out.println("==========================================");
        System.out.printf("| ID:        %-30d |\n", getId());
        System.out.printf("| Nombre:    %-30s |\n", getName());
        System.out.printf("| Especialidad: %-30s |\n", getSpecialty());
        System.out.printf("| Horario:   %-30s |\n", getSchedule());
        System.out.println("==========================================");
    }

    /**
     * @return the education
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the cellphone
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * @param cellphone the cellphone to set
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

}
