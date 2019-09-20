package algorithm.recursion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Permutations {
    static List<Integer> list;
    static boolean[] used;

    public static void main(String[] args) {
        //init
        int[] arr=new int[]{1,2,3,3};
        int len=arr.length;
        list = new LinkedList();
        used=new boolean[len];
        Arrays.sort(arr);

        //functions
        System.out.println("全排列");
        fullPermutation(arr);
        System.out.println("全排列的子集");
        subPermutation(arr,0);
    }

    /*
    给定一个含有重复元素的数组，求全排列，需要去重，不需要的话把if注释掉
     */
    private static void fullPermutation(int[] arr) {
        if(arr.length==list.size()){
            System.out.println(list);
        }else{
            for(int i=0;i<arr.length;i++){
                //排完序后相同的数字挨在一起，相同的数只从最左边开始选，用完后，左边第二个就不能选，因为相同的数字之前已经用过了
                //确保只有第一个重复数可以以第一个元素的身份算进去
                if(i>0 && arr[i-1]==arr[i] && !used[i-1]) continue;
                if(!used[i]){
                    used[i]=true;
                    list.add(arr[i]);
                    fullPermutation(arr);
                    list.remove(list.size()-1);
                    used[i]=false;
                }
            }
        }
    }

    /*
    给定一个有重复元素的数组，枚举每个元素是否出现的所有可能，需要去重
     */
    private static void subPermutation(int[] nums,int pos){
        System.out.println(list);
        for(int i=pos;i<nums.length;i++){
            if(i>pos && nums[i-1]==nums[i]) continue;
            list.add(nums[i]);
            subPermutation(nums,i+1);
            list.remove(list.size()-1);
        }
    }


}
