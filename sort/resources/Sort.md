<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=default"></script>

## 插入排序
### 直接插入排序
#### 排序思想

按照索引顺序，每一步将该索引上的值插入到前面已经有序的一组的值适当位置（通过从当前索引处往前的挨个比较）上，直到全部插入为止，详细算法步骤如下：

1. 从第一个元素开始，该元素可以认为已经被排序；
2. 取出下一个X元素，在已经排序的元素序列中从后向前扫描；
3. 如果扫描到的元素（已排序）大于X元素，将该元素往后移动一个位置；
4. 重复步骤3，直到找到已排序的元素小于或者等于X元素的位置；
5. 将X元素插入到该位置；
6. 重复步骤2~4。
    
#### 排序演示

![直接插入排序](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E7%9B%B4%E6%8E%A5%E6%8F%92%E5%85%A5%E6%8E%92%E5%BA%8F%E5%8A%A8%E5%9B%BE%E6%BC%94%E7%A4%BA.gif)


#### 代码实现

```java
public static class StraightInsertSort {
    public static void sort(int[] e) {
        int j;
        for (int i = 1; i < e.length; i++) {
            int x = e[i];
            for (j = i - 1; j >= 0 && e[j] > x; j--) {
                e[j + 1] = e[j];
            }
            e[j + 1] = x;
        }
    }
}
```
    
#### 结论

1. 当输入数据以**反序**输入时，直接插入排序的时间复杂度为\\(O(N^2)\\)，因为由于嵌套循环的每一个都花费N次迭代；
2. 当输入数据已**预先排序**，直接插入排序的时间复杂度为\\(O(N)\\)，因为内层的for循环的检测总是立即判定不成立而终止。
3. 直接插入排序适合**数据量比较小**的排序应用；
4. **逆序数**也正好是需要由插入排序执行的**交换次数**，而一个排过序的数组没有逆序。当输入数据是`34,8,64,51,32,21`时，该数据有9个逆序，即`(34,8),(34,32),(34,21),(64,51),(64,32),(64,21),(51,32),(51,21)`以及`(32,21)`。由于算法还有\\(O(N)\\)量的其他工作，因此插入排序的运行时间是\\(O(I+N)\\)，其中I为原始数组中的逆序数。于是，若逆序数是\\(O(N)\\)，则插入排序以线性时间运行。
5. N个互异数的数组的平均逆序数是\\(N(N-1)/4\\)；
6. 通过**交换相邻元素**进行排序的任何算法平均时间复杂度都需要\\(O(N^2)\\)，也就是说，为了使一个排序算法以\\(O(N^2)\\)时间运行，必须执行一些比较，特别是要对相距较远的元素进行交换。一个排序算法通过删除逆序得以向前进行，而为了有效的进行，他必须使每次交换删除不止一个逆序。

### 二分插入排序
#### 排序思想
按照索引顺序，每一步将该索引上的值插入到前面已经有序的一组的值适当位置（通过二分查找法找到，可以减少比较次数）上，直到全部插入为止。
#### 代码实现
```java
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
```
#### 结论
1. 当N比较大时，二分插入排序的比较次数比直接插入排序的最差情况（反序）要好得多，但是比直接插入排序的最好情况（基本有序）要差。
2. 当元素初始序列接近有序时，直接插入排序比二分插入排序的比较次数少。
3. 二分插入排序元素移动次数与直接插入排序相同，依赖于元素的初始序列。

### 希尔排序
#### 排序思想
希尔排序通过将比较的全部元素分为几个区域来提升插入排序的性能，这样可以让一个元素可以一次性地朝最终位置前进一大步。然后算法再取越来越小的步长进行排序，算法的最后一步就是普通的插入排序，但到了这一步，数据几乎已经排好序。

#### 排序演示
![希尔排序](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E8%B0%A2%E5%B0%94%E6%8E%92%E5%BA%8F%E5%9B%BE%E8%A7%A3.png)

#### 代码实现
```java
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
}
```
#### 结论
1. 如果有一个**很小的数据**在一个已按升序排好序的数组的**末端**，如果用复杂度为\\(O(N^2)\\)的排序算法（**冒泡排序**或**直接插入排序**），可能会进行**n次的比较和交换**才能将该数据移至正确的位置。而希尔排序会用**较大的步长**移动数据，所以小数据只需要进行**少数比较和交换**即可到正确位置。
2. 使用增量序列\\(h_k\\)进行一趟排序后，对于每个i我们都有\\(a[i]<=a[i+h_k]\\)，所有相隔\\(h_k\\)的元素都被排序，此时称文件时\\(h_k\\)排序的。
3. \\(h_k\\)排序的实质就是，将\\(h_k\\)，\\(h_k+1\\)，···，\\(N-1\\)中的每个位置i，把该位置对应的元素放到\\(i-{h_k}\\)中的正确位置上。
4. 一趟\\(h_k\\)排序的作用就是对\\(h_k\\)个独立的子数组执行一次插入排序。
5. 一个\\(h_k\\)排序的文件（然后是\\(h_{k-1}\\)排序）会一直保持它的\\(h_k\\)排序性，前面各趟排序的成果不会被后面的排序打乱。
6. 希尔排序**不是稳定的排序算法**
    
    虽然一次插入排序是稳定的，不会改变相同元素的相对顺序，但在不同的插入排序过程中，相同的元素可能在各自的插入排序中移动，最后其稳定性将会被打乱。
    
    比如序列{ 3, 5, 10, **8**, 7, 2, _8_, 1, 20, 6 }h=2时分成两个子序列 { 3, 10, 7, _8_, 20 } 和  { 5, **8**, 2, 1, 6 } ，未排序之前第二个子序列中的8在前面，现在对两个子序列进行插入排序，得到 { 3, 7, _8_, 10, 20 } 和 { 1, 2, 5, 6, **8** } ，即 { 3, 1, 7, 2, _8_, 5, 10, 6, 20, **8** } ，两个8的相对次序发生了改变。

## 选择排序
### 简单选择排序
#### 排序思想
按照索引顺序，每趟会在该索引后的元素中找出一个最小元素与当前索引处的元素进行交换。
#### 排序演示
1. 简单排序过程示例

    ![简单排序](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E7%AE%80%E5%8D%95%E6%8E%92%E5%BA%8F%E8%BF%87%E7%A8%8B.jpg)
    
2. 动图演示
    
    ![简单排序](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E7%AE%80%E5%8D%95%E9%80%89%E6%8B%A9%E6%8E%92%E5%BA%8F.gif)

#### 代码实现
```java
public static class SimpleSelectSort {
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
}
```
#### 结论
1. 简单选择排序的时间复杂度都为\\(O(N^2)\\)；
2. 是不稳定的排序算法

#### 简单选择排序的改进
将每趟循环可以确定两个元素（最大和最小值），从而减少排序所需的循环次数。 改进后对\\(N\\)个数据进行排序，最多只需进行\\(N/2\\)趟即可
```java
public class SimpleSelectSort{
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
```
### 堆排序
#### 排序思想
优先队列可以用于以`O(NlogN)`时间来排序，基于该思想的算法叫做**堆排序heapsort**。在建立N个元素的二叉堆时，该阶段花费\\(O(N)\\)时间，然后又执行N次deleteMin操作，由于每个deleteMin花费时间\\(O(logN)\\)，因此总运行时间是\\(O(NlogN)\\)。

优先队列的算法主要问题在于，它使用了一个附加数组，因此，存储需求增加一倍。但不会太影响时间问题，附加的时间消耗只有\\(O(N)\\)，只是增加了空间复杂度。

那么对于以上问题，在堆排序中的解决方案是：在每次deleteMin之后，将堆缩小1。因此，堆中的最后一个单元可以用来存放刚刚删除的元素。使用这种策略，在最后一次deleteMin之后，该数组将以递减的顺序包含这些元素。如果想要排成更典型的递增顺序，那么可以在构建堆的时候建立**最大堆**。

#### 算法演示
![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E5%A0%86%E6%8E%92%E5%BA%8F%E5%8A%A8%E5%9B%BE.gif)

#### 代码实例
```java
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
```

## 交换排序
### 冒泡排序
#### 排序思想
通过交换使相邻的两个数变成小数在前大数在后，这样每次遍历后，最大的数就“沉”到最后面了，重复N次即可以使数组有序。
#### 排序演示
1. 动图演示

    ![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm739525-20160329100443676-1647340243%5B1%5D.gif)
    
2. 排序过程示意图

    ![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F%E8%BF%87%E7%A8%8B.jpg)

#### 代码实现
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
#### 结论
冒泡排序是基于比较的算法，时间复杂度为\\(O(N^2)\\)，只有在**n比较小**的时候性能才比较好。

#### 冒泡排序算法改进
1. 设置一个标志性变量pos，用于记录每趟排序中最后一次进行交换的位置。由于pos之后的记录均已交换到位，因此在下一趟排序时只要扫描到pos位置即可。
    ```java
    public class BubbleSort {
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
    }
    
    ```
    
2. 传统冒泡排序在每趟的操作中只能找到一个最大值或最小值，因此，考虑利用在每趟排序中进行正向和反向的两边冒泡方法一次可以得到两个最终值（最大值和最小值），从而使排序趟数几乎减少一半。
    ```java
    public class BubbleSort {
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
    ```
    
### 快速排序
#### 排序思想
1. 选取一个基准pivot元素，通常选择第一个元素或者最后一个元素；
2. 进行分区partition操作，通过一趟排序将待排序的记录分割成两个部分，其中一个部分的元素均比基准元素小，另一部分元素均比基准元素大；
3. 对每个分区递归地进行步骤1~3，递归的结束条件是子序列的大小是0或1，这时整体已经排好序。

#### 排序演示
1. 动图演示

    ![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%E5%8A%A8%E5%9B%BE.gif)
2. 排序过程

    ![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%E8%BF%87%E7%A8%8B.png)

#### 代码实现
```java
public class QuickSort{
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
}
```

#### 结论
1. 最**坏**的情况下，也就是每次选取的基准都是**最大或最小的元素**（例如，在上例7,8,10,9中），导致每次只划分出了一个子序列，需要进行n-1次划分才能结束递归，时间复杂度为`O(n^2)`；
2. 最好的情况下，每次选取的基准都能均匀划分，只需要`logN`次划分就能结束递归，时间复杂度为`O(logN)`。
3. 平均情况下，需要的时间复杂度为`O(NlogN)`。
4. 快速排序不是稳定的排序算法。

#### 快速排序算法改进
快速排序通常被认为在同数量级\\(O(NlogN)\\)的排序方法中性能最好的，若初始序列已经基本有序，快排反而退化为冒泡排序。

在改进的算法中，只对长度大于k的子序列递归调用快速排序，让原序列基本有序，然后再对整个基本有序的序列使用直接插入排序。实践证明，改进后的算法时间复杂度有所降低，且当k取8左右的时候，改进算法的性能最优。

```java
public class QuickSort{
    public static void sort(int[] array, int k) {
        quickSortImprove(array, 0, array.length - 1, k);
        StraightInsertSort.sort(array);
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
}
```
## 归并排序
### 归并排序递归实现
#### 排序思想
归并排序是采用分治法的一个非常典型的应用，归并排序的思想就是先**递归分解**数组，再**合并数组**。

先考虑**合并**两个有序数组，基本思路是两个输入数组A和B，一个输出数组C，以及3个计数器ai、bi、ci，他们的初始置于对应数组的开始端。A[ai]和B[bi]中
的最小者被拷贝到C中的下一个位置，相关的计数器向前推进一步。当两个输入表有一个用完时，则将另一个表剩余部分拷贝到C中。

再考虑**递归分解**，基本思路是将数组分解成`left`和`right`，如果这两个数组内部数据是有序的，那么就可以用上面的合并数组方式将这两个数组合并排序。
如何让这两个数组内部有序？可以再二分，直至分解出的小组含有一个元素为止，此时认为该小组内部已有序，然后**合并**排序相邻两个小组即可。

#### 排序演示
1. 动图演示

    ![归并排序动图](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E5%BD%92%E5%B9%B6%E6%8E%92%E5%BA%8F%E5%8A%A8%E5%9B%BE.gif)

2. 排序过程

    ![归并排序](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E5%BD%92%E5%B9%B6%E6%8E%92%E5%BA%8F.jpg)
    
    ```text
    before sort= 5 9 4 3 2 6 10 1 7 8 
    -------------------
    left[0:0]  = 5 
                        ====> 5 9 0 0 0 0 0 0 0 0 
    right[1:1] = 9 
    -------------------
    left[0:1]  = 5 9 
                        ====> 4 5 9 0 0 0 0 0 0 0 
    right[2:2] = 4 
    -------------------
    left[3:3]  = 3 
                        ====> 4 5 9 2 3 0 0 0 0 0 
    right[4:4] = 2 
    -------------------
    left[0:2]  = 4 5 9 
                        ====> 2 3 4 5 9 0 0 0 0 0 
    right[3:4] = 2 3 
    -------------------
    left[5:5]  = 6 
                        ====> 2 3 4 5 9 6 10 0 0 0  
    right[6:6] = 10 
    -------------------
    left[5:6]  = 6 10 
                        ====> 2 3 4 5 9 1 6 10 0 0  
    right[7:7] = 1 
    -------------------
    left[8:8]  = 7 
                        ====> 2 3 4 5 9 1 6 10 7 8 
    right[9:9] = 8 
    -------------------
    left[5:7]  = 1 6 10 
                        ====> 2 3 4 5 9 1 6 7 8 10
    right[8:9] = 7 8 
    -------------------
    left[0:4]  = 2 3 4 5 9 
                        ====> 1 2 3 4 5 6 7 8 9 10 
    right[5:9] = 1 6 7 8 10 
    -------------------
    
    after sort = 1 2 3 4 5 6 7 8 9 10 
    ```

#### 代码实现
```java
public class MergeSort {
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

    private static void merge(int[] array, int[] tmpArray, int leftStart, int rightStart, int rightEnd) {
        int leftEnd = rightStart - 1;
        int tmpStart = leftStart;
        int numElements = rightEnd - leftStart + 1;

        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (array[leftStart] <= array[rightStart]) {
                tmpArray[tmpStart++] = array[leftStart++];
            } else {
                tmpArray[tmpStart++] = array[rightStart++];
            }
        }
        /*将left剩余元素复制到tmp中*/
        while (leftStart <= leftEnd) {
            tmpArray[tmpStart++] = array[leftStart++];
        }
        
        /*将right剩余元素复制到tmp中*/
        while (rightStart <= rightEnd) {
            tmpArray[tmpStart++] = array[rightStart++];
        }


        for (int i = 0; i < numElements; i++, rightEnd--) {
            array[rightEnd] = tmpArray[rightEnd];
        }
    }
}
```

#### 结论
1. 归并排序是经典的分治策略，它将问题**分（divide）**成一些小问题然后递归求解，而**治（conquer）**的阶段则将分的阶段解得的各答案修补在一起。
2. 最差的情况下，归并排序的运行时间是\\(O(NlogN)\\)，但是有一个明显的问题，整个算法还要花费将数据拷贝到临时数组再拷贝回来这样一个附加的工作，明显减慢了排序的速度。
3. 归并排序使用所有流行排序算法中最少的比较次数。

### 归并排序非递归实现
#### 排序思想
将数组中的相邻元素两两配对，构成\\(N/2\\)个长度为2的排好序的子数组，
然后再将他们排序成长度为4的子数组段，如此下去，直至整个数组排好序。

#### 排序演示
![归并排序非递归图解](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E9%9D%9E%E9%80%92%E5%BD%92%E5%BD%92%E5%B9%B6%E6%8E%92%E5%BA%8F.jpg)

#### 代码实现
```java
public class MergeSort {

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
}
```

## 
## 排序综合比较
| 名称 | 最差时间复杂度 | 最优时间复杂度 | 平均时间复杂度 | 辅助空间 | 稳定性 |
|:---:|:-------------:|:------------:|:------------:|:------:|:-----:|
| 直接插入排序 | \\(O(N^2)\\) | \\(O(N)\\) | \\(O(N^2)\\) | \\(O(1)\\) | 稳定 |
| 二分插入排序 | \\(O(N^2)\\) | \\(O(NlogN)\\) | \\(O(N^2)\\) | \\(O(1)\\) | 稳定 |
| 希尔排序 | \\(O(N^2)\\) | \\(O(N)\\) | \\(O(NlogN)-O(N^2)\\) | \\(O(1)\\) | 不稳定 |
| 简单选择排序 | \\(O(N^2)\\) | \\(O(N^{1.3})\\) | \\(O(NlogN)-O(N^2)\\) | \\(O(1)\\) | 不稳定 |
| 堆排序 | \\(O(NlogN)\\) | \\(O(NlogN)\\) | \\(O(NlogN)\\) | \\(O(1)\\) | 不稳定 |
| 冒泡排序 | \\(O(N^2)\\) | \\(O(N)\\) | \\(O(N^2)\\) | \\(O(1)\\) | 稳定 |
| 快排序 | \\(O(N^2)\\) | \\(O(NlogN)\\) | \\(O(NlogN)\\) | \\(O(logN)-O(N)\\) | 不稳定 |
| 归并排序 | \\(O(NlogN)\\) | \\(O(NlogN)\\) | \\(O(NlogN)\\) | \\(O(N)\\) | 稳定 |


## 参考资料
1. [常用排序算法总结（性能+代码）](https://segmentfault.com/a/1190000002595152#articleHeader18)
2. [经典排序算法总结与实现](http://wuchong.me/blog/2014/02/09/algorithm-sort-summary/)
3. [白话经典算法系列](http://blog.csdn.net/morewindows/article/details/7961256)
4. [排序算法可视化](http://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html)
5. [所谓堆和堆排序](http://blog.csdn.net/super_chris/article/details/4581900)
6. [几种经典排序算法](http://6924918.blog.51cto.com/6914918/1260860)

