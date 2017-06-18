import com.google.common.collect.Lists;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @title 给定一个包含 n 个整数的数组，和一个大小为 k 的滑动窗口,从左到右在数组中滑动这个窗口，
 * 找到数组中每个窗口内的中位数。(如果数组中有偶数，则在该窗口存储该数字后，返回第 N/2-1 个数字。)
 * @challenge 时间复杂度为 O(nlog(n))
 * @example 对于数组 [1,2,7,8,5], 滑动大小 k = 3 的窗口时，返回 [2,7,7]
 * <p>
 * 最初，窗口的数组是这样的：
 * <p>
 * [ | 1,2,7 | ,8,5] , 返回中位数 2;
 * <p>
 * 接着，窗口继续向前滑动一次。
 * <p>
 * [1, | 2,7,8 | ,5], 返回中位数 7;
 * <p>
 * 接着，窗口继续向前滑动一次。
 * <p>
 * [1,2, | 7,8,5 | ], 返回中位数 7;
 */
public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
        ArrayList<Integer> medianList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return medianList;
        }
        if (k == 1 || nums.length == 1) {
            for (int num : nums) {
                medianList.add(num);
            }
            return medianList;
        }
        this.maxHeap = new PriorityQueue<>(nums.length / 2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        this.minHeap = new PriorityQueue<>(nums.length / 2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < k; i++) {
            insert(nums[i]);
        }

        for (int i = 0, next = i + k - 1; i < nums.length && next < nums.length; i++, next = i + k - 1) {
            if (getSize() < k) {
                insert(nums[next]);
            }
            int median = median();
            medianList.add(median);
            remove(nums[i], median);
        }
        return medianList;
    }


    private int getSize() {
        return maxHeap.size() + minHeap.size();
    }

    private void remove(int num, int median) {
        if (num <= median) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }

        if (maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
        }

        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        }
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
        SlidingWindowMedian sliding = new SlidingWindowMedian();

        int[] nums;
        ArrayList<Integer> expected;

        nums = new int[]{1, 2, 7, 8, 5};
        expected = Lists.newArrayList(2, 7, 7);
        Assert.assertEquals(expected, sliding.medianSlidingWindow(nums, 3));


        nums = new int[]{1, 2, 7, 7, 2, 10, 3, 4, 5};
        expected = Lists.newArrayList(1, 2, 7, 2, 2, 3, 3, 4);
        Assert.assertEquals(expected, sliding.medianSlidingWindow(nums, 2));

        nums = new int[]{1, 2, 7, 7, 2};
        expected = Lists.newArrayList(1, 2, 7, 7, 2);
        Assert.assertEquals(expected, sliding.medianSlidingWindow(nums, 1));

        nums = new int[]{10};
        expected = Lists.newArrayList(10);
        Assert.assertEquals(expected, sliding.medianSlidingWindow(nums, 1));

        /*性能测试*/
        nums = new int[]{10};
        expected = Lists.newArrayList(10);
        Assert.assertEquals(expected, sliding.medianSlidingWindow(nums, 107));
    }


}
