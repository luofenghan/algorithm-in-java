import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @title 给出若干闭合区间，合并所有重叠的部分。
 * @example [                     [
 * <></>        [1, 3],          [1, 6],
 * <></>        [2, 6],      =>  [8, 10],
 * <></>        [8, 10],         [15, 18]
 * <></>        [15, 18]            ]
 * <></>    ]
 */
public class MergeRange {

    private static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Interval interval = (Interval) o;

            if (start != interval.start) return false;
            return end == interval.end;
        }

        @Override
        public int hashCode() {
            int result = start;
            result = 31 * result + end;
            return result;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return intervals;
        }
        heapSort(intervals);
        List<Interval> collect = new ArrayList<>();
        for (Interval interval : intervals) {
            combine(collect, interval);
        }
        return collect;
    }

    private void combine(List<Interval> collect, Interval interval) {
        if (collect.isEmpty()) {
            collect.add(interval);
        } else {
            Interval last = collect.get(collect.size() - 1);
            if (last.end < interval.start) {
                collect.add(interval);
            } else if (last.end <= interval.end) {
                last.end = interval.end;
            }
        }
    }

    private void heapSort(List<Interval> intervals) {
        for (int i = intervals.size() / 2; i >= 0; i--) {
            percolateDown(intervals, i, intervals.size());
        }
        for (int i = intervals.size() - 1; i > 0; i--) {
            Collections.swap(intervals, 0, i);
            percolateDown(intervals, 0, i);
        }
    }

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private void percolateDown(List<Interval> intervals, int i, int n) {
        int child;
        Interval tmp;
        for (tmp = intervals.get(i); leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && intervals.get(child).start < intervals.get(child + 1).start) {
                child++;
            }
            if (tmp.start < intervals.get(child).start) {
                intervals.set(i, intervals.get(child));
            } else {
                break;
            }
        }
        intervals.set(i, tmp);

    }


    public static void main(String[] args) {
        MergeRange merge = new MergeRange();
        /*功能测试*/

        Assert.assertEquals(Arrays.asList(new Interval(1, 6), new Interval(8, 10), new Interval(15, 18)),
                merge.merge(Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18))));

        Assert.assertEquals(Collections.singletonList(new Interval(0, 10)), merge.merge(Arrays.asList(new Interval(2, 6), new Interval(0, 2), new Interval(1, 4), new Interval(6, 10))));

        Assert.assertEquals(Collections.singletonList(new Interval(1, 4)), merge.merge(Arrays.asList(new Interval(1, 4), new Interval(1, 4))));

        /*负面测试*/

        Assert.assertEquals(Collections.emptyList(), merge.merge(Collections.emptyList()));
        /*边界测试*/

        /*性能测试*/

    }

}
