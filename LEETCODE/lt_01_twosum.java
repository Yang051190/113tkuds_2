// 題目：Two Sum
// 給定一個整數陣列 nums 和一個目標值 target，請回傳兩個索引，使得 nums[i] + nums[j] == target。

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 使用 HashMap 儲存數值與索引，加速查找
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 需要的另一個數字
            if (map.containsKey(complement)) {
                // 找到符合條件的組合，回傳索引
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i); // 將當前數字與索引存入 map
        }
        return new int[] {}; // 沒找到則回傳空陣列
    }
}

/*
解題思路：
1. 題目要求找到兩個數字相加等於 target。
2. 最簡單的方法是雙層迴圈，但時間複雜度 O(n^2)，不符合效率需求。
3. 改用 HashMap：將「數值 → 索引」記錄起來，查找是否存在另一個數字能與當前數字配對。
4. 若找到則回傳兩個索引。
5. 時間複雜度：O(n)，只需一次迴圈；空間複雜度：O(n)，用來儲存 HashMap。
*/
