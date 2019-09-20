package algorithm.stack;

import java.util.Stack;

public class TrapWater {

    /*
    给定一个二维直方图，凹下去的地方可以存水，求可以存多少水
     */

    public static void main(String[] args) {
        int[] arr=new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap1(arr));
    }

    /*
    单调递减栈，对于新来的柱体，如果栈里有比它矮的，计算矩形面积：
    宽为i到矮柱子的距离-1，高为矮柱子和上一个矮柱子的高度差，每有一个矮柱子就算一层面积
    最后再算一下当前柱子和上一个矮柱子之间能存多少水
     */
    static public int trap1(int[] heights) {
        int ans=0,len=heights.length;
        Stack<Integer> stack=new Stack();
        for(int i=0;i<len;i++){
            int curHeight=heights[i];
            int lastHeight=0;
            while(!stack.empty() && heights[stack.peek()]<=curHeight){
                int preIdx=stack.pop();
                //宽(i-preIdx-1)*高(preIndex的高-上一次的高)
                ans+=(i-preIdx-1)*(heights[preIdx]-lastHeight);
                lastHeight=heights[preIdx];
            }
            //还要计算最后和高柱子之间的积水
            if(!stack.empty()){
                ans+=(i-stack.peek()-1)*(curHeight-lastHeight);
            }
            stack.push(i);
        }
        return ans;
    }

    /*
    双指针往中间走
     */
    static public int trap2(int[] heights){
        int left=0,right=heights.length-1,ans=0;

        return ans;
    }
}
