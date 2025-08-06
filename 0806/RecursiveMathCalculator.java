
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * 遞迴數學計算器 1. 組合數 C(n, k) 2. 卡塔蘭數 Catalan(n) 3. 漢諾塔最少步數 hanoi(n) 4. 回文數判斷
 * isPalindrome(n)
 */
public class RecursiveMathCalculator {

    /* ------------------ 1) 組合數 C(n,k) ------------------ */
    private static final Map<String, BigInteger> combMemo = new HashMap<>();

    public static BigInteger combination(int n, int k) {
        if (k < 0 || k > n) {
            return BigInteger.ZERO;
        }
        if (k == 0 || k == n) {
            return BigInteger.ONE;
        }

        String key = n + "," + k;
        if (combMemo.containsKey(key)) {
            return combMemo.get(key);
        }

        BigInteger res = combination(n - 1, k - 1).add(combination(n - 1, k));
        combMemo.put(key, res);
        return res;
    }

    /* ------------------ 2) 卡塔蘭數 Catalan(n) ------------------ */
    private static final Map<Integer, BigInteger> catalanMemo = new HashMap<>();

    static {
        catalanMemo.put(0, BigInteger.ONE); // C(0) = 1
    }

    public static BigInteger catalan(int n) {
        if (catalanMemo.containsKey(n)) {
            return catalanMemo.get(n);
        }

        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            sum = sum.add(catalan(i).multiply(catalan(n - 1 - i)));
        }

        catalanMemo.put(n, sum);
        return sum;
    }

    /* ------------------ 3) 漢諾塔最少步數 hanoi(n) ------------------ */
    private static final Map<Integer, BigInteger> hanoiMemo = new HashMap<>();

    static {
        hanoiMemo.put(1, BigInteger.ONE); // 一個圓盤→1 步
    }

    public static BigInteger hanoi(int n) {
        if (hanoiMemo.containsKey(n)) {
            return hanoiMemo.get(n);
        }

        BigInteger steps = hanoi(n - 1).multiply(BigInteger.valueOf(2)).add(BigInteger.ONE);
        hanoiMemo.put(n, steps);
        return steps;
    }

    /* ------------------ 4) 判斷回文數 ------------------ */
    public static boolean isPalindrome(int n) {
        String s = Integer.toString(n);
        return isPalindromeRecursive(s, 0, s.length() - 1);
    }

    private static boolean isPalindromeRecursive(String s, int l, int r) {
        if (l >= r) {
            return true;
        }
        if (s.charAt(l) != s.charAt(r)) {
            return false;
        }
        return isPalindromeRecursive(s, l + 1, r - 1);
    }

    /* ------------------ Demo ------------------ */
    public static void main(String[] args) {
        System.out.println("組合數 C(5,2) = " + combination(5, 2));             // 10
        System.out.println("卡塔蘭數 C(5)  = " + catalan(5));                  // 42
        System.out.println("漢諾塔  n=3  最少步數 = " + hanoi(3));             // 7
        System.out.println("12321 是否回文？ " + isPalindrome(12321));         // true
        System.out.println("12345 是否回文？ " + isPalindrome(12345));         // false
    }
}
