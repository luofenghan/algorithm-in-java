import java.util.LinkedList;

/**
 * @title 设计实现一个带有下列属性的二叉查找树的迭代器：
 * 元素按照递增的顺序被访问（比如中序遍历）next()和hasNext()的询问操作要求均摊时间复杂度是O(1)
 * @example 对于下列二叉查找树，使用迭代器进行中序遍历的结果为 [1, 6, 10, 11, 12]
 * <p>
 * 10
 * /    \
 * 1      11
 * \       \
 * 6       12
 * @challenge 额外空间复杂度是O(h)，其中h是这棵树的高度
 * Super Star：使用O(1)的额外空间复杂度
 */
public class BSTIterator {
    private TreeNode root;
    private LinkedList<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.root = root;
        this.stack = new LinkedList<>();
    }

    public boolean hasNext() {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        return !stack.isEmpty();
    }

    public TreeNode next() {
        TreeNode node = stack.pop();
        root = node.right;
        return node;
    }
}
