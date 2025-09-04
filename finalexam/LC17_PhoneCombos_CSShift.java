// 題目 8：手機門號組合
// 檔名：LC17_PhoneCombos_CSShift.java
// 說明：輸入一個僅含 '2'-'9' 的數字字串，輸出所有可能的字母組合。
// 若字串為空，輸出 0 行。

import java.util.*;

public class LC17_PhoneCombos_CSShift {

    private static final String[] KEYS = {
        "", // 0
        "", // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs", // 7
        "tuv", // 8
        "wxyz" // 9
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine().trim();

        List<String> results = letterCombinations(digits);
        for (String s : results) {
            System.out.println(s);
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        backtrack(res, new StringBuilder(), digits, 0);
        return res;
    }

    private static void backtrack(List<String> res, StringBuilder path, String digits, int idx) {
        if (idx == digits.length()) {
            res.add(path.toString());
            return;
        }

        String letters = KEYS[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(res, path, digits, idx + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}

/*
解題思路：
1. 數字 2–9 對應電話按鍵字母表。
2. 使用回溯法：
   - 每層處理一個數字，展開所有可能字母。
   - 直到展開完整個字串，存入結果。
3. 邊界：空字串 → 輸出空集合（0 行）。
4. 時間複雜度 O(乘積)，空間 O(深度)。
 */
