// 題目：Letter Combinations of a Phone Number
// 給定數字字串 digits，回傳所有可能的字母組合。

import java.util.*;

class Solution {
    private static final String[] mapping = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        backtrack(res, new StringBuilder(), digits, 0);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder path, String digits, int index) {
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }

        String letters = mapping[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(res, path, digits, index + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}

/*
解題思路：
1. 數字到字母的映射：2->"abc", 3->"def"...。
2. 使用回溯法 (backtracking) 枚舉所有可能的組合。
3. 每次遞迴選擇一個字母，直到 digits 用完，將結果加入答案。
4. 時間複雜度 O(3^n ~ 4^n)，n 是 digits 長度。
5. 空間複雜度 O(n)。
*/
