package algorithm.dynamicProgram;

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if(s==null || p==null) return false;
        int sLen=s.length(),pLen=p.length();
        //dp[i][j]表示字符串substring(0,i)是否可以匹配正则表达式substring(0,j)
        boolean[][] dp=new boolean[sLen+1][pLen+1];
        dp[0][0]=true;
        for(int i=1;i<=pLen;i++){
            if(p.charAt(i-1)=='*' && dp[0][i-2]){
                dp[0][i]=true;
            }
        }
        for(int si=1;si<=sLen;si++){
            char sc=s.charAt(si-1);
            for(int pi=1;pi<=pLen;pi++){
                char pc=p.charAt(pi-1);
                if(pc=='*'){
                    //复杂的处理
                    char pcpre=p.charAt(pi-2);
                    if(pcpre==sc || pcpre=='.'){
                        dp[si][pi]=dp[si-1][pi]||dp[si][pi-2]||dp[si-1][pi-2];
                    }else{
                        dp[si][pi]=dp[si][pi-2];
                    }
                }else{
                    if(pc==sc || pc=='.'){
                        dp[si][pi]=dp[si-1][pi-1];
                    }
                    //这里的else一定是false就不写了
                }
            }
        }
        return dp[sLen][pLen];
    }
}
