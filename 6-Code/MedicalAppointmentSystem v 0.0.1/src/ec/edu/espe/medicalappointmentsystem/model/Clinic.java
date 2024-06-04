/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;

/**
 *
 * @author CommitCrew
 */
public class Clinic {

    public Clinic(String name, String adress) {
        this.name = name;
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Clinic{" + "name=" + name + ", adress=" + adress + '}';
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
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }
    private String name;
    private String adress;
    
}
