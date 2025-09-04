// 題目 14：班表 k 組反轉
// 檔名：LC25_ReverseKGroup_Shifts.java
// 說明：輸入一個鏈結串列與整數 k，將串列以 k 個節點為一組進行反轉，
// 若最後不足 k 個則保持原樣。

import java.util.*;

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LC25_ReverseKGroup_Shifts {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine(); // 讀掉換行
        String line = sc.nextLine().trim();

        if (line.isEmpty()) {
            System.out.println(); // 空串列
            return;
        }

        String[] tokens = line.split("\\s+");
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (String t : tokens) {
            curr.next = new ListNode(Integer.parseInt(t));
            curr = curr.next;
        }

        ListNode head = dummy.next;
        ListNode newHead = reverseKGroup(head, k);

        // 輸出結果
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

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;

        while (true) {
            ListNode kth = getKth(prevGroup, k);
            if (kth == null) {
                break; // 不足 k 個，不反轉
            }
            ListNode groupNext = kth.next;

            // 反轉當前區段
            ListNode prev = groupNext;
            ListNode curr = prevGroup.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // 接回已反轉區段
            ListNode tmp = prevGroup.next;
            prevGroup.next = kth;
            prevGroup = tmp;
        }

        return dummy.next;
    }

    // 找到從 node 開始往後第 k 個節點
    private static ListNode getKth(ListNode node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k--;
        }
        return node;
    }
}

/*
解題思路：
1. 使用 dummy 節點方便處理頭節點。
2. 每次嘗試找到第 k 個節點，若不足 k 個則結束。
3. 反轉當前 k 個節點，並將其接回主串列。
4. 重複直到串列走完。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
 */
