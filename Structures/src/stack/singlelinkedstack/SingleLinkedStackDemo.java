package stack.singlelinkedstack;

import java.util.Scanner;

/**
 * @author shkstart
 * @create 2020-09-03 21:48
 */
public class SingleLinkedStackDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("please enter a number to initial stack");
        int maxSize = scan.nextInt();
        SingleLinkedStack sls = new SingleLinkedStack(maxSize);
        boolean loop = true;
        String key = "";
        while (loop){
            System.out.print("enter pop to add element \n" +
                    "enter push to get a element\n" +
                    "enter list to check all elements\n" +
                    "enter exit to stop the process\n" +
                    "your choose:");
            key = scan.next();
            switch (key){
                case "list":
                    sls.list();
                    break;
                case "pop":
                    try {

                        System.out.println(sls.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "push":
                    System.out.print("please enter the number your wanna addd");
                    int x= scan.nextInt();
                    sls.push(x);
                    break;
                case "exit":
                    loop = false;
                    System.out.println("thanks for usage");
                    break;
            }
        }
    }
}

class SingleLinkedStack {
    private Node head = new Node(000);
    private int maxSize;
    private int count = 0;

    public SingleLinkedStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public boolean isFull() {
        return maxSize == count;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("stack is full cannot push");
            return;
        }
        Node newNode = new Node(value);
        newNode.next = head.next;
        head.next = newNode;
        count++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty cannot pop");
        }
        int value = head.next.data;
        head.next = head.next.next;
        count--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("stack is empty cannot list");
            return;
        }
        Node curNode = head;
        int pointer=count;
        while (curNode.next!=null){
            System.out.printf("空间：%d，数据：%d\n",pointer--,curNode.next.data);
            curNode=curNode.next;
        }
    }

    class Node {
        private int data;
        Node next = null;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "" + data;
        }
    }
}
