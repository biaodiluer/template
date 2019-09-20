package algorithm.graph;

import java.util.Arrays;

public class ShortestPath {
    private static int INF = 1000000;
    /*
    在有向图（无向图是特殊的有向图）中的最短路径算法
    1、dijkstra，单个点到任一点的最短路径，权值不能有负数
    2、（推荐）spfa，单个点到任一点的最短路径，权值可以是负数
    3、floyd，任意点到任一点的最短路径

    有向图的存储方式
    1、邻接矩阵二维数组，g[i][j]表示i->j的权值
    2、邻接表只记录存在的边，形式可以是map，list，c++里面是vector
     */

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 20, INF, INF, 100},
                {20, 0, 30, INF, INF},
                {INF, 30, 0, 20, INF},
                {INF, INF, 20, 0, 20},
                {100, INF, INF, 20, 0},};
        int[] res = dijkstra(graph, graph.length, 0);
        //floyd(graph);
        System.out.println(Arrays.toString(res));
    }

    /*
    dijkstra不适用于有负边的情况，会重复走负边达到最小值
    graph是输入的权值,INF代表不直接连通，start一般是0
     */
    private static int[] dijkstra(int[][] graph, int len, int start) {
        int[] dis = new int[len];//从start到各个点的最短距离
        boolean[] finished = new boolean[len];//从start到各个点的最短是否完全求出
        for (int i = 0; i < len; i++) dis[i] = graph[start][i];//先全部从图中搬过来
        dis[start] = 0;//自己到自己是0
        finished[start] = true;//自己到自己也是定下来的
        //一共执行times次
        for (int times = 1; times < len; times++) {
            //先找出没有定下来中和start最近的
            int target = -1, weight = INF;
            for (int i = 0; i < len; i++) {
                if (!finished[i] && dis[i] < weight) {
                    weight = dis[i];
                    target = i;
                }
            }
            //target这点定下来了
            finished[target] = true;
            //需要更新start->target->i
            for (int i = 0; i < len; i++) {
                dis[i] = Math.min(dis[i], weight + graph[target][i]);
            }
        }
        return dis;
    }

    /*
    spfa适用于有负边的情况

     */
    private static int spfa(int[][] graph) {
        return 0;
    }

    /*
    3层循环，第一层是中间节点，第二第三层是头尾节点
    也不适用于有负边的情况
     */
    private static void floyd(int[][] graph) {
        for (int center = 0; center < graph.length; center++) {
            for (int start = 0; start < graph.length; start++) {
                for (int end = 0; end < graph.length; end++) {
                    graph[start][end] = Math.min(graph[start][end],
                            graph[start][center] + graph[center][end]);
                }
            }
        }
        for (int i = 0; i < graph.length; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }
    }
}
