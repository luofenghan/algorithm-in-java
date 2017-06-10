import org.junit.Assert;

import java.util.*;

/**
 * @title 给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组。
 * @example 如S = {-1,0,1,2,-1,-4}, 你需要返回的三元组集合的是： (-1, 0, 1) (-1, -1, 2)
 */
public class ThreeSum {

    public ArrayList<ArrayList<Integer>> find(int[] numbers) {
        Set<ArrayList<Integer>> triples = new LinkedHashSet<>();
        if (numbers == null || numbers.length <= 2) {
            return new ArrayList<>(triples);
        }
        Arrays.sort(numbers);
        if (numbers[0] >= 0) {
            return new ArrayList<>(triples);
        }
        int pivot = partition(numbers);
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers.length - 1; j >= i + 1; j--) {
                int target = numbers[i] + numbers[j];
                int index;
                if (target > 0) {
                    index = binarySearch(numbers, 0, pivot, -target);
                } else {
                    index = binarySearch(numbers, pivot + 1, numbers.length - 1, target);
                }
                if (index != -1 && index != j) {
                    ArrayList<Integer> triple = new ArrayList<>();
                    triple.add(numbers[i]);
                    triple.add(numbers[j]);
                    triple.add(numbers[index]);
                    Collections.sort(triple);
                    triples.add(triple);
                }
            }
        }
        return new ArrayList<>(triples);
    }

    private static int binarySearch(int[] numbers, int low, int high, int target) {
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (target < numbers[mid]) {
                high = mid - 1;
            } else if (numbers[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分搜索查找值为0的索引
     *
     * @return
     */
    private static int partition(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        int mid = low;
        while (numbers[low] < 0 && numbers[high] >= 0) {
            if (high - low == 1) {
                mid = low;
                break;
            }
            mid = (low + high) / 2;
            if (numbers[mid] < 0) {
                low = mid;
            } else if (numbers[mid] > 0) {
                high = mid;
            } else {
                return mid;
            }
        }
        return mid;
    }


    public static void main(String[] args) {

        ThreeSum threeSum = new ThreeSum();


        int[] numbers;
        ArrayList<ArrayList<Integer>> tripleList;
        /*功能测试*/
        numbers = new int[]{-1, 0, 1, 2, -1, -4};//-4 -1 -1 0 1 2
        tripleList = threeSum.find(numbers);
        check(new Integer[][]{
                {-1, 0, 1},
                {-1, -1, 2}
        }, tripleList);

        numbers = new int[]{1, 0, -1, -1, -1, -1, 0, 1, 1, 1};
        tripleList = threeSum.find(numbers);
        check(new Integer[][]{
                {-1, 0, 1}
        }, tripleList);

        numbers = new int[]{-2, -3, 5, -1, -4, 5, -11, 7, 1, 2, 3, 4, -7, -1, -2, -3, -4, -5};
        // -11 -7 -5 -4 -4 -3 -3 -2 -2 -1 -1 1 2 3 4 5 5 7
        tripleList = threeSum.find(numbers);
        check(new Integer[][]{
                {-11, 4, 7}, {-7, 2, 5}, {-7, 3, 4}, {-5, -2, 7}, {-5, 1, 4}, {-5, 2, 3}, {-4, -3, 7}, {-4, -1, 5}, {-4, 1, 3}, {-3, -2, 5}, {-3, -1, 4}, {-3, 1, 2}, {-2, -2, 4}, {-2, -1, 3}, {-1, -1, 2}
        }, tripleList);

        numbers = new int[]{-2, -3, -4, -5, -100, 99, 1, 4, 4, 4, 5, 1, 0, -1, 2, 3, 4, 5};
        tripleList = threeSum.find(numbers);
        check(new Integer[][]{
                {-100, 1, 99}, {-5, 0, 5}, {-5, 1, 4}, {-5, 2, 3}, {-4, -1, 5}, {-4, 0, 4}, {-4, 1, 3}, {-3, -2, 5}, {-3, -1, 4}, {-3, 0, 3}, {-3, 1, 2}, {-2, -1, 3}, {-2, 0, 2}, {-2, 1, 1}, {-1, 0, 1}
        }, tripleList);

    }

    private static void check(Integer[][] expected, ArrayList<ArrayList<Integer>> tripleList) {
        ArrayList<ArrayList<Integer>> expectedList = new ArrayList<ArrayList<Integer>>();
        for (Integer[] integers : expected) {
            expectedList.add(new ArrayList<>(Arrays.asList(integers)));
        }
        Assert.assertEquals(expectedList, tripleList);
    }

}
