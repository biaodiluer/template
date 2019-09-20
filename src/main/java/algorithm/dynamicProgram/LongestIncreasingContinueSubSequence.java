package algorithm.dynamicProgram;

public class LongestIncreasingContinueSubSequence {
    public static void main(String[] args) {

    }

    static int helper(int[] arr) {
        int count = 1, max = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
