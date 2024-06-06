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
public class Menu {
  
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Appointment> appointments = new ArrayList<>();
        AppointmentFileManager appointmentFileManager = new AppointmentFileManager();

        int choice;
        do {
            System.out.println("Medical Appointment System");
            System.out.println("1. Create Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Add Doctor");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    

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

 

