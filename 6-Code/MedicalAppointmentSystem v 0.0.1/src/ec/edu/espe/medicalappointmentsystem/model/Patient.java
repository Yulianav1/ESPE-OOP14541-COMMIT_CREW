package ec.edu.espe.medicalappointmentsystem.model;

import java.util.Date;

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
