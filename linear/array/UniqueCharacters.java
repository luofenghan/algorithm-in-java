import org.junit.Assert;

/**
 * @title 实现一个算法确定字符串中的字符是否均唯一出现
 * @attention 如果不使用额外的存储空间，你的算法该如何改变？
 * @example 给出"abc"，返回 true  给出"aab"，返回 false
 */
public class UniqueCharacters {

    public boolean isUnique(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        if (str.length() == 1) {
            return true;
        }
        char[] chars = str.toCharArray();
        heapSort(chars);
        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == chars[i]) {
                return false;
            }
        }
        return true;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    private void heapSort(char[] chars) {
        for (int i = chars.length / 2; i >= 0; i--) {
            percolateDown(chars, i, chars.length);
        }
        for (int i = chars.length - 1; i > 0; i--) {
            swap(chars, 0, i);
            percolateDown(chars, 0, i);
        }
    }

    private void percolateDown(char[] chars, int i, int n) {
        int child;
        char tmp;
        for (tmp = chars[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && chars[child] < chars[child + 1]) child++;
            if (tmp < chars[child]) chars[i] = chars[child];
            else break;
        }
        chars[i] = tmp;
    }

    public static void main(String[] args) {

        UniqueCharacters unique = new UniqueCharacters();

        /*功能测试*/
        Assert.assertTrue(unique.isUnique("abc"));
        Assert.assertFalse(unique.isUnique("aabc"));
        Assert.assertFalse(unique.isUnique("abac"));

        /*负面测试*/
        Assert.assertFalse(unique.isUnique(""));
        Assert.assertFalse(unique.isUnique(null));

    }
}
