// 題目 13：班表兩兩交換
// 檔名：LC24_SwapPairs_Shifts.java
// 說明：輸入一條鏈結串列，將相鄰的節點兩兩交換，若長度為奇數則最後一個不變。

import java.util.*;

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LC24_SwapPairs_Shifts {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();
        if (line.isEmpty()) {
            System.out.println(); // 空串列 → 輸出空行
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
        ListNode swapped = swapPairs(head);

        // 輸出結果
        curr = swapped;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = prev.next.next;

            // 執行交換
            a.next = b.next;
            b.next = a;
            prev.next = b;

            // 移動 prev
            prev = a;
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 使用 dummy 節點方便處理頭節點交換。
2. 每次取兩個相鄰節點 (a, b)，執行交換：
   - a.next = b.next
   - b.next = a
   - prev.next = b
3. prev 移動到 a，繼續處理下一對。
4. 若長度為奇數，最後一個節點不會被交換。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
 */
