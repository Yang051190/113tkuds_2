// 題目 16：回收站清單移除指定元素
// 檔名：LC27_RemoveElement_Recycle.java
// 說明：輸入陣列與指定值 val，就地移除所有 val，輸出新長度與前段內容。

import java.util.*;

public class LC27_RemoveElement_Recycle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int newLen = removeElement(nums, val);
        System.out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i]);
            if (i < newLen - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static int removeElement(int[] nums, int val) {
        int write = 0;
        for (int x : nums) {
            if (x != val) {
                nums[write++] = x;
            }
        }
        return write;
    }
}

/*
解題思路：
1. 使用 write 指針覆寫非 val 元素。
2. 遍歷 nums，若元素 != val，則寫入 nums[write] 並遞增 write。
3. 最後 write 即為新長度。
4. 時間複雜度 O(n)，空間 O(1)。
 */
