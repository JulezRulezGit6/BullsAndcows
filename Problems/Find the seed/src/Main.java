import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int length = B - A + 1;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            int maxNumber = Integer.MIN_VALUE;
            Random random = new Random(i + A);
            int randomInt = 0;
            for (int j = 0; j < N; j++) {
                randomInt = random.nextInt(K);
                if (randomInt > maxNumber) {
                    maxNumber = randomInt;
                }

            }
            array[i] = maxNumber;
        }
        int minMaximum = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < length; i++) {
            if (array[i] < minMaximum) {
                minMaximum = array[i];
                minIndex = i;
            }
        }
        System.out.println(minIndex+A);
        System.out.println(minMaximum);
    }

}