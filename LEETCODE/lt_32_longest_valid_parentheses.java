// 題目：Longest Valid Parentheses
// 給定字串 s，回傳最長的有效括號子字串長度。

import java.util.*;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}

/*
解題思路：
1. 使用 stack 儲存索引。
2. 遇到 '(' → push。
3. 遇到 ')' → pop，如果空了就 push 當前索引，否則計算長度。
4. maxLen 記錄最大有效括號長度。
*/
