package queue;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
        while (true){
            System.out.println("----------------------------------------------");
            System.out.println("enter a if add element");
            System.out.println("enter c if you wanna check the first element");
            System.out.println("enter r if you wanna get the first element");
            System.out.println("enter s if you wanna check all elements");
            System.out.println("enter e if you wanna exit");
            System.out.print("please enter selection");
            java.util.Scanner scan = new java.util.Scanner(System.in);
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

class CircleArrayQueue{
    private int maxSize;
    private int front;//指向当前元素
    //指向最后元素的后一个位置,预留出一个动态的空缺位置
    //个人思考此举原因：避免判断空或满时，rear和front数值完全一致
    private int rear;
    private int[] circleQueue;

    public CircleArrayQueue(int arrMaxSize ) {
        circleQueue = new int[arrMaxSize+1];
        maxSize = arrMaxSize+1;
    }

    public boolean isEmpty(){
        if (rear == front)
            return true;
        return false;
    }

    public boolean isFull(){
        if((rear +1)%maxSize == front)
            return true;
        return false;
    }

    public void add(int num){
        if (isFull())
            throw new RuntimeException("queue is full");
        circleQueue[rear] = num;
        rear = (rear+1)%maxSize;
    }

    public int getFront(){
        if (isEmpty())
            throw new RuntimeException("queue is emply");
        int temp = circleQueue[front];
        front = (front+1)%maxSize;
        return temp;
    }

    public void checkFront(){
        if (isEmpty())
            throw new RuntimeException("queue is empty");
        System.out.println(circleQueue[front]);
    }

    public void listAll(){
        if (isEmpty())
            throw new RuntimeException("queue is empty");
        int temp = front;
        for (int i = 1; i <= size(); i++) {
            System.out.printf("第 %d 个 数据为：%d",i,circleQueue[temp]);
            temp = (temp+1)%maxSize;
        }
    }

    private int size(){
        if (isEmpty())
            throw new RuntimeException("queue is empty");
        return (rear + maxSize - front)%maxSize;
    }

}