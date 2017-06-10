import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 给定一个包含红，白，蓝且长度为 n 的数组，将数组元素进行分类使相同颜色的元素相邻，
 * 并按照红、白、蓝的顺序进行排序。我们可以使用整数 0，1 和 2 分别代表红，白，蓝。
 * @example 给你数组 [1, 0, 1, 2], 需要将该数组原地排序为 [0, 1, 1, 2]。
 * @challenge 一个相当直接的解决方案是使用计数排序扫描2遍的算法。
 * 首先，迭代数组计算 0,1,2 出现的次数，然后依次用 0,1,2 出现的次数去覆盖数组。
 * 你否能想出一个仅使用【常数级额外空间复杂度】且【只扫描遍历一遍数组】的算法？
 */
public class SortColors {
    public void sort(int[] nums) {
        // write your code here
        if (nums == null || nums.length <= 1) {
            return;
        }
        int size = nums.length;
        int r = 0, b = 0;
        for (int i = 0; i < size - b; i++) {
            if (nums[i] == 0) {
                swap(nums,i,r);
                r++;
            }else if(nums[i]==2){
                swap(nums,i,size-1-b);
                b++;
                i--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        SortColors sortColors = new SortColors();

        int[] nums;
        int[] expected;

        /*功能测试*/
        nums = new int[]{1, 0, 1, 2};
        expected = new int[]{0, 1, 1, 2};
        sortColors.sort(nums);
        Assert.assertTrue(Arrays.equals(expected, nums));

        nums = new int[]{2, 1, 0, 1, 2};
        expected = new int[]{0, 1, 1, 2, 2};
        sortColors.sort(nums);
        Assert.assertTrue(Arrays.equals(expected, nums));

        nums = new int[]{2, 2, 0, 0, 1};
        expected = new int[]{0, 0, 1, 2, 2};
        sortColors.sort(nums);
        Assert.assertTrue(Arrays.equals(expected, nums));

        /*负面测试*/
        nums = new int[]{};
        expected = new int[]{};
        sortColors.sort(nums);
        Assert.assertTrue(Arrays.equals(expected, nums));

        nums = null;
        expected = null;
        sortColors.sort(nums);
        Assert.assertTrue(Arrays.equals(expected, nums));


    }
}
