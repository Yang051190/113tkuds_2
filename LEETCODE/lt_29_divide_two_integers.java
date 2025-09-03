// 題目：Divide Two Integers
// 給定兩個整數 dividend 和 divisor，實現除法，不使用 *, /, %。
// 若結果超出 int 範圍，回傳 Integer.MAX_VALUE。

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int res = 0;

        while (a >= b) {
            long tmp = b, multiple = 1;
            while (a >= (tmp << 1)) {
                tmp <<= 1;
                multiple <<= 1;
            }
            a -= tmp;
            res += multiple;
        }

        return ((dividend > 0) ^ (divisor > 0)) ? -res : res;
    }
}

/*
解題思路：
1. 特殊情況：被除數 = MIN_VALUE，除數 = -1，會溢位，回傳 MAX_VALUE。
2. 將 dividend、divisor 轉為 long，避免溢位。
3. 使用位移法：每次找出 divisor 的最大倍數，減去 dividend。
4. 累加倍數 multiple，直到 dividend < divisor。
5. 根據正負號決定答案。
6. 時間複雜度 O(log n)，空間複雜度 O(1)。
*/
