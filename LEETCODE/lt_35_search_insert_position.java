// 題目：Search Insert Position
// 給定已排序陣列 nums 和目標值 target，找出應插入的位置。

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}

/*
解題思路：
1. 使用二分搜尋。
2. 如果找到目標，回傳索引。
3. 若沒找到，回傳應插入的位置 → left。
*/
