import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @title 给定一个旋转排序数组，在原地恢复其排序。
 * @example [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
 */
public class RecoverRotatedSortedArray {
    public void recover(ArrayList<Integer> nums) {
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)) {
                reverse(nums, 0, i);
                reverse(nums, i + 1, nums.size() - 1);
                reverse(nums, 0, nums.size() - 1);
                return;
            }
        }
    }

    private void reverse(ArrayList<Integer> nums, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
        }
    }


    public static void main(String[] args) {
        RecoverRotatedSortedArray recover = new RecoverRotatedSortedArray();

        ArrayList<Integer> nums;
        ArrayList<Integer> expected;

        /*功能测试*/
        nums = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 1, 2, 3));
        /*
        * 4, 5, 6, 7, 1, 2, 3
        * 1, 2, 3,| 7, 4, 5, 6
        * 1, 2, 3,| 6, 4, 5,| 7
        * 1, 2, 3,| 5, 4,| 6,| 7
        * 1, 2, 3,| 4, |5,| 6,| 7
        * */
        expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        recover.recover(nums);
        Assert.assertEquals(expected, nums);

        nums = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 1, 2)); /*1,2,5,6,7,3,4*/
        expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        recover.recover(nums);
        Assert.assertEquals(expected, nums);

        /*边界测试*/
        nums = new ArrayList<>(Arrays.asList(6, 7, 1, 2, 3, 4, 5));
        /*
        * 6, 7, 1, 2, 3, 4, 5|
        * 4, 5, 1, 2, 3,| 6, 7
        * 2, 3, 1,| 4, 5, 6, 7
        * 1,| 2, 3, 4, 5, 6, 7
        * */
        expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        recover.recover(nums);
        Assert.assertEquals(expected, nums);

        nums = new ArrayList<>(Arrays.asList(5, 6, 7, 1, 2, 3, 4));
        expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        recover.recover(nums);
        Assert.assertEquals(expected, nums);


        nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        recover.recover(nums);
        Assert.assertEquals(expected, nums);
        /*负面测试*/




        /*性能测试*/

    }
}
