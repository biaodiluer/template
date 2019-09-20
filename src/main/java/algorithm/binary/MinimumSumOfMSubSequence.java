package algorithm.binary;

public class MinimumSumOfMSubSequence {
    /*例子，给定一个正整数数组，长度为N，分成连续的M个子序列，每个子序列的和为S，求一种分法，使得最大的S能够最小，返回这个S值
     思路：要找这样的值，就相当于找一个mid，使得在一次划分中所有的S<=mid，找出mid的最小值
     该最小值一定在[left,right]中，开始的时候，left是元素的最小值，right是元素的和
     判断某个mid是否是答案，每次遍历这个数组，贪心的使得每个子序列里面的个数尽量多但又不超过mid，
     因为目标是缩小mid，即尽可能的将mid判断成合法，如果每个S都不超过mid且子序列个数<=M，那么分成M个子序列，最大的S会更小
     所以要让子序列里的元素尽可能的多
     直到left和right相等
      */

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 2, 5, 4};
        int M = 3;
        System.out.println(bSearch(arr, M));
    }

    private static int bSearch(int[] arr, int M) {
        //定义S的左右边界,[maxOfArr,sumOfArr]
        int left = Integer.MIN_VALUE, right = 0;
        for (int a : arr) {
            left = Math.max(left, a);
            right += a;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            //如果当前mid满足条件，则mid可以变得更小
            if (satisfy(arr, M, mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private static boolean satisfy(int[] arr, int M, int max) {
        int sum = 0;
        int parts = 1;
        System.out.println("max:" + max);
        for (int a : arr) {
            //如果当前元素算上去后的S>max,则应停止添加元素，sum从头开始
            if (sum + a <= max) {
                sum += a;
                System.out.print(a + " ");
            } else {
                System.out.print(" | ");
                parts++;
                sum = a;
                System.out.print(a + " ");
            }
        }
        System.out.println();
        if (parts > M) return false;
        return true;
    }
}
