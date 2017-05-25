import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 给定一个非负数，表示一个数字数组，在该数的基础上+1，返回一个新的数组。 该数字按照大小进行排列，最大的数在列表的最前面。
 * @example 给定 [1,2,3] 表示 123, 返回 [1,2,4]. 给定 [9,9,9] 表示 999, 返回 [1,0,0,0].
 */
public class PlusOne {

    public int[] plus(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        return carry(digits, digits.length - 1);
    }

    private int[] carry(int[] digits, int position) {
        if (position < 0) {
            return digits;
        }
        if (digits[position] == 9) {
            digits[position] = 0;
            if (position == 0) {
                int[] tmp = new int[digits.length + 1];
                System.arraycopy(digits, 0, tmp, 1, digits.length);
                digits = tmp;
                position++;
            }
            digits = carry(digits, position - 1);
        } else {
            digits[position]++;
        }
        return digits;
    }

    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();

        /*功能测试*/
        Assert.assertTrue(Arrays.equals(new int[]{1, 2, 4}, plusOne.plus(new int[]{1, 2, 3})));
        Assert.assertTrue(Arrays.equals(new int[]{1, 0, 0, 0}, plusOne.plus(new int[]{9, 9, 9})));

        /*负面测试*/
        Assert.assertTrue(Arrays.equals(new int[]{}, plusOne.plus(new int[]{})));
        Assert.assertTrue(Arrays.equals(null, plusOne.plus(null)));

    }
}
