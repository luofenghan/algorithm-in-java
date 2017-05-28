import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
 * @attention 你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 1 到 n，不是以 0 开头。
 * @example 给出 numbers = [2, 7, 11, 15], target = 9, 返回 [1, 2].
 */
public class TwoSum {
    private SubArray empty = new SubArray(-1, -1, 0);

    private static class SubArray {
        int start;
        int end;
        int sum;

        public SubArray(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) {
            return new int[]{0, 0};
        }
        SubArray array = find(numbers, target, 0, numbers.length - 1);
        if (array.sum == target) {
            return new int[]{array.start + 1, array.end + 1};
        } else {
            return new int[]{0, 0};
        }

    }

    private SubArray find(int[] numbers, int target, int low, int high) {
        if (low == high) {
            return new SubArray(low, high, numbers[low]);
        }
        int mid = (low + high) / 2;
        SubArray left = find(numbers, target, low, mid);
        if (left.sum == target) return left;
        SubArray right = find(numbers, target, mid + 1, high);
        if (right.sum == target) return right;
        SubArray cross = findFromCross(numbers, target, low, mid, high);
        if (cross.sum == target) return cross;
        return empty;
    }

    private SubArray findFromCross(int[] numbers, int target, int low, int mid, int high) {
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum += numbers[i];
            if (sum == target) {
                return new SubArray(i, mid, sum);
            }

        }
        for (int j = mid + 1; j <= high; j++) {
            sum += numbers[j];
            if (sum == target) {
                return new SubArray(mid, j, sum);
            }
        }
        return new SubArray(low, high, sum);

    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();

        int[] nums;

        /*功能测试*/
        nums = new int[]{2, 7, 11, 15, 12};
        Assert.assertTrue(Arrays.equals(new int[]{2, 3}, twoSum.twoSum(nums, 18)));

        nums = new int[]{2, 7, 11, 15, 12};
        Assert.assertTrue(Arrays.equals(new int[]{1, 2}, twoSum.twoSum(nums, 9)));

        nums = new int[]{2, 7, 11, 15, 12};
        Assert.assertTrue(Arrays.equals(new int[]{4, 5}, twoSum.twoSum(nums, 27)));

        nums = new int[]{1, 0, -1};
        Assert.assertTrue(Arrays.equals(new int[]{2, 3}, twoSum.twoSum(nums, -1)));

        /*负面测试*/
        nums = new int[]{12};
        Assert.assertTrue(Arrays.equals(new int[]{1, 1}, twoSum.twoSum(nums, 12)));

        nums = new int[]{12};
        Assert.assertTrue(Arrays.equals(new int[]{0, 0}, twoSum.twoSum(nums, 2)));

        nums = null;
        Assert.assertTrue(Arrays.equals(new int[]{0, 0}, twoSum.twoSum(nums, 2)));
    }
}
