package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Input the length of the secret code:");
        String length = scanner.nextLine();
        if (checkIfStringIsNumber(length)) return;
        int numberLength = Integer.parseInt(length);
        if (numberLength < 1) {
            System.out.printf("Error: \"%d\" isn't a valid number.\n",numberLength);
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        String possibilities = scanner.nextLine();
        if (checkIfStringIsNumber(possibilities)) return;

        int possibleSymbols = Integer.parseInt(possibilities);

        if (possibleSymbols < numberLength) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n", numberLength, possibleSymbols);
            return;
        }

        if (possibleSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).\n");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*".repeat(numberLength));
        String code = generateCodes(numberLength, possibleSymbols);
        if (possibleSymbols < 11) {
            System.out.printf("The secret is prepared: %s (0-%d)\n", stringBuilder.toString(), possibleSymbols - 1);
        } else {
            char lastChar = Character.forDigit(possibleSymbols - 1, possibleSymbols);
            System.out.printf("The secret is prepared: %s (0-9, a-%s).\n", stringBuilder.toString(), lastChar);
        }


        System.out.println("Okay, let's start a game!");
        int turns = 1;
        while (true) {
            System.out.println("Turn " + turns + ":");
            String input = scanner.next();
            String answer = calculateAnswer(code, input);
            if (answer.contains("Congratulations!")) {
                System.out.println(answer);
                return;
            }
            System.out.println(answer);
            turns++;
        }
    }

    private static boolean checkIfStringIsNumber(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (Character.isAlphabetic(string.charAt(i))) {
                System.out.printf("Error: \"%s\" isn't a valid number.\n", string);
                return true;
            }
        }
        return false;
    }


    private static String generateCodes(int numberLength, int possibleSymbols) {
        StringBuilder randomNumber = new StringBuilder();
        Random random = new Random();
        boolean[] digits = new boolean[possibleSymbols];
        while (randomNumber.length() != numberLength) {
            int currentInt = random.nextInt(possibleSymbols);
            if (!digits[currentInt]) {
                randomNumber.append(Character.forDigit(currentInt, possibleSymbols));
                digits[currentInt] = true;
            }

        }
        return randomNumber.toString();
    }


    private static String calculateAnswer(String codeString, String inputString) {
        int cows = 0;
        int bulls = 0;


        for (int i = 0; i < inputString.length(); i++) {
            for (int j = 0; j < inputString.length(); j++) {
                if (inputString.charAt(i) == codeString.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else cows++;
                }
            }
        }

        if (bulls == codeString.length()) {
            if (bulls == 1) {
                return String.format("Grade: %d bull\nCongratulations!You guessed the secret code.", bulls);
            } else {
                return String.format("Grade: %d bulls\nCongratulations!You guessed the secret code.", bulls);

            }
        } else if (bulls == 0 && cows == 0) {
            return "Grade: None.";
        } else if (bulls == 0 && cows == 1) {
            return String.format("Grade: %d cow.", cows);
        } else if (bulls == 0 && cows > 1) {
            return String.format("Grade: %d cows.", cows);
        } else if (bulls == 1 && cows == 0) {
            return String.format("Grade: %d bull.", bulls);
        } else if (bulls == 1 && cows > 1) {
            return String.format("Grade: %d bull amd %d cows.", bulls, cows);
        } else if (bulls == 1 && cows == 1) {
            return String.format("Grade: %d bull and %d cow.", bulls, cows);
        } else if (bulls > 1 && cows == 0) {
            return String.format("Grade: %d bulls.", bulls);
        } else if (bulls > 1 && cows == 1) {
            return String.format("Grade: %d bulls and %d cow.", bulls, cows);
        } else if (bulls > 1 && cows > 1) {
            return String.format("Grade: %d bulls and %d cows.", bulls, cows);
        } else return "-------";
    }
}

