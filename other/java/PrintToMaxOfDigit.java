/**
 * Created by cwc on 2017/5/21 0021.
 */
public class PrintToMaxOfDigit {

    public static void print(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        int[] numbers = new int[n];
        for (int i = 0, len = (int) (PowerWithUnsignedExponent.power(10, n) - 1); i < len; i++) {
            carry(numbers, n - 1);
            for (int j = 0, d = -1; j < numbers.length; j++) {
                if (numbers[j] == 0 && j == ++d) continue;
                System.out.print(numbers[j]);
            }
            System.out.print(" ");
        }
    }

    private static void carry(int[] numbers, int position) {
        if (position == -1) {
            return;
        }
        if (numbers[position] == 9) {
            numbers[position] = 0;
            carry(numbers, position - 1);
        } else {
            numbers[position]++;
        }
    }

    public static void main(String[] args) {
        print(10);
    }
}