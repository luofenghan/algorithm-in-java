import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 给一个包含 n 个整数的数组 S, 找到和与给定整数 target 最接近的三元组，返回这三个数的和。
 * @example 例如 S = [-1, 2, 1, -4] and target = 1. 和最接近 1 的三元组是 -1 + 2 + 1 = 2.
 * @challenge O(n^2) 时间, O(1) 额外空间。
 */
public class ThreeSumClosest {

    public int find(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        Arrays.sort(numbers);
        int closet = 0;
        for (int i = 0, delta = Integer.MAX_VALUE, sum; i < numbers.length - 2; i++) {
            int left = i + 1, right = numbers.length - 1;
            while (left < right) {
                sum = numbers[i] + numbers[left] + numbers[right];
                int tmp = Math.abs(sum - target);
                if (tmp < delta) {
                    delta = tmp;
                    closet = sum;
                }
                if (sum < target) left++;
                else right--;
            }
        }
        return closet;
    }

    public static void main(String[] args) {
        ThreeSumClosest closest = new ThreeSumClosest();
        int[] numbers;

        /*功能测试*/
        numbers = new int[]{-1, 2, 1, -4};
        Assert.assertEquals(2, closest.find(numbers, 1));

        /*负面测试*/
        numbers = new int[]{};
        Assert.assertEquals(0, closest.find(numbers, 1));
    }
}
