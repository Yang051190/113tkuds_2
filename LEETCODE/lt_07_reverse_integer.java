// 題目：Reverse Integer
// 給定一個 32 位整數 x，將它的數字反轉。如果反轉後超出 32 位有號整數範圍，則回傳 0。

class Solution {
    public int reverse(int x) {
        long rev = 0; // 使用 long 暫存，避免溢位
        while (x != 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
            // 檢查是否超出 int 範圍
            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) rev;
    }
}

/*
解題思路：
1. 取出數字的最後一位，逐步建立反轉後的數字。
2. 用 long 暫存，因為反轉過程可能會超過 int 範圍。
3. 每次檢查是否超過 [−2^31, 2^31 − 1]，若超過則回傳 0。
4. 時間複雜度 O(log|x|)，空間複雜度 O(1)。
*/
