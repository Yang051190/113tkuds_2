// 題目 19：延誤等級首末定位
// 檔名：LC34_SearchRange_DelaySpan.java
// 說明：輸入一個已排序陣列，回傳 target 的首個與最後一個索引，若不存在回 -1 -1。

import java.util.*;

public class LC34_SearchRange_DelaySpan {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] ans = searchRange(nums, target);
        System.out.println(ans[0] + " " + ans[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        return new int[]{findBound(nums, target, true), findBound(nums, target, false)};
    }

    private static int findBound(int[] nums, int target, boolean first) {
        int l = 0, r = nums.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                ans = mid;
                if (first) {
                    r = mid - 1; 
                }else {
                    l = mid + 1;
                }
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}

/*
解題思路：
1. 使用二分搜尋兩次：
   - 尋找最左邊出現位置。
   - 尋找最右邊出現位置。
2. 若沒找到，回傳 -1。
3. 時間複雜度 O(log n)，空間 O(1)。
 */
