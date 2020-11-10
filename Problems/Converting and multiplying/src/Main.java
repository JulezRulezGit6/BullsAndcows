import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            input = scanner.nextLine();
            if ("0".equals(input)) {
                return;
            } else {
                parseInt(input);
            }
        }
    }

    private static void parseInt(String input) {
        try {
            int currentInt = Integer.parseInt(input);
            System.out.println(currentInt*10);

        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid user input: " + input);
        }
    }
}