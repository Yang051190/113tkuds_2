
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M11_HeapSortWithTie {

    // 以 (score, index) 建 Max-Heap：score 大者優先；同分時 index 大者優先（方便從尾端回填）
    // 這樣完成後的整體排序即為：score 由小到大；同分時 index 由小到大。
    private static int[] score;
    private static int[] idx;
    private static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());
        score = new int[n];
        idx = new int[n];

        // 讀 n 個分數（可跨行）
        int c = 0;
        while (c < n) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens() && c < n) {
                score[c] = Integer.parseInt(st.nextToken());
                idx[c] = c; // 原始位置
                c++;
            }
        }

        // 建 Max-Heap（bottom-up）
        for (int i = (n >>> 1) - 1; i >= 0; i--) {
            siftDown(i, n);
        }

        // Heapsort：每次把最大值丟到尾端，縮小堆
        for (int end = n - 1; end > 0; end--) {
            swap(0, end);
            siftDown(0, end);
        }

        // 輸出分數（遞增；同分時依原索引遞增）
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                out.append(' ');
            }
            out.append(score[i]);
        }
        System.out.println(out.toString());
    }

    // 將 pos 往下沉到正確位置（堆大小為 size）
    private static void siftDown(int pos, int size) {
        while (true) {
            int left = (pos << 1) + 1;
            int right = left + 1;
            int best = pos;

            if (left < size && greater(left, best)) {
                best = left;
            }
            if (right < size && greater(right, best)) {
                best = right;
            }
            if (best == pos) {
                break;
            }
            swap(pos, best);
            pos = best;
        }
    }

    // Max-Heap 比較：score 大者優先；同分時 index 大者優先
    // （因為我們從尾端回填，較大的 index 先出堆，最後成品即 index 升冪）
    private static boolean greater(int i, int j) {
        if (score[i] != score[j]) {
            return score[i] > score[j];
        }
        return idx[i] > idx[j];
    }

    private static void swap(int i, int j) {
        int ts = score[i];
        score[i] = score[j];
        score[j] = ts;
        int ti = idx[i];
        idx[i] = idx[j];
        idx[j] = ti;
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：Bottom-up 建堆 O(n)，每次取出堆頂並下沉 O(log n) 共 n 次，合計 O(n log n)。
 *      「同分以索引較小者優先」藉由在 Max-Heap 中同分時令 index 大者較大，
 *      取最大回填到尾端後，完整序列即為 (score 升冪, index 升冪)。
 */
