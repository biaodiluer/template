package algorithm.sort;

public class QuickSort extends Sort {
    public static void main(String[] args) {
        Sort sort=new QuickSort();
        int[] arr=sort.generateArray(10);
        sort.sort(arr);
    }

    @Override
    void sort(int[] arr) {
        print(arr);
        quickSort(arr, 0, arr.length - 1);
        print(arr);
    }

    void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int i = left, j = right, base = arr[left];
        while (i < j) {
            //不断交换arr[i]>base>arr[j]的位置
            while (i < j && base <= arr[j]) j--;
            while (i < j && base >= arr[i]) i++;
            if (i < j) swap(arr, i, j);
        }
        swap(arr, left, i);
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
}
