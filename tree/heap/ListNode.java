/**
 * Created by cwc on 2017/06/15 0015.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListNode listNode = (ListNode) o;

        return val == listNode.val;
    }

    @Override
    public int hashCode() {
        return val;
    }
}
