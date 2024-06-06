/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author CommitCrew
 */
public class Specialty {
    
        
    private String specialtyName;
    private String description;

    public Specialty(String specialtyName, String description) {
        this.specialtyName = specialtyName;
        this.description = description;
    }
    public void displayAvailableSchedules(){
        int received=0;
        switch(received){
            case 1 : {
                System.out.println("The available hours are: from 7:00 am to 8:30 am");
            }break;
            case 2 : {
                System.out.println("The available hours are: from 8:30 am to 10:00 am");
            }break;
            case 3 : {
                System.out.println("The available hours are: from 10:00 am to 11:30 am");
            }break;
            case 4 : {
                System.out.println("The available hours are: from 11:30 am to 1:00 pm");
            }break;
            case 5 : {
                System.out.println("The available hours are: from 1:00 pm to 2:30 pm");
            }break;
        }
    }
    @Override
    public String toString() {
        return "Specialty{" + "specialtyName=" + specialtyName + ", description=" + description + '}';
    }
    
    /**
     * @return the specialtyName
     */
    public String getSpecialtyName() {
        return specialtyName;
    }

    /**
     * @param specialtyName the specialtyName to set
     */
    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
