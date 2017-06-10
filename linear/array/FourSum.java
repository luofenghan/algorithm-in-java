import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @title 给一个包含n个数的整数数组S，在S中找到所有使得和为给定整数target的四元组(a, b, c, d)。
 * @example 例如，对于给定的整数数组 S=[1, 0, -1, 0, -2, 2] 和 target=0 . 满足要求的四元组集合为：
 * <p>
 * (-1, 0, 0, 1)
 * <p>
 * (-2, -1, 1, 2)
 * <p>
 * (-2, 0, 0, 2)
 * @attention 四元组(a, b, c, d)中，需要满足 a <= b <= c <= d 答案中不可以包含重复的四元组。
 */
public class FourSum {
    public ArrayList<ArrayList<Integer>> find(int[] numbers, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (numbers == null || numbers.length < 4) {
            return result;
        }
        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length; i++) {
            if (i != 0 && numbers[i] == numbers[i - 1]) continue;

            int subTarget = target - numbers[i];
            for (int j = i + 1; j < numbers.length - 2; j++) {
                int left = j + 1, right = numbers.length - 1;
                while (left < right) {

                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
        FourSum fourSum = new FourSum();

        int[] nums;
        ArrayList<ArrayList<Integer>> expected;
        ArrayList<ArrayList<Integer>> result;

        /*功能测试*/
        nums = new int[]{1, 0, -1, 0, -2, 2}; //-2,-1,0,0,1,2
        expected = Lists.newArrayList(Lists.newArrayList(-1, 0, 0, 1), Lists.newArrayList(-2, -1, 1, 2), Lists.newArrayList(-2, 0, 0, 2));
        result = fourSum.find(nums, 0);
        Arrays.equals(expected.toArray(), result.toArray());


    }


}
