// 題目：Median of Two Sorted Arrays
// 給定兩個已排序的整數陣列 nums1 和 nums2，請找出這兩個陣列的中位數，並要求演算法時間複雜度為 O(log(min(m, n)))。

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 保證 nums1 是比較短的陣列，方便二分搜尋
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;

        while (left <= right) {
            int partition1 = (left + right) / 2;   // nums1 的切割位置
            int partition2 = (m + n + 1) / 2 - partition1; // nums2 的切割位置

            // 如果切在邊界，設為正無窮或負無窮方便處理
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            // 符合中位數的條件
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // 總長度為偶數 → 取中間兩數平均
                if ((m + n) % 2 == 0) {
                    return ((double)Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2;
                } 
                // 總長度為奇數 → 取左邊最大值
                else {
                    return (double)Math.max(maxLeft1, maxLeft2);
                }
            } 
            // 若 nums1 左半邊太大 → 往左移
            else if (maxLeft1 > minRight2) {
                right = partition1 - 1;
            } 
            // 若 nums1 右半邊太小 → 往右移
            else {
                left = partition1 + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted!");
    }
}

/*
解題思路：
1. 這題要求時間複雜度 O(log(min(m, n)))，所以要用「二分搜尋」而不是合併排序。
2. 我們把 nums1、nums2 想像成被切割成左右兩部分：
   - 左半邊：包含總長度的一半元素
   - 右半邊：包含剩下的一半元素
3. 中位數的條件：
   - 左半邊的最大值 <= 右半邊的最小值
   - 當總長度為偶數：取 (左半最大 + 右半最小) / 2
   - 當總長度為奇數：取左半最大
4. 我們對較短的陣列做二分搜尋，找到正確的切割點。
5. 時間複雜度：O(log(min(m, n)))
   空間複雜度：O(1)
*/
