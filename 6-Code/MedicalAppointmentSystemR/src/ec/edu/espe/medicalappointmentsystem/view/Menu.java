package ec.edu.espe.medicalappointmentsystem.view;

import ec.edu.espe.medicalappointmentsystem.controller.AppointmentController;
import ec.edu.espe.medicalappointmentsystem.model.*;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void menu(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Doctor> doctors = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();
        List<Appointment> appointments = FileManager.loadAppointments(); // Cargar citas desde el archivo

        Doctor doctor1 = new Doctor(1, "Dr. Samantha Villagomez", "Pediatra", "Martes-Jueves 7h-14h");
        Doctor doctor2 = new Doctor(2, "Dr. Stalin Aguilar", "Medico General", "Lunes-Miércoles 9h-17h");
        doctors.add(doctor1);
        doctors.add(doctor2);

        int choice;
        do {
            System.out.println("==========================================");
            System.out.println("|   Bienvenido al Sistema de Agendamiento de Citas  |");
            System.out.println("==========================================");
            System.out.println("| 1. Agendar Cita                                          |");
            System.out.println("| 2. Ver citas registradas                                 |");
            System.out.println("| 3. Agregar doctor                                        |");
            System.out.println("| 4. Ver calendario                                        |");
            System.out.println("| 5. Salir                                                 |");
            System.out.println("==========================================");
            System.out.print("Ingrese la operación a realizar: ");
            choice = input.nextInt();
            input.nextLine(); 

            switch (choice) {
                case 1:
                    AppointmentController.addAppointment(doctors, patients, input);
                    Reminder.PutReminder();
                    break;

                case 2:
                    AppointmentController.viewAppointments();
                    break;

                case 3:
                    // There are no more doctors for the moment
                    System.out.println("Opcion no autorizada por el momento1");
                    break;

                case 4:
                    Calendar myCalendar = new Calendar();
                    viewCalendar(myCalendar);
                    break;

                case 5:
                    System.out.println("Saliendo del programa... \n");
                    break;

                default:
                    System.out.println("Opción no válida. Ingrese un número del 1 al 5");
                    break;
            }
        } while (choice != 5);
    }

    public static void viewCalendar(Calendar myCalendar) {
        System.out.println("==========================================");
        System.out.println("|           Viendo el Calendario          |");
        System.out.println("==========================================");
        for (int i = 0; i < 5; i++) {
            System.out.println("Día " + (i + 1) + ":");
            for (int j = 0; j < 10; j++) {
                Appointment apt = myCalendar.getAppointment(i, j);
                if (apt != null) {
                    System.out.println("  Franja horaria " + (j + 1) + ": " + apt.getDoctor().getName());
                } else {
                    System.out.println("  Franja horaria " + (j + 1) + ": Sin cita");
                }
            }
            System.out.println("-------------------");
        }
    }

    public static List<Appointment> getAppointments() {
        return FileManager.loadAppointments();
    }

    public static void setAppointments(List<Appointment> appointments) {
        FileManager.saveAppointments(appointments);
    }
}
