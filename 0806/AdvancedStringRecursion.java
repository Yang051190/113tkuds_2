
import java.util.*;

/**
 * 練習 2.3 ─ 字串遞迴處理進階 --------------------------------------------- 1)
 * permutations 遞迴產生字串所有排列 2) recursiveMatch 遞迴字串匹配（回傳首個匹配起始 index，-1 代表找不到） 3)
 * removeDuplicates 遞迴移除重複字元（保留原先第一次出現） 4) allSubstrings 遞迴列舉所有子字串
 */
public class AdvancedStringRecursion {

    /* ---------- 1) 所有排列 ---------- */
    public static List<String> permutations(String s) {
        List<String> res = new ArrayList<>();
        permuteHelper("", s.toCharArray(), new boolean[s.length()], res);
        return res;
    }

    private static void permuteHelper(String prefix, char[] arr, boolean[] used, List<String> res) {
        if (prefix.length() == arr.length) {
            res.add(prefix);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            permuteHelper(prefix + arr[i], arr, used, res);
            used[i] = false;
        }
    }

    /* ---------- 2) 遞迴字串匹配 (naïve) ---------- */
    public static int recursiveMatch(String text, String pattern) {
        return matchHelper(text, pattern, 0, 0);
    }

    private static int matchHelper(String t, String p, int ti, int pi) {
        if (pi == p.length()) {
            return ti - pi;          // pattern 全比對成功

                }if (ti == t.length()) {
            return -1;               // text 比完還沒成功

                }if (t.charAt(ti) == p.charAt(pi)) {
            return matchHelper(t, p, ti + 1, pi + 1);  // 目前字元相等，繼續比下一個
         }else // 不相等，text 從下一位重新比
        {
            return matchHelper(t, p, ti - pi + 1, 0);
        }
    }

    /* ---------- 3) 遞迴移除重複字元 ---------- */
    public static String removeDuplicates(String s) {
        return dedupHelper(s, 0, new HashSet<>());
    }

    private static String dedupHelper(String s, int idx, Set<Character> seen) {
        if (idx == s.length()) {
            return "";
        }
        char c = s.charAt(idx);
        if (seen.contains(c)) {
            return dedupHelper(s, idx + 1, seen);      // 已看過 → 跳過

                }seen.add(c);
        return c + dedupHelper(s, idx + 1, seen);      // 新字元 → 保留
    }

    /* ---------- 4) 所有子字串 ---------- */
    public static List<String> allSubstrings(String s) {
        List<String> res = new ArrayList<>();
        substrHelper(s, 0, 1, res);
        return res;
    }

    private static void substrHelper(String s, int start, int len, List<String> res) {
        if (start == s.length()) {
            return;               // 起點跑完

                }if (start + len > s.length()) {                // 此起點長度已越界 → 換下一起點
            substrHelper(s, start + 1, 1, res);
            return;
        }
        res.add(s.substring(start, start + len));      // 收集
        substrHelper(s, start, len + 1, res);          // 同起點，子串長度 +1
    }

    /* ---------- Demo ---------- */
    public static void main(String[] args) {
        /* 1) permutations */
        System.out.println("排列 (abc)：");
        System.out.println(permutations("abc"));

        /* 2) match */
        String text = "abracadabra";
        String pattern = "cad";
        System.out.printf("%n字串匹配 \"%s\" in \"%s\" → %d%n",
                pattern, text, recursiveMatch(text, pattern)); // 4

        /* 3) remove duplicates */
        String dedup = removeDuplicates("banana");
        System.out.printf("%n去重 \"banana\" → %s%n", dedup); // "ban"

        /* 4) all substrings */
        System.out.println("\n子字串 (abc)：");
        System.out.println(allSubstrings("abc"));  // [a, ab, abc, b, bc, c]
    }
}
