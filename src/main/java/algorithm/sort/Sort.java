package algorithm.sort;

import java.util.Arrays;

public abstract class Sort {

    abstract void sort(int[] arr);

    public int[] generateArray(int len){
        int[] arr=new int[len];
        double min=-10,max=10;
        for(int i=0;i<len;i++){
            double a=min+Math.random()*(max-min);
            arr[i]=(int)a;
        }
        return arr;
    }

    protected void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    protected void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
