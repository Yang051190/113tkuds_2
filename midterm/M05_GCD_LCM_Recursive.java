
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M05_GCD_LCM_Recursive {

    // 遞迴版 Euclidean algorithm
    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private static long lcm(long a, long b, long g) {
        // 先除後乘，避免乘法溢位；題目 a,b ≤ 1e9，使用 long 安全
        return (a / g) * b;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        // 保險起見轉正
        a = Math.abs(a);
        b = Math.abs(b);

        long g = gcd(a, b);
        long l = lcm(a, b, g);

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }
}

/*
 * Time Complexity: O(log min(a, b))
 * 說明：歐幾里得算法每次將參數縮小到餘數，最多 log 次遞迴；
 *      LCM 以 (a / GCD) * b 常數時間完成。
 */
