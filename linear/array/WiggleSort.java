import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 给你一个没有排序的数组，请将原数组就地重新排列满足如下性质 nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * @example 给出数组为 nums = [3, 5, 2, 1, 6, 4] 一种输出方案为 [1, 6, 2, 5, 3, 4]
 * @attention 请就地排序数组，也就是不需要额外数组
 */
public class WiggleSort {
    public void wiggle(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        Arrays.sort(nums);
        for (int i = 1, tail = nums.length - 1; i < tail; i += 2) {
            int tmp = nums[tail];
            System.arraycopy(nums, i, nums, i + 1, tail - i);
            nums[i] = tmp;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void quickSort(int[] nums) {
        Arrays.sort(nums);
    }

    public static void main(String[] args) {
        WiggleSort wiggle = new WiggleSort();

        int[] nums;
        int[] expected;

        /*功能测试*/
        nums = new int[]{3, 5, 2, 1, 6, 4};

        expected = new int[]{1, 6, 2, 5, 3, 4};
        wiggle.wiggle(nums);
        Assert.assertTrue(Arrays.equals(expected, nums));

        nums = new int[]{1, 2, 3, 4, 5};
        expected = new int[]{1, 5, 2, 4, 3};
        wiggle.wiggle(nums);
        Assert.assertTrue(Arrays.equals(expected, nums));

        nums = new int[]{1, 1, 1, 1};
        expected = new int[]{1, 1, 1, 1};
        wiggle.wiggle(nums);
        Assert.assertTrue(Arrays.equals(expected, nums));

        /*边界测试*/

        nums = new int[]{1, 0};
        expected = new int[]{0, 1};
        wiggle.wiggle(nums);
        Assert.assertTrue(Arrays.equals(expected, nums));

        /*负面测试*/
        nums = new int[]{};
        expected = new int[]{};
        wiggle.wiggle(nums);
        Assert.assertTrue(Arrays.equals(expected, nums));

        nums = null;
        expected = null;
        wiggle.wiggle(nums);
        Assert.assertTrue(Arrays.equals(expected, nums));
    }
}
