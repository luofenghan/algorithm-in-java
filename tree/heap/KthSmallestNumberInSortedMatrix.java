import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @title 在一个排序矩阵中找从小到大的第 k 个整数。排序矩阵的定义为：每一行递增，每一列也递增。
 * @example 给出 k = 4 和一个排序矩阵：
 * <p>
 * [
 * [1 ,5 ,7],
 * [3 ,7 ,8],
 * [4 ,8 ,9],
 * ]
 * 返回 5。
 */
public class KthSmallestNumberInSortedMatrix {
    class Element {
        int x;
        int y;
        int val;

        public Element(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        if (0 == m) {
            return -1;
        }
        int n = matrix[0].length;
        PriorityQueue<Element> queue = new PriorityQueue<>(n, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return o1.val - o2.val;
            }
        });

        for (int i = 0; i < n; i++) {
            queue.add(new Element(0, i, matrix[0][i]));
        }

        for (int i = 0; i < k - 1; i++) {
            Element tmp = queue.poll();
            if (tmp.x < m - 1) {
                queue.add(new Element(tmp.x + 1, tmp.y, matrix[tmp.x + 1][tmp.y]));
            }
        }
        return queue.poll().val;
    }


    public static void main(String[] args) {
        KthSmallestNumberInSortedMatrix kthSmallest = new KthSmallestNumberInSortedMatrix();

        int[][] matrix;

        /*功能测试*/
        matrix = new int[][]{
                {3, 1, 4},
                {8, 5, 8},
                {1, 6, 9}
        };
        Assert.assertEquals(8, kthSmallest.kthSmallest(matrix, 8));

    }
}
