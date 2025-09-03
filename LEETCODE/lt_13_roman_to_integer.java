// 題目：Roman to Integer
// 將羅馬數字轉換為整數，範圍 1 <= num <= 3999。

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));
            if (i + 1 < s.length() && value < map.get(s.charAt(i + 1))) {
                sum -= value; // 特殊情況：IV, IX, XL, XC, CD, CM
            } else {
                sum += value;
            }
        }

        return sum;
    }
}

/*
解題思路：
1. 建立羅馬數字對應的數值 Map。
2. 從左到右讀取字串：
   - 若當前數字 < 下一個數字，表示是減法（如 IV=4），則減去當前數字。
   - 否則加上當前數字。
3. 最後的總和就是轉換後的整數。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
