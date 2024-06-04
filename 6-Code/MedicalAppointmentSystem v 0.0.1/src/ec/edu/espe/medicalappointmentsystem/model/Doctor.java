/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;

/**
 *
 * @author CommitCrew
 */
public class Doctor {

    public Doctor(int id, String name, String specialty, String Schedule) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.Schedule = Schedule;
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
    private int id;
    private String name;
    private String specialty;
    private String Schedule;
    
}
