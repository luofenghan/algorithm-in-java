import org.junit.Assert;

/**
 * @title 给定一个数组和一个值，在原地删除与值相同的数字，返回新数组的长度。元素的顺序可以改变，并且对新的数组不会有影响。
 * @example 给出一个数组 [0,4,4,0,0,2,4,4]，和值 4 ，返回 4 并且4个元素的新数组为[0,0,0,2]
 */
public class RemoveElement {
    /**
     * @param nums: A list of integers
     * @param elem: An integer
     * @return The new length after remove
     */
    public int remove(int[] nums, int elem) {
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        if (nums.length == 0) {
            return 0;
        }

        int size = nums.length;
        int tail = size - 1;
        for (int i = 0; i < size; i++) {
            if (nums[i] == elem) {
                int count = 1;
                if (i < tail) {
                    while (nums[tail] == elem) {
                        tail--;
                        count++;
                    }
                    nums[i] = nums[tail];
                    nums[tail--] = elem;
                }
                size -= count;

            }
        }
        return size;
    }

    public static void main(String[] args) {
        RemoveElement remove = new RemoveElement();
        int[] nums;

        /*功能测试*/
        nums = new int[]{0, 4, 4, 0, 0, 2, 4, 4};
        Assert.assertEquals(4, remove.remove(nums, 4));

        nums = new int[]{0, 4, 4, 0, 0, 2, 4, 4};
        Assert.assertEquals(8, remove.remove(nums, 1));

        /*边界测试*/
        nums = new int[]{0, 4, 4, 0, 0, 2, 4, 5};
        Assert.assertEquals(7, remove.remove(nums, 5));

        /*负面测试*/
        nums = new int[]{0};
        Assert.assertEquals(0, remove.remove(nums, 0));

        nums = new int[]{};
        Assert.assertEquals(0, remove.remove(nums, 1));

        try {
            nums = null;
            remove.remove(nums, 1);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
        }
    }


}
