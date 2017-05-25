import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 给一个数组 nums 写一个函数将 0 移动到数组的最后面，非零元素保持原数组的顺序
 * @example 给出 nums = [0, 1, 0, 3, 12], 调用函数之后, nums = [1, 3, 12, 0, 0].
 * @attention 1.必须在原数组上操作
 * <p></p>     2.最小化操作数
 */
public class MoveZeroes {

    public void move(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        for (int head = length - 1, tail = length - 1, flag = length; head >= 0; head--) {
            if (nums[head] == 0 && head == --flag) {
                tail--;
                continue;
            }
            if (nums[head] == 0) {
                System.arraycopy(nums, head + 1, nums, head, tail - head);
                nums[tail--] = 0;
            }
        }
    }

    public static void main(String[] args) {

        MoveZeroes move = new MoveZeroes();

        int[] nums;

        /*功能测试*/
        nums = new int[]{0, 1, 0, 3, 12};
        move.move(nums);
        Assert.assertTrue(Arrays.equals(new int[]{1, 3, 12, 0, 0}, nums));

        nums = new int[]{3, 5, 4, 0, 0};
        move.move(nums);
        Assert.assertTrue(Arrays.equals(new int[]{3, 5, 4, 0, 0}, nums));

        nums = new int[]{0, 0, 3, 5, 4};
        move.move(nums);
        Assert.assertTrue(Arrays.equals(new int[]{3, 5, 4, 0, 0}, nums));

        nums = new int[]{3, 5, 4};
        move.move(nums);
        Assert.assertTrue(Arrays.equals(new int[]{3, 5, 4}, nums));

        /*负面测试*/
        nums = new int[]{};
        move.move(nums);
        Assert.assertTrue(Arrays.equals(new int[]{}, nums));

        nums = null;
        move.move(nums);
        Assert.assertTrue(Arrays.equals(null, nums));
    }
}
