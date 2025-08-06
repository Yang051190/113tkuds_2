
import java.util.*;

/**
 * 練習 2.4 ─ 遞迴 vs 迭代 效能比較
 * ---------------------------------------------------------- 1) 二項式係數 C(n, k)
 * 2) 陣列乘積 product(arr) 3) 字串元音數 countVowel(str) 4) 括號匹配 isBalanced(str) (僅 ()
 * 一對作示範)
 *
 * 執行時會列出遞迴 (R) 與迭代 (I) 版本的結果與耗時 (ns)。
 */
public class RecursionVsIteration {

    /* ======================================================
       1) 二項式係數 C(n,k)
       ====================================================== */
    // 遞迴 (備註：加 memo 避免重複計算)
    private static final Map<String, Long> combMemo = new HashMap<>();

    public static long combR(int n, int k) {
        if (k < 0 || k > n) {
            return 0;
        }
        if (k == 0 || k == n) {
            return 1;
        }
        String key = n + "," + k;
        if (combMemo.containsKey(key)) {
            return combMemo.get(key);
        }
        long val = combR(n - 1, k - 1) + combR(n - 1, k);
        combMemo.put(key, val);
        return val;
    }

    // 迭代 (自底向上 DP)
    public static long combI(int n, int k) {
        long[] dp = new long[k + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j > 0; j--) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[k];
    }

    /* ======================================================
       2) 陣列中所有元素乘積
       ====================================================== */
    // 遞迴
    public static long productR(int[] arr, int idx) {
        if (idx == arr.length - 1) {
            return arr[idx];
        }
        return arr[idx] * productR(arr, idx + 1);
    }

    // 迭代
    public static long productI(int[] arr) {
        long p = 1;
        for (int v : arr) {
            p *= v;
        }
        return p;
    }

    /* ======================================================
       3) 字串中元音 (a e i o u) 數量
       ====================================================== */
    private static final Set<Character> vowelSet = Set.of('a', 'e', 'i', 'o', 'u',
            'A', 'E', 'I', 'O', 'U');

    // 遞迴
    public static int vowelR(String s, int idx) {
        if (idx == s.length()) {
            return 0;
        }
        int add = vowelSet.contains(s.charAt(idx)) ? 1 : 0;
        return add + vowelR(s, idx + 1);
    }

    // 迭代
    public static int vowelI(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (vowelSet.contains(c)) {
                cnt++;
            }
        }
        return cnt;
    }

    /* ======================================================
       4) 檢查括號 () 是否平衡
       ====================================================== */
    // 遞迴：把最外層配對後遞迴檢查裡面
    public static boolean balancedR(String s) {
        return balancedRHelper(s, 0, s.length() - 1);
    }

    private static boolean balancedRHelper(String s, int l, int r) {
        if (l > r) {
            return true;
        }
        if (s.charAt(l) != '(') {
            return false;
        }
        int depth = 0;
        for (int i = l; i <= r; i++) {
            depth += s.charAt(i) == '(' ? 1 : -1;
            if (depth == 0) // 找到與 s[l] 配對的 ')'
            {
                return balancedRHelper(s, l + 1, i - 1)
                        && balancedRHelper(s, i + 1, r);
            }
        }
        return false;
    }

    // 迭代：堆疊計數
    public static boolean balancedI(String s) {
        int depth = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                depth++; 
            }else if (c == ')') {
                if (depth == 0) {
                    return false;
                }
                depth--;
            }
        }
        return depth == 0;
    }

    /* ======================================================
       測試與效能量測
       ====================================================== */
    private static void measure(String tag, Runnable task) {
        long t0 = System.nanoTime();
        task.run();
        long t1 = System.nanoTime();
        System.out.printf("%s 耗時 %d ns%n", tag, (t1 - t0));
    }

    public static void main(String[] args) {

        /* ---------- 1) 二項式係數 ---------- */
        int n = 30, k = 15;
        measure("C(n,k) 遞迴", () -> System.out.println("結果 = " + combR(n, k)));
        measure("C(n,k) 迭代", () -> System.out.println("結果 = " + combI(n, k)));
        System.out.println();

        /* ---------- 2) 陣列乘積 ---------- */
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        measure("Product 遞迴", () -> System.out.println("結果 = " + productR(arr, 0)));
        measure("Product 迭代", () -> System.out.println("結果 = " + productI(arr)));
        System.out.println();

        /* ---------- 3) 元音數 ---------- */
        String str = "Supercalifragilisticexpialidocious";
        measure("Vowel 遞迴", () -> System.out.println("結果 = " + vowelR(str, 0)));
        measure("Vowel 迭代", () -> System.out.println("結果 = " + vowelI(str)));
        System.out.println();

        /* ---------- 4) 括號匹配 ---------- */
        String brackets = "((())(()))()";
        measure("Balanced 遞迴", () -> System.out.println("結果 = " + balancedR(brackets)));
        measure("Balanced 迭代", () -> System.out.println("結果 = " + balancedI(brackets)));
    }
}
