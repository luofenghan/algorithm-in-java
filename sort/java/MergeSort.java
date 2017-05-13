/**
 * Created by cwc on 2017/5/12 0012.
 */
public class MergeSort {


    /**
     * 递归排序
     *
     * @param array
     */
    public static void sort(int[] array) {
        int[] tmpArray = new int[array.length];
        sort(array, tmpArray, 0, array.length - 1);
    }

    private static void sort(int[] array, int[] tmp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            sort(array, tmp, left, center);
            sort(array, tmp, center + 1, right);
            merge(array, tmp, left, center + 1, right);
        }
    }

    private static void merge(int[] array, int[] temp, int leftStart, int rightStart, int rightEnd) {
        int leftEnd = rightStart - 1;
        int tmpStart = leftStart;
        int numElements = rightEnd - leftStart + 1;

        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (array[leftStart] <= array[rightStart]) {
                temp[tmpStart++] = array[leftStart++];
            } else {
                temp[tmpStart++] = array[rightStart++];
            }
        }
        /*将left剩余元素复制到tmp中*/
        while (leftStart <= leftEnd) {
            temp[tmpStart++] = array[leftStart++];
        }

        /*将right剩余元素复制到tmp中*/
        while (rightStart <= rightEnd) {
            temp[tmpStart++] = array[rightStart++];
        }


        for (int i = 0; i < numElements; i++, rightEnd--) {
            array[rightEnd] = temp[rightEnd];
        }
    }


    /**
     * 非递归排序
     */
    public static void sort2(int[] array) {
        int[] tmp = new int[array.length];
        for (int gap = 1, len = array.length, tmpIndex, leftStart, leftEnd, rightStart, rightEnd; gap < len; gap *= 2) {
            for (tmpIndex = 0, leftStart = 0; leftStart < len - gap; leftStart = rightEnd) {
                if ((rightEnd = ((rightStart = leftEnd = leftStart + gap) + gap)) > len) {
                    rightEnd = len;
                }

                while (leftStart < leftEnd && rightStart < rightEnd) {
                    tmp[tmpIndex++] = array[leftStart] > array[rightStart] ? array[rightStart++] : array[leftStart++];
                }

                while (leftStart < leftEnd) {
                    array[--rightStart] = array[--leftEnd];
                }

                while (tmpIndex > 0) {
                    array[--rightStart] = tmp[--tmpIndex];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println("归并排序（递归）：");
        Utils.shuffle(array);
        Utils.print("\tbefore sort:", array);
        MergeSort.sort(array);
        Utils.print("\t after sort:", array);

        System.out.println("归并排序（非递归）：");
        Utils.shuffle(array);
        Utils.print("\tbefore sort:", array);
        MergeSort.sort2(array);
        Utils.print("\t after sort:", array);

    }
}
