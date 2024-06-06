
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.view;

import ec.edu.espe.medicalappointmentsystem.controller.AppointmentController;
import ec.edu.espe.medicalappointmentsystem.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class Menu {

    private static List<Appointment> appointments;
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
            System.out.println("4. View Calendar");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    AppointmentController.addAppointment(doctors, appointments);
                    break;

                case 2:
                    viewAppointments();
                    break;

                case 3:
                    //AppointmentController.addAppointment(doctors);
                    break;

                case 4:
<<<<<<< HEAD
                    System.out.println("Exiting program. Goodbye!");
                    break;
                case 5:
                    Calendar myCalendar = new Calendar(); 
                    viewCalendar(myCalendar); 
=======
                    Calendar myCalendar = new Calendar(); // Crear una instancia de Calendar
                    viewCalendar(myCalendar); // Llamar a viewCalendar() pasando la instancia de Calendar
>>>>>>> dafb916965bfe88299f2d141aa5b0322a79a91b2
                    break;

                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        } while (choice != 5);
    }


    

    public static void viewAppointments() {
        // Mostrar las citas
        System.out.println("Viewing Appointments:");
        for (Appointment apt : appointments) {
            System.out.println("Doctor: " + apt.getDoctor().getName());
            System.out.println("Date: " + apt.getDateAppointment());
            System.out.println("Time: " + apt.getTime());
            System.out.println("-------------------");
        }
    }

     public static void viewCalendar(Calendar myCalendar) { // Modificar la firma del método para aceptar un objeto Calendar
        // Mostrar el calendario
        System.out.println("Viewing Calendar:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Day " + (i + 1) + ":");
            for (int j = 0; j < 10; j++) {
                Appointment apt = myCalendar.getAppointment(i, j); // Llamar al método getAppointment() en la instancia de Calendar
                if (apt != null) {
                    System.out.println("Time Slot " + (j + 1) + ": " + apt.getDoctor().getName());
                } else {
                    System.out.println("Time Slot " + (j + 1) + ": No Appointment");
                }
            }
            System.out.println("-------------------");
        }
    }

    public static List<Appointment> getAppointments() {
        List<Appointment> appointment = null;
        return appointment;
    }

    public static void setAppointments(List<Appointment> appointments) {
        Menu.appointments = appointments;
    }
}