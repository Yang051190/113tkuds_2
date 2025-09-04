// 題目 7：高鐵站點三元組 3Sum
// 檔名：LC15_3Sum_THSRStops.java
// 說明：輸入一個整數陣列，輸出所有和為 0 的不重複三元組，每行一組。

import java.util.*;

public class LC15_3Sum_THSRStops {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        List<List<Integer>> results = threeSum(nums);
        for (List<Integer> triplet : results) {
            System.out.println(triplet.get(0) + " " + triplet.get(1) + " " + triplet.get(2));
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 跳過重複

                        }if (nums[i] > 0) {
                break; // 若最小數已 >0，不可能有解
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}

/*
解題思路：
1. 將陣列排序，方便去重與雙指針操作。
2. 固定一個數 nums[i]，用雙指針 (left, right) 找另外兩個數，使和為 0。
3. 若 nums[i] > 0，則不可能有解，提前結束。
4. 若找到一組解，將 left++、right--，並跳過重複元素避免重複解。
5. 時間複雜度 O(n^2)，空間複雜度 O(1)（不計輸出結果）。
 */
