package ec.edu.espe.medicalappointmentsystem.model;

import ec.edu.espe.medicalappointmentsystem.util.IdValidator;
import java.util.Scanner;

public class Patient {
    
    private String id;
    private String name;
    private int age;
    private String email;
    private String celphone;
    
    public Patient() {
    }

    public Patient(String id, String name, int age, String email, String celphone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.celphone = celphone;
    }

    public Patient(String id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + getId() + ", name=" + getName() + ", age=" + getAge() + ", email=" + getEmail() + '}';
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

public static Patient inputPatientData(Scanner input) {
    String id = "";
    String name = "";
    int age = 0;
    String email = "";

    try {
        System.out.println("==========================================");
        System.out.println("|      Ingrese los datos del Paciente     |");
        System.out.println("==========================================");

        while (true) {
            try {
                System.out.print("| Ingrese el ID del paciente (cédula): ");
                id = input.nextLine().trim();
                IdValidator.idValidator(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada inválida. Ingrese un número válido para el ID.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("| Ingrese el nombre y apellido del paciente: ");
                name = input.nextLine().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("El nombre no puede estar vacío.");
                }
                if (!name.matches("[a-zA-Z ]+")) {
                    throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
                }
                if (name.trim().split("\\s+").length < 2) {
                    throw new IllegalArgumentException("El nombre debe contener al menos dos palabras.");
                }
                name = capitalizeWords(name);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("| Ingrese la edad del paciente: ");
                age = Integer.parseInt(input.nextLine().trim());
                if (age <= 0) {
                    throw new IllegalArgumentException("La edad debe ser un número positivo.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada inválida. Ingrese un número válido para la edad.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("| Ingrese el correo electrónico del paciente: ");
                email = input.nextLine().trim();
                if (!email.contains("@") || !email.contains(".")) {
                    throw new IllegalArgumentException("El correo electrónico no es válido.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("==========================================");
        return new Patient(id, name, age, email);
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        return null;
    }
}

    public void printPatientInfo() {
        System.out.println("==========================================");
        System.out.println("|        Información del Paciente         |");
        System.out.println("==========================================");
        System.out.printf("| ID:         %-30d |\n", getId());
        System.out.printf("| Nombre:     %-30s |\n", getName());
        System.out.printf("| Edad:       %-30d |\n", getAge());
        System.out.printf("| Correo:     %-30s |\n", getEmail());
        System.out.println("==========================================");
    }
    public static String capitalizeWords(String str) {
        String[] words = str.split("\\s+");
        StringBuilder capitalizedStr = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                capitalizedStr.append(Character.toUpperCase(word.charAt(0)))
                               .append(word.substring(1).toLowerCase())
                               .append(" ");
            }
        }
        return capitalizedStr.toString().trim();
    }

    /**
     * @return the celphone
     */
    public String getCelphone() {
        return celphone;
    }

    /**
     * @param celphone the celphone to set
     */
    public void setCelphone(String celphone) {
        this.celphone = celphone;
    }
}