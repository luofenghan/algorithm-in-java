import org.junit.Assert;

/**
 * @title 给定一个未排序的整数数组，找到其中位数。
 * <p>    中位数是排序后数组的中间值，如果数组的个数是偶数个，则返回排序后数组的第N/2个数。</p>
 * @example 给出数组[4, 5, 1, 2, 3]， 返回 3  ， 给出数组[7, 9, 4, 5]，返回 5
 */
public class MiddleNumberOfArray {

    public int find(int[] array) {
        if (array == null || array.length == 0) return -1;
        quickSort(array);
        int index = (array.length & 0x1) == 1 ? array.length / 2 /*奇数*/ : array.length / 2 - 1/*偶数*/;
        return array[index];
    }

    private void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1, 8);
        straightInsertSort(array);
    }

    private void quickSort(int[] array, int low, int high, int flag) {
        if (low + flag < high) {
            int pivot = partition(array, low, high);
            quickSort(array, 0, pivot - 1, flag);
            quickSort(array, pivot + 1, high, flag);
        }
    }

    private int partition(int[] array, int low, int high) {
        for (int pivot = array[low]; low < high; ) {
            while (low < high && array[high] >= pivot) high--;
            swap(array, low, high);
            while (low < high && array[low] <= pivot) low++;
            swap(array, low, high);
        }
        return low;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void straightInsertSort(int[] array) {
        for (int i = 1, j; i < array.length; i++) {
            int current = array[i];
            for (j = i - 1; j >= 0 && array[j] > current; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = current;
        }
    }

    public static void main(String[] args) {
        MiddleNumberOfArray middle = new MiddleNumberOfArray();

        /*FT*/
        Assert.assertEquals(3, middle.find(new int[]{4, 5, 1, 2, 3}));
        Assert.assertEquals(5, middle.find(new int[]{7, 9, 4, 5}));

        /*NT*/
        Assert.assertEquals(-1, middle.find(new int[]{}));
        Assert.assertEquals(-1, middle.find(null));

        /*BT*/


        /*PT*/

    }

}
