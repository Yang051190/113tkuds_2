// 題目：3Sum
// 給定整數陣列 nums，回傳所有不重複的三元組 [nums[i], nums[j], nums[k]]，
// 使得 nums[i] + nums[j] + nums[k] == 0。

import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 先排序

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 跳過重複

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }
}

/*
解題思路：
1. 將陣列排序，方便用雙指針處理。
2. 固定一個數 nums[i]，然後用 left、right 兩個指針找出另外兩個數。
3. 若總和為 0，存入結果，並跳過重複的數字。
4. 若總和小於 0，左指針右移；若總和大於 0，右指針左移。
5. 時間複雜度 O(n^2)，空間複雜度 O(1)（不計輸出結果）。
*/
