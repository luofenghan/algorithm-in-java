import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @title 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
 * @attention 你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 1 到 n，不是以 0 开头。
 * @example 给出 numbers = [2, 7, 11, 15], target = 9, 返回 [1, 2].
 */
public class TwoSum {

    public int[] twoSum1(int[] numbers, int target) {
        int[] res = new int[2];

        if (numbers == null || numbers.length <= 1) {
            return res;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                res[0] = map.get(target - numbers[i]) + 1;
                res[1] = i + 1;
                return res;
            }
            map.put(numbers[i], i);
        }
        return res;
    }

    public int[] twoSum2(int[] numbers, int target) {
        int[] res = new int[2];
        if (numbers == null || numbers.length <= 1) {
            return res;
        }
        Arrays.sort(numbers);
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                res[0] = numbers[l] + 1;
                res[1] = numbers[r] + 1;
                return res;
            } else if (numbers[l] + numbers[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();

        int[] nums;

        /*功能测试*/
        nums = new int[]{2, 7, 11, 15, 12};
        Assert.assertTrue(Arrays.equals(new int[]{2, 3}, twoSum.twoSum1(nums, 18)));

        nums = new int[]{2, 7, 11, 15, 12};
        Assert.assertTrue(Arrays.equals(new int[]{1, 2}, twoSum.twoSum2(nums, 9)));

        nums = new int[]{2, 7, 11, 15, 12};
        Assert.assertTrue(Arrays.equals(new int[]{4, 5}, twoSum.twoSum1(nums, 27)));

        nums = new int[]{1, 0, -1};
        Assert.assertTrue(Arrays.equals(new int[]{2, 3}, twoSum.twoSum2(nums, -1)));

        /*负面测试*/
        nums = new int[]{12};
        Assert.assertTrue(Arrays.equals(new int[]{1, 1}, twoSum.twoSum2(nums, 12)));

        nums = new int[]{12};
        Assert.assertTrue(Arrays.equals(new int[]{0, 0}, twoSum.twoSum2(nums, 2)));

        Assert.assertTrue(Arrays.equals(new int[]{0, 0}, twoSum.twoSum2(null, 2)));
    }
}
