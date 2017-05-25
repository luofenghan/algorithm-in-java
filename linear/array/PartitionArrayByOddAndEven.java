import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 分割一个整数数组，使得奇数在前偶数在后。
 * @example 给定 [1, 2, 3, 4]，返回 [1, 3, 2, 4]。
 */
public class PartitionArrayByOddAndEven {

    public void partition(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        for (int evenIndex = 0, oddIndex = nums.length - 1; evenIndex < oddIndex; evenIndex++) {
            if ((nums[evenIndex] & 0x1) == 0) {/*偶数*/
                /*从后往前扫描，直到找到奇数为止。*/
                while (oddIndex > evenIndex && (nums[oddIndex] & 0x1) != 1) oddIndex--;
                int tmp = nums[evenIndex];
                nums[evenIndex] = nums[oddIndex];
                nums[oddIndex] = tmp;
            }
        }


    }

    public static void main(String[] args) {
        PartitionArrayByOddAndEven partition = new PartitionArrayByOddAndEven();

        int[] nums;
        /*功能测试*/
        nums = new int[]{1, 2, 3, 4};
        partition.partition(nums);
        Assert.assertTrue(Arrays.equals(new int[]{1, 3, 2, 4}, nums));

        nums = new int[]{2, 4, 6, 8};
        partition.partition(nums);
        Assert.assertTrue(Arrays.equals(new int[]{2, 4, 6, 8}, nums));

        nums = new int[]{1, 3, 5, 9};
        partition.partition(nums);
        Assert.assertTrue(Arrays.equals(new int[]{1, 3, 5, 9}, nums));

        /*边界测试*/
        nums = new int[]{2};
        partition.partition(nums);
        Assert.assertTrue(Arrays.equals(new int[]{2}, nums));

        nums = new int[]{2, 4, 6, 8, 1, 3, 5, 7};
        partition.partition(nums);
        Assert.assertTrue(Arrays.equals(new int[]{7, 5, 3, 1, 8, 6, 4, 2}, nums));

        /*负面测试*/
        nums = new int[]{};
        partition.partition(nums);
        Assert.assertTrue(Arrays.equals(new int[]{}, nums));

        nums = null;
        partition.partition(nums);
        Assert.assertTrue(Arrays.equals(null, nums));
    }
}
