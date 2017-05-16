/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class OrderedTwoDimensionArray {
    public static boolean find(int target, int[][] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        final int rows = array.length;
        for (int i = 0, j = array[0].length - 1; i < rows && j >= 0; ) {
            int val = array[i][j];
            if (val == target) {
                return true;
            } else if (target > val) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] array = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {2, 4, 6, 8, 10, 12, 14, 16},
                {3, 4, 7, 9, 11, 13, 15, 17}
        };
        System.out.println(OrderedTwoDimensionArray.find(34, array));

    }

}
