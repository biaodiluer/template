package algorithm;

public class Josephus {
    /*
    n个人围成一圈，从1开始报数，第k个人出局，下一个再从1报数，求出去的序列
     */

    /*
    模拟解法,模拟每一次报数，可以求出出环序列
     */
    static void helper(int n, int k) {
        boolean[] isOut = new boolean[n];
        int num = n;//剩余人数
        int pos = 0;//当前位置
        int count = 0;//当前口令
        while (num > 0) {
            count++;
            if (count == k) {
                //当前这个人要出去
                isOut[pos] = true;
                num--;
                count = 0;
                System.out.println(pos);
            }
            //找到下一个在环里面的人:剩余人数>0 && pos合法 && isOut[pos]==true
            while (num > 0) {
                pos = (pos + 1) % n;
                if (!isOut[pos]) break;
            }
        }
    }

    /*
    递推公式，找规律
    0,1,2,3...m-2,m-1,m,m+1,m+2,...n
    第一次m-1出环，用@@表示
    0,1,2,3...m-2,@@,m,m+1,m+2,...n
    重新编号让m为0
    n-m,n-m+1,...,@@,0,1,2...n-1-m
    类似于求
    0,1,2,3...m-2,m-1,m,m+1,m+2,...n-1了

    res[i]=(res[i-1]+m)%i;
     */
    static void helper1(int n, int k) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + k) % i;
            System.out.println(res);
        }
        //System.out.println(res);
    }


    public static void main(String[] args) {
        helper(5,3);
        //System.out.println();
        //helper1(5,3);
        //System.out.println(helper11(5, 2));
    }


    /*
    第一次m，第二次m2，第三次m3求最后一个
     */
    static int helper11(int n, int m) {
        boolean[] isOut = new boolean[n + 1];
        int pos = 1;
        for (int t = 1, left = n; t <= n; t++, left--) {
            //获得要走的步数
            int steps = 1;
            for (int i = 1; i <= t; i++) steps = steps * m % left;
            if (steps == 0) steps = left;
            //把步数走完
            while (true) {
                //如果当前还在圈里面
                if (!isOut[pos]) {
                    steps--;
                    if (steps == 0) {
                        isOut[pos] = true;
                        System.out.println(pos);
                        break;
                    }
                }
                pos++;
                if (pos > n) pos = 1;
            }
        }
        return pos;
    }
}
