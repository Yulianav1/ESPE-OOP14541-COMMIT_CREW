/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author USUARIO
 */
public class DateValidator {

    private static int appointmentCounter = 1; // Contador para el número de citas

    public static String generatePatientID(LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd")); // Formato: yyyyMMdd
        String patientID = formattedDate + String.format("%04d", appointmentCounter++); // Agregar número de cita (con formato de 4 dígitos)
        return patientID;
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now(); // Obtener la fecha actual
    }

    public static void saveAppointmentToJson(String patientID, LocalDate appointmentDate, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Crear un objeto con la fecha de la cita y la ID del paciente
        AppointmentData appointmentData = new AppointmentData(patientID, appointmentDate);
        String json = gson.toJson(appointmentData);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            System.out.println("Appointment date successfully saved to JSON file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving appointment date to JSON file: " + e.getMessage());
            e.printStackTrace(); // Imprimir el rastro de la pila para depurar
        }
    }

    public static void main(String[] args) {
        LocalDate currentDate = getCurrentDate(); // Obtener la fecha actual
        String patientID = generatePatientID(currentDate); // Generar ID del paciente
        saveAppointmentToJson(patientID, currentDate, "appointment.json"); // Guardar en JSON
    }
}

class AppointmentData {

    private String patientID;
    private LocalDate appointmentDate;

    public AppointmentData(String patientID, LocalDate appointmentDate) {
        this.patientID = patientID;
        this.appointmentDate = appointmentDate;
    }

    public String getPatientID() {
        return patientID;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
}

