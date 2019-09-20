package algorithm.unionfind;

public class FriendCircle {

    /*
    并查集：一开始让每个元素构成只包含一个元素的几何，然后按照一定顺序将属于同一类的元素的集合合并，期间要反复查找一个元素在哪个集合里，
    是一种类似树的结构，每个集合选定一个元素作为根节点，有几个集合就有几棵树
    find用于找根节点，用于确定两个元素是否是同一个集合
    union将两个集合合并
     */

    /*
    给定一个0|1的二维矩阵，为1表示i和j是朋友关系，0表示非朋友关系，如果i和j是朋友，j和k是朋友，则ijk都是朋友，称为一个朋友圈，共有几个朋友圈
    1、可以用泛洪查找4连通区域的个数
    2、并查集
     */

    public int findCircleNum(int[][] M) {
        int len=M.length;
        int[] father=new int[len];
        for(int i=0;i<len;i++)
            father[i]=i;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                if(M[i][j]==1){
                    //是朋友就要合并
                    union(father,i,j);
                }
            }
        }
        int ans=0;
        for(int i=0;i<len;i++){
            if(i==find(father,i)) ans++;
        }
        return ans;
    }

    //找到i的根节点
    private int find(int[] father,int i){
        while(father[i]!=i) {
            father[i]=father[father[i]];//这一行是路径压缩，设当前i为儿子，直接将儿子指向爷节点，即少了一层
            i=father[i];
        }
        return i;
    }

    //将i和j所在的集合合并，即将i的根节点和j的根节点合并，直接将一方设为另一方的父节点即可
    private void union(int[] father,int a,int b){
        int fa=find(father,a);
        int fb=find(father,b);
        if(fa!=fb){
            father[fa]=fb;//把a的根的父节点设为b的根
        }
    }
}
