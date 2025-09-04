// 題目 4：緊急通報格式括號檢查
// 檔名：LC20_ValidParentheses_AlertFormat.java
// 說明：給定一個只含 ()[]{} 的字串，檢查是否為合法的括號格式。
// 合法條件：成對、順序正確、無交錯錯誤。

import java.util.*;

public class LC20_ValidParentheses_AlertFormat {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();

        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                // 碰到閉括號，檢查棧頂是否匹配
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            } else {
                // 開括號，推入堆疊
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}

/*
解題思路：
1. 使用 Stack 模擬括號檢查。
2. Map 建立閉括號對應的開括號：
   - ')' -> '('
   - ']' -> '['
   - '}' -> '{'
3. 遍歷字串：
   - 若遇到開括號，推入 stack。
   - 若遇到閉括號，檢查棧頂是否對應，否則 false。
4. 遍歷完成後 stack 必須為空，才算合法。
5. 時間複雜度 O(n)，空間複雜度 O(n)。
 */
