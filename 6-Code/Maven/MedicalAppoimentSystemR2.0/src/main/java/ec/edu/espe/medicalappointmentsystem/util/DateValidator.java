package ec.edu.espe.medicalappointmentsystem.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DateValidator {

    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static LocalDate getValidAppointmentDate() {
        LocalDate appointmentDate = null;
        int year = getValidYear();
        int month = getValidMonth(year);
        int day = getValidDay(year, month);

        while (true) {
            System.out.println("Ha ingresado la fecha: " + LocalDate.of(year, month, day) + ". ¿Desea confirmar esta fecha? (si/no): ");
            String confirm = SCANNER.nextLine().trim().toLowerCase();
            if (confirm.equals("si")) {
                appointmentDate = LocalDate.of(year, month, day);
                break;
            } else if (confirm.equals("no")) {
                return getValidAppointmentDate(); // Recursive call for new date
            } else {
                System.out.println("Entrada inválida. Por favor ingrese 'si' o 'no'.");
            }
        }

        return appointmentDate;
    }

    private static int getValidYear() {
        while (true) {
            try {
                System.out.println("Ingrese el año de la cita: ");
                int year = Integer.parseInt(SCANNER.nextLine());
                if (year != CURRENT_DATE.getYear() && year != CURRENT_DATE.getYear() + 1) {
                    throw new IllegalArgumentException("El año debe ser " + CURRENT_DATE.getYear() + " o " + (CURRENT_DATE.getYear() + 1) + ".");
                }
                return year;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número válido para el año.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getValidMonth(int year) {
        while (true) {
            try {
                System.out.println("Ingrese el mes de la cita (1-12): ");
                int month = Integer.parseInt(SCANNER.nextLine());
                if (month < 1 || month > 12) {
                    throw new IllegalArgumentException("El mes debe ser entre 1 y 12.");
                }
                if (year == CURRENT_DATE.getYear() && month < CURRENT_DATE.getMonthValue()) {
                    throw new IllegalArgumentException("El mes no puede ser anterior al mes actual.");
                }
                return month;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un mes válido.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getValidDay(int year, int month) {
        while (true) {
            try {
                System.out.println("Ingrese el día de la cita: ");
                int day = Integer.parseInt(SCANNER.nextLine());
                LocalDate date = LocalDate.of(year, month, day);
                if (date.isBefore(CURRENT_DATE)) {
                    throw new IllegalArgumentException("La fecha no puede ser anterior al día de hoy.");
                }
                return day;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un día válido.");
            } catch (DateTimeParseException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getValidAppointmentTime() {
        while (true) {
            try {
                System.out.println("Seleccione el intervalo de tiempo de la cita (1-5): ");
                System.out.println("1. 7:00 am - 8:30 am");
                System.out.println("2. 8:30 am - 10:00 am");
                System.out.println("3. 10:00 am - 11:30 am");
                System.out.println("4. 11:30 am - 1:00 pm");
                System.out.println("5. 1:00 pm - 2:30 pm");
                int timeSlot = Integer.parseInt(SCANNER.nextLine());
                if (timeSlot < 1 || timeSlot > 5) {
                    throw new IllegalArgumentException("El intervalo de tiempo debe ser entre 1 y 5.");
                }
                return timeSlot;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número válido para el intervalo de tiempo.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
     private static final Map<String, String> TIME_SLOTS = new HashMap<>();

    static {
        TIME_SLOTS.put("1", "07:00 AM - 08:30 AM");
        TIME_SLOTS.put("2", "08:30 AM - 10:00 AM");
        TIME_SLOTS.put("3", "10:00 AM - 11:30 AM");
        TIME_SLOTS.put("4", "11:30 AM - 01:00 PM");
        TIME_SLOTS.put("5", "01:00 PM - 02:30 PM");
    }

    public static String getAppointmentTime(String timeSlot) {
        return TIME_SLOTS.getOrDefault(timeSlot, "Hora no válida");
    }

    public static boolean isDateAfterToday(LocalDate date) {
        return date.isAfter(CURRENT_DATE);
    }
}
