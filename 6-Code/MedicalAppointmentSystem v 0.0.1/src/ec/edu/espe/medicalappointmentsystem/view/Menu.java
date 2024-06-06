/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.view;

import ec.edu.espe.medicalappointmentsystem.controller.AppointmentController;
import ec.edu.espe.medicalappointmentsystem.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class Menu {

    public static void menu(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Appointment> appointments = new ArrayList<>();
        List<Doctor> doctors = new ArrayList<>();
        Doctor doctor1 = new Doctor(1, "Dr. Samantha Villagomez", "Pediatra", "Martes-Jueves 7h-14h");
        Doctor doctor2 = new Doctor(2, "Dr. Stalin Aguilar", "Médico General", "Lunes-Miércoles 9h-17h");
        doctors.add(doctor1);
        doctors.add(doctor2);
        int choice;
        do {
            System.out.println("Medical Appointment System");
            System.out.println("1. Create Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Add Doctor");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    AppointmentController.addAppointment(doctors);
                    break;

                case 2:
                    // View appointments
                    System.out.println("Viewing Appointments:");
                    for (Appointment apt : appointments) {
                        System.out.println(apt);
                    }
                    break;

                case 3:
                    //AppointmentController.addAppointment(doctors);
                    break;

                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }
        } while (choice != 4);
    }
}
