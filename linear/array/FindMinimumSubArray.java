import org.junit.Assert;

import java.util.ArrayList;

/**
 * @title 给定一个整数数组，找到一个具有最小和的子数组。返回其最小和。
 * @example 给出数组[1, -1, -2, 1]，返回 -3
 */
public class FindMinimumSubArray {
    private static class SubArray {
        int left;
        int right;
        int sum;

        public SubArray(int left, int right, int sum) {
            this.left = left;
            this.right = right;
            this.sum = sum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SubArray subArray = (SubArray) o;

            if (left != subArray.left) return false;
            if (right != subArray.right) return false;
            return sum == subArray.sum;
        }

        @Override
        public int hashCode() {
            int result = left;
            result = 31 * result + right;
            result = 31 * result + sum;
            return result;
        }
    }

    public SubArray find(ArrayList<Integer> array) {
        return find(array, 0, array.size() - 1);
    }

    private SubArray find(ArrayList<Integer> array, int low, int mid, int high) {
        int minLeftSum = Integer.MAX_VALUE;
        int minLeftIndex = low;
        for (int i = mid, sum = 0; i >= low; i--) {
            sum += array.get(i);
            if (minLeftSum > sum) {
                minLeftSum = sum;
                minLeftIndex = i;
            }
        }

        int minRightSum = Integer.MAX_VALUE;
        int minRightIndex = high;
        for (int i = mid + 1, sum = 0; i <= high; i++) {
            sum += array.get(i);
            if (minRightSum > sum) {
                minRightSum = sum;
                minRightIndex = i;
            }
        }

        return new SubArray(minLeftIndex, minRightIndex, minLeftSum + minRightSum);
    }

    private SubArray find(ArrayList<Integer> array, int low, int high) {
        if (low == high) {
            return new SubArray(low, high, array.get(low));
        }
        int mid = (low + high) / 2;
        SubArray left = find(array, low, mid);
        SubArray right = find(array, mid + 1, high);
        SubArray cross = find(array, low, mid, high);

        if (left.sum <= right.sum && left.sum <= cross.sum) return left;
        else if (right.sum <= left.sum && right.sum <= cross.sum) return right;
        else return cross;
    }

    public static void main(String[] args) {
        FindMinimumSubArray subArray = new FindMinimumSubArray();

        /*功能测试*/
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(1);
        Assert.assertEquals(new SubArray(0, 0, 1), subArray.find(arrayList));

    }
}
