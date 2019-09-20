package algorithm.dynamicProgram;

public class EditDistance {
    //动态规划的经典题目
    //给定两个字符串，每次操作可以对一个字符串进行增删改一个字符，求最少操作次数可以让两个字符串相等
    public static void main(String[] args) {
        System.out.println(helper("abc", "bca"));
    }

    static private int helper(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        //dp[i][j]表示让s1的前i个字符和s2的前j个字符相等需要的最少操作次数，需要考虑空字符串
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            char c1 = s1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char c2 = s2.charAt(j - 1);
                if (c1 == c2) {
                    //如果新的两个字符相等，那么只需要对前面的字符串进行操作，最小次数和不加新的字符一样
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //如果不相等，那么就是各取前面的字符串和都取前面的字符串里面最小的+1,1是当前不一样的地方
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
