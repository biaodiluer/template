package algorithm.prefixanddifference;

public class MaxGridSum {

    /*
    1、前缀和
     一维：arr[0],arr[1],arr[2],,,,,,arr[n]
     用一个数组保存arr前i个数字和（不一定是和），prefix[i]=arr[0]+arr[1]+...+arr[i]
     好处是可以在O(1)时间内求出某一段的sum
     二维：可以求某一个矩形的面积，S=prefix(右下)-prefix(右上)-prefix(左下)+prefix(左上)
     */

    /*
    grid是一个二维矩阵
    让边长为a,b的矩形去截取grid，求能够截到权值和最大的值
     */
    static int maxSum(int[][] grid, int a, int b) {
        int row = grid.length, col = grid[0].length;
        //构造新的grid，让第一行和第一列空出来，便于处理
        int[][] newGrid = new int[row + 1][col + 1];
        for (int r = 1; r <= row; r++) {
            for (int c = 1; c <= col; c++) {
                newGrid[r][c] = grid[r - 1][c - 1];
            }
        }
        //构造newGrid的前缀和
        for (int r = 1; r <= row; r++) {
            for (int c = 1; c <= col; c++) {
                //右下角的前缀和=右下角的权值+右上角的前缀和+左下角的前缀和-左上角的前缀和
                newGrid[r][c] = newGrid[r][c] + newGrid[r - 1][c] + newGrid[r][c - 1] - newGrid[r - 1][c - 1];
            }
        }
        int ans = 0;
        //求所有矩形的sum
        for (int r = a + 1; r <= row; r++) {
            for (int c = b + 1; c <= col; c++) {
                // sum=右下角的前缀和-右上角的前缀和-左下角的前缀和+左上角的前缀和
                //算上边界的是s[j]-s[i-1]，不算边界的是s[j]-s[i]
                int sum = grid[r][c] - grid[r - a - 1][c] - grid[r][c - b - 1] + grid[r - a - 1][c - b - 1];
                ans = Math.max(ans, sum);
            }
        }
        return ans;
    }
}
