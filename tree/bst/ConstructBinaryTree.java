/**
 * @title 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * @example 输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * @idea {
 * 1. 前序遍历：数组第一个元素总是树的根节点的值
 * 2. 中序遍历：根节点的值在序列的中间，左子树的节点位于根节点的左边，右子树的值位于根节点的值右边；
 * <p>
 * }
 */
public class ConstructBinaryTree {


    /**
     * @param pre 前序遍历序列
     * @param in  中序遍历序列
     * @return
     */
    public static TreeNode construct(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        return construct(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private static TreeNode construct(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        for (int i = inStart; i <= inEnd; i++) {
            if (root.val == in[i]) {
                root.left = construct(pre, preStart + 1, i - inStart + preStart, in, inStart, i - 1);
                root.right = construct(pre, i - inStart + preStart + 1, preEnd, in, i + 1, inEnd);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = construct(pre, in);
        System.out.println(root);
    }


}
