/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.util;

/**
 *
 * @author Domenics Villagomez CommitCrew, DCCO-ESPE
 */
public class IdValidator {

    public static void idValidator(String numId) throws IllegalArgumentException {
        // Eliminar espacios en blanco alrededor del ID
        numId = numId.trim();
        
        // Verificar si la cédula tiene 10 dígitos
        if (numId.length() == 10) {
            String regionDigitStr = numId.substring(0, 2);
            int regionDigitInt = Integer.parseInt(regionDigitStr);

            // Verificar si la cédula pertenece a una región válida
            if (regionDigitInt >= 1 && regionDigitInt <= 24) {
                String lastDigitStr = numId.substring(9, 10);
                int lastDigitInt = Integer.parseInt(lastDigitStr);

                // Sumar los dígitos en posiciones pares
                int evenSum = Integer.parseInt(numId.substring(1, 2)) +
                              Integer.parseInt(numId.substring(3, 4)) +
                              Integer.parseInt(numId.substring(5, 6)) +
                              Integer.parseInt(numId.substring(7, 8));

                // Sumar los dígitos en posiciones impares, aplicando las reglas específicas
                int oddSum = 0;
                for (int i = 0; i < 9; i += 2) {
                    int oddDigit = Integer.parseInt(numId.substring(i, i + 1)) * 2;
                    if (oddDigit > 9) {
                        oddDigit -= 9;
                    }
                    oddSum += oddDigit;
                }

                // Calcular el dígito verificador
                int totalSum = evenSum + oddSum;
                int firstDigitSum = Integer.parseInt(Integer.toString(totalSum).substring(0, 1));
                int validatorDigit = ((firstDigitSum + 1) * 10) - totalSum;

                // Ajustar el dígito verificador si es necesario
                if (validatorDigit == 10) {
                    validatorDigit = 0;
                }

                // Verificar si el dígito verificador coincide
                if (validatorDigit != lastDigitInt) {
                    throw new IllegalArgumentException("La cédula " + numId + " no es válida.");
                }
            } else {
                throw new IllegalArgumentException("La cédula no pertenece a ninguna región válida.");
            }
        } else {
            throw new IllegalArgumentException("La cédula debe tener 10 dígitos.");
        }
    }
}