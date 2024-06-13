
package ec.edu.espe.medicalappointmentsystem.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */

public class DateValidator {

    public static LocalDate getValidAppointmentDate() {
        Scanner scanner = new Scanner(System.in);
        int year;
        int month;
        int day;

        while (true) {
            try {
                System.out.println("Ingrese el anio de la cita: ");
                year = Integer.parseInt(scanner.nextLine());
                int currentYear = LocalDate.now().getYear();
                if (year < currentYear || year > currentYear + 1) {
                    throw new IllegalArgumentException("El anio debe ser el actual o el siguiente como maximo.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada Invalida. Ingrese un número valido para el año.");
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
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada Invalida. Ingrese un mes valido.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        
        while (true) {
            try {
                System.out.println("Ingrese el dia de la cita: ");
                day = Integer.parseInt(scanner.nextLine());
                LocalDate appointmentDate = LocalDate.of(year, month, day);
                LocalDate today = LocalDate.now();
                LocalDate oneYearFromNow = today.plusYears(1);

                if (appointmentDate.isBefore(today)) {
                    throw new IllegalArgumentException("La fecha no puede ser anterior al día de hoy.");
                }
                if (appointmentDate.isAfter(oneYearFromNow)) {
                    throw new IllegalArgumentException("La fecha debe ser dentro de un anio a partir de hoy.");
                }
                return appointmentDate;
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Ingrese un día valido.");
            } catch (DateTimeParseException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
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
                System.out.println("Entrada inválida. Ingrese un número valido para el intervalo de tiempo.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return timeSlot;
    }
    
    

    public static void saveAppointmentToJson(LocalDate appointmentDate, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(appointmentDate);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            System.out.println("La fecha de la cita se guardó correctamente en el archivo JSON.");
        } catch (IOException e) {
            System.out.println("Se produjo un error al guardar la fecha de la cita en un archivo JSON:" + e.getMessage());
            e.printStackTrace(); 
        }
    }
    
  
}
