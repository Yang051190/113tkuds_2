// 題目：3Sum Closest
// 給定整數陣列 nums 與目標值 target，找到三個整數，使得和最接近 target。
// 回傳這三個數的總和。

import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum; // 完全等於 target，直接回傳
                }
            }
        }
        return closest;
    }
}

/*
解題思路：
1. 先排序，然後固定一個數 nums[i]，用雙指針找另外兩個數。
2. 計算三數和 sum，若比目前記錄的 closest 更接近 target，就更新。
3. 如果 sum < target，移動 left；若 sum > target，移動 right。
4. 若剛好等於 target，直接回傳。
5. 時間複雜度 O(n^2)，空間複雜度 O(1)。
*/
