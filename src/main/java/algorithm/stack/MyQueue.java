package algorithm.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyQueue<T> {

    /*
    先往q1里放，取得时候先从q2取，没了再把q1的送到q2里去
     */
    private Stack<T> stack1;
    private Stack<T> stack2;
    private int size;

    MyQueue(){
        stack1=new Stack<>();
        stack2=new Stack<>();
    }

    public void offer(T element){
        stack1.push(element);
        size++;
    }

    public T poll(){
        if(size==0){
            return null;
        }else{
            size--;
            if(!stack2.isEmpty()){
                return stack2.pop();
            }else{
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
        }
    }

    public static void main(String[] args) {
        MyQueue<String> q=new MyQueue<>();
        q.offer("1");
        q.offer("11");
        System.out.println(q.poll());
        q.offer("111");
        q.offer("1111");
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
    }
}
