package programs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Test
    void testValidPassword() {
        assertEquals(true,Defend_Code.isPasswordValid("AFJSKLF1231!!sd"));
    }
    @Test
    void testInvalidPasswordConsecutiveLetters() {
        assertEquals(false,Defend_Code.isPasswordValid("AFJSKLF1231!!sdfa"));
    }
    @Test
    void testInvalidPasswordLength() {
        assertEquals(false,Defend_Code.isPasswordValid("AS!1saA"));
    }
    @Test
    void testPasswordValidHash() {
        byte[] salter = Defend_Code.salt();
        String firstPass = "AFJSKLF1231!!sd";
        String secondPass = "AFJSKLF1231!!sd";
        byte[] firstPassword = Defend_Code.hash(firstPass, salter);
        Defend_Code.fileWrite(firstPassword);
        byte[] secondPassword = Defend_Code.hash(secondPass, salter);
        byte[] filePass = Defend_Code.fileRead();
        assertEquals(true, Defend_Code.isEqual(filePass, secondPassword));
    }
    @Test
    void testPasswordInvalidHash() {
        byte[] salter = Defend_Code.salt();
        String firstPass = "AFJSKLF1231!!sd";
        String secondPass = "AFJSKLF1231!!sdawa";
        byte[] firstPassword = Defend_Code.hash(firstPass, salter);
        Defend_Code.fileWrite(firstPassword);
        byte[] secondPassword = Defend_Code.hash(secondPass, salter);
        byte[] filePass = Defend_Code.fileRead();
        assertEquals(false, Defend_Code.isEqual(filePass, secondPassword));
    }

    /**
     * Enter a normal value of 15 for getInt method.
     */
    @Test
    void testIntNormal() {
        String userInput = "15";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        assertEquals(15, Defend_Code.getInt());
    }

    /**
     * Enter max value of 2147483647 for getInt method.
     */
    @Test
    void testIntMaxValue() {
        String userInput = "2147483647";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        assertEquals(2147483647, Defend_Code.getInt());
    }

    /**
     * Enter min value of -2147483647 for getInt method.
     */
    @Test
    void testIntMinValue() {
        String userInput = "-2147483647";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        assertEquals(-2147483647, Defend_Code.getInt());
    }

    /**
     * Enter a non-numeric character for getInt method.
     */
    @Test
    void testIntNonNumber() {
        String userInput = "a";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        assertThrows(NoSuchElementException.class, Defend_Code::getInt, "\"a\" should be rejected and ask user for input again");
    }

    /**
     * Enter a number too large for getInt method.
     */
    @Test
    void testIntTooBig() {
        String userInput = "3000000000";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        assertThrows(NoSuchElementException.class, Defend_Code::getInt, "\"3000000000\" should be rejected and ask user for input again");
    }

    /**
     * Enter a number too small for getInt method.
     */
    @Test
    void testIntTooSmall() {
        String userInput = "-3000000000";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        assertThrows(NoSuchElementException.class, Defend_Code::getInt, "\"-3000000000\" should be rejected and ask user for input again");
    }

    /**
     * Enter a number with commas for getInt method.
     */
    @Test
    void testIntCommas() {
        String userInput = "1,050";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        assertThrows(NoSuchElementException.class, Defend_Code::getInt, "\"1,050\" should be rejected and ask user for input again");
    }

    /**
     * Adding 5 and 15
     */
    @Test
    void testAddingNormal() {
        assertEquals("20", Defend_Code.adding(5, 15));
    }

    /**
     * Adding 5 and -15
     */
    @Test
    void testAddingNormalNegative() {
        assertEquals("-10", Defend_Code.adding(5, -15));
    }

    /**
     * Adding 2147483647 and 2147483647, should not overflow.
     */
    @Test
    void testAddingMaxInputs() {
        assertEquals("4294967294", Defend_Code.adding(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    /**
     * Adding -2147483648 and -2147483648, should not underflow.
     */
    @Test
    void testAddingMinInputs() {
        assertEquals("-4294967296", Defend_Code.adding(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    /**
     * Adding 0 and 0.
     */
    @Test
    void testAddingZeros() {
        assertEquals("0", Defend_Code.adding(0, 0));
    }

    /**
     * Multiplying 5 and 15
     */
    @Test
    void testMultiplyingNormal() {
        assertEquals("12", Defend_Code.multiplying(3, 4));
    }

    /**
     * Multiplying 5 and -15
     */
    @Test
    void testMultiplyingNormalNegative() {
        assertEquals("-20", Defend_Code.multiplying(10, -2));
    }

    /**
     * Multiplying 2147483647 and 2147483647, should not overflow.
     */
    @Test
    void testMultiplyingMaxInputs() {
        assertEquals("4611686014132420609", Defend_Code.multiplying(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    /**
     * Multiplying -2147483648 and -2147483648, should not underflow.
     */
    @Test
    void testMultiplyingMinInputs() {
        assertEquals("4611686018427387904", Defend_Code.multiplying(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    /**
     * Multiplying 0 and 0.
     */
    @Test
    void testMultiplyingZeros() {
        assertEquals("0", Defend_Code.multiplying(0, 0));
    }
}
