package algorithm.queue;

import java.util.PriorityQueue;

public class FindMedian {
    PriorityQueue<Integer> left,right;

    /** initialize your data structure here. */
    public FindMedian() {
        left=new PriorityQueue<>((o1, o2) -> o2-o1);//小顶堆，保存后半部分的值
        right=new PriorityQueue<>((o1, o2) -> o1-o2);//大顶堆，保存前半部分的值
    }

    //始终保持left比right最多多一个
    public void addNum(int num) {
        //前两行用于将新的num融合进堆中，保证left.peek<=right.peek
        left.offer(num);
        right.offer(left.poll());
        if(left.size()<right.size())
            left.offer(right.poll());
    }

    public double findMedian() {
        if(left.size()>right.size()) return left.peek();
        return (left.peek()+right.peek())/2.0;
    }

    public static void main(String[] args) {
        FindMedian findMedian=new FindMedian();
        findMedian.addNum(1);
        findMedian.addNum(3);
        System.out.println(findMedian.findMedian());
        findMedian.addNum(2);
        System.out.println(findMedian.findMedian());
    }
}
