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

    private static List<Appointment> rAppointments;

    public static void menu(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Appointment> appointments = new ArrayList<>();
        List<Doctor> doctors = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();
        Doctor doctor1 = new Doctor(1, "Dr. Samantha Villagomez", "Pediatra", "Martes-Jueves 7h-14h");
        Doctor doctor2 = new Doctor(2, "Dr. Stalin Aguilar", "Medico General", "Lunes-Miércoles 9h-17h");
        doctors.add(doctor1);
        doctors.add(doctor2);

        int choice;
        do {
            System.out.println("Welcome to the Medical Appointment System");
            System.out.println("1. Agendar Cita");
            System.out.println("2. Ver citas registradas");
            System.out.println("3. Agregar doctor");
            System.out.println("4. ver calendario");
            System.out.println("5. Exit");
            System.out.print("Ingrese la operacion a realizar: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    AppointmentController.addAppointment(doctors, appointments, patients, input);
                    break;

                case 2:
                    AppointmentController.viewAppointment(appointments);
                    break;

                case 3:
                    //AppointmentController.addAppointment(doctors);
                    break;

                case 4:
                    Calendar myCalendar = new Calendar();
                    viewCalendar(myCalendar);
                    break;

                case 5:
                    System.out.println("Saliendo del programa... \n");
                    break;

                default:
                    System.out.println("Opción no valida. Ingrese un numero del 1 al 5");
                    break;
            }
        } while (choice != 5);
    }

    public static void viewCalendar(Calendar myCalendar) {
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

    public static List<Appointment> getAppointments() {
        List<Appointment> appointment = null;
        return appointment;
    }

    public static void setAppointments(List<Appointment> appointments) {
        Menu.rAppointments = appointments;
    }
}
