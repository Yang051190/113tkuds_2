// 題目：Remove Duplicates from Sorted Array
// 給定已排序陣列 nums，移除重複元素，使每個元素只出現一次，回傳新長度。

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0; // 慢指針
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}

/*
解題思路：
1. 已排序陣列，重複元素會相鄰。
2. 使用快慢指針：
   - j 負責遍歷陣列。
   - i 指向最後一個不重複的元素。
3. 當 nums[j] != nums[i]，就更新 nums[++i]。
4. 最後答案是 i+1。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
