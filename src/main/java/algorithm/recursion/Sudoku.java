package algorithm.recursion;

public class Sudoku {

    /*
    给定9*9的有空白的格子，填充1-9使得
    1、每一行出现1-9各一次
    2、每一列出现1-9各一次
    3、所在小格子1-9各一次
     */

    public void solveSudoku(char[][] grid) {
        dfs(grid,0);
    }

    //表示当前状态是否可以变成合法的数独，用于在获得合法填充方式时停止递归
    private boolean dfs(char[][] grid, int pos){
        if(pos==81) return true;
        int row=pos/9,col=pos%9;
        if(grid[row][col]!='.') {
            return dfs(grid,pos+1);
        }else{
            for(int k=1;k<=9;k++){
                //枚举9个数字，需要满足一定的条件
                char c=(char)(k+'0');
                if(legal(grid,row,col,c)){
                    grid[row][col]=c;
                    if(dfs(grid,pos+1)) return true;
                    grid[row][col]='.';
                }
            }
            return false;
        }
    }

    //判断在xy位置上填c是否合法
    private boolean legal(char[][] grid, int x, int y, char c){
        if(grid[x][y]==c) return false;
        //需要判断所在列所在行和所在小格子是否有相同的数字
        for(int i=0;i<9;i++){
            if(grid[x][i]==c) return false;
            if(grid[i][y]==c) return false;
            //所在小格子的左上角顶点
            int sx=x/3*3+i%3;
            int sy=y/3*3+i%3;
            if(grid[sx][sy]==c) return false;
        }
        return true;
    }

}
