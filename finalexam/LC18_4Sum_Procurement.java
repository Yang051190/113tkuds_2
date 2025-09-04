// 題目 9：採購限額 4Sum
// 檔名：LC18_4Sum_Procurement.java
// 說明：輸入一個整數陣列 nums 和 target，輸出所有和為 target 的不重複四元組。
// 結果每行輸出一組，四個數升序。

import java.util.*;

public class LC18_4Sum_Procurement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long target = sc.nextLong();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        List<List<Integer>> results = fourSum(nums, target);
        for (List<Integer> quad : results) {
            System.out.println(quad.get(0) + " " + quad.get(1) + " " + quad.get(2) + " " + quad.get(3));
        }
    }

    public static List<List<Integer>> fourSum(int[] nums, long target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去重 i
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // 去重 j
                }
                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++; // 去重 L

                                                }while (left < right && nums[right] == nums[right + 1]) {
                            right--; // 去重 R

                                            }} else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}

/*
解題思路：
1. 將 nums 排序，方便去重與雙指針搜尋。
2. 兩層迴圈固定 i, j，然後使用雙指針 (left, right) 搜尋其餘兩個數。
3. 當 sum == target 時加入答案，並跳過重複元素避免重複組合。
4. 若 sum < target，left++；若 sum > target，right--。
5. 時間複雜度 O(n^3)，空間複雜度 O(1)（不計輸出結果）。
 */
