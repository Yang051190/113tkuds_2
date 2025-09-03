// 題目：Add Two Numbers
// 給定兩個非空的鏈結串列，分別代表兩個非負整數。數字以倒序方式儲存，每個節點只存一位數。
// 請將兩個數字相加，並以相同形式（倒序鏈結串列）回傳結果。

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 建立一個 dummy 節點，方便最後回傳結果
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0; // 紀錄進位

        // 當 l1 或 l2 還有節點，或有進位時，持續迴圈
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry; // 先加上進位

            if (l1 != null) {
                sum += l1.val; // 加上 l1 的值
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val; // 加上 l2 的值
                l2 = l2.next;
            }

            carry = sum / 10; // 更新進位
            curr.next = new ListNode(sum % 10); // 建立新節點存儲該位數字
            curr = curr.next;
        }

        return dummy.next; // 回傳真正的頭節點
    }
}

/*
解題思路：
1. l1 和 l2 分別代表兩個倒序的數字，例如：
   l1 = 2 -> 4 -> 3 代表 342
   l2 = 5 -> 6 -> 4 代表 465
   相加結果為 807，應輸出 7 -> 0 -> 8。
2. 使用 while 迴圈，逐位相加，並考慮進位。
3. 每次 sum = l1的值 + l2的值 + carry。
   - 新節點值為 sum % 10
   - carry = sum / 10
4. 直到 l1、l2 都走完，且 carry = 0 才停止。
5. 時間複雜度 O(max(m, n))，m、n 分別為 l1、l2 長度。
   空間複雜度 O(max(m, n))，因為要建立新串列存放結果。
*/
