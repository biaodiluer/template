package algorithm.recursion;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {
    /*
    给定一个grid，求连通区域个数（8邻域）
     */

    static int helper(int[][] grid, int rows, int cols) {
        int ans = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    ans++;
                    //dfs(grid, rows, cols, r, c);
                    bfs(grid, rows, cols, r, c);
                }
            }
        }
        return ans;
    }

    private static void dfs(int[][] grid, int rows, int cols, int r, int c) {
        grid[r][c] = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int x = r + i, y = c + j;
                if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) {
                    dfs(grid, rows, cols, x, y);
                }
            }
        }
    }

    //这种深度遍历的写法会更容易栈溢出，尽量在调用之前就剪枝，而不是调用后在里面判断
    private static void dfsSlow(int[][] grid, int rows, int cols, int r, int c) {
        if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1) {
            grid[r][c] = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int x = r + i, y = c + j;
                    dfsSlow(grid, rows, cols, x, y);
                }
            }
        }
    }

    private static void bfs(int[][] grid, int rows, int cols, int r, int c) {
        Queue<Point> q = new LinkedList();
        q.offer(new Point(r, c));
        grid[r][c] = 0;
        while (!q.isEmpty()) {
            Point temp = q.poll();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int x = temp.r + i, y = temp.c + j;
                    if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) {
                        q.offer(new Point(x, y));
                        grid[x][y] = 0;
                    }
                }
            }
        }
    }

    static class Point {
        int r;
        int c;

        Point(int i, int j) {
            r = i;
            c = j;
        }
    }
}

