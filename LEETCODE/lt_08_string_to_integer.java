// 題目：String to Integer (atoi)
// 將字串轉換為整數，規則類似 C/C++ 的 atoi 函數。

class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0, n = s.length();
        // 1. 跳過前導空白
        while (i < n && s.charAt(i) == ' ') i++;

        // 2. 判斷正負號
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. 轉換數字
        long result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            if (sign == 1 && result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && -result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            i++;
        }

        return (int) (sign * result);
    }
}

/*
解題思路：
1. 跳過字串開頭的空格。
2. 判斷是否有正負號。
3. 逐位轉換數字，並在過程中檢查是否溢位。
4. 若超過範圍，回傳邊界值 (Integer.MAX_VALUE 或 Integer.MIN_VALUE)。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
