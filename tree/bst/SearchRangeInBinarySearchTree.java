import java.util.ArrayList;

/**
 * @title 给定两个值 k1 和 k2（k1 < k2）和一个二叉查找树的根节点。找到树中所有值在 k1 到 k2 范围内的节点。
 * 即打印所有x (k1 <= x <= k2) 其中 x 是二叉查找树的中的节点值。返回所有升序的节点值。
 * @example 如果有 k1 = 10 和 k2 = 22, 你的程序应该返回 [12, 20, 22].
 * 20
 * /  \
 * 8   22
 * / \
 * 4  12
 */
public class SearchRangeInBinarySearchTree {

    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        ArrayList<Integer> rangeList = new ArrayList<>();
        if (root == null) {
            return rangeList;
        }
        inOrderTraversal(root, k1, k2, rangeList);
        return rangeList;
    }

    private void inOrderTraversal(TreeNode node, int k1, int k2, ArrayList<Integer> rangeList) {
        if (node != null) {
            inOrderTraversal(node.left, k1, k2, rangeList);
            if (k1 <= node.val && node.val <= k2) {
                rangeList.add(node.val);
            }
            inOrderTraversal(node.right, k1, k2, rangeList);
        }
    }

}
