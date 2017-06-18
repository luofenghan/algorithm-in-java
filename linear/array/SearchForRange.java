import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 给定一个包含 n 个整数的排序数组，找出给定目标值 target 的起始和结束位置。如果目标值不在数组中，则返回[-1, -1]
 * @example 给出[5, 7, 7, 8, 8, 10]和目标值target=8, 返回[3, 4]
 */
public class SearchForRange {

    public int[] range(int[] array, int target) {
        if (array == null || array.length == 0) {
            return new int[]{-1, -1};
        }
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (target < array[mid]) {
                high = mid - 1;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                break;
            }
        }
        if (array[mid] != target) {
            return new int[]{-1, -1};
        }
        int[] range = new int[]{mid, mid};
        while ((range[0] - 1) >= 0 && array[range[0] - 1] == target) range[0]--;
        while ((range[1] + 1 <= array.length - 1) && array[range[1] + 1] == target) range[1]++;
        return range;
    }

    public static void main(String[] args) {
        SearchForRange searchForRange = new SearchForRange();

        int[] array;
        int[] expected;
        int target;

        /*功能测试*/
        target = 8;
        array = new int[]{5, 7, 7, 8, 8, 10};
        expected = new int[]{3, 4};
        Assert.assertTrue(Arrays.equals(expected, searchForRange.range(array, target)));

        target = 9;
        array = new int[]{5, 7, 7, 8, 8, 10};
        expected = new int[]{-1, -1};
        Assert.assertTrue(Arrays.equals(expected, searchForRange.range(array, target)));

        /*边界测试*/
        target = 1;
        array = new int[]{1};
        expected = new int[]{0, 0};
        Assert.assertTrue(Arrays.equals(expected, searchForRange.range(array, target)));

        target = 5;
        array = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        expected = new int[]{0, 9};
        Assert.assertTrue(Arrays.equals(expected, searchForRange.range(array, target)));
    }
}
