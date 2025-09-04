// 題目 18：旋轉陣列搜尋
// 檔名：LC33_SearchRotated_RentHot.java
// 說明：輸入一個旋轉過的升序陣列，搜尋 target 索引，若不存在回 -1。

import java.util.*;

public class LC33_SearchRotated_RentHot {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[l] <= nums[mid]) { // 左半有序
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else { // 右半有序
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}

/*
解題思路：
1. 使用二分搜尋，每次判斷左半或右半是否有序。
2. 若目標值落在有序半部，縮小至該半區間。
3. 否則縮小至另一半。
4. 時間複雜度 O(log n)，空間 O(1)。
 */
