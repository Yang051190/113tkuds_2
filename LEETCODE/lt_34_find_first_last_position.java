// 題目：Find First and Last Position of Element in Sorted Array
// 給定已排序陣列 nums 和目標值 target，找出其起始與結束索引。

class Solution {
    public int[] searchRange(int[] nums, int target) {
        return new int[] { findBound(nums, target, true), findBound(nums, target, false) };
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1, ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                ans = mid;
                if (isFirst) right = mid - 1;
                else left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}

/*
解題思路：
1. 使用二分搜尋兩次：
   - 找第一個出現位置。
   - 找最後一個出現位置。
2. 如果找不到，回傳 [-1, -1]。
*/
