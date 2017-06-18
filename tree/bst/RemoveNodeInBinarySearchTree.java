/**
 * @title 给定一棵具有不同节点值的二叉查找树，删除树中与给定值相同的节点。
 * 如果树中没有相同值的节点，就不做任何处理。你应该保证处理之后的树仍是二叉查找树。
 * @example
 */
public class RemoveNodeInBinarySearchTree {
    public TreeNode removeNode(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode parent = findParentOfNode(dummy, root, value);

        TreeNode deleteNode;
        if (parent.left != null && parent.left.val == value) {
            deleteNode = parent.left;
        } else if (parent.right != null && parent.right.val == value) {
            deleteNode = parent.right;
        } else {
            return dummy.left;
        }

        deleteNode(parent, deleteNode);
        return dummy.left;
    }

    private TreeNode findParentOfNode(TreeNode parent, TreeNode node, int val) {
        if (node == null) {/*没有找到节点*/
            return parent;
        }

        if (node.val == val) {/*找到了节点*/
            return parent;
        }

        if (node.val < val) {
            return findParentOfNode(node, node.right, val);
        } else {
            return findParentOfNode(node, node.left, val);
        }

    }

    private void deleteNode(TreeNode parent, TreeNode node) {
        if (node.right == null) {/*当前节点的【右孩子】为空，则直接将该【节点】的【左孩子引用】传递给父节点*/
            if (parent.left == node) {/*当前节点是左节点*/
                parent.left = node.left;
            } else {/*当前节点是右节点*/
                parent.right = node.left;
            }
        } else {
            /*找到【当前节点】的【右孩子】下的【最小节点】*/
            TreeNode father = node;
            TreeNode minNode = node.right;

            while (minNode.left != null) {
                father = minNode;
                minNode = minNode.left;
            }

            /*将最小节点的右孩子传给father*/
            if (father.left == minNode) {
                father.left = minNode.right;
            } else {
                father.right = minNode.right;
            }

            /*用最小节点代替node节点*/
            if (parent.left == node) {
                parent.left = minNode;
            } else {
                parent.right = minNode;
            }

            minNode.left = node.left;
            minNode.right = node.right;

        }
    }
}
