// 題目：Longest Substring Without Repeating Characters
// 給定一個字串 s，請找出其中不含有重複字元的最長子字串長度。

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>(); // 用來記錄目前子字串中的字元
        int left = 0;  // 左指針
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            // 若發現重複字元，則移動左指針直到該字元被移除
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            // 加入當前字元
            set.add(s.charAt(right));
            // 更新最長長度
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

/*
解題思路：
1. 使用「滑動視窗」技巧（Two Pointers + HashSet）。
2. 用左右指針 (left, right) 來維護一個不含重複字元的子字串。
   - 右指針 right 不斷向右移動，將字元加入 set。
   - 若遇到重複字元，則移動左指針 left 並移除字元，直到沒有重複。
3. 每次更新 maxLen = 當前視窗長度 (right - left + 1)。
4. 時間複雜度 O(n)，因為每個字元最多被左右指針處理兩次。
5. 空間複雜度 O(k)，k 為字元集大小（英文字母最多 26 或 ASCII 128）。
*/
