// 題目 3：地震速報雙資料源中位數
// 檔名：LC04_Median_QuakeFeeds.java
// 說明：輸入兩個已排序的浮點數數列，輸出合併後的中位數（不用真的合併）。
// 若總長度為偶數，取兩個中間值平均。

import java.util.*;

public class LC04_Median_QuakeFeeds {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        double[] A = new double[n];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) {
            A[i] = sc.nextDouble();
        }
        for (int j = 0; j < m; j++) {
            B[j] = sc.nextDouble();
        }

        double median = findMedianSortedArrays(A, B);
        System.out.printf("%.1f\n", median); // 輸出保留 1 位小數
    }

    public static double findMedianSortedArrays(double[] A, double[] B) {
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A); // 確保 A 是較短的
        }

        int n = A.length, m = B.length;
        int totalLeft = (n + m + 1) / 2;

        int left = 0, right = n;
        while (left <= right) {
            int i = (left + right) / 2; // A 左半部分元素數量
            int j = totalLeft - i;      // B 左半部分元素數量

            double Aleft = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                if ((n + m) % 2 == 1) {
                    return Math.max(Aleft, Bleft);
                } else {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            } else if (Aleft > Bright) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays not sorted properly.");
    }
}

/*
解題思路：
1. 題目需求：合併兩個已排序陣列，計算中位數，但不能真的合併。
2. 解法：二分搜尋切割法（Median of Two Sorted Arrays 經典）。
   - 確保 A 是較短的陣列。
   - 計算 A 的切點 i，B 的切點 j，使得左半元素數量 = (n+m+1)/2。
   - 確保 Aleft <= Bright && Bleft <= Aright。
   - 若成立 → 依總長度奇偶回傳中位數。
   - 否則調整切點。
3. 特殊處理：當 i=0 或 i=n 時，邊界用 ±Infinity。
4. 時間複雜度 O(log(min(n,m)))，空間複雜度 O(1)。
 */
