package algorithm.queue;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack<T> {

    //每取一次，将有数据的给没数据的，始终一个有一个没有

    private Queue<T> q1;
    private Queue<T> q2;
    private int size;

    MyStack(){
        q1=new LinkedList<>();
        q2=new LinkedList<>();
    }

    public T pop(){
        if(size==0) return null;
        size--;
        Queue<T> hasData=q2.isEmpty()?q1:q2;
        Queue<T> noData=q2.isEmpty()?q2:q1;
        while(hasData.size()>1){
            noData.offer(hasData.poll());
        }
        return hasData.poll();
    }

    public void push(T element){
        size++;
        //要往有数据的push，都没有数据就选q1，不可能都有数据
        Queue hasData;
        if(q2.isEmpty()){
            hasData=q1;
        }else{
            hasData=q2;
        }
        hasData.offer(element);
    }

    public static void main(String[] args) {
        MyStack<String> stack=new MyStack<>();
        stack.push("1");
        stack.push("2");
        System.out.println(stack.pop());
        stack.push("3");
        System.out.println(stack.pop());
        stack.push("4");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
