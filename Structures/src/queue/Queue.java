package queue;

import java.util.Scanner;

public class Queue {
    int maxSize;
    int front;//第一个元素的前一个位置,头部
    int rear;//指向最后一个元素
    int[] arrQueue;

    public Queue(int arrMaxSize){
        arrQueue = new int[arrMaxSize];
        maxSize = arrMaxSize;
        front = rear = -1;
    }

    public boolean isFull(){
        if(rear+1 == maxSize)
            return true;
        else
            return false;
    }

    public boolean isEmpty(){
        if (front == rear)
            return true;
        else
            return false;
    }

    public void addElement(int num){
        if(isFull()){
            throw new RuntimeException("队列已满");
        }
        else {
            arrQueue[++rear] = num;
            System.out.println("添加成功");
        }
    }

    public int getElement(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法取出数据");
        }
        else{
            return arrQueue[++front];
        }
    }

    public void checkFront(){
        if (isEmpty())
            System.out.println("当前队列没有数据");
        else
            System.out.println(arrQueue[front + 1]);
    }

    public void show(){
        if (isEmpty())
            System.out.println("当前队列没有数据");
        int count =0;
        for (int i = front; i < rear; i++) {
            System.out.println("第"+ ++count +"个数据： " + arrQueue[i+1]);
        }
    }
}

class testQueue{
    public static void main(String[] args) {
        Queue queue = new Queue(3);
        while (true){
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
                        queue.addElement(temp);
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
                        x = queue.getElement();
                        System.out.println("you get a number:"+ x);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's':
                    try {
                        queue.show();
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