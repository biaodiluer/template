package algorithm;

public class QuickPower {

    /*
     快速幂的时间复杂度是O(log power)
     本来是power个base相乘，On的复杂度，可以把power看成二进制，比如power为11,二进制为1011,等价于求base^(1+2+8)=base^1*base^2*base^8
     base^i这个整体每次访问某一位是就是之前的平方
     */

    public static void main(String[] args) {
        double base = 2.0;
        int power = 10;//Integer.MIN_VALUE+1;
        int mod = 8;
        System.out.println((int) myPower1(base, power, mod));
    }

    //power是非负数
    static double myPower1(double base, int power, int mod) {
        base %= mod;
        double ans = 1.0;
        while (power > 0) {
            if ((power & 1) == 1) ans = ans * base % mod;
            base = base * base % mod;
            power >>= 1;
        }
        ans %= mod;
        return ans;
    }

    //power可以为负数,power为Integer.MIN_VALUE时，取相反数会越界，java就处理成还是本身了
    static double myPower2(double base, int power, int mod) {
        base %= mod;
        double ans = 1.0;
        if (power < 0) {
            power = -power;
            base = 1 / base;//因为power时负数，所以base是倒数
        }
        //注意这里是!=0
        while (power != 0) {
            if ((power & 1) == 1) ans = ans * base % mod;
            base = base * base % mod;
            // >>带符号右移  >>>不带符号右移（不管正负全补0）
            //对于正数，>>和>>>都是一样的，左边补0
            //对于负数，>>左边补1，>>>左边补0
            power >>>= 1;
        }
        ans %= mod;
        return ans;
    }
}
