// 題目：Next Permutation
// 將整數陣列 nums 重新排列為字典序中的下一個更大排列。
// 若已經是最大排列，則改為最小排列（排序後）。

class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 找到第一個遞減的位置
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}

/*
解題思路：
1. 從右往左找到第一個下降的位置 i。
2. 從右往左找到比 nums[i] 大的最小值，交換。
3. 將 i 後面的數字反轉，變成最小排列。
4. 若找不到 i，表示已是最大排列，整個反轉成最小。
*/
