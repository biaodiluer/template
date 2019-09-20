package algorithm;

import java.util.ArrayList;
import java.util.List;

public class KMP {
    /*
    利用已经匹配的信息，直接跳到下一个可能匹配的地方

    引入字符串的最长相同前后缀数组，next[i]表示字串[0,i]的最长相同前后缀长度
    abaabbabaab
    00112012345
    第一个2=>abaab ab是最长的相同的前后缀，所以是2
    第一个3=>abaabbaba aba是最长的相同的前后缀，所以是3
    第一个5=>abaabbabaab abaab是最长的相同前后缀，所以是5

    为了方便使用，会把数组的最后一个去掉，在最前面加上-1，形成next数组

    就变成了
    -1 0 0 1 1 2 0 1 2 3 4

     */

    private static int[] next(String pattern){
        int[] next=new int[pattern.length()];
        next[0]=-1;
        int i=0,len=-1;
        while(i<pattern.length()-1){
            //如果相同就都往右移，否则len跳到前缀长度的下一个位置，并从这里继续和i匹配
            if(len==-1 || pattern.charAt(len)==pattern.charAt(i)){
                i++;
                len++;
                next[i]=len;
            }else{
                len=next[len];
            }
        }
        return next;
    }

    static List<Integer> kmpSearch(String text,String pattern){
        List<Integer> list=new ArrayList();
        int[] next=next(pattern);
        int i=0,j=0;//i指向text，j指向pattern
        while(i<text.length()){
            if(j==-1 || text.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
            }else{
                //如果没有匹配上，就要把pattern往右移若干距离，使得j是下一个要被匹配的位置，即j要变小
                j=next[j];
            }
            if(j==pattern.length()){
                list.add(i-j);
                //匹配成功了的话，i还不能移动，所以要移回来
                i--;
                j--;
                j=next[j];
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String text="abaaabaabbabaab523cabaabbabaab";
        String pattern="abaabbabaab";
        List<Integer> ans=kmpSearch(text,pattern);
        System.out.println(ans);
    }
}
