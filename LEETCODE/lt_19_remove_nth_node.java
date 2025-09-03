// 題目：Remove Nth Node From End of List
// 給定一個鏈結串列，移除倒數第 n 個節點，回傳頭節點。

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        // fast 先走 n+1 步，確保 slow 最後停在要刪除節點的前一個
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}

/*
解題思路：
1. 使用雙指針法，先讓 fast 走 n+1 步。
2. 然後 fast、slow 一起走，直到 fast 到達尾端。
3. 此時 slow 停在要刪除節點的前一個位置，直接刪除 slow.next。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
