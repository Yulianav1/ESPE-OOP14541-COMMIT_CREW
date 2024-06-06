/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.util;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author USUARIO
 */
public class FileManager {
   
    public static void save(String data, String fileName) {
        String formattedFileName = fileName + ".json";

        try (FileWriter fileWriter = new FileWriter(formattedFileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(data);
            bufferedWriter.newLine();

            System.out.println("Data successfully saved to JSON file.");

        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}


