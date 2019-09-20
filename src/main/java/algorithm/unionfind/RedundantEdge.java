package algorithm.unionfind;

public class RedundantEdge {
    public static void main(String[] args) {
        int[][] edges=new int[][]{{1,2},{2,3},{3,4},{1,4},{4,5},{2,4}};
        findRedundant(edges);
    }

    /*
    按照顺序找出最后一条边，去了之后依然是连通的
     */
    private static void findRedundant(int[][] edges) {
        int N=edges.length;
        int[] father=new int[N+1];
        for(int i=1;i<=N;i++)
            father[i]=i;
        for(int[] edge:edges){
            int a=edge[0],b=edge[1];
            int fa=find(father,a),fb=find(father,b);
            if(fa==fb){
                System.out.println(a+" "+b);
            }
            father[fa]=fb;
        }
    }

    private static int find(int[] father,int x){
        while(father[x]!=x){
            father[x]=father[father[x]];
            x=father[x];
        }
        return x;
    }

}
