import org.junit.Assert;

/**
 * @title 假设有一个排序的按未知的旋转轴旋转的数组(比如0 1 2 4 5 6 7 可能成为4 5 6 7 0 1 2)。
 * 给定一个目标值进行搜索，如果在数组中找到目标值返回数组中的索引位置，否则返回-1。
 * 你可以假设数组中不存在重复的元素。
 * @example 给出[4, 5, 1, 2, 3]和target=1，返回 2  ;给出[4, 5, 1, 2, 3]和target=0，返回 -1
 * @challenge O(logN) time
 */
public class SearchInRotatedSortedArray {

    public int search(int[] array, int target) {
        // write your code here
        if (array == null || array.length == 0) {
            return -1;
        }
        int pivot = partition(array);
        if (array[pivot] == target) {
            return pivot;
        }
        if (array[array.length - 1] < target) {
            return binarySearch(array, 0, pivot - 1, target);
        } else {
            return binarySearch(array, pivot, array.length - 1, target);
        }
    }

    private int binarySearch(int[] array, int start, int end, int target) {
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (target < array[mid]) {
                end = mid - 1;
            } else if (array[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private int partition(int[] array) {
        int low = 0;
        int high = array.length - 1;
        int mid = high;
        while (array[low] > array[high]) {
            if (high == low + 1) {
                mid = high;
                return mid;
            }
            mid = (low + high) / 2;
            if (array[low] <= array[mid]) {
                low = mid;
            } else if (array[mid] <= array[high]) {
                high = mid;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray sortedArray = new SearchInRotatedSortedArray();

        int[] array;
        int target;
        int expected;

        /*功能测试*/
        array = new int[]{4, 5, 1, 2, 3};
        target = 1;
        expected = 2;
        Assert.assertEquals(expected, sortedArray.search(array, target));

        array = new int[]{4, 5, 1, 2, 3};
        target = 5;
        expected = 1;
        Assert.assertEquals(expected, sortedArray.search(array, target));

        /*负面测试*/
        array = new int[]{1, 2, 3, 0};
        target = 1;
        expected = 0;
        Assert.assertEquals(expected, sortedArray.search(array, target));

    }
}
