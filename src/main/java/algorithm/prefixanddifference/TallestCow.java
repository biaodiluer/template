package algorithm.prefixanddifference;

import java.util.Set;

public class TallestCow {
    /*
    有N头牛，第P头牛最高有H，当且仅当两头牛中间的牛身高都比它们矮时，两头牛方可看到对方，给出M对关系，求所有牛的最高身高
    set里面的是从1开始的
     */
    static void heightestCow(int N, int H, Set<String> set) {
        //利用差分，如果l和r可以看见，则说明之间的身高一定比l和r低，即-1
        int[] heightDiff = new int[N + 2];
        heightDiff[1] = H;
        for (String s : set) {
            String[] ss = s.split(" ");
            int l = Integer.parseInt(ss[0]);
            int r = Integer.parseInt(ss[1]);
            //因为l和r之间的要-1，不包括l和r本身，所以是[l+1]和[r]
            heightDiff[l + 1]--;
            heightDiff[r]++;
        }
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans += heightDiff[i];
            System.out.println(ans);
        }
    }
}
