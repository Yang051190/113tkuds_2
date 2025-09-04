// 題目 17：公告全文搜尋
// 檔名：LC28_StrStr_NoticeSearch.java
// 說明：在字串 haystack 中尋找子字串 needle 的首次出現位置，不存在回 -1。

import java.util.*;

public class LC28_StrStr_NoticeSearch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String haystack = sc.nextLine().trim();
        String needle = sc.nextLine().trim();

        System.out.println(strStr(haystack, needle));
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}

/*
解題思路：
1. 暴力法：從 haystack 每個可能起點開始，比對 needle。
2. 若 needle 為空，回傳 0。
3. 若長度大於 haystack，回傳 -1。
4. 時間複雜度 O(n*m)，n=haystack 長度，m=needle 長度。
 */
