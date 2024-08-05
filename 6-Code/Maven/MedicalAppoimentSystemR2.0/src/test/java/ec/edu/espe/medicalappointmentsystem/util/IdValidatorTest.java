/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.util;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author usuar
 */
public class IdValidatorTest {

    @Test
    public void testCalculateAge() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1995, Calendar.MARCH, 15);
        Date birthDate = calendar.getTime();

        int expectedAge = Calendar.getInstance().get(Calendar.YEAR) - 1995;
        if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) < calendar.get(Calendar.DAY_OF_YEAR)) {
            expectedAge--;
        }

        assertEquals(expectedAge, IdValidator.calculateAge(birthDate));
    }

    @Test
    public void testIdValidatorValidId() {
        assertDoesNotThrow(() -> IdValidator.idValidator("0102030405"));
    }

    @Test
    public void testIdValidatorInvalidRegion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> IdValidator.idValidator("2502030405"));
        assertEquals("La cédula no pertenece a ninguna región válida.", exception.getMessage());
    }

    @Test
    public void testIdValidatorInvalidLength() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> IdValidator.idValidator("010203040"));
        assertEquals("La cédula debe tener 10 dígitos.", exception.getMessage());
    }

    @Test
    public void testIdValidatorInvalidChecksum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> IdValidator.idValidator("0102030406"));
        assertEquals("La cédula 0102030406 no es válida.", exception.getMessage());
    }

    @Test
    public void testIdValidatorValidEdgeCase() {
        assertDoesNotThrow(() -> IdValidator.idValidator("0101010101"));
    }

    @Test
    public void testIdValidatorInvalidCharacter() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> IdValidator.idValidator("abcdefghij"));
        assertEquals("La cédula debe tener 10 dígitos.", exception.getMessage());
    }

    @Test
    public void testIdValidatorLeadingZeroes() {
        assertDoesNotThrow(() -> IdValidator.idValidator("0011223345"));
    }

    @Test
    public void testCalculateAgeLeapYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.FEBRUARY, 29);
        Date birthDate = calendar.getTime();

        int expectedAge = Calendar.getInstance().get(Calendar.YEAR) - 2000;
        if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) < calendar.get(Calendar.DAY_OF_YEAR)) {
            expectedAge--;
        }

        assertEquals(expectedAge, IdValidator.calculateAge(birthDate));
    }

    @Test
    public void testIdValidatorInvalidNonNumeric() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> IdValidator.idValidator("01020304a5"));
        assertEquals("La cédula debe tener 10 dígitos.", exception.getMessage());
    }
}
