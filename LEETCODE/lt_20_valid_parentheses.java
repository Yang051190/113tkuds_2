// 題目：Valid Parentheses
// 給定字串 s，判斷其中的括號是否有效。有效定義：
// 1. 必須由相同類型的括號閉合。
// 2. 括號必須正確嵌套。

import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else {
                if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }
        return stack.isEmpty();
    }
}

/*
解題思路：
1. 使用堆疊記錄期待的右括號。
2. 每遇到一個左括號，將對應的右括號推入 stack。
3. 每遇到一個右括號，檢查是否與 stack 頂端一致，否則不合法。
4. 最後若 stack 為空，表示所有括號都正確匹配。
5. 時間複雜度 O(n)，空間複雜度 O(n)。
*/
