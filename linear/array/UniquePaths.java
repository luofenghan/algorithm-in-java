import org.junit.Assert;

/**
 * @title 有一个机器人的位于一个 m × n 个网格左上角。 机器人每一时刻只能向下或者向右移动一步。机器人试图达到网格的右下角。问有多少条不同的路径？
 * @example 给出 m = 3 和 n = 3, 返回 6. 给出 m = 4 和 n = 5, 返回 35.
 * @attention n和m均不超过100
 */
public class UniquePaths {
    private class Matrix {
        int m;
        int n;
        int paths;

        public Matrix(int m, int n, int paths) {
            this.m = m;
            this.n = n;
            this.paths = paths;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Matrix matrix = (Matrix) o;

            if (m != matrix.m) return false;
            return n == matrix.n;
        }

        @Override
        public int hashCode() {
            int result = m;
            result = 31 * result + n;
            return result;
        }
    }

    public int uniquePaths(int m, int n) {

        return 0;
    }


    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();

        Assert.assertEquals(6, uniquePaths.uniquePaths(3, 3));
        Assert.assertEquals(35, uniquePaths.uniquePaths(4, 5));

    }
}
