/**
 * Created by cwc on 2017/5/16 0016.
 */
public class FindMaximumSubArray {
    private static class SubArray {
        int low;
        int high;
        int sum;

        SubArray(int low, int high, int sum) {
            this.low = low;
            this.high = high;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "SubArray{" +
                    "low=" + low +
                    ", high=" + high +
                    ", sum=" + sum +
                    '}';
        }
    }

    private static SubArray find(int[] array, int low, int mid, int high) {
        int maxLeftSum = 0;
        int maxLeft = 0;
        for (int i = mid, sum = 0; i >= low; i--) {
            sum += array[i];
            if (sum > maxLeftSum) {
                maxLeftSum = sum;
                maxLeft = i;
            }
        }

        int maxRightSum = 0;
        int maxRight = 0;
        for (int j = mid + 1, sum = 0; j < high; j++) {
            sum += array[j];
            if (sum > maxRightSum) {
                maxRightSum = sum;
                maxRight = j;
            }
        }
        return new SubArray(maxLeft, maxRight, maxLeftSum + maxRightSum);
    }

    public static SubArray find(int[] array) {
        return find(array, 0, array.length - 1);
    }

    private static SubArray find(int[] array, int low, int high) {
        if (low == high) {
            return new SubArray(low, high, array[low]);
        }

        int mid = (low + high) / 2;
        SubArray left = find(array, low, mid);
        SubArray right = find(array, mid + 1, high);
        SubArray cross = find(array, low, mid, high);
        if (left.sum >= right.sum
                && left.sum >= cross.sum) {
            return left;
        } else if (right.sum >= left.sum
                && right.sum >= cross.sum) {
            return right;
        } else {
            return cross;
        }
    }

    public static void main(String[] args) {
        int[] stockChangeArray = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        SubArray array = FindMaximumSubArray.find(stockChangeArray);
        System.out.println(array);
    }
}
