// 題目：Find the Index of the First Occurrence in a String
// 給定字串 haystack 與 needle，回傳 needle 在 haystack 中第一次出現的索引。
// 若不存在，回傳 -1。

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int m = haystack.length(), n = needle.length();

        for (int i = 0; i <= m - n; i++) {
            if (haystack.substring(i, i + n).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}

/*
解題思路：
1. 使用暴力法，從 haystack 每個位置開始比對 needle。
2. 若找到完全匹配，回傳索引。
3. 若整個 haystack 都比對不到，回傳 -1。
4. 時間複雜度 O((m-n+1)*n)，空間複雜度 O(1)。
*/
