/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.controller;
import ec.edu.espe.medicalappointmentsystem.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author USUARIO
 */
public class MedicalAppointmentSystem {
  
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Appointment> appointments = new ArrayList<>();
        AppointmentFileManager appointmentFileManager = new AppointmentFileManager();

        int choice;
        do {
            System.out.println("Medical Appointment System");
            System.out.println("1. Create Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Appointment ID: ");
                    int id = input.nextInt();
                    input.nextLine();  // Consume newline

                    System.out.println("Enter Appointment Date (yyyy-MM-dd): ");
                    String dateStr = input.nextLine();
                    Date dateAppointment;
                    try {
                        dateAppointment = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        break;
                    }

                    // Create appointment
                    Appointment appointment = new Appointment(id, dateAppointment);
                    appointments.add(appointment);

                    // Save appointment to file
                    appointmentFileManager.saveAppointment(appointment.toString(), "appointments");
                    System.out.println("Appointment created successfully.");
                    break;

                case 2:
                    // View appointments
                    System.out.println("Viewing Appointments:");
                    for (Appointment apt : appointments) {
                        System.out.println(apt);
                    }
                    break;

                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                    break;
            }
        } while (choice != 3);
    }
}

 

