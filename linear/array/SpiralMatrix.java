import com.google.common.collect.Lists;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwc on 2017/06/10 0010.
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiralPath = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return spiralPath;
        }
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        int i;
        while (true) {
            //上边，自左至右
            for (i = left; i <= right; i++) spiralPath.add(matrix[top][i]);
            if (++top > bottom) break;

            //右边，自上至下
            for (i = top; i <= bottom; i++) spiralPath.add(matrix[i][right]);
            if (left > --right) break;

            //下边，自右至左
            for (i = right; i >= left; i--) spiralPath.add(matrix[bottom][i]);
            if (top > --bottom) break;

            //左边，自下至上
            for (i = bottom; i >= top; i--) spiralPath.add(matrix[i][left]);
            if (++left > right) break;
        }
        return spiralPath;
    }


    public static void main(String[] args) {
        SpiralMatrix spiralMatrix = new SpiralMatrix();

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> expected = Lists.newArrayList(1, 2, 3, 6, 9, 8, 7, 4, 5);
        Assert.assertEquals(expected, spiralMatrix.spiralOrder(matrix));

    }
}
