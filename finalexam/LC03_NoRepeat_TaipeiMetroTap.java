// 題目 2：北捷刷卡最長無重複片段
// 檔名：LC03_NoRepeat_TaipeiMetroTap.java
// 說明：輸入一個字串 s（捷運刷卡流水），
// 找到最長的不含重複字元的連續子字串長度，輸出這個長度。

import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();

        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c) && map.get(c) >= left) {
                // 左指針跳到該字元上次出現位置 +1
                left = map.get(c) + 1;
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

/*
解題思路：
1. 使用滑動視窗，維護一段不重複的子字串。
2. Map<char, lastIndex> 記錄每個字元最後出現位置。
3. 右指針 right 遍歷字串：
   - 若當前字元已出現過，且位置 >= left，則更新 left = 上次位置 + 1。
4. 每次更新 maxLen = max(maxLen, right - left + 1)。
5. 時間複雜度 O(n)，空間複雜度 O(k)，k 為字元種類。
 */
