// 題目：Generate Parentheses
// 給定整數 n，生成所有可能且有效的括號組合。

import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder curr, int open, int close, int n) {
        if (curr.length() == 2 * n) {
            res.add(curr.toString());
            return;
        }

        if (open < n) {
            curr.append('(');
            backtrack(res, curr, open + 1, close, n);
            curr.deleteCharAt(curr.length() - 1);
        }
        if (close < open) {
            curr.append(')');
            backtrack(res, curr, open, close + 1, n);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}

/*
解題思路：
1. 使用回溯法生成所有可能字串。
2. 條件：
   - 左括號數量 < n 時可以加 '('。
   - 右括號數量 < 左括號數量時可以加 ')'。
3. 當長度 = 2*n，加入答案。
4. 時間複雜度 O(4^n / √n)，空間複雜度 O(n)。
*/
