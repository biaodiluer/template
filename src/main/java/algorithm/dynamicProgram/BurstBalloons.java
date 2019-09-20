package algorithm.dynamicProgram;

public class BurstBalloons {
    /*
    给定一个数组，每去掉一个数字，可以获得nums[k-1]*nums[k]*nums[k+1]分
    求最大的分数
    dp[i][j]表示[i,j]范围内的最大得分
    对于范围left和right的最大得分
    dp[left][right]=max(self,
        左边部分的最大值+右边部分的最大值+curx*(left-1)v*(right+1)v,因为是最后一个刺破的，所以不是nums[k-1]*nums[k]*nums[k+1]
        dp[left][k-1]+dp[k+1][right]+nums[left-1]*nums[k]*nums[right+1]);
     */

    private static int[] newArr(int[] nums) {
        int len = nums.length;
        int[] arr = new int[len + 2];
        for (int i = 0; i < len; i++) arr[i + 1] = nums[i];
        arr[0] = arr[len + 1] = 1;
        return arr;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 5, 8};
        System.out.println(helper1(nums));
        System.out.println(helper2(nums));
    }

    /*
    递归的做法,left和right会被戳爆
     */
    private static int helper1(int[] nums) {
        int[] arr = newArr(nums);
        int[][] dp = new int[arr.length][arr.length];
        return burst(arr, dp, 1, arr.length - 2);
    }

    private static int burst(int[] arr, int[][] dp, int left, int right) {
        if (left > right) return 0;
        if (dp[left][right] > 0) return dp[left][right];
        //枚举最后一个戳破的气球，得分为curV*leftV*rightV(要假设左边和右边的全部去掉了)+左边的得分+右边的得分
        for (int i = left; i <= right; i++) {
            int leftV = burst(arr, dp, left, i - 1);
            int rightV = burst(arr, dp, i + 1, right);
            dp[left][right] = Math.max(dp[left][right],
                    leftV + rightV + arr[i] * arr[left - 1] * arr[right + 1]);
        }
        return dp[left][right];
    }

    /*
    动归的做法
     */
    private static int helper2(int[] nums) {
        int num = nums.length;
        int[] arr = newArr(nums);
        int len = arr.length;
        int[][] dp = new int[len][len];
        //枚举粒度
        for (int l = 1; l <= num; l++) {
            //枚举左端点
            for (int left = 1; left <= num - l + 1; left++) {
                //根据左端点得到右端点
                int right = left + l - 1;
                //枚举最后一个刺破的气球
                for (int k = left; k <= right; k++) {
                    int leftV = dp[left][k - 1];
                    int rightV = dp[k + 1][right];
                    dp[left][right] = Math.max(dp[left][right],
                            leftV + rightV + arr[left - 1] * arr[k] * arr[right + 1]);
                }
            }
        }
        return dp[1][len - 2];
    }
}
