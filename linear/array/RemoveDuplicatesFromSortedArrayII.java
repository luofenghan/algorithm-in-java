import org.junit.Assert;

/**
 * @title 跟进“删除重复数字”：如果可以允许出现两次重复将如何处理？
 * @example
 */
public class RemoveDuplicatesFromSortedArrayII {

    public int filter(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int size = nums.length;
        for (int i = 0, gap; i < size; i++) {
            gap = 0;
            for (int j = i + 1; j < size; j++) {
                if (nums[j] == nums[i]) {
                    if (j - i > 1) {
                        gap++;
                    }
                } else if (gap != 0) {
                    System.arraycopy(nums, j, nums, j - gap, size - j);
                    break;
                } else {
                    break;
                }
            }
            size -= gap;
        }
        return size;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArrayII filter = new RemoveDuplicatesFromSortedArrayII();

        int[] nums;

        /*功能测试*/
        nums = new int[]{1, 2, 3, 4, 5, 6};
        Assert.assertEquals(6, filter.filter(nums));

        nums = new int[]{1, 1, 1, 4, 5, 6};
        Assert.assertEquals(5, filter.filter(nums));

        nums = new int[]{1, 1, 1, 4, 4, 5, 5, 6, 7, 8, 8};
        Assert.assertEquals(10, filter.filter(nums));

        /*负面测试*/
        nums = new int[]{1};
        Assert.assertEquals(1, filter.filter(nums));

        nums = new int[]{};
        Assert.assertEquals(0, filter.filter(nums));

        nums = null;
        Assert.assertEquals(0, filter.filter(nums));


    }
}
