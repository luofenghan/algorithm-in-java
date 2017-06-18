/**
 * @title 给定一棵二叉查找树和一个新的树节点，将节点插入到树中。你需要保证该树仍然是一棵二叉查找树。
 * @example 给出如下一棵二叉查找树，在插入节点6之后这棵二叉查找树可以是这样的：
 * 2             2
 * / \           / \
 * 1   4   -->   1   4
 * /             / \
 * 3             3   6
 * @
 */
public class InsertNodeInBinarySearchTree {
    public TreeNode insert(TreeNode root, TreeNode node) {
        if (root == null) {
            root = node;
            return root;
        }
        TreeNode target = root;
        while (true) {
            if (node.val < target.val) {
                if (target.left != null) {
                    target = target.left;
                } else {
                    target.left = node;
                    break;
                }
            } else if (target.val < node.val) {
                if (target.right != null) {
                    target = target.right;
                } else {
                    target.right = node;
                    break;
                }
            } else {
                return root;
            }
        }
        return root;
    }


}
