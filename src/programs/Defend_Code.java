package programs;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;


public class Defend_Code {

    static Scanner scan = new Scanner(System.in);
    static SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
//        inputFile();
        Password();
    }

    public static void name(){
        System.out.println("Enter a name and last name, both at most 50 characters \n " +
                "comma to split last and first name");
        System.out.println("Valid characters are [A-Z][a-z]");
        System.out.println("Example: Arashpreet, Pandher");
        String name = scan.nextLine();
        String output = nameValidation(name);
        System.out.println(output);



    }
    public static String nameValidation(String input){
        String pattern = "^[a-zA-z]{0,50}(\\s){0,},?(\\s){0,}[a-zA-z]{0,50}(\\s){0,50}$";
        Pattern pat = Pattern.compile(pattern);

        Matcher m = pat.matcher(input);

        if(m.find()){
            return "Valid";
        }
        else{
            return "Reenter";
        }
    }


    public static void inputFile(){
        System.out.println("Enter file used for input, must end in txt extension. Include directory" +
                "\nexample: C:Windows\\System32\\system\\input.txt");
        String inputFile = scan.nextLine();
        String output = inputFileValidation(inputFile);
        System.out.println(output);

        //check if file exists
        File f = new File(inputFile);

        if(f.exists()){
            System.out.println("File exists");
        }
        else{
            System.out.println("File doesn't exist");

        }

    }

    public static String inputFileValidation(String input){
        String pattern = "^[a-zA-z0-9:_/\\\\]{1,}\\.txt$";
        Pattern pat = Pattern.compile(pattern);

        Matcher m = pat.matcher(input);

        if(m.find()){
            return "Valid";
        }
        else{
            return "Reenter";
        }
    }

    public static void twoInts() {
        System.out.println("Enter two integer numbers between -2,147 to 2,147 (either one per line or separated by a space, no commas):");
        int int1 = 0, int2 = 0;
        String line;
        boolean ok = false;
        while (!ok) {
            line = scan.next();
            try {
                int1 = Integer.parseInt(line);
                if (int1 >= -2147 && int1 <= 2147) {
                    ok = true;
                } else {
                    System.err.println("Not an integer numbers between -2,147 to 2,147");
                    System.out.println("Try again: ");
                }
            } catch (NumberFormatException e) {
                System.err.println("Not an integer numbers between -2,147 to 2,147 " + e.getMessage());
                System.out.println("Try again: ");
            }
        }

        ok = false;
        while (!ok) {
            line = scan.next();
            try {
                int2 = Integer.parseInt(line);
                if (int2 >= -2147 && int2 <= 2147) {
                    ok = true;
                } else {
                    System.err.println("Not an integer numbers between -2,147 to 2,147");
                    System.out.println("Try again: ");
                }
            } catch (NumberFormatException e) {
                System.err.println("Not an integer numbers between -2,147 to 2,147 " + e.getMessage());
                System.out.println("Try again: ");
            }
        }

        System.out.println(int1 + " " + int2);
    }

    static Scanner scanner = new Scanner(System.in);

    public static void Password() {
        boolean correct = false;
        byte[] salter = salt();
        while(correct == false){
            System.out.println("Password must include:\n" +
                    "at least one upper case character,\n" +
                    "at least one digit,\n" +
                    "at least one lower case character,\n" +
                    "at least one punctuation mark\n" +
                    "No three consecutive lower case characters\n" +
                    "Enter Password: ");
            String password = scanner.nextLine();
        if(isPasswordValid(password)) {
            byte[] hashedPass = hash(password, salter);
            fileWrite(hashedPass);
            System.out.println("Re-enter password to verify: ");
            password = scanner.nextLine();
            byte[] newPassword = hash(password, salter);
            byte[] filePassword = fileRead();
            if (isEqual(filePassword, newPassword)) {
                correct = true;
                System.out.println("Password is set");
            } else {
                System.out.println("Password does not match");
            }
        }
// AFJSKLF1231!!sd
        else{
                System.out.println("Invalid password");
            }
        }

    }
    public static boolean isPasswordValid(String userString){
        String validity = "^((?=.*[A-Z])(?=.*[a-z])(?!.*[a-z]{3,})(?=.*[\\d])(?=.*[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{~}])).{10,}$";
        Pattern pattern = Pattern.compile(validity);
        Matcher match = pattern.matcher(userString);
        return match.find();

    }
    public static byte[] salt(){
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    public static byte[] hash(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 100, 128);
        SecretKeyFactory factory;
        byte[] hash = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("error");
        }
        return hash;
    }
    public static void fileWrite(byte[] password){
        File file = new File("Password_File.txt");
        Path path = file.toPath();
        try {
            Files.write(path,password);
        } catch (IOException e) {
            System.out.println("Invalid file");
        }

    }
    public static byte[] fileRead(){
        File file = new File("Password_File.txt");
        Path path = file.toPath();
        byte[] passConf = null;
        try {
            passConf = Files.readAllBytes(path);
        } catch (IOException e) {
            System.out.println("Invalid File");
        }
        return passConf;
    }
 public static boolean isEqual(byte[] oldPass, byte[] newPass){
        if(oldPass.length != newPass.length){
            return false;
        }
        else {
            for(int i = 0; i < oldPass.length; i++){
                if(oldPass[i] != newPass[i]){
                    return false;
                }
            }
            return true;
        }
 }

}
