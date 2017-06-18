import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @title 数字是不断进入数组的，在每次添加一个新的数进入数组的同时返回当前新数组的中位数。
 * @example 持续进入数组的数的列表为：[1, 2, 3, 4, 5]，则返回[1, 1, 2, 2, 3]
 * <p>
 * 持续进入数组的数的列表为：[4, 5, 1, 3, 2, 6, 0]，则返回 [4, 4, 4, 3, 3, 3, 3]
 * <p>
 * 持续进入数组的数的列表为：[2, 20, 100]，则返回[2, 2, 20]
 * @description 中位数的定义：
 * <p>
 * 中位数是排序后数组的中间值，如果有数组中有n个数，则中位数为A[(n-1)/2]。
 * 比如：数组A=[1,2,3]的中位数是2，数组A=[1,19]的中位数是1。
 */
public class DataStreamMedian {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public int[] medianII(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        this.maxHeap = new PriorityQueue<>(nums.length / 2, Comparator.comparingInt(o -> -o));
        this.minHeap = new PriorityQueue<>(nums.length / 2, Comparator.comparingInt(o -> o));

        int[] medians = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            insert(nums[i]);
            medians[i] = median();
        }
        return medians;
    }

    private int getSize() {
        return maxHeap.size() + minHeap.size();
    }

    private void insert(int num) {
        /*当两堆中的数据量为偶数时，将数据存到最小堆中*/
        if ((getSize() & 0x1) == 0) {
            /*新插入的值比最大堆的数据还要小*/
            if (maxHeap.size() > 0 && num < maxHeap.peek()) {
                /*先把新的数据插入到最大堆中*/
                maxHeap.add(num);
                /*接着把最大堆的最大的数字拿出来插入到最小堆中*/
                num = maxHeap.poll();
            }
            minHeap.add(num);
        } else {
            if (minHeap.size() > 0 && num > minHeap.peek()) {
                minHeap.add(num);
                num = minHeap.poll();
            }
            maxHeap.add(num);
        }
    }

    private int median() {
        int size = getSize();
        if (size == 0) {
            throw new IllegalStateException("No number");
        }

        int median;
        if ((size & 0x1) == 1) {
            median = minHeap.peek();
        } else {
            median = maxHeap.peek();
        }
        return median;
    }

    public static void main(String[] args) {
        DataStreamMedian median = new DataStreamMedian();

        int[] nums;
        int[] expected;

        nums = new int[]{1, 2, 3, 4, 5};
        expected = new int[]{1, 1, 2, 2, 3};
        Assert.assertTrue(Arrays.equals(expected, median.medianII(nums)));

        nums = new int[]{4, 5, 1, 3, 2, 6, 0};
        expected = new int[]{4, 4, 4, 3, 3, 3, 3};
        Assert.assertTrue(Arrays.equals(expected, median.medianII(nums)));

        nums = new int[]{2, 20, 13, 18, 15, 8, 3, 5, 4, 25};
        expected = new int[]{2, 2, 13, 13, 15, 13, 13, 8, 8, 8};
        Assert.assertTrue(Arrays.equals(expected, median.medianII(nums)));


        nums = new int[]{-4, 5, -4, 5, -4, 5, -4, 5, -4, 5, -4, 5, -4, 5, -4, 5, -4, 5, -1000};
        expected = new int[]{-4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4};
        Assert.assertTrue(Arrays.equals(expected, median.medianII(nums)));

    }

}
