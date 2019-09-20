package algorithm.twopointers;

public class MinimumWindowSubstring {

    //求满足条件的子串，都是先移动右指针让子串满足条件，然后将左指针尽量往前移
    //求最短字串时，每次移动左指针时记录
    //求最长字串时，每次移动右指针时记录

    //找字符串的最短或最长满足条件的字串，用滑动窗口
    public static void main(String[] args) {
        String s="ADOBECODEBANC";
        String t="ABBC";
        System.out.println(minWindowSubString(s,t));
    }

    /*
    给定字符串 s=ADOBECODEBANC 和 t=ABC,在s中找最短字串使得字串包含t中所有字符,BANC
     */
    static String minWindowSubString(String s,String t){
        int[] bucket=new int[256];//还缺多少
        int has=0;
        for(char c:t.toCharArray()) bucket[c]++;
        int begin=-1,window=Integer.MAX_VALUE;//结果字串的区间
        int l=0,r=0;
        while(r<s.length()){
            //获得右指针的字符
            char rc=s.charAt(r);
            if(bucket[rc]>0){
                //如果新来的字符是在t中，即bucket[rc]>0，则has++，表示已有数量+1
                has++;
            }
            bucket[rc]--;
            r++;
            while(t.length()==has){
                //先看一下是不是最短字串
                if(r-l<window){
                    window=r-l;
                    begin=l;
                }
                //获得左指针的字符
                char lc=s.charAt(l);
                //如果左边的字符是在t中，即bucket[lc]>=0（如果是小于0说明不在t里面，无关紧要，右指针扫描的时候会减掉！）
                //则has--，表示已有数量-1
                if(bucket[lc]>=0) has--;
                bucket[lc]++;
                l++;
            }
        }
        return begin==-1?"":s.substring(begin,begin+window);
    }

}
