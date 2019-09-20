package algorithm.twopointers;

public class RemoveDuplicates {

    /*
    给定一个递增数组，里面有和重复的元素，要把重复的去掉只剩一个，并返回最后剩下的长度
    一个指针在前，表示元素插入的位置，一个指针在后，发现两个不一样的数字就把后者复制到前面去
     */

    public int removeDuplicates(int[] nums) {
        int pos=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i-1]!=nums[i]){
                nums[pos++]=nums[i];
            }
        }
        return pos;
    }
}
