package algorithm.recursion;

import java.util.LinkedList;
import java.util.List;

public class CombinationSum {
    static List<Integer> list;
    static boolean[] used;

    public static void main(String[] args) {
        //init
        list = new LinkedList();

        //functions
        System.out.println("无重复元素可重复选");
        combinationRepeat(new int[]{2,3,6,7},7,0);
        System.out.println("有重复元素不可中复选");
        combinationNoRepeat(new int[]{1,1,2,5,6,7,10},8,0);
        System.out.println("k个数字和为target");
        combinationStepsTarget(1,3,14);
    }

    /*
    给定一个没有重复元素的数组，可重复的选出若干元素，使其和为target，列出所有的选法
     */

    static void combinationRepeat(int[] nums,int target,int pos){
        if(target==0){
            System.out.println(list);
        }else if(target>0){
            for(int i=pos;i<nums.length;i++){
                if(target-nums[i]<0) break;//排序后，这句避免了很多不必要的情况
                list.add(nums[i]);
                combinationRepeat(nums,target-nums[i],i);
                list.remove(list.size()-1);
            }
        }
    }

    /*
    给定一个有重复元素的数组，不可重复的选出和为target的组合
     */
    static void combinationNoRepeat(int[] nums,int target,int pos){
        if(target==0){
            System.out.println(list);
        }else if(target>0){
            for(int i=pos;i<nums.length;i++){
                if(target-nums[i]<0) break;
                if(i>pos && nums[i-1]==nums[i]) continue;//去掉这一句就可以有重复的组合了
                list.add(nums[i]);
                combinationNoRepeat(nums,target-nums[i],i+1);
                list.remove(list.size()-1);
            }
        }
    }

    /*
    求k个数字之和为target，且k个数字不重复的组合
     */
    static void combinationStepsTarget(int cur,int steps,int target){
        if(target==0 && steps==0){
            System.out.println(list);
            return;
        }
        if(target>0 && steps>0){
            int i=cur;
            while(target-i>=0){
                list.add(i);
                combinationStepsTarget(i+1,steps-1,target-i);
                list.remove(list.size()-1);
                i++;
            }
        }
    }
}
