import org.junit.Assert;

/**
 * @title 汉明距离
 * @explain 汉明距离是一个概念，它表示两个（相同长度）字对应位不同的数量。对两个字符串进行异或运算，并统计结果为1的个数，那么这个数就是汉明距离
 * @difficulty easy
 */
public class HammingDistance {

    public static int solution1(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public static int solution2(int x, int y) {
        int count = 0;
        for (int xor = x ^ y; xor > 0; xor &= xor - 1) ++count;
        return count;
    }

    public static int solution3(int x, int y) {
        int count = 0;
        for (int i = 0, xor = x ^ y; i < 32; i++) {
            count += (xor >> 1) & 1;
        }
        return count;
    }

    public static int solution4(int x, int y) {
        int count = 0;
        for (int xor = x ^ y; xor > 0; xor >>= 1) {
            if ((xor >> 1) << 1 != xor) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int distance = HammingDistance.solution2(1, 4);
        Assert.assertEquals(2, distance);
    }
}
