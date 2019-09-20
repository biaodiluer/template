package algorithm.queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MaxInSlidingWindow {

    /*
    单调队列：滑动窗口中的最值,双向队列，队头保存最值，只删除，队尾添加和删除
     */

    /*
    给定一个数组[1,3,-1,-3,5,3,6,7] window为3，求window中额最大数
    为[3,3,5,5,6,7]

    单调队列是双端队列，队头是最大元素下标，到队尾下标对应的元素值依次减小

     */

    public static void main(String[] args) {
        int[] arr=new int[]{1,3,-1,-3,5,3,6,7};
        int[] ans=maxSlidingWindow(arr,3);
        System.out.println(Arrays.toString(ans));
    }

    static int[] maxSlidingWindow(int[] arr,int k){
        if(k==0) return arr;
        int[] ans=new int[arr.length-k+1];
        Deque<Integer> dq=new LinkedList();
        for(int i=0;i<arr.length;i++){
            int num=arr[i];
            //判断头部是否需要删除
            if(!dq.isEmpty() && i-dq.getFirst()>=k) dq.removeFirst();
            //从队尾删除过期或者比当前数字小的（过期这个可以忽略，因为不可能出现）
            while(!dq.isEmpty() && (arr[dq.getLast()]<num || i-dq.getLast()>=k)){
                dq.removeLast();
            }
            dq.addLast(i);
            if(i>=k-1){
                ans[i-k+1]=arr[dq.getFirst()];
            }
        }
        return ans;
    }
}
