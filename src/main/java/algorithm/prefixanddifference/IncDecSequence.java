package algorithm.prefixanddifference;

public class IncDecSequence {

    /*
    2、差分
     arr[0],arr[1],arr[2],,,,,,arr[n]
     用一个数组保存相邻两个arr的差b[i]=arr[i]-arr[i-1] (b[0]=arr[0])
     arr就是b的前缀和序列 arr[i]=b[0]+b[1]+...+b[i]
     可以在O(1)时间内在给一个区间里所有的数加上某个值
     在区间[l,r]加上一个常数C后 --> 差分序列更新：b[l]+=C b[r+1]-=C 因为arr[l]加上C后就导致b[l]多了C，同理b[r+1]少了C

     */

    public static void main(String[] args) {
        int[] arr=new int[]{1,1,2,2};
        incdec(arr);
    }

    /*
    有一个数组，在任意连续区间可以把区间内的所有数字+1或者-1
    求所有数字变成一样的最少操作次数，以及可能的方案数
     */
    static void incdec(int[] arr) {
        //因为题目有说连续区间所有数字加一个常数，所以用差分法
        long pos = 0, neg = 0;
        for (int i = 1; i < arr.length; i++) {
            long diff = arr[i] - arr[i - 1];
            //对于1-n的差分，最后等于0就行，需要统计1-n中正负数的和。
            if (diff > 0) pos += diff;
            else neg -= diff;
        }
        //正数-1，负数+1（b[l]+=C b[r]-=C），最少操作次数=min(pos,neg)+abs(pos-neg)=max(pos,neg)
        long minOpNums = Math.max(pos, neg);
        //方案数，即最后数字有几种可能，可以做到最后的非0数字在最后一个数字上，
        long nums = Math.abs(pos - neg) + 1;

        System.out.println(minOpNums+"  "+nums);
    }
}
