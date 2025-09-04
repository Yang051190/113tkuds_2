// 題目：Search in Rotated Sorted Array
// 在一個被旋轉過的升序陣列中搜尋目標值，若存在回傳索引，否則 -1。

class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) return mid;

            if (nums[left] <= nums[mid]) { // 左半邊有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 右半邊有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}

/*
解題思路：
1. 使用改良版二分搜尋。
2. 每次判斷哪一半是有序的。
3. 根據 target 是否落在有序區間，決定往哪邊搜尋。
*/
