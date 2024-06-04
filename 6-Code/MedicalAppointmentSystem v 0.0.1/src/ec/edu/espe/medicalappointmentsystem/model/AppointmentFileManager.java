/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * AppointmentFileManager Model
 
 Author: Luis Vaca, TheJavaBandits, DCCO-ESPE
 */
public class AppointmentFileManager {
   
    
    public static void saveAppointment(String data, String fileName) {
        fileName = fileName + ".txt"; // Siempre guardar como archivo de texto
        
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}


