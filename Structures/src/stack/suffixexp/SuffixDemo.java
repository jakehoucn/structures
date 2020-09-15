package stack.suffixexp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author shkstart
 * @create 2020-09-08 10:25
 */
public class SuffixDemo {
    /**
     * 中缀表达式转换为后缀表达式的思路：
     * 1、中缀表达式转换为一个list：
     * 1.1 创建两个容器，一个存储数字，一个存储符号
     * 1.2 数字容器：直接存储数据，可以使用list，因为该容器没有弹出功能
     * 1.3 符号容器：存储符号，可以使用stack，因为有取值操作，且取值原则为后进先出
     * 符号容器的具体逻辑：
     *  - 如果容器为空，或是上一个符号是"(" --> 入栈
     *  - 如果当前符号为"(" --> 入栈
     *  - 如果当前符号为")" --> 循环弹出符号，到数字容器中，直到遇到")"，只执行弹出操作并退出
     *  - 如果当前符号的优先级大于栈顶的优先级，加入到数字容器
     *  - 若当前符号的优先级小于等于栈顶的优先级，弹栈并入数字容器，然后入栈
     * */
    @Test
    public void test(){
        String s= "2+2*((3+2)-3)-1";
        ArrayList<String> list = list(s);
        System.out.println(list);
        System.out.println(calculate(list));
    }

    public int calculate(ArrayList<String> suffixExp){
        Stack<String> cal = new Stack<>();
        int num1,num2;
        for (String s : suffixExp) {
            if ("+".equals(s)){
                num1 = Integer.parseInt(cal.pop());
                num2 = Integer.parseInt(cal.pop());
                cal.push(num2+num1+"");
            } else if ("-".equals(s)){
                num1 = Integer.parseInt(cal.pop());
                num2 = Integer.parseInt(cal.pop());
                cal.push(num2-num1+"");
            }else if ("*".equals(s)){
                num1 = Integer.parseInt(cal.pop());
                num2 = Integer.parseInt(cal.pop());
                cal.push(num2*num1+"");
            }else if ("/".equals(s)){
                num1 = Integer.parseInt(cal.pop());
                num2 = Integer.parseInt(cal.pop());
                cal.push(num2/num1+"");
            }else {
                cal.push(s);
            }
        }

        return Integer.parseInt(cal.pop());
    }

    public ArrayList<String> list(String str){
        ArrayList<String> infixExp = new ArrayList<>();
        int index=0;
        Stack<String> oper = new Stack<>();
        String help;
        char temp;

        while (index<str.length()) {
            temp=str.charAt(index);
            help=temp+"";
            index++;
            if (temp>=48&&temp<=57){//数字
                while(index<str.length()&&str.charAt(index)>=48&&str.charAt(index)<=57){//多位数字
                    temp = str.charAt(index);
                    help+=temp;
                }
                infixExp.add(help);
            }else{//符号
                if (oper.isEmpty()||oper.peek().equals("(")){
                    oper.push(help);
                }else if(help.equals("(")){
                    oper.push(help);
                }else if(help.equals(")")){
                    while (!oper.peek().equals("(")){
                        infixExp.add(oper.pop());
                    }
                    oper.pop();
                }else if(operPriority(help)>operPriority(oper.peek())){
                    oper.push(help);
                }else if(operPriority(help)<=operPriority(oper.peek())){
                    infixExp.add(oper.pop());
                    oper.push(help);
                }
            }
        }

        while (!oper.isEmpty()){
            infixExp.add(oper.pop());
        }

        return infixExp;
    }

    public static int operPriority(String str){
        if (str.equals("+")||str.equals("-")){
            return 0;
        }else if (str.equals("*")||str.equals("/")){
            return 1;
        }else{
            throw new RuntimeException("NullOperException");
        }
    }
}
