// 題目：Count and Say
// 給定 n，輸出 count-and-say 序列的第 n 項。

class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String prev = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();

        int count = 1;
        for (int i = 1; i <= prev.length(); i++) {
            if (i < prev.length() && prev.charAt(i) == prev.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(prev.charAt(i - 1));
                count = 1;
            }
        }
        return sb.toString();
    }
}

/*
解題思路：
1. 遞迴定義：n 的序列由 n-1 產生。
2. 對前一個字串進行「數字計數」，組合成新字串。
3. 例如：
   n=1 → "1"
   n=2 → "11" (一個 1)
   n=3 → "21" (兩個 1)
   n=4 → "1211" (一個 2 一個 1)
*/
