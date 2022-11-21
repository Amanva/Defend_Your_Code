package programs;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Defend_Code {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
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

}
