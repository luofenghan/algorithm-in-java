
## 简单选择排序
### 排序思想
按照索引顺序，每趟会在该索引后的元素中找出一个最小元素与当前索引处的元素进行交换。

### 算法实现
1. 简单排序过程示例

    ![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E7%AE%80%E5%8D%95%E6%8E%92%E5%BA%8F%E8%BF%87%E7%A8%8B.jpg)
    
2. 动图演示
    
    ![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E7%AE%80%E5%8D%95%E9%80%89%E6%8B%A9%E6%8E%92%E5%BA%8F.gif)

3. 代码实现
    ```
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
### 结论
1. 简单选择排序的时间复杂度都为`O(N^2)`；
2. 是不稳定的排序算法

### 简单选择排序的改进
将每趟循环可以确定两个元素（最大和最小值），从而减少排序所需的循环次数。 改进后对n个数据进行排序，最多只需进行[n/2]趟即可
```text
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
```

## 堆排序
### 排序思想
优先队列可以用于以`O(NlogN)`时间来排序，基于该思想的算法叫做**堆排序heapsort**。在建立N个元素的二叉堆时，该阶段花费`O(N)`时间，然后又执行N次deleteMin操作，由于每个deleteMin花费时间`O(logN)`，因此总运行时间是`O(NlogN)`。

优先队列的算法主要问题在于，它使用了一个附加数组，因此，存储需求增加一倍。但不会太影响时间问题，附加的时间消耗只有`O(N)`，只是增加了空间复杂度。

那么对于以上问题，在堆排序中的解决方案是：在每次deleteMin之后，将堆缩小1。因此，堆中的最后一个单元可以用来存放刚刚删除的元素。使用这种策略，在最后一次deleteMin之后，该数组将以递减的顺序包含这些元素。如果想要排成更典型的递增顺序，那么可以在构建堆的时候建立**最大堆**。

### 算法描述
1. **构造最大堆（Build_Max_Heap）**：若数组下标范围为0~n，考虑到单独一个元素是大根堆，则从下标`n/2`开始的元素均为大根堆。于是只要从`n/2-1`开始，向前依次构造大根堆，这样就能保证，构造到某个节点时，它的左右子树都已经是大根堆。
2. **堆排序（HeapSort）**：由于堆是用数组模拟的。得到一个大根堆后，数组内部并不是有序的。因此需要将堆化数组有序化。思想是移除根节点，并做最大堆调整的递归运算。第一次将`heap[0]`与`heap[n-1]`交换，再对`heap[0...n-2]`做最大堆调整。第二次将`heap[0]`与`heap[n-2]`交换，再对`heap[0...n-3]`做最大堆调整。重复该操作直至`heap[0]`和`heap[1]`交换。由于每次都是将最大的数并入到后面的有序区间，故操作完后整个数组就是有序的了。
3. **最大堆调整（Max_Heapify）**：该方法是提供给上述两个过程调用的。目的是将堆的末端子节点作调整，使得子节点永远小于父节点 。
### 算法实现
1. 动图演示

    ![](http://op87q3xru.bkt.clouddn.com/java/images/algorithm%E5%A0%86%E6%8E%92%E5%BA%8F%E5%8A%A8%E5%9B%BE.gif)

2. 代码实现
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

## 参考资料
1. [经典排序算法总结与实现](http://wuchong.me/blog/2014/02/09/algorithm-sort-summary/)
2. [白话经典算法系列](http://blog.csdn.net/morewindows/article/details/7961256)
3. [排序算法可视化](http://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html)
4. [所谓堆和堆排序](http://blog.csdn.net/super_chris/article/details/4581900)
5. [几种经典排序算法](http://6924918.blog.51cto.com/6914918/1260860)

