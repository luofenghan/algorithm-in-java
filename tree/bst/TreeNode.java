/**
 * Created by cwc on 2017/06/12 0012.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        this.val = x;
    }

    @Override
    public String toString() {
        return "{" +
                "\"val\":\"" + val + "\"," +
                "\"left\":" + left + "," +
                "\"right\":" + right +
                "}";
    }
}
