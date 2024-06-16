/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;

import java.util.Scanner;

/**
 *
 * @author CommitCrew
 */
public class Doctor {

    private String Schedule;

    public Doctor(int id, String name, String specialty, String Schedule) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.Schedule = Schedule;
    }

    private int id;
    public String name;
    private String specialty;
    private String schedule;
    public Doctor() {
    }
    @Override
    public String toString() {
        return "Doctor{" + "id=" + id + ", name=" + name + ", specialty=" + specialty + ", Schedule=" + Schedule + '}';
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
     * @return the specialty
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * @param specialty the specialty to set
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     * @return the Schedule
     */
    public String getSchedule() {
        return Schedule;
    }

    /**
     * @param Schedule the Schedule to set
     */
    public void setSchedule(String Schedule) {
        this.Schedule = Schedule;
    }

    public static Doctor inputDoctorData() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter doctor's ID:");
            int id = input.nextInt();
            input.nextLine(); // Consume newline

            System.out.println("Enter the doctor's name:");
            String name = input.nextLine();

            System.out.println("Enter the doctor's specialty:");
            String specialty = input.nextLine();

            System.out.println("Enter the doctor's schedule:");
            String schedule = input.nextLine();

            return new Doctor(id, name, specialty, schedule);
        }
    }

    public void printDoctorInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Specialty: " + specialty);
        System.out.println("Schedule: " + schedule);
    }

}
