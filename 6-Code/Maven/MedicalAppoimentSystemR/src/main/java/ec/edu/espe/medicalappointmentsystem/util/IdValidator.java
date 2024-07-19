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
        numId = numId.trim();
        
        if (numId.length() == 10) {
            String regionDigitStr = numId.substring(0, 2);
            int regionDigitInt = Integer.parseInt(regionDigitStr);

            if (regionDigitInt >= 1 && regionDigitInt <= 24) {
                String lastDigitStr = numId.substring(9, 10);
                int lastDigitInt = Integer.parseInt(lastDigitStr);

                int evenSum = Integer.parseInt(numId.substring(1, 2)) +
                              Integer.parseInt(numId.substring(3, 4)) +
                              Integer.parseInt(numId.substring(5, 6)) +
                              Integer.parseInt(numId.substring(7, 8));

                int oddSum = 0;
                for (int i = 0; i < 9; i += 2) {
                    int oddDigit = Integer.parseInt(numId.substring(i, i + 1)) * 2;
                    if (oddDigit > 9) {
                        oddDigit -= 9;
                    }
                    oddSum += oddDigit;
                }

                int totalSum = evenSum + oddSum;
                int firstDigitSum = Integer.parseInt(Integer.toString(totalSum).substring(0, 1));
                int validatorDigit = ((firstDigitSum + 1) * 10) - totalSum;

                if (validatorDigit == 10) {
                    validatorDigit = 0;
                }

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