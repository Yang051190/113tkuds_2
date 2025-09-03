// 題目：Zigzag Conversion
// 將字串 s 依照指定的行數 numRows，以 Z 字形排列，然後逐行讀取，輸出新的字串。

class Solution {
    public String convert(String s, int numRows) {
        // 特殊情況：如果只有一行或字串長度小於行數，直接回傳
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // 使用 StringBuilder 陣列模擬每一行
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currRow = 0;
        boolean goingDown = false;

        // 模擬字元放置
        for (char c : s.toCharArray()) {
            rows[currRow].append(c);
            // 遇到頂端或底端就轉向
            if (currRow == 0 || currRow == numRows - 1) {
                goingDown = !goingDown;
            }
            currRow += goingDown ? 1 : -1;
        }

        // 將所有行合併
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}

/*
解題思路：
1. Z 字形排列規則：
   - 往下逐行放置字元，到達最底行時轉向往上。
   - 到達最頂行時再轉向往下。
2. 用 numRows 個 StringBuilder 模擬每一行。
3. 使用 currRow 紀錄當前行數，goingDown 紀錄方向。
4. 遍歷字串，每個字元放到對應的行，遇到頂端或底端時反轉方向。
5. 最後把所有行拼接起來就是答案。
6. 時間複雜度 O(n)，n 為字串長度；空間複雜度 O(n)。
*/
