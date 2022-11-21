package programs;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Defend_Code {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        name();
        twoInts();
        inputFile();
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
        System.out.println("Enter file used for input, must end in txt extension" +
                "\nexample: input.txt");
        String inputFile = scan.nextLine();
        String output = inputFileValidation(inputFile);
        System.out.println(output);

    }

    public static String inputFileValidation(String input){
        String pattern = "^[a-zA-z0-9_]{0,260}\\.txt$";
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
}
