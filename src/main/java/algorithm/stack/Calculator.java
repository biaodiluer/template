package algorithm.stack;

import java.util.Stack;

public class Calculator {

    public static void main(String[] args) {
        Calculator cal=new Calculator();
        System.out.println(cal.calculate("3/2*2"));
    }

    /*
    给定一个没有括号的字符串表达式，求值
     */
    public int calculate(String s){
        Stack<Integer> stack=new Stack();
        int ans=0,num=0;
        char sign='+';
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(Character.isDigit(c)){
                num=num*10+c-'0';
            }
            //碰到符号了就要处理一下上一个符号
            if(c=='+' || c=='-' || c=='*' || c=='/' || i==s.length()-1){
                if(sign=='+') stack.push(num);
                if(sign=='-') stack.push(-num);
                if(sign=='*') stack.push(stack.pop()*num);
                if(sign=='/') stack.push(stack.pop()/num);
                sign=c;
                num=0;
            }
        }
        while(!stack.empty()) ans+=stack.pop();
        return ans;
    }

}
