import org.junit.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @title 合并k个排序链表，并且返回合并后的排序链表。尝试分析和描述其复杂度。
 * @example 给出3个排序链表[2->4->null,null,-1->null]，返回 -1->2->4->null
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        PriorityQueue<ListNode> listNodes = new PriorityQueue<>(lists.size(), new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode listNode : lists) {
            if (listNode != null) {
                listNodes.add(listNode);
            }
        }

        ListNode root = null, p = null;
        while (!listNodes.isEmpty()) {
            ListNode tmp = listNodes.poll();
            if (root == null) {
                root = tmp;
                p = root;
            } else {
                p.next = tmp;
                p = tmp;
            }
            if (tmp.next != null) {
                ListNode node = tmp.next;
                listNodes.add(node);
                tmp.next = null;
            }
        }
        return root;
    }


    public static void main(String[] args) {
        MergeKSortedLists merge = new MergeKSortedLists();

        ListNode one = new ListNode(2);
        ListNode two = new ListNode(4);
        one.next = two;

        ListNode three = new ListNode(-1);

        List<ListNode> listNodes = new ArrayList<>();
        listNodes.add(one);
        listNodes.add(null);
        listNodes.add(three);

        ListNode listNode = merge.mergeKLists(listNodes);
        Assert.assertEquals(listNode, three);
        Assert.assertEquals(listNode.next, one);
        Assert.assertEquals(listNode.next.next, two);
    }
}
