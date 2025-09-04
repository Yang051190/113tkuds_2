// 題目 11：合併兩院區掛號清單
// 檔名：LC21_MergeTwoLists_Clinics.java
// 說明：輸入兩條已排序的單向鏈結串列，合併成一條升序串列。

import java.util.*;

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LC21_MergeTwoLists_Clinics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 建立第一條串列
        ListNode dummy1 = new ListNode(0);
        ListNode curr = dummy1;
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        ListNode l1 = dummy1.next;

        // 建立第二條串列
        ListNode dummy2 = new ListNode(0);
        curr = dummy2;
        for (int i = 0; i < m; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        ListNode l2 = dummy2.next;

        ListNode merged = mergeTwoLists(l1, l2);

        // 輸出結果
        curr = merged;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 使用 Dummy 節點簡化合併過程。
2. 比較 l1 與 l2 當前節點值，將較小的接到 tail 後面。
3. 當其中一條串列走完，直接接上另一條剩餘節點。
4. 輸出合併後的完整序列。
5. 時間複雜度 O(n+m)，空間複雜度 O(1)。
 */
