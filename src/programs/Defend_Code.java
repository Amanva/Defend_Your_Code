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
        System.out.println("Enter two numbers between -2,147 to 2,147");
        int int1 = 0, int2 = 0;
        boolean ok = false;

        // TODO needs to clean of commas
        while (!ok) {
            System.out.print("Enter the first number: ");
            try {
                if (scan.hasNextInt()) {
                    int1 = scan.nextInt();
                    if (int1 >= -2147 && int1 <= 2147) {
                        ok = true;
                    }
                } else {
                    scan.nextLine();
                    System.out.println("Not a number between -2,147 to 2,147");
                }
            } catch (InputMismatchException e) {
                System.out.println("Try again");
            }
        }
        ok = false;
        while (!ok) {
            System.out.print("Enter the second number: ");
            try {
                if (scan.hasNextInt()) {
                    int2 = scan.nextInt();
                    if (int2 >= -2147 && int2 <= 2147) {
                        ok = true;
                    }
                } else {
                    scan.nextLine();
                    System.out.println("Not a number between -2,147 to 2,147");
                }
            } catch (InputMismatchException e) {
                System.out.println("Try again");
            }
        }

        System.out.println(int1 + " " + int2);
    }
    static Scanner scanner = new Scanner(System.in);

    public static void Password() {
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        byte[] salter = salt();
        if(isPasswordValid(password)){
                byte[] hashedPass = hash(password, salter);
            System.out.println("Re-enter password to verify: ");
            password = scanner.nextLine();
            byte[] newPassword = hash(password, salter);
            System.out.println(Arrays.toString(newPassword));

// AFJSKLF1231!!sd

        }
        else{
            System.out.println("Invalid password");
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
    public static void fileWrite(String password){
        File file = new File("Password_File.txt");
        Path path = file.toPath();
        try {
            Files.writeString(path,password, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
