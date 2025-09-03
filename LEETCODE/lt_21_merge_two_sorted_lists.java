// 題目：Merge Two Sorted Lists
// 將兩個已排序的鏈結串列合併為一個新的排序鏈結串列。

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 != null) curr.next = l1;
        if (l2 != null) curr.next = l2;

        return dummy.next;
    }
}

/*
解題思路：
1. 使用虛擬頭節點 dummy 簡化操作。
2. 每次比較 l1、l2 的當前值，取小的接到新串列後面。
3. 若其中一個串列走完，直接接上另一個串列。
4. 時間複雜度 O(m+n)，空間複雜度 O(1)。
*/
