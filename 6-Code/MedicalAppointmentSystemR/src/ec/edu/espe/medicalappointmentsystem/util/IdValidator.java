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
    public static void idValidator(String numId){
        if (numId.length() == 10){
            
            String regionDigitStr = numId.substring(0,2) ;
            int regionDigitInt = Integer.parseInt(regionDigitStr);
            
            if (regionDigitInt >= 1 && regionDigitInt <=24){
             var lastDigit = numId.substring(9, 10);
             int LastDigitInt = Integer.parseInt(lastDigit);
             int even = Integer.parseInt(numId.substring(1, 2)) + Integer.parseInt(numId.substring(3, 4)) + Integer.parseInt(numId.substring(5, 6)) + Integer.parseInt(numId.substring(7, 8));
             
             String idDigit1Str = numId.substring(0, 1);
             int idDigit1Int= Integer.parseInt(idDigit1Str);
             if (idDigit1Int > 9){
                 idDigit1Int = (idDigit1Int - 9);
             }
             
             String idDigit3Str = numId.substring(2, 3);
             int idDigit3Int= Integer.parseInt(idDigit3Str);
             if (idDigit3Int > 9){
                 idDigit3Int = (idDigit3Int - 9);
             }
             
             String idDigit5Str = numId.substring(4, 5);
             int idDigit5Int= Integer.parseInt(idDigit5Str);
             if (idDigit5Int > 9){
                 idDigit5Int = (idDigit5Int - 9);
             }
             
             String idDigit7Str = numId.substring(6, 7);
             int idDigit7Int= Integer.parseInt(idDigit7Str);
             if (idDigit7Int > 9){
                 idDigit7Int = (idDigit7Int - 9);
             }
             
             String idDigit9Str = numId.substring(0, 1);
             int idDigit9Int= Integer.parseInt(idDigit9Str);
             if (idDigit9Int > 9){
                 idDigit9Int = (idDigit9Int - 9);
             }
             
             int odd = idDigit1Int + idDigit3Int + idDigit5Int + idDigit7Int + idDigit9Int;
             int totalSum = even + odd;
             String firstDigitSumStr = Integer.toString(totalSum).substring(0, 1);
             int dozen = ( (Integer.parseInt(firstDigitSumStr)) + 1) *10 ;
             int validatorDigit = dozen - totalSum;
             
             if(validatorDigit == 10){
                 validatorDigit = 0;
             }
             
             if(validatorDigit == LastDigitInt){
                 System.out.println("La cedula " +numId + "es valida");
             } else {
                 System.out.println(" La cedula " + numId + " no es valida");
             }
            } else {
                System.out.println("La cedula no pertenece a ninguna region. ");
            }
        } else {
            System.out.println("La cedula tiene menos de 10 digitos. ");
        }
    }
}
