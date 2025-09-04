// 題目 6：連假油量促銷最大區間
// 檔名：LC11_MaxArea_FuelHoliday.java
// 說明：輸入一個整數陣列 heights，選兩個索引 i, j（i<j），
// 使 (j - i) * min(heights[i], heights[j]) 最大，輸出最大值。

import java.util.*;

public class LC11_MaxArea_FuelHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }

        int result = maxArea(heights);
        System.out.println(result);
    }

    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int area = h * (right - left);
            maxArea = Math.max(maxArea, area);

            // 內縮較短邊
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
1. 使用雙指針，分別放在最左與最右。
2. 計算當前面積 = (right-left) * min(height[left], height[right])。
3. 為了可能獲得更大值，內縮較短的邊（因為高度受限於短邊）。
4. 更新最大值，直到左右指針相遇。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
 */
