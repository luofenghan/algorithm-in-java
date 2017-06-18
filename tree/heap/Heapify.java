import org.junit.Assert;

import java.util.Arrays;

/**
 * @title {
 * <p>
 * 给出一个整数数组，堆化操作就是把它变成一个最小堆数组。对于堆数组A，A[0]是堆的根，
 * 并对于每个A[i]，A [i * 2 + 1]是A[i]的左儿子并且A[i * 2 + 2]是A[i]的右儿子。
 * </p>
 * }
 * @challenge
 * @description <ol>
 * <li>什么是堆？</li>
 * </ol>
 */
public class Heapify {

    public void heapify(int[] array) {
        for (int i = array.length / 2; i >= 0; i--) {
            percolateDown(array, i, array.length);
        }
    }

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private void percolateDown(int[] array, int i, int n) {
        int child;
        int tmp;
        for (tmp = array[i]; leftChild(i) <= n; i = child) {
            child = leftChild(i);
            /*找到i孩子节点中最大的一个*/
            if (child != n && array[child] > array[child + 1]) {
                child++;
            }

            if (tmp > array[child]) {
                array[i] = array[child];
            } else {
                break;
            }
        }
        array[i] = tmp;
    }

    public static void main(String[] args) {
        Heapify heapify = new Heapify();

        int[] array;
        int[] expected;

        array = new int[]{3, 2, 1, 4, 5};
        expected = new int[]{1, 2, 3, 4, 5};
        heapify.heapify(array);
        Assert.assertTrue(Arrays.equals(expected, array));
    }
}
