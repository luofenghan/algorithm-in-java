
@SuppressWarnings("all")
public class InsertSort {

    /**
     * 直接插入排序
     */
    public static class StraightInsertSort {
        public static void sort(int[] e) {
            sort(e, 1);
        }

        static void sort(int[] e, int gap) {
            int j;
            for (int i = gap; i < e.length; i++) {
                int current = e[i];
                for (j = i - gap; j >= 0 && e[j] > current; j -= gap) {
                    e[j + gap] = e[j];
                }
                e[j + gap] = current;
            }
        }
    }

    /**
     * 二分插入排序
     */
    public static class BinaryInsertSort {
        public static void sort(int[] array) {
            int left, current, mid, right;
            for (int i = 1; i < array.length; i++) {
                current = array[i];
                left = 0;
                right = i - 1;
                while (left <= right) {
                    mid = (left + right) / 2;
                    if (current > array[mid]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                for (int k = i - 1; k >= left; k--) {
                    array[k + 1] = array[k];
                }
                if (left != i) {
                    array[left] = current;
                }
            }
        }
    }

    /**
     * 希尔排序
     */
    public static class ShellSort {

        public static void sort(int[] e) {
            for (int gap = e.length / 2; gap > 0; gap /= 2) {
                int j;
                for (int i = gap; i < e.length; i++) {
                    int current = e[i];
                    for (j = i - gap; j >= 0 && e[j] > current; j -= gap) {
                        e[j + gap] = e[j];
                    }
                    e[j + gap] = current;
                }
            }
        }

        public static void sort1(int[] e) {
            for (int gap = e.length / 2; gap > 0; gap /= 2) {
                StraightInsertSort.sort(e, gap);
            }
        }
    }

    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


        System.out.println("直接插入排序：");
        Utils.shuffle(arrays);
        Utils.print("\tbefore sort:", arrays);
        StraightInsertSort.sort(arrays);
        Utils.print("\t after sort:", arrays);


        System.out.println("二分插入排序：");
        Utils.shuffle(arrays);
        Utils.print("\tbefore sort:", arrays);
        BinaryInsertSort.sort(arrays);
        Utils.print("\t after sort:", arrays);


        System.out.println("谢尔排序：");
        Utils.shuffle(arrays);
        Utils.print("\tbefore sort:", arrays);
        ShellSort.sort(arrays);
        Utils.print("\t after sort:", arrays);
    }

}
