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
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 
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

    public static LocalDate getValidAppointmentDate() {
        Scanner scanner = new Scanner(System.in);
        int year;
        int month;
        int day;

        while (true) {
            try {
                System.out.println("Enter the appointment year: ");
                year = Integer.parseInt(scanner.nextLine());
                int currentYear = LocalDate.now().getYear();
                if (year < currentYear || year > currentYear + 1) {
                    throw new IllegalArgumentException("The year must be within the current year or the next year.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Please type a valid number for the year.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Enter the appointment month (1-12): ");
                month = Integer.parseInt(scanner.nextLine());
                if (month < 1 || month > 12) {
                    throw new IllegalArgumentException("The month must be between 1 and 12.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Please type a valid number for the month.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Enter the appointment day: ");
                day = Integer.parseInt(scanner.nextLine());
                LocalDate appointmentDate = LocalDate.of(year, month, day);
                LocalDate today = LocalDate.now();
                LocalDate oneYearFromNow = today.plusYears(1);

                if (appointmentDate.isBefore(today)) {
                    throw new IllegalArgumentException("The date cannot be in the past.");
                }
                if (appointmentDate.isAfter(oneYearFromNow)) {
                    throw new IllegalArgumentException("The date must be within one year from today.");
                }
                return appointmentDate;
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Please type a valid number for the day.");
            } catch (DateTimeParseException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void saveAppointmentToJson(LocalDate appointmentDate, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(appointmentDate);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            System.out.println("Appointment date successfully saved to JSON file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving appointment date to JSON file: " + e.getMessage());
            e.printStackTrace(); // Imprimir el rastro de la pila para depurar
        }
    }
}


