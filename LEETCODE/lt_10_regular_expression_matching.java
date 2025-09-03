// 題目：Regular Expression Matching
// 給定一個字串 s 與一個模式 p，實現支援 '.' 與 '*' 的正則比對：
// '.' 可以匹配任意單一字元。
// '*' 可以匹配零個或多個前一個元素。

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j] 表示 s[0..i-1] 是否能與 p[0..j-1] 匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // 預處理：處理像 a*, a*b*, a*b*c* 這種可以匹配空字串的情況
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 動態規劃
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // '*' 代表零次出現
                    dp[i][j] = dp[i][j - 2];
                    // '*' 代表一次或多次出現
                    char prev = p.charAt(j - 2);
                    if (prev == '.' || prev == sc) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}

/*
解題思路：
1. 使用動態規劃 (DP)，dp[i][j] 表示 s 的前 i 個字元能否匹配 p 的前 j 個字元。
2. 如果當前字元相同，或 p 的字元是 '.'，則轉移為 dp[i-1][j-1]。
3. 如果 p 的字元是 '*'：
   - 它可以代表前一個字元出現 0 次 → dp[i][j-2]。
   - 或者前一個字元出現一次以上，且 s 當前字元與 p 的前一字元相符 → dp[i-1][j]。
4. 最終答案是 dp[m][n]。
5. 時間複雜度 O(m * n)，空間複雜度 O(m * n)。
*/
