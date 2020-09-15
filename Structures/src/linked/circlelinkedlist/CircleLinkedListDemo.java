package linked.circlelinkedlist;

public class CircleLinkedListDemo {
    public static void main(String[] args) {
        CircleLinkedList cll = new CircleLinkedList();
        cll.joseph(1,2,5);
    }
}

class CircleLinkedList{

    private Child first = new Child(-1);

    public CircleLinkedList() {
    }

    private CircleLinkedList(int num) {
        add(num);
    }

    public Child getFirst() {
        return first;
    }

    public void setFirst(Child first)
    {
        this.first = first;
    }

    public void joseph(int startNo, int countNo, int totalNum){
        if (startNo < 1 || startNo>totalNum || totalNum <= 1){
            System.out.println("输入的数字有误");
        }

        add(totalNum);

        Child start = first;
        Child helper = first;
        for (int i = 0; i < totalNum - 1; i++) {
            helper = helper.next;
        }

        for (int i = 0; i < startNo - 1 ; i++) {
            start = start.next;
            helper = helper.next;
        }

        while(start.next != start){
            for (int i = 0; i < countNo - 1; i++) {
                start = start.next;
                helper = helper.next;
            }
            System.out.printf("当前出圈的小孩编号为：%d\n",start.num);
            start = start.next;
            helper.next = start;
        }
        System.out.printf("最后一个圈中小孩为：%d\n",start.num);
    }

    public void josephus(int n){
        if (first.next == null) {
            System.out.println("当前链表为空");
            return;
        }
        if (n<1){
            System.out.println("这样游戏就没法玩了");
        }
        Child curChild = first;
        while(curChild.next!=curChild){
            Child temp = curChild;
            for (int i = 1; i<n;i++){
                curChild = curChild.next;
            }
            System.out.println(curChild);
            curChild=curChild.next;
            temp.next = curChild;
        }
        System.out.println(curChild);
    }

    public void add(int num){
        if (num<1){
            System.out.println("小朋友太少了，不能玩这个游戏哦");
            return;
        }

        first = new Child(1);
        Child curChild = first;//first 应该换掉
        for (int i = 2; i <=num; i++) {
            curChild.next = new Child(i);
            curChild.next.next = first;
            curChild = curChild.next;
        }
    }

    public void listAll(){
        if (first.next == null) {
            System.out.println("当前链表为空");
            return;
        }

        Child curChild = first;
        while (true){
            System.out.println(curChild);
            curChild = curChild.next;
            if (curChild==first){
                break;
            }
        }
    }

    class Child{
        private int num;
        private Child next;

        public Child(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Child{" +
                    "num=" + num +
                    '}';
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Child getNext() {
            return next;
        }

        public void setNext(Child next) {
            this.next = next;
        }
    }

}


