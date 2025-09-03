// 題目：Palindrome Number
// 判斷一個整數是否為迴文數（正反讀都相同）。

class Solution {
    public boolean isPalindrome(int x) {
        // 負數不可能是回文
        if (x < 0) return false;

        int original = x;
        long rev = 0;

        while (x != 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        return rev == original;
    }
}

/*
解題思路：
1. 負數一定不是回文，直接回傳 false。
2. 將數字反轉後，與原始數字比較是否相等。
3. 需要用 long 暫存，避免反轉過程中溢位。
4. 時間複雜度 O(log n)，空間複雜度 O(1)。
*/
