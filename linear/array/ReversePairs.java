import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。给你一个数组，求出这个数组中逆序对的总数。
 * 概括：如果a[i] > a[j] 且 i < j， a[i] 和 a[j] 构成一个逆序对。
 * @example 序列 [2, 4, 1, 3, 5] 中，有 3 个逆序对 (2, 1), (4, 1), (4, 3)，则返回 3 。
 */
public class ReversePairs {
    private int totalCount;

    public long count(int[] array) {
        // Write your code here
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] copy = Arrays.copyOf(array, array.length);
        mergeSort(copy, 0, array.length - 1);
        return totalCount;
    }

    private void mergeSort(int[] array, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        int count = 0;
        for (int l = left, r = mid + 1; l <= mid; ) {
            if (r > right || array[l] <= array[r]) {
                l++;
                totalCount += count;
            } else {
                r++;
                count++;
            }
        }

        int[] temp = new int[right - left + 1];
        for (int l = left, r = mid + 1, k = 0; l <= mid || r <= right; ) {
            if (l <= mid && ((r > right) || array[l] < array[r])) {
                temp[k++] = array[l++];
            } else {
                temp[k++] = array[r++];
            }
        }
        System.arraycopy(temp, 0, array, left + 0, temp.length);
    }


    public static void main(String[] args) {
        ReversePairs reversePairs = new ReversePairs();

        int[] array;
        long expected;
        /*功能测试*/
        array = new int[]{2, 4, 1, 3, 5};
        expected = 3;
        Assert.assertEquals(expected, reversePairs.count(array));

        array = new int[]{4, 3, 2, 1};
        expected = 6;
        Assert.assertEquals(expected, reversePairs.count(array));

    }
}
