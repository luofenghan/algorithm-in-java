import com.google.common.collect.Lists;
import org.junit.Assert;

import java.util.List;

/**
 * @title 给你一个数n生成一个包含1-n^2的螺旋形矩阵
 * @example n = 3
 * 矩阵为
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrixII {

    public int[][] generateMatrix(int n) {
        // Write your code here
        if (n <= 0) {
            return new int[0][0];
        }
        int[][] spiralMatrix = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;

        int i = 0;
        int num = 1;
        while (true) {
            /*从左到右*/
            for (i = left; i <= right; i++) spiralMatrix[top][i] = num++;
            if (++top > bottom) break;

            /*从上到下*/
            for (i = top; i <= bottom; i++) spiralMatrix[i][right] = num++;
            if (left > --right) break;

            /*从右到左*/
            for (i = right; i >= left; i--) spiralMatrix[bottom][i] = num++;
            if (top > --bottom) break;

            //左边，自下至上
            for (i = bottom; i >= top; i--) spiralMatrix[i][left] = num++;
            if (++left > right) break;
        }
        return spiralMatrix;

    }

    public static void main(String[] args) {
        SpiralMatrixII spiralMatrixII = new SpiralMatrixII();
        SpiralMatrix spiralMatrix2 = new SpiralMatrix();

        int n = 3;
        List<Integer> expected = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(expected, spiralMatrix2.spiralOrder(spiralMatrixII.generateMatrix(n)));


        n = 2;
        expected = Lists.newArrayList(1, 2, 3, 4);
        Assert.assertEquals(expected, spiralMatrix2.spiralOrder(spiralMatrixII.generateMatrix(n)));
    }
}
