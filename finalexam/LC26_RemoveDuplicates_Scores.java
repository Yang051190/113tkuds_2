// 題目 15：去重學生成績單
// 檔名：LC26_RemoveDuplicates_Scores.java
// 說明：輸入一個已排序整數陣列，移除重複元素，並輸出新長度與前段內容。

import java.util.*;

public class LC26_RemoveDuplicates_Scores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println(0);
            return;
        }

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int newLen = removeDuplicates(nums);
        System.out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i]);
            if (i < newLen - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int write = 1; // 可寫位置
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[write - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }
}

/*
解題思路：
1. 已排序陣列，重複元素必相鄰。
2. 使用雙指針：
   - write 指向下一個可寫位置。
   - i 遍歷陣列，若 nums[i] != nums[write-1]，則寫入 nums[write]。
3. 遍歷完成後，write 即為新長度。
4. O(n) 時間、O(1) 額外空間。
 */
