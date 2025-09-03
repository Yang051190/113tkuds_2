// 題目：Longest Common Prefix
// 找出字串陣列中最長的共同前綴字串，若不存在回傳空字串 ""。

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }
}

/*
解題思路：
1. 假設第一個字串是共同前綴 prefix。
2. 依序與後面的字串比對，若不符合則縮短 prefix。
3. 重複直到比對完成或 prefix 變成空字串。
4. 時間複雜度 O(n * m)，n 是字串數量，m 是最長字串長度。
5. 空間複雜度 O(1)。
*/
