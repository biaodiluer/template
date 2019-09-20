package algorithm.twopointers;

public class LongestSubstringWithoutRepeats {

    //求满足条件的子串，都是先移动右指针让子串满足条件，然后将左指针尽量往前移
    //求最短字串时，每次移动左指针时记录
    //求最长字串时，每次移动右指针时记录

    public static void main(String[] args) {
        String s="bbbaabb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    /*
    求一个字符串中不包含重复字符的子串
     */
    static int lengthOfLongestSubstring(String s){
        int[] bucket=new int[256];
        int l=0,r=0,window=0;
        while(r<s.length()){
            char lc=s.charAt(l);
            char rc=s.charAt(r);
            if(bucket[rc]==0){
                //right可以被包含
                bucket[rc]++;
                r++;
                window=Math.max(window,r-l);
            }else{
                //left要往前移
                bucket[lc]--;
                l++;
            }
        }
        return window;
    }
}
