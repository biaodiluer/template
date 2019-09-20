package algorithm.recursion;

public class Hanoi {
    public static void main(String[] args) {
        //helper(64,'a','b','c');
        System.out.println(count(31));
    }

    // 汉诺塔的打印
    // 该函数的作用是将n个盘子借助b从a移到c
    static void helper(int n, char a, char b, char c) {
        if (n == 1) {
            System.out.println("No." + n + " " + a + "->" + c);
        } else {
            //先将n-1个盘子借助c从a移到b
            helper(n - 1, a, c, b);
            //最下面的从a移到c
            System.out.println("No." + n + " " + a + "->" + c);
            //再将n-1个盘子借助a从b移到c
            helper(n - 1, b, a, c);
        }
    }

    // 移动次数
    // 计算n个盘子要移动几次
    static int count(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            //n-1整体移动到另一根，自己移一次，n-1整体在移动到目标柱子上
            dp[i] = dp[i - 1] + 1 + dp[i - 1];
        }
        return dp[n];
    }
}
