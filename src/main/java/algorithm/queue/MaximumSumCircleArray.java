package algorithm.queue;

import java.util.Deque;
import java.util.LinkedList;

public class MaximumSumCircleArray {

    public static void main(String[] args) {
        int[] arr=new int[]{-2,1,3,-2};
        System.out.println(maxSubarraySumCircular(arr));
    }

    static public int maxSubarraySumCircular(int[] A) {
        //将长度为n的循环数组变成2n的非循环数组
        int[] arr=new int[2*A.length];
        for(int i=0;i<A.length;i++){
            arr[i]=arr[i+A.length]=A[i];
        }
        //在新数组中找区间长度为[1,n]的和的最大值
        //构造前缀和
        int[] prefix=new int[arr.length+1];
        for(int i=1;i<prefix.length;i++){
            prefix[i]=prefix[i-1]+arr[i-1];
        }
        //问题就转化成了求prefix[i]-prefix[j]的最大值，其中i-j<=n,即求某个数左边n个数以内的最小值
        Deque<Integer> dq=new LinkedList();
        dq.addFirst(0);
        int ans=Integer.MIN_VALUE;
        for(int i=1;i<prefix.length;i++){
            if(!dq.isEmpty() && i-dq.getFirst()>A.length) dq.removeFirst();
            ans=Math.max(ans,prefix[i]-prefix[dq.getFirst()]);
            while(!dq.isEmpty() && prefix[dq.getLast()]>prefix[i]) dq.removeLast();
            dq.addLast(i);
        }
        return ans;
    }
}
