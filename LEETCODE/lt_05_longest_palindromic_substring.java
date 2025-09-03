// 題目：Longest Palindromic Substring
// 給定一個字串 s，請找出其中最長的回文子字串。

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // 以 i 為中心（單字元回文）
            int len1 = expandAroundCenter(s, i, i);
            // 以 i 和 i+1 為中心（偶數回文）
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);
            if (len > end - start) {
                // 更新最長回文子字串範圍
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // 輔助函式：從中心往外擴展，回傳回文長度
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}

/*
解題思路：
1. 回文字串有兩種形式：
   - 奇數長度（例如 "aba"）：中心是一個字元。
   - 偶數長度（例如 "abba"）：中心是兩個字元。
2. 我們可以枚舉每個位置 i，將它當作回文中心，分別處理：
   - 以 s[i] 為中心。
   - 以 s[i] 和 s[i+1] 為中心。
3. 從中心向兩側擴展，直到不再是回文，計算回文長度。
4. 不斷更新最長回文的起點與終點。
5. 時間複雜度 O(n^2)，空間複雜度 O(1)。
*/
