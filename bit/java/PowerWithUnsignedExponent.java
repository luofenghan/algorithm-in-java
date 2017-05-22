import org.junit.Assert;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class PowerWithUnsignedExponent {

    public static double power(double base, int exponent) {
        if (base == 0 && exponent < 0) {
            throw new IllegalArgumentException();
        }
        if (exponent < 0) {
            return 1 / power2(base, -exponent);
        } else {
            return power2(base, exponent);
        }
    }

    private static double power2(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        // 使用右移运算符代替了除以2
        double result = power2(base, exponent >> 1);
        result *= result;
        // 使用位与运算符代替求余运算符来判断是奇数还是偶数
        if ((exponent & 0x1) == 1) {
            result *= base;
        }
        return result;
    }

    public static void main(String[] args) {

        Assert.assertEquals(0.125, power(2, -3), 0);
        Assert.assertEquals(1, power(21, 0), 0);
        Assert.assertEquals(21, power(21, 1), 0);
        Assert.assertEquals(0, power(0, 1), 0);
        Assert.assertEquals(1, power(0, 0), 0);
        try {
            power(0, -1);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
        }
    }
}
