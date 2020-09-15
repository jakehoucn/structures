package stack.calculater;

/**
 * @author shkstart
 * @create 2020-09-04 0:17
 */
public class CalculaterDemo {
    public static void main(String[] args) {
        String exp = "5*(3+2)-10";
        System.out.println(Calculate.calculator(exp));

        //个人方法，在判断数字长度的时候用到了运算符，老韩用到了字符串的拼接，效率更高
        String state = "4*3+10-5";
        int num1,num2,res;
        char oper,temp;
        Calculate numStack = new Calculate();
        Calculate operStack = new Calculate();
        int index=0;
        int numTemp;
        while(index!=state.length()){
            temp = state.charAt(index);//取出字符
            index++;
            if (Calculate.isOper(temp)){//是符号
                if (operStack.isEmpty()){//符号栈为空
                    operStack.push(temp);
                }else{
                    //符号栈不为空，且先前符号的优先级大于当前符号
                    //弹出符号，数字，进行运算，将运算结果压入数栈，
                    //将符号压入栈
                    if (Calculate.operPriority(temp) <= Calculate.operPriority(operStack.popTop())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char)operStack.pop();
                        res = Calculate.calculate(num1,num2,oper);
                        operStack.push(temp);
                        numStack.push(res);
                    }else{
                        //如果当前的符号优先级大于栈中的符号，那么继续运算
                        operStack.push(temp);
                    }
                }
            }else{//不是符号，直接压入数栈中
                numTemp = temp - 48;
                while (index<state.length()&&(!Calculate.isOper(state.charAt(index)))){
                    numTemp = numTemp*10 + state.charAt(index)-48;
                    index++;
                }
                numStack.push(numTemp);
            }
        }

        while(!operStack.isEmpty()){
            oper = (char) operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            res = Calculate.calculate(num1,num2,oper);
            numStack.push(res);
        }

        res = numStack.pop();
        System.out.println(res);
    }
}

class Calculate {
    private Node head = new Node(000);
    private int count = 0;

    public Calculate() {
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    //入栈
    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = head.next;
        head.next = newNode;
        count++;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty cannot pop");
        }
        int value = head.next.data;
        head.next = head.next.next;
        count--;
        return value;
    }

    //遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("stack is empty cannot list");
            return;
        }
        Node curNode = head;
        int pointer = count;
        while (curNode.next != null) {
            System.out.printf("空间：%d，数据：%d\n", pointer--, curNode.next.data);
            curNode = curNode.next;
        }
    }


    public static boolean isOper(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/'|| ch == '(' || ch == ')';
    }

    public static int operPriority(char ch) {
        if (ch == '*' || ch == '/') {
            return 1;
        } else if (ch == '+' || ch == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public static int calculate(int num1, int num2, char oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

    public static int calculator(String exp){
        int num1,num2,res;
        char oper,temp;
        Calculate numStack = new Calculate();
        Calculate operStack = new Calculate();
        int index=0;
        String helper="";
        while(index!=exp.length()){
            temp = exp.charAt(index);//取出字符
            index++;
            if (Calculate.isOper(temp)){//是符号
                if (operStack.isEmpty()||operStack.popTop() == '('){//符号栈为空，或上一个是小括号
                    operStack.push(temp);
                }else{
                    //符号栈不为空，且先前符号的优先级大于当前符号
                    //弹出符号，数字，进行运算，将运算结果压入数栈，
                    //将符号压入栈
                    if ( temp == ')'){
                        while(operStack.popTop()!='('){
                            //只要符号位不是（，就弹出符号
                            oper = (char)operStack.pop();
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            res = Calculate.calculate(num1,num2,oper);
                            numStack.push(res);
                        }
                        //弹出左括号
                        operStack.pop();
                    }else if (temp!='('&&Calculate.operPriority(temp) <= Calculate.operPriority(operStack.popTop())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char)operStack.pop();
                        res = Calculate.calculate(num1,num2,oper);
                        operStack.push(temp);
                        numStack.push(res);
                    }else{
                        //如果当前的符号优先级大于栈中的符号，那么继续运算
                        operStack.push(temp);
                    }
                }
            }else{//不是符号，直接压入数栈中
                helper+=temp;
                while (index<exp.length()&&(!Calculate.isOper(exp.charAt(index)))){
                    helper += exp.charAt(index);
                    index++;
                }
                numStack.push(Integer.parseInt(helper));
                //重置helper，非常重要
                helper="";
            }
        }

        while(!operStack.isEmpty()){
            oper = (char) operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            res = Calculate.calculate(num1,num2,oper);
            numStack.push(res);
        }

        res = numStack.pop();
        return res;
    }

    public char popTop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }

        return (char) head.next.data;
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
