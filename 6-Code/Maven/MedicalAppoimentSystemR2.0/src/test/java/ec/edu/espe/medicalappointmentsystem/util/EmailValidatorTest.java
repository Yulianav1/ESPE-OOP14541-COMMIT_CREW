/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Domenica Villagomez, CommitCrew, DCCO-ESPE
 */
public class EmailValidatorTest {

    @Test
    public void testEmailValidatorValidEmail() {
        assertDoesNotThrow(() -> EmailValidator.emailValidator("john.doe@example.com"));
    }

    @Test
    public void testEmailValidatorInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.emailValidator("john.doe@.com"));
        assertEquals("El correo electrónico john.doe@.com no es válido.", exception.getMessage());
    }

    @Test
    public void testEmailValidatorInvalidEmailNoDomain() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.emailValidator("john.doe@"));
        assertEquals("El correo electrónico john.doe@ no es válido.", exception.getMessage());
    }

    @Test
    public void testEmailValidatorInvalidEmailNoAt() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.emailValidator("john.doeexample.com"));
        assertEquals("El correo electrónico john.doeexample.com no es válido.", exception.getMessage());
    }

    @Test
    public void testEmailValidatorValidEmailWithSubdomain() {
        assertDoesNotThrow(() -> EmailValidator.emailValidator("user@mail.example.com"));
    }

    @Test
    public void testEmailValidatorValidEmailWithPlusSign() {
        assertDoesNotThrow(() -> EmailValidator.emailValidator("user+name@example.com"));
    }

    @Test
    public void testEmailValidatorInvalidEmailWithSpace() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.emailValidator("user name@example.com"));
        assertEquals("El correo electrónico user name@example.com no es válido.", exception.getMessage());
    }

    @Test
    public void testEmailValidatorValidEmailWithHyphen() {
        assertDoesNotThrow(() -> EmailValidator.emailValidator("user-name@example.com"));
    }

    @Test
    public void testEmailValidatorInvalidEmailWithMultipleAt() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.emailValidator("user@@example.com"));
        assertEquals("El correo electrónico user@@example.com no es válido.", exception.getMessage());
    }

    @Test
    public void testEmailValidatorInvalidEmailWithSpecialCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmailValidator.emailValidator("user@exa!mple.com"));
        assertEquals("El correo electrónico user@exa!mple.com no es válido.", exception.getMessage());
    }
}
