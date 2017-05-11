## 冒泡排序

### 排序思想
通过交换使相邻的两个数变成小数在前大数在后，这样每次遍历后，最大的数就“沉”到最后面了，重复N次即可以使数组有序。

### 算法实现
1. 动图演示

    ![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm739525-20160329100443676-1647340243%5B1%5D.gif)
    
2. 排序过程示意图

    ![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F%E8%BF%87%E7%A8%8B.jpg)
    
3. 代码实现

    ```java
    public static class BubbleSort {
        public static void sort(int[] array) {
            for (int i = 0; i < array.length; i++) {
                // j < array.length - i - 1 意思是后面的已经有序，不需要在判断
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        Utils.swap(array, j, j + 1);
                    }
                }
            }
        }
    }
    ```
    
### 结论
冒泡排序是基于比较的算法，时间复杂度为`O(N^2)`，只有在**n比较小**的时候性能才比较好。

### 算法改进
1. 设置一个标志性变量pos，用于记录每趟排序中最后一次进行交换的位置。由于pos之后的记录均已交换到位，因此在下一趟排序时只要扫描到pos位置即可。
    ```
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
    ```
    
2. 传统冒泡排序在每趟的操作中只能找到一个最大值或最小值，因此，考虑利用在每趟排序中进行正向和反向的两边冒泡方法一次可以得到两个最终值（最大值和最小值），从而使排序趟数几乎减少一半。
    ```
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
    ```
    
## 快速排序
### 排序思想
1. 选取一个基准pivot元素，通常选择第一个元素或者最后一个元素；
2. 进行分区partition操作，通过一趟排序将待排序的记录分割成两个部分，其中一个部分的元素均比基准元素小，另一部分元素均比基准元素大；
3. 对每个分区递归地进行步骤1~3，递归的结束条件是序列的大小是0或1，这时整体已经排好序。

### 算法实现
1. 动图演示

    ![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%E5%8A%A8%E5%9B%BE.gif)
2. 排序过程

    ![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%E8%BF%87%E7%A8%8B.png)
3. 代码实现
    ```
    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }
    
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            quickSort(array, 0, pivot - 1);
            quickSort(array, pivot + 1, high);
        }
    }
    
    private static int partition(int[] array, int low, int high) {
        for (int pivot = array[low]; low < high; ) {
            while (low < high && array[high] >= pivot) {
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
    ```

### 结论
1. 最坏的情况下，也就是每次选取的基准都是最大或最小的元素（例如，在上例7,8,10,9中），导致每次只划分出了一个子序列，需要进行n-1次划分才能结束递归，时间复杂度为`O(n^2)`；
2. 最好的情况下，每次选取的基准都能均匀划分，只需要`logN`次划分就能结束递归，时间复杂度为`O(logN)`。
3. 平均情况下，需要的时间复杂度为`O(NlogN)`。
4. 快速排序不是稳定的排序算法。

### 快速排序的改进
快速排序通常被认为在同数量级`O(NlogN)`的排序方法中性能最好的，若初始序列已经基本有序，快排反而退化为冒泡排序。

在改进的算法中，只对长度大于k的子序列递归调用快速排序，让原序列基本有序，然后再对整个基本有序的序列使用直接插入排序。实践证明，改进后的算法时间复杂度有所降低，且当k取8左右的时候，改进算法的性能最优。

```
public static void sort(int[] array, int k) {
    quickSortImprove(array, 0, array.length - 1, k);
    InsertSort.StraightInsertSort.sort(array);
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
```