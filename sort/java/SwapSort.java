/**
 * Created by cwc on 2017/5/10 0010.
 */
public class SwapSort {

    public static class BubbleSort {
        public static void sort(int[] array) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        Utils.swap(array, j, j + 1);
                    }
                }
            }
        }

        /**
         * 设置标记变量pos，用于记录每趟排序中最后一次进行交换的位置，
         * 由于pos位置之后的记录已交换到位，所以在进行下一趟排序只要扫描到pos位置即可。
         *
         * @param array
         */
        public static void sort1(int[] array) {
            for (int i = array.length - 1; i > 0; ) {
                int pos = 0;
                for (int j = 0; j < i; j++) {
                    if (array[j] > array[j + 1]) {
                        pos = j;
                        Utils.swap(array, j, j + 1);
                    }
                }
                i = pos;
            }
        }

        /**
         * 传统冒泡排序每一堂排序操作只能找到一个最大值或者最小值，
         * 因此，考虑利用在每趟排序中进行正向和反向冒泡的方式可以一趟得到两个最终值（最小值和最大值），
         * 从而使排序躺输几乎减少了一半。
         *
         * @param array
         */
        public static void sort2(int[] array) {
            int low = 0;
            int high = array.length - 1;
            while (low < high) {
                for (int i = low; i < high; i++) {
                    if (array[i] > array[i + 1]) {
                        Utils.swap(array, i, i + 1);
                    }
                }
                high--;
                for (int i = high; i > low; i--) {
                    if (array[i] < array[i - 1]) {
                        Utils.swap(array, i, i - 1);
                    }
                }
                low++;
            }

        }
    }

    public static class QuickSort {

        private static void quickSort(int[] array, int low, int high) {
            if (low < high) {
                int pivot = partition(array, low, high);
                quickSort(array, 0, pivot - 1);
                quickSort(array, pivot + 1, high);
            }
        }

        private static void quickSortImprove(int[] array, int low, int high, int k) {
            if (high - low > k) {
                int pivot = partition(array, low, high);
                quickSortImprove(array, 0, pivot - 1, k);
                quickSortImprove(array, pivot + 1, high, k);
            }
        }

        private static int partition(int[] array, int low, int high) {
            for (int pivot = array[low]; low < high; ) {//从表的两端交替地向中间扫描
                while (low < high && array[high] >= pivot) { //从high 所指位置向前搜索，至多到low+1 位置。将比基准元素小的交换到低端
                    high--;
                }
                Utils.swap(array, low, high);
                while (low < high && array[low] <= pivot) {
                    low++;
                }
                Utils.swap(array, low, high);
            }
            return low;
        }

        public static void sort(int[] array) {
            quickSort(array, 0, array.length - 1);
        }

        public static void sort(int[] array, int k) {
            quickSortImprove(array, 0, array.length - 1, k);
            InsertSort.StraightInsertSort.sort(array);
        }

    }

    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


        System.out.println("冒泡排序：");
        Utils.shuffle(arrays);
        Utils.print("\tbefore sort:", arrays);
        BubbleSort.sort2(arrays);
        Utils.print("\t after sort:", arrays);

        System.out.println("快速排序：");
        Utils.shuffle(arrays);
        Utils.print("\tbefore sort:", arrays);
        QuickSort.sort(arrays, 4);
        Utils.print("\t after sort:", arrays);
    }
}
