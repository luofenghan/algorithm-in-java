import org.junit.Assert;

import java.util.Arrays;

/**
 * @title 合并两个排序的整数数组A和B变成一个新的数组。
 * @example 给出 A = [1, 2, 3, empty, empty], B = [4, 5] 合并之后 A 将变成 [1,2,3,4,5]
 */
public class MergeSortedArray {


    public void merge(int[] arrayA, int sizeA, int[] arrayB, int sizeB) {
        if (arrayA == null || arrayB == null) {
            return;
        }
        if (arrayA.length != sizeA + sizeB) {
            return;
        }
        int i = 0, j;
        for (int lastInsert = i; i < sizeB; i++) {
            for (j = lastInsert; j < sizeA; j++) {
                if (arrayB[i] <= arrayA[j]) {
                    System.arraycopy(arrayA, j, arrayA, j + 1, arrayA.length - 1 - j);
                    arrayA[j] = arrayB[i];
                    lastInsert = j;
                    sizeA++;
                    break;
                }
            }
            if (j == sizeA) {
                System.arraycopy(arrayB, i, arrayA, sizeA, sizeB - i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        MergeSortedArray merge = new MergeSortedArray();
        int[] arrayA;
        int[] arrayB;

        /*功能测试*/
        arrayA = new int[]{1, 2, 3, 6, -1, -1, -1};
        arrayB = new int[]{4, 4, 7};
        merge.merge(arrayA, 4, arrayB, 3);
        Assert.assertTrue(Arrays.equals(new int[]{1, 2, 3, 4, 4, 6, 7}, arrayA));


        arrayA = new int[]{1, 3, 4, 6, -1, -1};
        arrayB = new int[]{2, 5};
        merge.merge(arrayA, 4, arrayB, 2);
        Assert.assertTrue(Arrays.equals(new int[]{1, 2, 3, 4, 5, 6}, arrayA));

        arrayA = new int[]{29, 132, 249, 438, 722, 739, 1294, 1381, 1646, 1935};
        arrayB = new int[]{20, 34, 53, 66, 75, 81, 97, 111, 136, 164, 166, 175, 190, 210, 231, 260, 273, 288, 311, 313, 321, 332, 335, 383, 389, 391, 399, 409, 427, 529, 530, 550, 569, 571, 607, 612, 613, 625, 638, 671, 681, 687, 705, 719, 720, 738, 788, 791, 803, 817, 823, 869, 931, 933, 959, 977, 985, 988, 988, 992, 996, 1008, 1025, 1028, 1046, 1054, 1103, 1113, 1118, 1128, 1160, 1164, 1169, 1187, 1195, 1208, 1256, 1324, 1335, 1341, 1354, 1394, 1400, 1404, 1409, 1421, 1434, 1464, 1485, 1492, 1492, 1510, 1528, 1546, 1552, 1565, 1567, 1570, 1584, 1592, 1609, 1621, 1624, 1629, 1641, 1668, 1682, 1705, 1713, 1717, 1741, 1749, 1769, 1781, 1783, 1786, 1796, 1808, 1856, 1883, 1889, 1889, 1894, 1900, 1904, 1931, 1933, 1942, 1953};
        int[] arrayc = new int[10 + 129];
        System.arraycopy(arrayA, 0, arrayc, 0, 10);
        arrayA = arrayc;
        merge.merge(arrayA, 10, arrayB, 129);
        Assert.assertTrue(Arrays.equals(new int[]{20, 29, 34, 53, 66, 75, 81, 97, 111, 132, 136, 164, 166, 175, 190, 210, 231, 249, 260, 273, 288, 311, 313, 321, 332, 335, 383, 389, 391, 399, 409, 427, 438, 529, 530, 550, 569, 571, 607, 612, 613, 625, 638, 671, 681, 687, 705, 719, 720, 722, 738, 739, 788, 791, 803, 817, 823, 869, 931, 933, 959, 977, 985, 988, 988, 992, 996, 1008, 1025, 1028, 1046, 1054, 1103, 1113, 1118, 1128, 1160, 1164, 1169, 1187, 1195, 1208, 1256, 1294, 1324, 1335, 1341, 1354, 1381, 1394, 1400, 1404, 1409, 1421, 1434, 1464, 1485, 1492, 1492, 1510, 1528, 1546, 1552, 1565, 1567, 1570, 1584, 1592, 1609, 1621, 1624, 1629, 1641, 1646, 1668, 1682, 1705, 1713, 1717, 1741, 1749, 1769, 1781, 1783, 1786, 1796, 1808, 1856, 1883, 1889, 1889, 1894, 1900, 1904, 1931, 1933, 1935, 1942, 1953}, arrayA));
        /*负面测试*/


        /*边界测试*/
        arrayA = new int[]{9, 10, 11, 12, 13, -1, -1, -1, -1};
        arrayB = new int[]{4, 5, 6, 7};
        merge.merge(arrayA, 5, arrayB, 4);
        Assert.assertTrue(Arrays.equals(new int[]{4, 5, 6, 7, 9, 10, 11, 12, 13}, arrayA));

        arrayA = new int[]{1, 2, 3, -1, -1};
        arrayB = new int[]{4, 5};
        merge.merge(arrayA, 3, arrayB, arrayB.length);
        Assert.assertTrue(Arrays.equals(new int[]{1, 2, 3, 4, 5}, arrayA));


        /*性能测试*/
    }
}
