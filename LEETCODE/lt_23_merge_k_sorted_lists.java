// 題目：Merge k Sorted Lists
// 將 k 個已排序的鏈結串列合併為一個排序鏈結串列。

import java.util.*;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode node : lists) {
            if (node != null) pq.add(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) pq.add(node.next);
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 使用最小堆 (PriorityQueue)，每次取出當前最小節點。
2. 將該節點接到新串列，若該節點有下一個，則加入堆中。
3. 重複直到所有節點處理完畢。
4. 時間複雜度 O(N log k)，N 為總節點數，k 為串列數量。
5. 空間複雜度 O(k)。
*/
