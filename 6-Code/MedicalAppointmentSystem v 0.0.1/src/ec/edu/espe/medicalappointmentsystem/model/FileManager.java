/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;


import java.util.ArrayList;
import java.util.List;

/**
 * FileManager Model
 * 
 * Author: Luis Vaca, TheJavaBandits, DCCO-ESPE
 */
public class FileManager {
    private List<User> doctorList;
    private List<User> patientList;

    public FileManager() {
        this.doctorList = new ArrayList<>();
        this.patientList = new ArrayList<>();
    }

    // Operations
    public void addUser(User newUser, String userType) {
        if ("doctor".equalsIgnoreCase(userType)) {
            doctorList.add(newUser);
            System.out.println("Added new doctor: " + newUser.getName());
        } else if ("patient".equalsIgnoreCase(userType)) {
            patientList.add(newUser);
            System.out.println("Added new patient: " + newUser.getName());
        } else {
            System.out.println("User type " + userType + " not recognized.");
        }
    }

    public void removeUser(int userID, String userType) {
        List<User> listToRemoveFrom = "doctor".equalsIgnoreCase(userType) ? doctorList : patientList;
        User userToRemove = listToRemoveFrom.stream()
            .filter(user -> user.getId() == userID)
            .findFirst()
            .orElse(null);
        
        if (userToRemove != null) {
            listToRemoveFrom.remove(userToRemove);
            System.out.println("Removed " + userType + ": " + userToRemove.getName());
        } else {
            System.out.println(userType + " with ID " + userID + " not found.");
        }
    }

    // Getters
    public List<User> getDoctorList() {
        return doctorList;
    }

    public List<User> getPatientList() {
        return patientList;
    }
}

