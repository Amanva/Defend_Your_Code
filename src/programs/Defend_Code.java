package programs;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * This program takes input from user such as name, two int values, input file, output file, and password. Then it
 * validates all of input to make sure it is safe.
 *
 * @authors Arashpreet S. Pandher, Aman Vahora, Matthew Pham-Nguyen
 * @version 1.0
 *
 */

public class Defend_Code {

    /**
     * scanner for input form user
     */
    static Scanner scan = new Scanner(System.in);
    /**
     *
     */
    static SecureRandom random = new SecureRandom();

    /**
     * main method to call functions
     * @param args
     */
    public static void main(String[] args) {
        String fName = firstName();
        String lName = lastName();
        String inputFile = inputFile();
        String output = outputFile();
        int value1 = getInt();
        int value2 = getInt();

        writeToFile(fName, lName, inputFile,output, value1,value2);
    }

    /**
     * This method gets first name from user
     * @return returns string name
     */
    public static String firstName(){
        String name = "";
        while(true) {
            System.out.println("Enter first name, at most 50 characters excluding spaces");
            System.out.println("Valid characters are [A-Z][a-z]");
            System.out.println("Example: Arashpreet");
            name = scan.nextLine();
            String output = nameValidation(name);
            if(output.equals("Valid")){
                break;
            }
        }
        return name;
    }

    /**
     * This method gets the last name from user
     * @return returns string name
     */
    public static String lastName(){
        String name = "";
        while(true) {
            System.out.println("Enter last name, at most 50 characters excluding spaces");
            System.out.println("Valid characters are [A-Z][a-z]");
            name = scan.nextLine();
            String output = nameValidation(name);
            System.out.println(output);
            if(output.equals("Valid")){
                break;
            }
        }
        return name;
    }

    /**
     * This method checks if name is valid
     * @param input name input (string)
     * @return returns string name
     */
    public static String nameValidation(String input){
        String pattern = "^[a-zA-z]{0,50}$";
        Pattern pat = Pattern.compile(pattern);
        input = input.strip();
        Matcher m = pat.matcher(input);

        if(m.find()){
            return "Valid";
        }
        else{
            return "Invalid";
        }
    }

    /**
     * This method gets input file from user
     * @return returns input file as string
     */
    public static String inputFile(){
        System.out.println("Enter file used for input, must end in txt extension. Include directory or file will be taken from project folder." +
                "\nexample: C:Windows\\System32\\system\\input.txt");
        String inputFile = "";
        while(true) {
            inputFile = scan.nextLine();
            String output = fileValidation(inputFile);
            System.out.println(output);
            if(output == "Valid"){
                break;
            }
            System.out.println("Reenter");
        }
        //check if file exists

        return inputFile;

    }

    /**
     * This method checks if file is valid
     * @param input file name input
     * @return returns string
     */
    public static String fileValidation(String input){
        String pattern = "^[a-zA-z0-9:_/\\\\]{1,}\\.txt$";
        Pattern pat = Pattern.compile(pattern);

        Matcher m = pat.matcher(input);

        File f = new File(input);


        if(m.find()){
            if(f.exists()){
                return "Valid";
            }
            else{
                return "File doesn't exist";
            }
        }
        else{
            return "Invalid";
        }
    }

    /**
     * This method checks if output file is valid
     * @return returns if valid or not valid
     */
    public static String outputFile(){
        System.out.println("Enter outfile, must end in txt extension. Can include directory or file will be taken from project folder.");
        String outputFileGiven = "";
        while(true) {
            outputFileGiven = scan.nextLine();
            String output = fileValidation(outputFileGiven);
            System.out.println(output);
            if(output == "Valid"){
                break;
            }
            System.out.println("Reenter");
        }

        return outputFileGiven;
    }

    /**
     * this method writes to output file
     * @param fName first name
     * @param lName last name
     * @param inputFile inputfile
     * @param outputFile outputfile
     * @param intOne first number
     * @param intTwo second number
     */
    public static void writeToFile(String fName, String lName, String inputFile, String outputFile, int intOne, int intTwo){
        try {
            FileWriter fw = new FileWriter(outputFile);
            fw.write("First Name: " + fName+ "\n");
            fw.write("Last Name: " + lName + "\n");
            //int writing
            fw.write("Addition: " + adding(intOne, intTwo) + "\n");
            fw.write("Multiplication: " + multiplying(intOne, intTwo) + "\n");
            fw.write("Input File: " + inputFile +"\n" + "File contents: \n");
            fw.write(writeInputFile(inputFile));
            fw.close();
            System.out.println("Finished Writing");
        } catch (IOException e) {
            System.out.println("Writing to file Error");
        }

    }

    /**
     * This writes content of input file to outputfile
     * @param inputFile file input
     * @return returns file content as string
     */
    public static String writeInputFile(String inputFile){
        StringBuilder string = new StringBuilder();
        try {
            FileReader read = new FileReader(inputFile);
            int r=read.read();
            while(r != -1){
                string.append((char)r);
                r= read.read();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error File not found");
        } catch (IOException e) {
            System.out.println("Error reading input file");
        }
        return string.toString();
    }


    public static int getInt() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an integer number between -2,147,483,648 to 2,147,483,647. Do not include commas or white spaces:");
        int int1 = 0;
        String line;
        boolean ok = false;
        while (!ok) {
            line = scan.nextLine();
            try {
                int1 = Integer.parseInt(line);
//                if (int1 >= Integer.MIN_VALUE && int1 <= Integer.MAX_VALUE) {
                    ok = true;
//                } else {
//                    System.err.println("Not a valid input");
//                    System.out.println("Try again: ");
//                }
            } catch (NumberFormatException e) {
                System.err.println("Not a valid input " + e.getMessage());
                System.out.println("Try again: ");
            }
        }

        return int1;
    }

    public static String adding(int int1, int int2) {
        BigInteger bigInt1 = BigInteger.valueOf(int1);
        BigInteger bigInt2 = BigInteger.valueOf(int2);
        BigInteger sum = bigInt1.add(bigInt2);
        return sum.toString();
    }

    public static String multiplying(int int1, int int2) {
        BigInteger bigInt1 = BigInteger.valueOf(int1);
        BigInteger bigInt2 = BigInteger.valueOf(int2);
        BigInteger product = bigInt1.multiply(bigInt2);
        return product.toString();
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
                    "At least 10 characters\n" +
                    "Enter Password: ");
            String password = scanner.nextLine();
            password = password.strip();
        if(isPasswordValid(password)) {
            byte[] hashedPass = hash(password, salter);
            fileWrite(hashedPass);
            System.out.println("Re-enter password to verify: ");
            password = scanner.nextLine();
            password = password.strip();
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
                System.out.println("Invalid password: re-enter password");
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
