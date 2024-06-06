
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

<<<<<<< HEAD
   private static List<Appointment> appointments = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static Calendar calendar = new Calendar();

    public static void main(String[] args) {
        menu(); // Llamar al método menu() sin pasar argumentos
    }

    public static void menu() {
        Scanner input = new Scanner(System.in);

        // Agregar doctores de ejemplo
=======
    private static List<Appointment> appointments;
public static void menu(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Appointment> appointments = new ArrayList<>();
        List<Doctor> doctors = new ArrayList<>();

>>>>>>> 01175e5c77cab827d1ea4ae9289445dd371866fa
        Doctor doctor1 = new Doctor(1, "Dr. Samantha Villagomez", "Pediatra", "Martes-Jueves 7h-14h");
        Doctor doctor2 = new Doctor(2, "Dr. Stalin Aguilar", "Médico General", "Lunes-Miércoles 9h-17h");
        doctors.add(doctor1);
        doctors.add(doctor2);

        int choice;
        do {
            System.out.println("Medical Appointment System");
            System.out.println("1. Create Appointment");
<<<<<<< HEAD
            System.out.println("2. Add Doctor");
            System.out.println("3. View Calendar");
            System.out.println("4. Exit");
=======
            System.out.println("2. View Appointments");
            System.out.println("3. Add Doctor");
            System.out.println("4. View Calendar");
            System.out.println("5. Exit");
>>>>>>> 01175e5c77cab827d1ea4ae9289445dd371866fa
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    AppointmentController.addAppointment(doctors, appointments);
                    break;

                case 2:
<<<<<<< HEAD
                    addDoctor();
=======
                    viewAppointments();
>>>>>>> 01175e5c77cab827d1ea4ae9289445dd371866fa
                    break;

                case 3:
                    viewCalendar(calendar);
                    break;

                case 4:
<<<<<<< HEAD
                    System.out.println("Exiting program. Goodbye!");
                    break;
<<<<<<< HEAD
=======
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
>>>>>>> 01175e5c77cab827d1ea4ae9289445dd371866fa

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        } while (choice != 4);
    }

<<<<<<< HEAD
    private static void addDoctor() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Doctor ID:");
        int id = input.nextInt();
        input.nextLine();
        System.out.println("Enter Doctor Name:");
        String name = input.nextLine();
        System.out.println("Enter Doctor Specialty:");
        String specialty = input.nextLine();
        System.out.println("Enter Doctor Schedule:");
        String schedule = input.nextLine();

        Doctor doctor = new Doctor(id, name, specialty, schedule);
        doctors.add(doctor);

        System.out.println("Doctor added successfully.");
    }

    public static void viewCalendar(Calendar myCalendar) {
=======

    

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
>>>>>>> 01175e5c77cab827d1ea4ae9289445dd371866fa
        // Mostrar el calendario
        System.out.println("Viewing Calendar:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Day " + (i + 1) + ":");
            for (int j = 0; j < 10; j++) {
                Appointment apt = myCalendar.getAppointment(i, j);
                if (apt != null) {
                    System.out.println("Time Slot " + (j + 1) + ": " + apt.getDoctor().getName());
                } else {
                    System.out.println("Time Slot " + (j + 1) + ": No Appointment");
                }
            }
            System.out.println("-------------------");
        }
    }
<<<<<<< HEAD
=======

    public static List<Appointment> getAppointments() {
        List<Appointment> appointment = null;
        return appointment;
    }

    public static void setAppointments(List<Appointment> appointments) {
        Menu.appointments = appointments;
    }
>>>>>>> 01175e5c77cab827d1ea4ae9289445dd371866fa
}