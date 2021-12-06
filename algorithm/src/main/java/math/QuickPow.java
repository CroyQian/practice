package math;

/**
 * @author Croy Qian
 * @createDate 2021/12/6
 * @Description 快速幂
 */
public class QuickPow {
    public static int intPow(int x, int n) {
        int res = 1;
        while(n != 0) {
            if (n % 2 == 1) {
                res *= x;
            }
            x *= x;
            n /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(intPow(2,10));
    }
}
