package algorithm.sort;

public class BubbleSort extends Sort{
    public static void main(String[] args) {
        Sort sort=new BubbleSort();
        int[] arr=sort.generateArray(10);
        sort.sort(arr);
    }

    @Override
    void sort(int[] arr) {
        print(arr);
        bubbleSort(arr);
        print(arr);
    }

    void bubbleSort(int[] arr){
        int len=arr.length;
        for(int i=0;i<len;i++){
            boolean tag=false;
            for(int j=0;j<len-i-1;j++){
                if(arr[j]>arr[j+1]) {
                    tag = true;
                    swap(arr, j, j + 1);
                }
            }
            if(!tag) break;
        }
    }
}
