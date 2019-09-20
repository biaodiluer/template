package algorithm.dynamicProgram;

import java.util.Arrays;

public class LongestPalindromeSubSequence {
    public static void main(String[] args) {
        System.out.println(helper("BBABCBCAB"));
    }

    /*
    给定一个字符串，求最长回文子序列的长度
    构造二维矩阵dp[i,j]表示区间[i,j]中最长回文子序列的长度
    外循环处理以i为起点的最长长度，内循环处理每一个结尾
     */
    static int helper(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int left = len - 1; left >= 0; left--) {
            dp[left][left] = 1;
            char cl = s.charAt(left);
            for (int right = left + 1; right < len; right++) {
                char cr = s.charAt(right);
                if (cl == cr) {
                    dp[left][right] = dp[left + 1][right - 1] + 2;
                } else {
                    dp[left][right] = Math.max(dp[left + 1][right], dp[left][right - 1]);
                }
            }
        }
        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }
        return dp[0][len - 1];
    }
}
