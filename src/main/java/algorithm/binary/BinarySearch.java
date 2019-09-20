package algorithm.binary;

public class BinarySearch {
    /*
    模拟满足条件的函数
     */
    private static boolean satisfy(int mid, int target) {
        return true;
    }

    /*
    模板1：找精确值，适用于找到了就返回的情况
    循环条件是left<=right,分成三个区间，[left,mid-1],mid,[mid+1,right]
     */
    public void bSearch1(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (satisfy(mid, target)) return;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
    }

    /*
    模板2：找满足情况的最小值，通过不断变小mid，使得条件满足（该条件必须满足二段性，即前一段不满足后一段满足）
    循环条件是left<right,分成两个区间，[left,mid],[mid+1,right]
     */
    public void bSearch2(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            //因为满足条件，所以可以继续变小，当前值不能去掉
            if (satisfy(mid, target)) right = mid;
            else left = mid + 1;
        }
    }

    /*
    模板3：找满足情况的最大值，通过不断变大mid，使得条件满足
    循环条件是left<right,分成两个区间，[left,mid-1],[mid,right]
     */
    public void bSearch3(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            //因为满足条件，所以可以继续变大，当前值不能去掉
            if (satisfy(mid, target)) left = mid;
            else right = mid - 1;
        }
    }

    /*
    模板4：针对于浮点型数据的较为精确值,或者是乱序数组
    循环条件是left<right,分成两个区间，[left,mid-1],[mid,right]
     */
    public void bSearch3(double start, double end) {
        while (end - start > 1e-6) {
            double mid = (start + end) / 2;
            //这里不分最小最大值
            if (satisfy(1, 1)) start = mid;
            else end = mid;
        }
    }
}
