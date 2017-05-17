import org.junit.Assert;

import java.util.Stack;

/**
 * 题目：用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 阶梯思路：
 */
public class QueueWithTwoStacks {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        synchronized (this) {
            stack1.push(node);
        }
    }

    public int pop() {
        synchronized (this) {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }

    public static void main(String[] args) {
        QueueWithTwoStacks queue = new QueueWithTwoStacks();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        Assert.assertEquals(1, queue.pop());
        Assert.assertEquals(2, queue.pop());
        queue.push(4);
        Assert.assertEquals(3, queue.pop());
        queue.push(5);
        Assert.assertEquals(4, queue.pop());
        Assert.assertEquals(5, queue.pop());
    }
}
