
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class M02_YouBikeNextArrival {

    private static int toMinutes(String hhmm) {
        hhmm = hhmm.trim();
        int h = Integer.parseInt(hhmm.substring(0, 2));
        int m = Integer.parseInt(hhmm.substring(3, 5));
        return h * 60 + m;
    }

    private static String toHHMM(int minutes) {
        int h = minutes / 60, m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 讀 n
        int n = Integer.parseInt(br.readLine().trim());

        // 讀 n 行已排序時刻
        List<Integer> times = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            times.add(toMinutes(line));
        }

        // 讀查詢時間
        String qLine = br.readLine().trim();
        int q = toMinutes(qLine);

        // 二分搜尋：找「第一個 > 查詢」的位置（upper_bound）
        int lo = 0, hi = n;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (times.get(mid) > q) {
                hi = mid; 
            }else {
                lo = mid + 1;
            }
        }

        if (lo == n) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHMM(times.get(lo)));
        }
    }
}
