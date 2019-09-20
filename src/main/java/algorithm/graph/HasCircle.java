package algorithm.graph;

import java.util.*;

public class HasCircle {

    public static void main(String[] args) {
        int[][] rel=new int[][]{{0,1},{1,2},{1,3}};
        int n=4;
        HasCircle hasCircle=new HasCircle();
        System.out.println(hasCircle.hasCircleInGraph1(n,rel));
        System.out.println(hasCircle.hasCircleInGraph2(n,rel));
        System.out.println(hasCircle.hasCircleInUndirectedGraph3(n,rel));
    }

    /*
    给定有向图的若干对关系，求是否存在环
    深度搜索，如果某条路径上出现了已经访问过的节点，则有环
     */
    boolean hasCircleInGraph1(int n, int[][] rel){
        List[] graph=new ArrayList[n];//比邻接矩阵更省空间和时间
        boolean[] used=new boolean[n];
        for(int i=0;i<n;i++) graph[i]=new ArrayList();
        for(int[] arr:rel) graph[arr[0]].add(arr[1]);
        for(int i=0;i<n;i++) {
            if (dfs(graph, used, i))
                return true;
        }
        return false;
    }
    private boolean dfs(List[] graph, boolean[] used, int cur) {
        if(used[cur]){
            return true;
        }else{
            used[cur]=true;
            List<Integer> tmp=graph[cur];
            for(int i=0;i<tmp.size();i++){
                if(dfs(graph,used,tmp.get(i))) return true;
            }
            used[cur]=false;
            return false;
        }
    }

    /*
    给定有向图的若干对关系，求是否存在环
    宽度搜索，每次模拟删除入度为0的节点A，则被A所指向的节点入度需要-1，如果没有环，则所有节点都会被模拟删除
    也可以判断最后是否存在入度为非0的节点
     */
    boolean hasCircleInGraph2(int n, int[][] rel) {
        List[] graph=new ArrayList[n];//比邻接矩阵更省空间和时间
        int[] inDegree=new int[n];
        Queue<Integer> q=new LinkedList();
        for(int i=0;i<n;i++) graph[i]=new ArrayList();
        for(int[] arr:rel) {
            graph[arr[0]].add(arr[1]);
            inDegree[arr[1]]++;
        }
        for(int i=0;i<n;i++){
            if(inDegree[i]==0)
                q.offer(i);
        }
        int cnt=0;
        while(!q.isEmpty()){
            cnt++;
            int cur=q.poll();
            List<Integer> tmp=graph[cur];
            for (Integer t : tmp) {
                inDegree[t]--;
                if (inDegree[t] == 0)
                    q.offer(t);
            }
        }
        return cnt!=n;
    }

    /*
    给定无向图的若干对关系，求是否存在环
    宽度搜索，每次模拟删除度为1的节点A，则A和被A所指向节点的度需要-1，如果没有环，则所有节点都会被模拟删除
    也可以判断最后是否存在入度为非0的节点
     */
    boolean hasCircleInUndirectedGraph3(int n, int[][] rel) {
        List[] graph=new ArrayList[n];//比邻接矩阵更省空间和时间
        int[] degree=new int[n];
        Queue<Integer> q=new LinkedList();
        for(int i=0;i<n;i++) graph[i]=new ArrayList();
        for(int[] arr:rel) {
            graph[arr[0]].add(arr[1]);
            graph[arr[1]].add(arr[0]);
            degree[arr[0]]++;
            degree[arr[1]]++;
        }
        for(int i=0;i<n;i++){
            if(degree[i] == 1)
                q.offer(i);
        }
        System.out.println(Arrays.toString(degree));
        int cnt=0;
        while(!q.isEmpty()){
            cnt++;
            int cur=q.poll();
            List<Integer> tmp=graph[cur];
            for (Integer t : tmp) {
                degree[cur]--;
                degree[t]--;
                if (degree[t] == 1)
                    q.offer(t);
            }
        }
        return cnt!=n;
    }

}
