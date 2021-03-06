import java.util.Random;

/**
 * Created by cwc on 2017/5/8 0008.
 */
public class Utils {

    public static void print(String log, int[] elements) {
        System.out.print(log);
        for (int order : elements) {
            System.out.print(order + " ");
        }
        System.out.println();
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void shuffle(int[] elements) {
        Random rnd = new Random();
        for (int i = elements.length; i > 1; i--) {
            swap(elements, i - 1, rnd.nextInt(i));
        }
    }

}
