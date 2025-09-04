// 題目 5：北捷進出站最長有效片段
// 檔名：LC32_LongestValidParen_Metro.java
// 說明：輸入一個只含 '(' 與 ')' 的字串，找出最長的合法括號子字串長度。

import java.util.*;

public class LC32_LongestValidParen_Metro {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();

        int result = longestValidParentheses(s);
        System.out.println(result);
    }

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 基準點
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // 設定新的基準點
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
1. 使用索引堆疊，棧底放 -1 代表基準。
2. 遍歷字串：
   - 遇到 '(' → push 當前索引。
   - 遇到 ')' → pop 棧頂：
     - 若棧空 → push 當前索引作為新基準。
     - 若棧不空 → 更新 maxLen = i - stack.peek()。
3. 最終 maxLen 即為最長合法括號片段長度。
4. 時間複雜度 O(n)，空間複雜度 O(n)。
 */
