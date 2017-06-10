import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 给定两个整数数组（第一个是数组 A，第二个是数组 B），在数组 A 中取 A[i]，数组 B 中取 B[j]，A[i] 和 B[j]两者的差越小越好(|A[i] - B[j]|)。返回最小差。
 * @example 给定数组 A = [3,4,6,7]， B = [2,3,8,9]，返回 0。
 * @challenge 时间复杂度 O(n log n)
 */
public class TheSmallestDifference {

    public int diff(int[] array1, int[] array2) {
        if (array1 == null || array1.length == 0 || array2 == null || array2.length == 0) {
            return 0;
        }
        Arrays.sort(array1);
        Arrays.sort(array2);
        int min = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < array1.length && j < array2.length; ) {
            min = Math.min(min, Math.abs(array1[i] - array2[j]));
            if (array1[i] > array2[j]) {
                j++;
            } else if (array1[i] < array2[j]) {
                i++;
            } else {
                break;
            }
        }
        return min;
    }


    public static void main(String[] args) {
        TheSmallestDifference difference = new TheSmallestDifference();

        int[] array1;
        int[] array2;

        /*功能测试*/
        array1 = new int[]{3, 4, 6, 7};
        array2 = new int[]{2, 3, 8, 9};
        Assert.assertEquals(0, difference.diff(array1, array2));

        array1 = new int[]{3, 4, 6, 7};
        array2 = new int[]{1, 2, 3, 7};
        Assert.assertEquals(0, difference.diff(array1, array2));

        array1 = new int[]{12, 23, 25, 30};
        array2 = new int[]{1, 2, 13, 14, 27};
        Assert.assertEquals(1, difference.diff(array1, array2));

        array1 = new int[]{10, 25, 55, 34};
        array2 = new int[]{18, 47, 46, 42};
        Assert.assertEquals(7, difference.diff(array1, array2));

        /*负面测试*/
        array1 = new int[]{};
        array2 = new int[]{};
        Assert.assertEquals(0, difference.diff(array1, array2));

        array1 = null;
        array2 = null;
        Assert.assertEquals(0, difference.diff(array1, array2));
    }

}
