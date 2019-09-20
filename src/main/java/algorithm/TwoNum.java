package algorithm;

public class TwoNum {
    //大数相加，相乘
    public static void main(String[] args) {
        String num1 = "456", num2 = "123";
        System.out.println(add(num1, num2));
        System.out.println(mul(num1, num2));
    }

    private static String mul(String num1, String num2) {
        int[] temp = new int[num1.length() + num2.length() + 1];
        int decimal = 10;//10进制
        //先翻转一下便于计算
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        //先不管进位，把所有的乘积按位置加好
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                temp[i + j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        //计算进位
        for (int i = 0; i < temp.length - 1; i++) {
            temp[i + 1] += temp[i] / decimal;
            temp[i] %= decimal;
        }
        StringBuilder res = new StringBuilder();
        int i = temp.length - 1;
        //从后面第一个不为0的数字开始拼接
        while (temp[i] == 0) i--;
        while (i >= 0) res.append(temp[i--]);
        return res.toString();
    }

    private static String add(String num1, String num2) {
        int decimal = 10;//10进制
        int maxLen = Math.max(num1.length(), num2.length()), carry = 0;
        //先翻转一下便于计算
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            int sum = carry;
            if (i < num1.length()) {
                sum += num1.charAt(i) - '0';
            }
            if (i < num2.length()) {
                sum += num2.charAt(i) - '0';
            }
            carry = sum / decimal;
            res.append(sum % decimal);
        }
        //最后有进位的需要加上
        if (carry > 0)
            res.append(carry);
        return res.reverse().toString();
    }
}
