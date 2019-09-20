package algorithm.twopointers;

public class TwoSum {

    //给定一个递增数组，求和为target的两个数的下标

    public int[] twoSum(int[] numbers, int target) {
        int[] ans=new int[2];
        int l=0,r=numbers.length-1;
        while(l<r){
            int sum=numbers[l]+numbers[r];
            if(sum==target) break;
            if(sum>target) r--;
            else l++;
        }
        ans[0]=l+1;
        ans[1]=r+1;
        return ans;
    }
}
