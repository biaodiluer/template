package algorithm.sort;

public class HeapSort extends Sort {
    public static void main(String[] args) {
        Sort sort=new HeapSort();
        int[] arr=sort.generateArray(10);
        sort.sort(arr);
    }

    @Override
    void sort(int[] arr) {
        print(arr);
        heapSort(arr);
        print(arr);
    }

    //0号位置有数据，father:(i-1)/2 | left:2*i+1 | right:2*i+2
    void heapSort(int[] arr) {
        //构造大顶堆，i=arr.length/2-1，从最后一个非叶子节点开始，从下往上构造大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //将堆顶元素和末尾元素交换
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            adjustHeap(arr, 0, i);
        }
    }

    //将cur位置为根节点的子树调整成最大堆
    void adjustHeap(int[] arr, int cur, int len) {
        for (int i = cur * 2 + 1; i < len; i = i * 2 + 1) {
            //选出cur两个儿子中大的内个
            if (i + 1 < len && arr[i] < arr[i + 1]) {
                i++;
            }
            //将大的儿子和自己交换一下，并且将cur指向大的儿子，如果没有儿子比自己大，就不用继续了，cur已经是子树中最大的
            if (arr[i] > arr[cur]) {
                swap(arr, i, cur);
                cur = i;
            } else {
                break;
            }
        }
    }
}
