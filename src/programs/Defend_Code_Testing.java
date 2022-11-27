package programs;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
public class Defend_Code_Testing {


    /**
     * This tests name validation. The case which is valid.
     */
    @Test
    void testFirstNameValid() {
        String result = Defend_Code.nameValidation("Jake");
        assertEquals("Valid", result);
    }

    /**
     * This tests for valid name with spaces.
     */
    @Test
    void testFirstNameValidSpaces() {
        String result = Defend_Code.nameValidation("Jake  ");
        assertEquals("Valid", result);
    }

    /**
     * This tests for valid name with numbers.
     */
    @Test
    void testFirstNameInvalid() {
        String result = Defend_Code.nameValidation("Jake123");
        assertEquals("Invalid", result);
    }

    /**
     * This tests for name validation out of character length.
     */
    @Test
    void testFirstNameValidLength50() {
        //length 50
        String result = Defend_Code.nameValidation("JakeJakealkdfjalkkffdjakjfljjkklafkakjkljaadfafdad");
        assertEquals("Valid", result);
    }

    /**
     * Name validation out of character length.
     */
    @Test
    void testFirstNameInvalidLength50() {
        //length 51
        String result = Defend_Code.nameValidation("JakeJakealkdfjalkkffdjakjfljjkklafkakjkljaadfafdada");
        assertEquals("Invalid", result);
    }

    /**
     * File validation
     */
    @Test
    void testFileValid() {
        String result = Defend_Code.fileValidation("input.txt");
        assertEquals("Valid", result);
    }

    /**
     * File validation with different extension.
     */
    @Test
    void testFileInvalid() {
        String result = Defend_Code.fileValidation("input.txxt");
        assertEquals("Invalid", result);
    }

    /**
     * File validation if file doesn't exist.
     */
    @Test
    void testFileInvalidFileDoesntExist() {
        String result = Defend_Code.fileValidation("inputt.txt");
        assertEquals("File doesn't exist", result);
    }






}
