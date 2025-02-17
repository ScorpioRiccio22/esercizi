package primo.TestParametrico;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailValidatorTest {

    private final EmailValidator emailValidator = new EmailValidator();

    @ParameterizedTest
    @ValueSource(strings = {
        "test@example.it",
        "user.name+tag+sorting@example.com",
        "user-name@example.co.uk",
        "user@example.org",
        "user.name@subdomain.example.com"
    })
    public void testValidEmail(String email) {
        assertTrue(emailValidator.isValidEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "plainaddress",
        "@missingusername.com",
        "username@.com",
        "username@.missingTLD",
        "username@domain..com"
    })
    public void testInvalidEmail(String email) {
        assertFalse(emailValidator.isValidEmail(email));
    }

    @ParameterizedTest
    @CsvSource({
        "test@example.com, true",
        "invalid@ site.com, false",
        "another.test@example.org, true",
        "example@subdomain.example.com, true",
        "bad@website, false",
        "@nouser.com, false"
    })
    public void testEmailCsvInterno(String email, boolean expected) {
        assertEquals(expected, emailValidator.isValidEmail(email));
    }
    
    @ParameterizedTest
    @CsvFileSource(resources= "/Email.csv",numLinesToSkip= 1)
    public void testEmailCsvEsterno(String email, boolean expected) {
        assertEquals(expected, emailValidator.isValidEmail(email));
    }
}
    
    
    
    