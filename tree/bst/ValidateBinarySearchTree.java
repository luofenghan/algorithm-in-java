import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @title 给定一个二叉树，判断它是否是合法的二叉查找树(BST)
 * <p>
 * 一棵BST定义为：
 * <p>
 * 节点的左子树中的值要严格小于该节点的值。
 * 节点的右子树中的值要严格大于该节点的值。
 * 左右子树也必须是二叉查找树。
 * 一个节点的树也是二叉查找树。
 * @example 一个例子：
 * <>
 * 2
 * / \
 * 1   4
 * / \
 * 3   5
 * 上述这棵二叉树序列化为 {2,1,4,#,#,3,5}.
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root, pre = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            TreeNode current = stack.pop();
            if (pre != null && current.val <= pre.val)
                return false;
            pre = current;
            node = current.right;
        }
        return true;
    }

    public boolean isValidBST2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree validate = new ValidateBinarySearchTree();

        int[] nodeArray;

        nodeArray = new int[]{2, 1, 2};
    }

}
