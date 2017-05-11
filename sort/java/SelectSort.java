/**
 * Created by cwc on 2017/5/11 0011.
 */
public class SelectSort {


    public static class SimpleSelectSort {
        /**
         * 按照索引顺序，每趟选择一个最小元素与当前索引元素进行交换
         */
        public static void sort(int[] array) {
            for (int i = 0; i < array.length; i++) {
                int minValue = array[i];
                int minIndex = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < minValue) {
                        minValue = array[j];
                        minIndex = j;
                    }
                }
                array[minIndex] = array[i];
                array[i] = minValue;
            }
        }

        /**
         * 将每趟循环可以确定两个元素（最大和最小值），从而减少排序所需的循环次数。
         * 改进后对n个数据进行排序，最多只需进行[n/2]趟即可
         *
         * @param array
         */
        public static void sort2(int[] array) {
            for (int i = 1, min, max, len = array.length; i <= len / 2; i++) {
                min = max = i;
                for (int j = i + 1; j <= len - i; j++) {
                    if (array[j] > array[max]) {
                        max = j;
                        continue;
                    }
                    if (array[j] < array[min]) {
                        min = j;
                    }
                }
                //该交换操作还可分情况讨论以提高效率
                Utils.swap(array, min, i - 1);
                Utils.swap(array, max, len - i);
            }
        }
    }

    public static class HeapSort {
        public static void sort(int[] array) {
            for (int i = array.length / 2; i >= 0; i--) {
                percolateDown(array, i, array.length);
            }
            for (int i = array.length - 1; i > 0; i--) {
                Utils.swap(array, 0, i);
                percolateDown(array, 0, i);
            }
        }

        private static int leftChild(int i) {
            return 2 * i + 1;
        }

        private static void percolateDown(int[] a, int i, int n) {
            int child;
            int tmp;
            for (tmp = a[i]; leftChild(i) < n; i = child) {
                child = leftChild(i);
                //找到i孩子节点中最大的一个
                if (child != n - 1 && a[child] < a[child + 1]) {
                    child++;//i的右孩子
                }
                if (tmp < a[child]) {//如果较大的子结点大于父结点
                    a[i] = a[child]; // 那么把较大的子结点往上移动，替换它的父结点
                } else {
                    break;
                }
            }
            a[i] = tmp;
        }

    }

    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


        System.out.println("简单选择排序：");
        Utils.shuffle(arrays);
        Utils.print("\tbefore sort:", arrays);
        SimpleSelectSort.sort2(arrays);
        Utils.print("\t after sort:", arrays);

        System.out.println("堆排序：");
        Utils.shuffle(arrays);
        Utils.print("\tbefore sort:", arrays);
        HeapSort.sort(arrays);
        Utils.print("\t after sort:", arrays);
    }
}
