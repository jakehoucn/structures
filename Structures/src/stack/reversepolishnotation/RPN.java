package stack.reversepolishnotation;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author shkstart
 * @create 2020-09-07 13:36
 */
public class RPN {
    public static void main(String[] args) {
        //将中缀表达式“1+((2+3)×4)-5”转换为后缀表达式的过程如下
        // 因此结果为 "1 2 3 + 4 × + 5 –"
        //遇到数字压入栈中，遇到符号，弹出两个数字进行运算，并将结果压入栈中，
        String exp = "1 2 3 + 4 * + 5 -";
        String[] s = exp.split(" ");
        Stack<String> rpn = new Stack<>();
        int num1,num2,res;
        for (String s1 : s) {
            if (s1.matches("\\d+")) {
                rpn.push(s1);
            }else{
                num1 = Integer.parseInt(rpn.pop());
                num2 = Integer.parseInt(rpn.pop());
                res = calculater(num1,num2,s1);
                rpn.push(res+"");
            }
        }
        System.out.println(rpn.pop());

        ArrayList<String> list = getList(exp);
        int res1 = calculate(list);
        System.out.println(res1);

    }

    public static int calculater(int num1 , int num2, String oper){
        if (oper.equals("+")){
            return num2 + num1;
        }else if(oper.equals("-")){
            return num2 - num1;
        }else if(oper.equals("*")){
            return num2 * num1;
        }else if(oper.equals("/")){
            return num2 / num1;
        }else{
            throw new RuntimeException("unkonwn operator");
        }
    }

    public static ArrayList<String> getList(String exp){
        ArrayList<String> rpnList = new ArrayList<>();
        String[] s = exp.split(" ");
        for (String s1 : s) {
            rpnList.add(s1);
        }
        return rpnList;
    }

    public static int calculate(ArrayList<String> rpnList){
        Stack<String> rpn = new Stack<>();
        int num1,num2,res;
        for (String s : rpnList) {
            if ( s.matches("\\d+")){
                rpn.push(s);
            }else{
                num1 = Integer.parseInt(rpn.pop());
                num2 = Integer.parseInt(rpn.pop());
                res = calculater(num1,num2,s);
                rpn.push(res+"");
            }
        }
        return Integer.parseInt(rpn.pop());
    }
}
