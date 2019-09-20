package algorithm;

import java.util.Arrays;

public class NumberTheory {

    /*
    一些数论方面的模板
     */

    public static void main(String[] args) {
        //primes(100);
        System.out.println(Arrays.toString(exgcd(15, 10)));
    }

    /*
    最大公约数 logn，想象a>b
     */
    private static int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

    /*
    拓展欧几里得算法，logn内求出ax+by=gcd(a,b)中x、y的值
     */
    private static int[] exgcd(int a, int b) {
        int[] ans = new int[3];//0-最大公约数，1-x，2-y
        if (b == 0) {
            ans[0] = a;
            ans[1] = 1;
            ans[2] = 0;
            return ans;
        }
        int[] temp = exgcd(b, a % b);
        ans[0] = temp[0];
        ans[1] = temp[2];
        ans[2] = temp[1] - (a / b) * temp[2];
        return ans;
    }

    /*
    求1-n的素数
    1、最傻的做法是一个一个判断，O(nlogn)
    2、普通筛选：将当前数字的2345倍全部去掉，2的3倍和3的2倍会重复
    2、线性筛选：将当前数字作为倍数，和已有的素数相乘并且筛去，不会有重复
     */
    private static void primes(int n) {
        int cnt = 0;
        int[] primes = new int[n];
        boolean[] flag = new boolean[n + 2];//true是合数，false是素数（默认）
        for (int i = 2; i <= n; i++) {
            //如果当前的是素数
            if (!flag[i]) primes[cnt++] = i;
            //线性筛选就不会有重复去除的情况
            for (int j = 0; j < cnt; j++) {
                int ji = primes[j] * i;
                if (ji > n) break;
                flag[ji] = true;
            }
        }
        System.out.println(Arrays.toString(primes));
    }
}
