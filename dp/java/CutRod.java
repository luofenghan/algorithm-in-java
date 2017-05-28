import org.junit.Assert;

/**
 * Created by cwc on 2017/5/22 0022.
 */
public class CutRod {

    public static int simpleCut(int[] price, int length) {
        if (length == 0) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i <= length; i++) {
            profit = Math.max(profit, price[i - 1] + simpleCut(price, length - i));
        }
        return profit;
    }


    public static int memoizedCut(int[] price, int length) {
        if (length == 0) {
            return 0;
        }
        int[] memoized = new int[length + 1];
        for (int i = 0; i < memoized.length; i++) {
            memoized[i] = Integer.MIN_VALUE;
        }
        return memoizedCut(price, length, memoized);
    }

    private static int memoizedCut(int[] price, int length, int[] memoized) {
        if (memoized[length] >= 0) {
            return memoized[length];
        }
        int profit = 0;
        if (length == 0) {
            return profit;
        }
        for (int i = 1; i <= length; i++) {
            profit = Math.max(profit, price[i - 1] + memoizedCut(price, length - i, memoized));
        }
        memoized[length] = profit;
        return profit;
    }

    public static int bottomUpCut(int[] price, int length) {
        int[] memoized = new int[length + 1];
        int profit = 0;
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("price[r%d=%d],memoized[r%d=%d]\n",j,price[j - 1],i - j,memoized[i - j]);
                profit = Math.max(profit, price[j - 1] + memoized[i - j]);
            }
            memoized[i] = profit;
            System.out.printf("memoized[r%d]=%d\n---\n",i,profit);
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    /*    Assert.assertEquals(30, memoizedCut(price, 10));
        Assert.assertEquals(18, memoizedCut(price, 7));
        Assert.assertEquals(0, memoizedCut(price, 0));

        Assert.assertEquals(30, bottomUpCut(price, 10));
        Assert.assertEquals(18, bottomUpCut(price, 7));
        Assert.assertEquals(0, bottomUpCut(price, 0));*/

        long start = System.nanoTime();
        Assert.assertEquals(30, simpleCut(price, 10));
        long end = System.nanoTime();
        System.out.printf("自顶向下简单递归的时间：%d\n",end-start);

         start = System.nanoTime();
        Assert.assertEquals(30, memoizedCut(price, 10));
         end = System.nanoTime();
        System.out.printf("带备忘的自顶向下法的时间：%d\n",end-start);

        start = System.nanoTime();
        Assert.assertEquals(30, bottomUpCut(price, 10));
        end = System.nanoTime();
        System.out.printf("带备忘的自底向上法的时间：%d\n",end-start);
    }
}
