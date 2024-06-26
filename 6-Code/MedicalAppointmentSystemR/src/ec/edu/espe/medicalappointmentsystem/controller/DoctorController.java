package ec.edu.espe.medicalappointmentsystem.controller;

import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
import ec.edu.espe.medicalappointmentsystem.util.IdValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Domenica Villagomez, CommitCrew, DCCO-ESPE
 */
public class DoctorController {

    private List<Doctor> doctorList;
    private static final String FILE_NAME = "Doctors.json";

    public DoctorController() {
        this.doctorList = new ArrayList<>();
        Doctor doctor1 = new Doctor(1, "Dr. Samantha Villagomez", "Pediatra", "Martes-Jueves 7h-14h");
        Doctor doctor2 = new Doctor(2, "Dr. Stalin Aguilar", "Medico General", "Lunes-Miércoles 9h-17h");
        doctorList.add(doctor1);
        doctorList.add(doctor2);

        FileManager.saveDoctorsToFile(doctorList, FILE_NAME);
    }

    public void addDoctor(Doctor doctor) {
        doctorList.add(doctor);
        FileManager.saveDoctorsToFile(doctorList, FILE_NAME);
    }

    public void addDoctor() {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        String name = "";
        String specialty = "";
        String schedule = "";
        try {

            while (true) {
                try {
                    System.out.print("| Ingrese el ID del doctor (cedula): ");
                    id = Integer.parseInt(scanner.nextLine());
                    String numId = Integer.toString(id);
                    IdValidator.idValidator(numId);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Entrada inválida. Ingrese un número válido para el ID.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            try {
                System.out.print("Ingrese el nombre del doctor: ");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("El nombre no puede estar vacío.");
                }

                System.out.println("Seleccione la especialidad: ");
                System.out.println("1. Cardiología");
                System.out.println("2. Dermatología");
                System.out.println("3. Neurología");
                System.out.println("4. Pediatría");
                System.out.println("5. General");

                int specialtyOption = scanner.nextInt();
                scanner.nextLine(); 

                switch (specialtyOption) {
                    case 1:
                        specialty = "Cardiología";
                        break;
                    case 2:
                        specialty = "Dermatología";
                        break;
                    case 3:
                        specialty = "Neurología";
                        break;
                    case 4:
                        specialty = "Pediatría";
                        break;
                    case 5:
                        specialty = "General";
                        break;
                    default:
                        throw new IllegalArgumentException("Opción no válida para especialidad.");
                }

                System.out.println("Ingrese el horario disponible: ");
                System.out.println("1. Lunes, Miércoles, Viernes 13pm - 19pm");
                System.out.println("2. Martes, Viernes, Domingo 9am - 5pm");
                System.out.println("3. Lunes, Jueves, Sábado 10am - 17pm");

                int scheduleOption = scanner.nextInt();
                switch (scheduleOption) {
                    case 1:
                        schedule = "Lunes, Miércoles, Viernes 13pm - 19pm";
                        break;
                    case 2:
                        schedule = "Martes, Viernes, Domingo 9am - 5pm";
                        break;
                    case 3:
                        schedule = "Lunes, Jueves, Sábado 10am - 17pm";
                        break;
                    default:
                        throw new IllegalArgumentException("Opción no válida para horario.");
                }

                Doctor newDoctor = new Doctor(id, name, specialty, schedule);
                addDoctor(newDoctor);
                System.out.println("Doctor agregado exitosamente!");

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public void viewDoctors() {
        if (doctorList.isEmpty()) {
            System.out.println("No hay doctores registrados.");
        } else {
            for (Doctor doctor : doctorList) {
                System.out.println("ID: " + doctor.getId());
                System.out.println("Nombre: " + doctor.getName());
                System.out.println("Especialidad: " + doctor.getSpecialty());
                System.out.println("Horario: " + doctor.getSchedule());
                System.out.println("---------------------------");
            }
        }
    }

}
