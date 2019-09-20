package algorithm.sort;

public class MergeSort extends Sort {
    public static void main(String[] args) {
        Sort sort=new MergeSort();
        int[] arr=sort.generateArray(10);
        sort.sort(arr);
    }

    @Override
    void sort(int[] arr) {
        print(arr);
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
        print(arr);
    }

    private void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) >> 1;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) temp[t++] = arr[i++];
            else temp[t++] = arr[j++];
        }
        while (i <= mid) temp[t++] = arr[i++];
        while (j <= right) temp[t++] = arr[j++];
        t = 0;
        while (left <= right) arr[left++] = temp[t++];
    }
}
