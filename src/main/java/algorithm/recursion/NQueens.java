package algorithm.recursion;

public class NQueens {
    public static void main(String[] args) {
        NQueens main=new NQueens();
        //main.queen1(4);
        main.queenyxc(4);
    }

    /*
    给定棋盘边长，打印出所有皇后不相互攻击的阵型
     */
    private void queen1(int n) {
        boolean[][] grid=new boolean[n][n];
        dfs1(grid,0,n);
    }

    private void dfs1(boolean[][] grid, int col, int n){
        if(col>=n){
            printGrid(grid);
        }else{
            for(int row=0;row<n;row++){
                if(legal1(grid,row,col)){
                    grid[row][col]=true;
                    dfs1(grid,col+1,n);
                    grid[row][col]=false;
                }
            }
        }
    }

    //判断当前位置xy是否能放皇后
    private boolean legal1(boolean[][] grid, int x, int y){
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<y;c++){
                if(grid[r][c]){
                    //如果当前位置xy所在行有皇后，或者两个对角线方向上有皇后，则不能放
                    if(x==r || x-r==y-c || x+y==r+c) return false;
                }
            }
        }
        return true;
    }

    //======================================= 分割线 =================================================

    /*
    yxc版本，可以把每一行每一列每一个对角线是否有皇后保存下来，效率快很多
     */
    private void queenyxc(int n) {
        boolean[][] grid=new boolean[n][n];
        boolean[] inRows=new boolean[n];//该行中是否有皇后
        boolean[] inCols=new boolean[n];//该列中是否有皇后
        boolean[] lb_rt=new boolean[2*n];//左下到右上是否有皇后
        boolean[] lt_rb=new boolean[2*n];//左上到右下是否有皇后
        dfs2(grid,inRows,inCols,lb_rt,lt_rb,0);
    }

    private void dfs2(boolean[][] grid, boolean[] inRows, boolean[] inCols, boolean[] lb_rt, boolean[] lt_rb, int col) {
        if(col>=grid.length){
            printGrid(grid);
        }else{
            int n=grid.length;
            for(int row=0;row<n;row++){
                if(inRows[row] || inCols[col] || lb_rt[row+col] || lt_rb[n-1+row-col]) continue;
                inRows[row]=true;
                inCols[col]=true;
                lb_rt[row+col]=true;
                lt_rb[n-1+row-col]=true;
                grid[row][col]=true;
                dfs2(grid,inRows,inCols,lb_rt,lt_rb,col+1);
                inRows[row]=false;
                inCols[col]=false;
                lb_rt[row+col]=false;
                lt_rb[n-1+row-col]=false;
                grid[row][col]=false;
            }
        }
    }

    private void printGrid(boolean[][] grid){
        for(boolean[] line:grid){
            String s="";
            for(boolean p:line){
                s+=p?"Q":".";
            }
            System.out.println(s);
        }
        System.out.println("===========================");
    }
}
