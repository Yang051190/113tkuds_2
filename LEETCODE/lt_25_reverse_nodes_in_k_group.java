// 題目：Reverse Nodes in k-Group
// 給定一個鏈結串列，將每 k 個節點反轉，返回新的鏈結串列。
// 若最後一組不足 k 個節點，則保持原樣。

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;

        while (true) {
            ListNode kth = getKthNode(prevGroup, k);
            if (kth == null) break;
            ListNode nextGroup = kth.next;

            // 反轉 [prevGroup.next, kth]
            ListNode prev = nextGroup;
            ListNode curr = prevGroup.next;

            while (curr != nextGroup) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            ListNode tmp = prevGroup.next;
            prevGroup.next = kth;
            prevGroup = tmp;
        }

        return dummy.next;
    }

    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}

/*
解題思路：
1. 使用虛擬頭節點 dummy。
2. 每次找到第 k 個節點，作為一組的結尾。
3. 反轉這一組節點，然後接回鏈結串列。
4. 若不足 k 個節點，則保持原樣。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
