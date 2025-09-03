// 題目：Substring with Concatenation of All Words
// 給定字串 s 與字串陣列 words，找出所有子字串的起始索引，
// 這些子字串正好是 words 中所有字串的串聯（不分順序）。

import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;

        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;

        Map<String, Integer> wordCount = new HashMap<>();
        for (String w : words) {
            wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);
        }

        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i, count = 0;
            Map<String, Integer> seen = new HashMap<>();

            while (right + wordLen <= s.length()) {
                String w = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordCount.containsKey(w)) {
                    seen.put(w, seen.getOrDefault(w, 0) + 1);
                    count++;

                    while (seen.get(w) > wordCount.get(w)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    if (count == words.length) {
                        res.add(left);
                    }
                } else {
                    seen.clear();
                    count = 0;
                    left = right;
                }
            }
        }
        return res;
    }
}

/*
解題思路：
1. 每個 word 長度固定，總字串長度 = wordLen * words.length。
2. 使用滑動視窗 + HashMap，檢查每個長度為 totalLen 的子字串。
3. 每次檢查子字串是否能拆分成 words 中的所有字詞，且出現次數正確。
4. 為避免重複檢查，對不同偏移 i (0 ~ wordLen-1) 分別處理。
5. 時間複雜度 O(n * wordLen)，空間複雜度 O(k)，k 為 words 數量。
*/
