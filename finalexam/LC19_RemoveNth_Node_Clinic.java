// 題目 10：護理紀錄刪除倒數第 N 筆
// 檔名：LC19_RemoveNth_Node_Clinic.java
// 說明：輸入一個單向鏈結串列與整數 k，刪除倒數第 k 個節點，輸出刪除後的序列。

import java.util.*;

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LC19_RemoveNth_Node_Clinic {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 建立鏈結串列
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }

        int k = sc.nextInt();
        ListNode newHead = removeNthFromEnd(dummy.next, k);

        // 輸出刪除後序列
        curr = newHead;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        // fast 先走 n+1 步，確保 slow 在待刪節點前
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow.next
        slow.next = slow.next.next;
        return dummy.next;
    }
}

/*
解題思路：
1. 使用雙指針法：
   - fast 先走 n+1 步。
   - 然後 fast 與 slow 一起走，直到 fast 到尾。
   - 此時 slow 在待刪節點的前一個。
2. 執行 slow.next = slow.next.next，刪掉目標節點。
3. Dummy 節點用來處理刪除頭節點的特殊情況。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
 */
