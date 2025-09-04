// 題目 1：高鐵連假加班車 Two Sum
// 檔名：LC01_TwoSum_THSRHoliday.java
// 說明：給定一個陣列 (各班次可釋出座位數) 與一個 target (臨時新增需求總座位)，
// 找到兩個不同索引 i, j 使得 nums[i] + nums[j] == target。
// 若無解，輸出 -1 -1。

import java.util.*;

public class LC01_TwoSum_THSRHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 班次數量
        int target = sc.nextInt(); // 需求座位總數

        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextInt();
        }

        int[] result = twoSum(seats, target);
        System.out.println(result[0] + " " + result[1]);
    }

    // 使用 HashMap 解 Two Sum
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}

/*
解題思路：
1. 題目需求：找兩個索引 (i, j)，使 nums[i] + nums[j] == target。
2. 使用 HashMap：
   - Key: 數值
   - Value: 索引
3. 遍歷陣列時，對每個 nums[i] 檢查 map 裡是否存在 target - nums[i]。
   - 若存在 → 回傳對應索引與 i。
   - 若不存在 → 將 nums[i] 存入 map。
4. 若遍歷結束仍無解 → 回傳 -1 -1。
5. 時間複雜度：O(n)，空間複雜度：O(n)。
 */
