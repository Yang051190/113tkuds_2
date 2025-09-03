// 題目：Container With Most Water
// 給定一個長度為 n 的整數陣列 height，height[i] 代表一條直線的高度。
// 選出兩條線，形成容器，能裝最多水，回傳最大水量。

class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int area = h * (right - left);
            maxArea = Math.max(maxArea, area);

            // 移動較短的那邊，尋找更大可能
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

/*
解題思路：
1. 使用雙指針 left、right 分別指向最左和最右的線。
2. 計算當前容器面積 = min(height[left], height[right]) * (right - left)。
3. 為了增加面積，移動較短的邊（因為面積受限於短邊）。
4. 重複直到左右指針相遇。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
