import org.junit.Assert;

/**
 * @title 给定一个排序的{整数数组（升序）}和一个要查找的整数{target}，
 * <p>     用O(logn)的时间查找到target第一次出现的下标（从0开始），
 * <></>   如果target不存在于数组中，返回-1。
 * @example 在数组 [1, 2, 3, 3, 4, 5, 10] 中二分查找3，返回2。
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                while (mid > 0 && nums[mid] == nums[mid - 1]) mid--;
                return mid;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();

        int[] emptyArray = {};
        int[] array = {1, 4, 4, 5, 7, 7, 8, 9, 9, 10};

        /*功能测试*/
        Assert.assertEquals(4, search.search(array, 7));
        Assert.assertEquals(1, search.search(array, 4));
        Assert.assertEquals(-1, search.search(array, 100));

        /*边界测试*/
        Assert.assertEquals(9, search.search(array, 10));
        Assert.assertEquals(0, search.search(array, 1));

        /*负面测试*/
        Assert.assertEquals(-1, search.search(emptyArray, 11));

        /*性能测试*/


    }
}
