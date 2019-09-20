package algorithm.graph;

import java.util.Arrays;

public class MinimumSpanningTree {
    private static int INF = 100000;
    /*
    最小生成树，给定一个带权无向图，求出最小连通子图，包含所有n个点，n-1条边，形成最小生成树
     */

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 10, INF, INF, INF, 11, INF},
                {10, 0, 18, INF, INF, INF, 16},
                {INF, 18, 0, 22, INF, INF, INF},
                {INF, INF, 22, 0, 20, INF, INF},
                {INF, INF, INF, 20, 0, 26, INF},
                {11, INF, INF, INF, 26, 0, 17},
                {INF, 16, INF, INF, INF, 17, 0}
        };
        System.out.println(Arrays.toString(prim(graph, graph.length)));
    }

    private static void kruskal() {

    }


    /*
    从已有的最小生成树中找出与其邻接的权值最小的节点，并加入到最小生成树中
    从0开始最为生成树的根节点
    步骤：
    1、在树外的结点中选出和树最近的结点，加入到生成树中
    2、更新树外结点到生成树的距离，并更新树外节点的父节点为最近结点
     */
    private static int[] prim(int[][] graph, int len) {
        int res = 0;//生成树的权值
        int[] dis = new int[len];//点i到当前生成树的最短边长度，0用于区别生成树内外节点
        int[] mst = new int[len];//mst[j]=k,表示j的父节点为k，k为-1表示没有父节点
        //初始化dis,一开始表示0(已加入生成树)这个点到其他节点的最小距离
        for (int i = 1; i < len; i++) {
            dis[i] = graph[0][i];
        }
        mst[0] = -1;
        //0已经加入，执行len-1次就行
        for (int times = 1; times < len; times++) {
            //找到距离生成树最短的结点
            int target = -1, weight = INF;
            for (int i = 1; i < len; i++) {
                if (dis[i] != 0 && dis[i] < weight) {
                    weight = dis[i];
                    target = i;
                }
            }
            //置0表示加入生成树
            dis[target] = 0;
            res += weight;
            //更新生成树外的结点到生成树的最短距离,并且更新最小生成树
            for (int i = 1; i < len; i++) {
                //target到树外结点i的结点距离比上一次生成树到i的距离更短
                if (graph[target][i] < dis[i]) {
                    dis[i] = graph[target][i];
                    mst[i] = target;
                }
            }
        }
        return mst;
    }
}
