// 題目：Swap Nodes in Pairs
// 給定一個鏈結串列，每次交換相鄰的兩個節點，返回新的串列。

class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null && curr.next.next != null) {
            ListNode first = curr.next;
            ListNode second = curr.next.next;

            // 交換
            first.next = second.next;
            second.next = first;
            curr.next = second;

            // 移動到下一組
            curr = first;
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 使用虛擬頭節點 dummy。
2. 每次取出相鄰的兩個節點 first 和 second，交換它們。
3. 交換後將 curr 移到下一組。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
