/**
 * Created by cwc on 2017/5/12 0012.
 */
public class RadixSort {

    private static int maxBit(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int a : array) {
            if (a > max) {
                max = a;
            }
        }
        int d = 1;
        while (max >= 10) {
            max /= 10;
            ++d;
        }
        return d;

    }

    public static void sort(int[] array) {
        int[] tmp = new int[array.length];
        int base = 1;
    }

    public static void main(String[] args) {
        int[] arrays = {9012, 19702, 39867, 68957, 83556, 102456};

        System.out.println("桶排序：");
        Utils.shuffle(arrays);
        Utils.print("\tbefore sort:", arrays);
        RadixSort.sort(arrays);
        Utils.print("\t after sort:", arrays);
    }
}
