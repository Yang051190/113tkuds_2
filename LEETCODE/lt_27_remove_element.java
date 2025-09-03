// 題目：Remove Element
// 給定陣列 nums 和數值 val，移除所有等於 val 的元素，回傳新長度。

class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}

/*
解題思路：
1. 使用快慢指針。
2. 當 nums[j] != val，就將 nums[j] 放到 nums[i]，i++。
3. 結束後 i 就是新長度。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
