package algorithm.recursion;

import java.util.Arrays;

public class MatchSticksToSquare {
    /*
    给定若干根已知长度的火柴，求不折断的情况下能否拼成一个正方形
     */
    public static void main(String[] args) {

    }

    public boolean makesquare(int[] nums) {
        int sum=0;
        for(int n:nums) sum+=n;
        if(sum%4!=0 || sum<4) return false;
        //对于sum和avg不合格的去掉
        int target=sum>>2;
        int len=nums.length;
        boolean[] used=new boolean[len];
        //先排个序，后面要从最后一个开始走
        Arrays.sort(nums);
        //因为是四个边所以执行四次，有一次不能满足就返回false
        for(int i=0;i<4;i++){
            //这里从大的火柴开始用，因为这样既能减少搜索深度，又能保证大的能够用上
            if(!dfs(nums,used,target,nums.length-1)) return false;
        }
        return true;
    }

    //找里面有没有和为target的情况
    boolean dfs(int[] nums,boolean[] used,int target,int pos){
        if(target==0) return true;
        if(target<0) return false;
        for(int i=pos;i>=0;i--){
            if(!used[i]){
                used[i]=true;
                if(dfs(nums,used,target-nums[i],i-1)) return true;
                used[i]=false;
            }
        }
        return false;
    }
}
