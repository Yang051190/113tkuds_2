
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M04_TieredTaxSimple {

    // 速算扣除額版（對齊題目範例的數字）
    // 區間與稅率：
    // 0–120000 -> 5%
    // 120001–500000 -> 12% - 9,000
    // 500001–1000000 -> 20% - 49,000
    // 1000001 以上 -> 30% - 120,000
    private static long taxOf(long income) {
        if (income <= 120_000) {                 // 5%
            return income * 5 / 100;
        } else if (income <= 500_000) {          // 12% − 9,000
            return income * 12 / 100 - 9_000;
        } else if (income <= 1_000_000) {        // 20% − 49,000
            return income * 20 / 100 - 49_000;
        } else {                                 // 30% − 120,000
            return income * 30 / 100 - 120_000;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        long sumTax = 0;

        for (int i = 0; i < n; i++) {
            long income = Long.parseLong(br.readLine().trim());
            long t = taxOf(income);
            sumTax += t;
            System.out.println("Tax: " + t);
        }

        long avg = Math.round((double) sumTax / n); // 平均取整數
        System.out.println("Average: " + avg);
    }
}

/*
 * Time Complexity: O(n)
 * 說明：每筆收入僅做常數次區間判斷與四則運算，總共 n 筆，故為線性時間。
 */
