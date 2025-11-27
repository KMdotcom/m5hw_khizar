package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a string: ");
        final String userInput = scanner.nextLine();
        scanner.close();
        System.out.println("You entered \"" + userInput + "\"");
        System.out.println(checkForPassword(userInput, 6));
        System.out.println(extractEmails(userInput));
        System.out.println(checkForDoubles(userInput));
    }

    /**
     * Returns whether a given string is non-empty, contains one lower case letter,
     * at least one upper case letter, at least one digit, and meets the minimum length.
     */
    public static boolean checkForPassword(String str, int minLength) {
        if (str == null) {
            return false;
        }
        if (str.length() < minLength) {
            return false;
        }
        // Check for at least one lowercase, one uppercase, and one digit
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;

        for (char c : str.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
        }

        return hasLower && hasUpper && hasDigit;
    }

    /**
     * Returns a list of email addresses that occur in a given string.
     */
    public static List<String> extractEmails(String str) {
        final List<String> result = new ArrayList<>();
        if (str == null) {
            return result;
        }
        final Pattern pattern = Pattern.compile("\\b[a-zA-Z0-9._%+-]+@(?:mail\\.)?utoronto\\.ca\\b");
        final Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * Checks whether a given string contains the same capital letter twice.
     */
    public static boolean checkForDoubles(String str) {
        if (str == null) {
            return false;
        }
        // More reliable approach for checking duplicate capital letters
        boolean[] seen = new boolean[26]; // A-Z
        for (char c : str.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                int index = c - 'A';
                if (seen[index]) {
                    return true; // Found duplicate
                }
                seen[index] = true;
            }
        }
        return false;
    }
}