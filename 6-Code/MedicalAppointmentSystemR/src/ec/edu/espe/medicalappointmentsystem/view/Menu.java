package ec.edu.espe.medicalappointmentsystem.view;

import ec.edu.espe.medicalappointmentsystem.model.*;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
import ec.edu.espe.medicalappointmentsystem.util.Reminder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Doctor> doctors = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();
        List<Appointment> appointments = FileManager.loadAppointments(); // Cargar citas desde el archivo

        Doctor doctor1 = new Doctor(1, "Dr. Samantha Villagomez", "Pediatra", "Martes-Jueves 7h-14h");
        Doctor doctor2 = new Doctor(2, "Dr. Stalin Aguilar", "Medico General", "Lunes-Miércoles 9h-17h");
        doctors.add(doctor1);
        doctors.add(doctor2);

        Calendar myCalendar = new Calendar(); // Suponiendo que ya tienes una instancia válida de Calendar

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
   
    if (patients.isEmpty()) {
        System.out.println("No hay pacientes registrados. Agregue pacientes primero.");
    } else {
        Appointment newAppointment = Appointment.createAppointment(doctors, patients.get(0));
        if (newAppointment != null) {
            appointments.add(newAppointment);
            Reminder.putReminder(); // Llama a putReminder sin argumentos
        }
    }
    break;

                case 2:
                    viewAppointments(appointments);
                    break;

                case 3:
                    addDoctor(doctors);
                    break;

                case 4:
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
        System.out.println("|           Calendario                   |");
        System.out.println("==========================================");

        // Mostrar calendario para los próximos 5 días
        for (int i = 0; i < 5; i++) {
            System.out.println("Fecha " + myCalendar.getDate(i) + ":"); // Corregido método getDate(i)
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

    public static void viewAppointments(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            System.out.println("Citas registradas:");
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }

    public static void addDoctor(List<Doctor> doctors) {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese el ID del doctor: ");
        int id = input.nextInt();
        input.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese el nombre del doctor: ");
        String name = input.nextLine();

        System.out.print("Ingrese la especialidad del doctor: ");
        String specialty = input.nextLine();

        System.out.print("Ingrese el horario del doctor: ");
        String schedule = input.nextLine();

        Doctor newDoctor = new Doctor(id, name, specialty, schedule);
        doctors.add(newDoctor);

        System.out.println("Doctor agregado exitosamente:");
        newDoctor.printDoctorInfo();
    }
}













