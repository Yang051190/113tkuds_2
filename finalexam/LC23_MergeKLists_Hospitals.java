// 題目 12：多院區即時叫號合併
// 檔名：LC23_MergeKLists_Hospitals.java
// 說明：輸入 k 條已排序單向鏈結串列，合併成一條升序串列。
// 使用最小堆維持 k 條串列當前的頭節點，每次取出最小值。

import java.util.*;

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LC23_MergeKLists_Hospitals {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine(); // 換行

        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            String line = sc.nextLine().trim();
            String[] tokens = line.split("\\s+");

            ListNode dummy = new ListNode(0);
            ListNode curr = dummy;
            for (String t : tokens) {
                int val = Integer.parseInt(t);
                if (val == -1) {
                    break;
                }
                curr.next = new ListNode(val);
                curr = curr.next;
            }
            lists[i] = dummy.next;
        }

        ListNode merged = mergeKLists(lists);

        // 輸出結果
        ListNode curr = merged;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                pq.add(node.next);
            }
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 使用最小堆 PriorityQueue 維護 k 條串列當前的頭節點。
2. 初始將所有非空串列的頭節點放入堆。
3. 每次從堆取出最小值，接到結果串列，若該節點有 next，則將 next 放回堆。
4. 重複直到堆空，完成合併。
5. 時間複雜度 O(N log k)，N 為總節點數。
 */
