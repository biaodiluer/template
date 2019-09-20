package algorithm.twopointers;

public class LongestValidParentheses {

    /*
    给定括号字符串的最长有效括号字串
    一个有效括号串<=>所有前缀和>=0 && 最后一个前缀和=0
    (:+1
    ):-1
    sum>0:[l,r]不是非法括号串，continue
    sum<0:[l,r]是非法的，直接l=r，并且sum=0
    sum=0:[l,r]记录
    前后扫描两边
     */
    public int longestValidParentheses(String s) {
        int ans=0;
        //根据括号匹配的性质，正着扫描一遍
        for(int l=0,r=0,sum=0;r<s.length();r++){
            char rc=s.charAt(r);
            if(rc=='(') sum++;
            else sum--;
            if(sum==0) ans=Math.max(ans,r-l+1);
            else if(sum<0) {
                l=r+1;
                sum=0;
            }
        }
        //反着扫描一遍
        for(int l=s.length()-1,r=s.length()-1,sum=0;l>=0;l--){
            char lc=s.charAt(l);
            if(lc==')') sum++;
            else sum--;
            if(sum==0) ans=Math.max(ans,r-l+1);
            else if(sum<0) {
                r=l-1;
                sum=0;
            }
        }
        return ans;
    }
}
