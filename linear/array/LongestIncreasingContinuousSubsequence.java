import org.junit.Assert;

/**
 * @title 给定一个整数数组（下标从 0 到 n-1， n 表示整个数组的规模），请找出该数组中的最长上升连续子序列。
 * <></>    最长上升连续子序列可以定义为从右到左或从左到右的序列
 * @example 给定 [5, 4, 2, 1, 3], 其最长上升连续子序列（LICS）为 [5, 4, 2, 1], 返回 4.
 * <></>     给定 [5, 1, 2, 3, 4], 其最长上升连续子序列（LICS）为 [1, 2, 3, 4], 返回 4.
 */
public class LongestIncreasingContinuousSubsequence {

    public int longestSubsequence(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int maxIncrease = 0, increase = 0;
        int maxDecrease = 0, decrease = 0;
        for (int i = 1, flag = -1; i < array.length; i++) {
            if (flag == -1) {
                flag = array[i] > array[i - 1] ? 1 : 0;
                if (flag == 1)
                    increase++;
                else
                    decrease++;
                continue;
            }
            if (array[i - 1] < array[i]) {
                if (flag == 0) {
                    maxDecrease = maxDecrease > decrease + 1 ? maxDecrease : decrease + 1;
                    decrease = 0;
                }
                increase++;
                flag = 1;
            } else {
                if (flag == 1) {
                    maxIncrease = maxIncrease > increase + 1 ? maxIncrease : increase + 1;
                    increase = 0;
                }
                decrease++;
                flag = 0;
            }
        }
        maxDecrease = maxDecrease > decrease + 1 ? maxDecrease : decrease + 1;
        maxIncrease = maxIncrease > increase + 1 ? maxIncrease : increase + 1;
        return maxIncrease >= maxDecrease ? maxIncrease : maxDecrease;
    }


    public static void main(String[] args) {
        LongestIncreasingContinuousSubsequence longest = new LongestIncreasingContinuousSubsequence();


        /*功能测试*/
        Assert.assertEquals(4, longest.longestSubsequence(new int[]{5, 4, 2, 1, 3}));
        Assert.assertEquals(4, longest.longestSubsequence(new int[]{5, 1, 2, 3, 4}));
        Assert.assertEquals(2, longest.longestSubsequence(new int[]{5, 1, 6, 1, 4}));

        /*负面测试*/
        Assert.assertEquals(0, longest.longestSubsequence(null));


        /*边界测试*/
        Assert.assertEquals(2, longest.longestSubsequence(new int[]{5, 1}));
        Assert.assertEquals(2, longest.longestSubsequence(new int[]{5, 10, 1}));
        Assert.assertEquals(1, longest.longestSubsequence(new int[]{5}));
        /*性能测试*/

    }
}
