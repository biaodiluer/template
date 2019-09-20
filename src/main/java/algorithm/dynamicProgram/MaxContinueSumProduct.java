package algorithm.dynamicProgram;

public class MaxContinueSumProduct {
    /*
    规定一个数组，求连续区间里的最大和或者乘积
     */

    /*
    求最大和
    和越正越好，如果小于零了就把和置位当前数字，否则就加上当前数字
     */
    static int sum(int[] arr) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int a : arr) {
            if (sum < 0) {
                sum = a;
            } else {
                sum += a;
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    /*
    乘积最大：保存两个变量max和min，表示当前数字之前的最大乘积和最小乘积
    最大乘积和最小乘积是三种情况之一，max*cur || min*cur || cur
     */
    static int product(int[] arr) {
        int max = arr[0], min = arr[0];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            int tempMax = max * cur;
            int tempMin = min * cur;
            max = Math.max(cur, Math.max(tempMax, tempMin));
            min = Math.min(cur, Math.min(tempMax, tempMin));
            ans = Math.max(ans, max);
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
