package ec.edu.espe.medicalappointmentsystem.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateValidator {

    public static LocalDate getValidAppointmentDate() {
        Scanner scanner = new Scanner(System.in);
        int year;
        int month;
        int day;
        LocalDate appointmentDate = null;
        LocalDate currentDate = LocalDate.now();

        while (true) {
            try {
                System.out.println("Ingrese el año de la cita: ");
                year = Integer.parseInt(scanner.nextLine());
                if (year < 2024) {
                    throw new IllegalArgumentException("El año debe ser 2024 o mayor.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número válido para el año.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Ingrese el mes de la cita (1-12): ");
                month = Integer.parseInt(scanner.nextLine());
                if (month < 1 || month > 12) {
                    throw new IllegalArgumentException("El mes debe ser entre 1 y 12.");
                }
                if (year == currentDate.getYear() && month < currentDate.getMonthValue()) {
                    throw new IllegalArgumentException("El mes no puede ser anterior al mes actual.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un mes válido.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Ingrese el día de la cita: ");
                day = Integer.parseInt(scanner.nextLine());
                appointmentDate = LocalDate.of(year, month, day);
                if (appointmentDate.isBefore(LocalDate.now())) {
                    throw new IllegalArgumentException("La fecha no puede ser anterior al día de hoy.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un día válido.");
            } catch (DateTimeParseException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Ha ingresado la fecha: " + appointmentDate.toString() + ". ¿Desea confirmar esta fecha? (si/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (confirm.equals("si")) {
                return appointmentDate;
            } else if (confirm.equals("no")) {
                return getValidAppointmentDate();
            } else {
                System.out.println("Entrada inválida. Por favor ingrese 'si' o 'no'.");
            }
        }
    }

    public static int getValidAppointmentTime() {
        Scanner scanner = new Scanner(System.in);
        int timeSlot;

        while (true) {
            try {
                System.out.println("Seleccione el intervalo de tiempo de la cita (1-5): ");
                System.out.println("1. 7:00 am - 8:30 am");
                System.out.println("2. 8:30 am - 10:00 am");
                System.out.println("3. 10:00 am - 11:30 am");
                System.out.println("4. 11:30 am - 1:00 pm");
                System.out.println("5. 1:00 pm - 2:30 pm");
                timeSlot = Integer.parseInt(scanner.nextLine());
                if (timeSlot < 1 || timeSlot > 5) {
                    throw new IllegalArgumentException("El intervalo de tiempo debe ser entre 1 y 5.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número válido para el intervalo de tiempo.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return timeSlot;
    }
}
