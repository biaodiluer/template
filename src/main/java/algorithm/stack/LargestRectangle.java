package algorithm.stack;

import java.util.Stack;

public class LargestRectangle {

    /*
    单调栈：寻找某个元素左边第一个比它小的数
    有一个直方图，求里面的最大覆盖矩形
     */

    public static void main(String[] args) {
        int[] histo = new int[]{2,1,5,6,2,3};
        System.out.println(largestRectangleArea2(histo));
        //template(histo);
    }

    /*
    模板：输出左边第一个比自己小的数
     */
    static void template(int[] arr){
        Stack<Integer> stack=new Stack();
        for(int i=0;i< arr.length;i++){
            while(!stack.empty() && arr[stack.peek()] > arr[i]) stack.pop();
            System.out.println(stack.empty()?-1:arr[stack.peek()]);
            stack.push(i);
        }
    }


    /*
    思路1：以每个柱子为中心向两边扩散直到遇到比中心短的就停止，保证中心是最短的 O(N^2)
    可以提前扫描一遍把左右两边第一个比自己小的下标保存起来，是O(n)
     */
    private static int largestRectangleArea1(int[] heights) {
        int ans=0,len=heights.length;
        int[] left=new int[len];
        int[] right=new int[len];
        //先保存左边的最矮的下标
        Stack<Integer> stack=new Stack();
        for(int i=0;i<len;i++){
            int height=heights[i];
            //把栈里面大于height的数删掉
            while(!stack.empty() && heights[stack.peek()]>=height) stack.pop();
            left[i]=stack.empty()?-1:stack.peek();
            stack.push(i);
        }
        //栈要复用，把栈清空
        stack.removeAllElements();
        for(int i=len-1;i>=0;i--){
            int height=heights[i];
            while(!stack.empty() && heights[stack.peek()]>=height) stack.pop();
            right[i]=stack.empty()?len:stack.peek();
            stack.push(i);
        }
        for(int i=0;i<len;i++)
            ans=Math.max(ans,heights[i]*(right[i]-left[i]-1));
        return ans;
    }



    /*
     思路2：维护一个单调递增栈，栈中下面的元素就是第一个比当前高度矮的下标
     从左往右遍历，比栈顶大的直接往里加，比栈顶小的，要逐个弹出并计算：以“弹出的高度”为高，以i到左边最近最矮距离为宽，的矩形面积
     */
    private static int largestRectangleArea2(int[] heights) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= heights.length; i++) {
            //需要考虑将最后一个元素变成最矮的柱子，好结算之前的
            int curHeight = (i == heights.length) ? 0 : heights[i];
            //栈里面存在比cur大的元素，要一个个拿出来计算
            while (!stack.empty() && heights[stack.peek()] > curHeight) {
                //以栈顶柱体为高
                int preHeight = heights[stack.pop()];
                //以当前位置i，到弹出柱体的前一个最近最矮柱体距离为宽
                int width=stack.empty()?i:i-stack.peek()-1;
                //更新面积
                res = Math.max(res, preHeight * width);
            }
            //将当前的下标push进栈
            stack.push(i);
        }
        return res;
    }
}
