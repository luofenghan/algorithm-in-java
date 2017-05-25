import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 合并两个排序的整数数组A和B变成一个新的数组。
 * @example 给出A=[1,2,3,4]，B=[2,4,5,6]，返回 [1,2,2,3,4,4,5,6]
 */
public class MergeTwoSortedArrays {

    public int[] merge(int[] array1, int[] array2) {
        if (array1 == null || array2 == null) {
            return null;
        }
        if (array1.length == 0 && array2.length == 0) {
            return array1;
        } else if (array1.length == 0) {
            return array2;
        } else if (array2.length == 0) {
            return array1;
        } else {
            int[] mergedArray = new int[array1.length + array2.length];
            int i, j, k;
            for (i = j = k = 0; i < array1.length && j < array2.length; ) {
                if (array1[i] < array2[j]) {
                    mergedArray[k++] = array1[i++];
                } else if (array1[i] > array2[j]) {
                    mergedArray[k++] = array2[j++];
                } else {
                    mergedArray[k++] = array1[i++];
                    mergedArray[k++] = array2[j++];
                }
            }
            while (i < array1.length) mergedArray[k++] = array1[i++];
            while (j < array2.length) mergedArray[k++] = array2[j++];
            return mergedArray;
        }

    }

    public static void main(String[] args) {
        MergeTwoSortedArrays merge = new MergeTwoSortedArrays();

        int[] array1, array2;

        /*功能测试*/
        array1 = new int[]{1, 2, 3, 4};
        array2 = new int[]{2, 4, 5, 6};
        Assert.assertTrue(Arrays.equals(new int[]{1, 2, 2, 3, 4, 4, 5, 6}, merge.merge(array1, array2)));

        array1 = new int[]{1, 2, 3, 4};
        array2 = new int[]{5, 6};
        Assert.assertTrue(Arrays.equals(new int[]{1, 2, 3, 4, 5, 6}, merge.merge(array1, array2)));

        /*负面测试*/

        /*边界测试*/

    }
}
