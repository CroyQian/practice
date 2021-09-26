package math;

/**
 * @author Croy Qian
 * @createDate 2021/9/26
 * @Description 两整数之和
 * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。
 * 提示1： 在位运算操作中，异或 ^ 的一个重要特性是无进位加法
 * 提示2： 在位运算操作中，可以使用与 & 运算来获得进位
 */
public class SumOfTwoIntegers {
    public int getSum1(int a, int b) {
        int ans = 0;
        for (int i = 0, t = 0; i < 32; i++) {
            //从a和b的最低位开始计算
            int u1 = (a >> i) & 1, u2 = (b >> i) & 1;
            if (u1 == 1 && u2 == 1) {
                ans = ans ^ (t << i);
                t = 1;
            } else if (u1 == 1 || u2 == 1) {
                ans = ans ^ ((1 ^ t) << i);
            } else {
                ans = ans ^ (t << i);
                t = 0;
            }
        }
        return ans;
    }

    /**
     * a ^ b 不算进位 a和b的和
     * （a & b）<< 1 只算进位 a和b的进位和
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum2(int a, int b) {
        return b == 0 ? a : getSum2(a ^ b, (a & b) << 1);
    }
}
