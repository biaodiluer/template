package algorithm.dynamicProgram;

public class LongestCommonSubSequence {
    /*
    最长公共子序列  LCS
    给定两个序列，找出最长的不一定连续的公共子序列
    打印任一一个序列？？？
     */

    static int helper(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            int a = arr1[i - 1];
            for (int j = 1; j <= len2; j++) {
                int b = arr2[j - 1];
                if (a == b) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
//                    System.out.println(a);
                } else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
//        for(int[] arr:dp){
//            System.out.println(Arrays.toString(arr));
//        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 2, 4, 1, 2};
        int[] arr2 = new int[]{2, 4, 3, 1, 2, 1};
        System.out.println(helper(arr1, arr2));
    }
}
