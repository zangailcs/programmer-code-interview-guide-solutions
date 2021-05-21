import java.util.Scanner;
import java.util.Stack;

public class MainOfCD6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TwoStacksQueue queue = new TwoStacksQueue();
        int opNum = Integer.parseInt(in.nextLine());
        for (int i = 0; i < opNum; i++) {
            String line = in.nextLine();
            if ("peek".equals(line)) {
                System.out.println(queue.peek());
            } else if ("poll".equals(line)) {
                queue.poll();
            } else {
                int value = Integer.parseInt(line.split(" ")[1]);
                queue.add(value);
            }
        }
    }
}


class TwoStacksQueue {
    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public TwoStacksQueue() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    // push 栈向 pop 栈倒入数据
    private void pushToPop() {
        if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public void add(int pushInt) {
        stackPush.push(pushInt);
        pushToPop();
    }

    public int poll() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty!");
        }
        pushToPop();
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty!");
        }
        pushToPop();
        return stackPop.peek();
    }
}
