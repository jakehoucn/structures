package stack.arraystack;

/**
 * @author shkstart
 * @create 2020-09-03 21:23
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);

        stack.listAll();

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top=-1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //满
    public boolean isFull(){
        return top == maxSize-1;
    }

    //空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("当前栈空间已满");
            return;
        }
        stack[++top] = value;
    }

    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("当前栈为空");
        }
        return stack[top--];
    }

    //遍历
    public void listAll(){
        if (isEmpty()){
            System.out.println("当前栈为空");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("堆栈数据：stack[%d]=%d\n",i,stack[i]);
        }
    }
}
