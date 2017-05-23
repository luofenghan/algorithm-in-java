import org.junit.Assert;

/**
 * @title 给定一个整数数组，找到一个具有最大和的子数组，返回其最大和。
 * @example 给出数组[−2,2,−3,4,−1,2,1,−5,3]，符合要求的子数组为[4,−1,2,1]，其最大和为6
 */
public class FindMaximumSubArray {
    private static class SubArray {
        int low;
        int high;
        int sum;

        public SubArray(int low, int high, int sum) {
            this.low = low;
            this.high = high;
            this.sum = sum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SubArray subArray = (SubArray) o;

            return low == subArray.low && high == subArray.high && sum == subArray.sum;
        }

        @Override
        public int hashCode() {
            int result = low;
            result = 31 * result + high;
            result = 31 * result + sum;
            return result;
        }

    }

    private SubArray find(int[] array, int low, int mid, int high) {
        int maxLeftSum = Integer.MIN_VALUE;
        int maxLeft = low;
        for (int i = mid, sum = 0; i >= low; i--) {
            sum += array[i];
            if (sum > maxLeftSum) {
                maxLeftSum = sum;
                maxLeft = i;
            }
        }

        int maxRightSum = Integer.MIN_VALUE;
        int maxRight = high;
        for (int j = mid + 1, sum = 0; j <= high; j++) {
            sum += array[j];
            if (sum > maxRightSum) {
                maxRightSum = sum;
                maxRight = j;
            }
        }
        return new SubArray(maxLeft, maxRight, maxLeftSum + maxRightSum);
    }

    public SubArray find(int[] array) {
        return find(array, 0, array.length - 1);
    }

    private SubArray find(int[] array, int low, int high) {
        if (low == high) {
            return new SubArray(low, high, array[low]);
        }

        int mid = (low + high) / 2;
        SubArray left = find(array, low, mid);
        SubArray right = find(array, mid + 1, high);
        SubArray cross = find(array, low, mid, high);
        if (left.sum >= right.sum && left.sum >= cross.sum)
            return left;
        else if (right.sum >= left.sum && right.sum >= cross.sum)
            return right;
        else
            return cross;

    }


    public static void main(String[] args) {
        FindMaximumSubArray subArray = new FindMaximumSubArray();

        /*功能测试*/
        Assert.assertEquals(new SubArray(3, 6, 6), subArray.find(new int[]{-2, 2, -3, 4, -1, 2, 1, -5, 3}));
        Assert.assertEquals(new SubArray(0, 0, 1), subArray.find(new int[]{1}));
        Assert.assertEquals(new SubArray(0, 0, -1), subArray.find(new int[]{-1, -2, -3, -100, -1, -50}));

        /*负面测试*/

        /*边界测试*/

        /*性能测试*/

    }


}
