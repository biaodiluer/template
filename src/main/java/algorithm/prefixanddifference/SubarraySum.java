package algorithm.prefixanddifference;

import java.util.HashMap;
import java.util.Map;

public class SubarraySum {

    public static void main(String[] args) {
        int[] arr=new int[]{1,1,1};
        int k=2;
        System.out.println(subarraySum(arr,k));
    }

    /*
    给定一个数组和一个k，求数组中sum为k的子数组的个数，前缀和
    [1,1,1] 2 返回2
     */
    public static int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap();
        int ans=0,sum=0;
        /*
        用前缀和sum记录[0,i]的和，并在i之前能否找到[0,j]和为sum-k的情况，为什么是sum-k呢，因为
        [0,j]+[j+1,i]=[0,j] => [0,j]+k=sum => [0,j]=sum-k
        就需要把每次的sum作为key，出现的次数作为value保存起来
        */
        for(int n:nums){
            sum+=n;
            if(sum==k) ans++;//这个算法不能处理[0,i]就已经是k的情况，所以要额外加一次[0,i]的，在循环外面加map.put(0,1)也是可以的
            if(map.containsKey(sum-k)){
                ans+=map.get(sum-k);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return ans;
    }
}
