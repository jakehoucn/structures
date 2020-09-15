package queue;

import java.util.Scanner;

import javax.swing.plaf.BorderUIResource;

public class circleQueue {
    private int maxSize;
    private int front;//指向第一个元素
    private int rear;//指向最后一个元素的后一个位置
    private int[] arrQueue;

    public circleQueue(int arrMaxSize){
        arrQueue = new int[arrMaxSize];
        maxSize = arrMaxSize;
    }

    public boolean isEmpty(){
        if (front == rear)
            return true;
        return false;
    }

    public boolean isFull(){
        if ((rear - front) == maxSize)
            return true;
        return false;
    }

    public void add(int num){
        if (isFull())
            throw new RuntimeException("队列已满");
        arrQueue[rear++%maxSize] = num;
        System.out.println("数据添加成功");
    }

    public int getFront(){
        if (isEmpty())
            throw new RuntimeException("当前队列没有数据");
        int temp = arrQueue[front++%maxSize];
        return temp;
    }

    public void checkFront(){
        if (isEmpty())
            throw new RuntimeException("当前队列没有数据");
        System.out.println(arrQueue[front%maxSize]);
    }

    public void listAll(){
        if (isEmpty())
            throw new RuntimeException("当前队列没有数据");
        int temp = front;
        for (int i = 1; i <= (rear - front); i++) {
            System.out.println("第"+ i + "数据为：" + arrQueue[temp%maxSize]);
            temp++;
        }
    }
}

class testCircleQueue{
    public static void main(String[] args) {
        circleQueue queue = new circleQueue(3);
        while (true){
            System.out.println("----------------------------------------------");
            System.out.println("enter a if add element");
            System.out.println("enter c if you wanna check the first element");
            System.out.println("enter r if you wanna get the first element");
            System.out.println("enter s if you wanna check all elements");
            System.out.println("enter e if you wanna exit");
            System.out.print("please enter selection");
            Scanner scan = new Scanner(System.in);
            char selection = scan.next().toLowerCase().charAt(0);
            switch (selection){
                case 'a':
                    System.out.println("please enter a number");
                    int temp = scan.nextInt();
                    try {
                        queue.add(temp);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'c':
                    try {
                        queue.checkFront();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'r':
                    int x = 0;
                    try {
                        x = queue.getFront();
                        System.out.println("you get a number:"+ x);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's':
                    try {
                        queue.listAll();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    System.out.println("thanks, bye");
                    return;
            }
        }
    }

}

