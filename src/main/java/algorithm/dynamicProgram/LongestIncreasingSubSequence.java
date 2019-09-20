package algorithm.dynamicProgram;

import java.util.Arrays;

public class LongestIncreasingSubSequence {
    /*
    最长递增子序列 LIS
    某个数组，求最长的递增的子序列（不一定连续）
     */
    static int helper(int[] arr) {
        int[] dp = new int[arr.length];
        int max = 1;
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            int right = arr[i];
            for (int j = 0; j < i; j++) {
                int left = arr[j];
                if (left < right) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        //打印任意一个最长递增子序列
        int temp = max;
        String ans = "";
        for (int i = dp.length - 1; i >= 0 && temp > 0; i--) {
            if (dp[i] == temp) {
                temp--;
                ans = arr[i] + " " + ans;
            }
        }
        System.out.println(ans);
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 8, 7, 6, 9, 90, 4, 3, 10, 5, 2, 3, 1};
        System.out.println(helper(arr));
    }

    /*
    求最长递增子序列的个数
     */
    static int count(int[] arr) {
        //用于存储arr[i]结尾的LIS的长度
        int[] dp = new int[arr.length];
        //用于存储arr[i]结尾的LIS的个数
        int[] dpNum = new int[arr.length];
        int max = 1;
        dp[0] = 1;
        Arrays.fill(dpNum, 1);
        for (int i = 1; i < arr.length; i++) {
            int right = arr[i];
            for (int j = 0; j < i; j++) {
                int left = arr[j];
                if (left < right) {
                    if (dp[i] < dp[j] + 1) {
                        //如果dp[i]是第一次被赋值
                        dp[i] = dp[j] + 1;
                        dpNum[i] = dpNum[j];
                    } else if (dp[i] == dp[j] + 1) {
                        //如果之前已经有长度相同的出现过了
                        dpNum[i] += dpNum[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            if (dpNum[i] == max) ans += max;
        }
        return ans;
    }
}
