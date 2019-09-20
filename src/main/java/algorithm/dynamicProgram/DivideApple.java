package algorithm.dynamicProgram;

import java.util.Arrays;

public class DivideApple {
    /*
    有M个苹果和N个盘子，有几种分发，1|1|2和2|1|1视为同一种,允许空盘
    如果不允许空盘，则调用apple0(M-N,N)即可，每个盘子先放一个
     */
    static int apple0(int M, int N) {
        //dp[i][j]表示i个苹果j个盘子有几种分法，i初始值为0，j初始值为1，即0个盘子分法为0
        int[][] dp = new int[M + 1][N + 1];
        //0个苹果，i个盘子只有一种
        for (int i = 1; i <= N; i++) {
            //0个盘子是非法的
            dp[0][i] = 1;
        }
        //i个苹果，1个盘子只有一种
        for (int i = 0; i <= M; i++) {
            dp[i][1] = 1;
        }
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (i < j) {
                    //如果苹果数量小于盘子数量，必定有i-j个盘子是空的，干脆去掉，等价于i个苹果i个盘子
                    dp[i][j] = dp[i][i];
                } else {
                    //如果苹果较多，那么有两种情况
                    //其中一个盘子不动都放在其他盘子里dp[i][j-1]+每个盘子只加一个苹果dp[i-j][j]
                    dp[i][j] = dp[i][j - 1] + dp[i - j][j];
                }
            }
        }
        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }
        return dp[M][N];
    }

    /*
    递归版
     */
    static int appleRecursion(int M, int N) {
        if (M == 0 || N == 1) return 1;
        if (M < N) return appleRecursion(M, M);
        else return appleRecursion(M, N - 1) + appleRecursion(M - N, N);
    }

    public static void main(String[] args) {
        int M = 7, N = 3;
        System.out.println(apple0(M, N));
    }
}
