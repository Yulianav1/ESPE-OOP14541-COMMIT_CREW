package ec.edu.espe.medicalappointmentsystem.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Domenica Villagomez, CommitCrew, DCCO-ESPE
 */

public class EmailValidator {

    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@(.+)$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static void emailValidator(String email) throws IllegalArgumentException {
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("El correo electrónico " + email + " no es válido.");
        }
    }
}