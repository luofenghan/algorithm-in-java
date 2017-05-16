
/**
 * Created by cwc on 2017/5/12 0012.
 */
public class CountingSort {
    public static void sort(int[] array) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int a : array) {
            max = Math.max(max, a);
            min = Math.min(min, a);
        }
        int[] count = new int[max + 1];
        for (int i : array) {
            count[i]++;
        }
        for (int i = min, j = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                array[j++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println("计数排序：");
        Utils.shuffle(arrays);
        Utils.print("\tbefore sort:", arrays);
        CountingSort.sort(arrays);
        Utils.print("\t after sort:", arrays);
    }
}
