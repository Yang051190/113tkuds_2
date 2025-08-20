
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M01_BuildHeap {

    private static boolean isMax;      // true 代表 Max-Heap，false 代表 Min-Heap
    private static int[] heap;         // 0-based 陣列儲存堆
    private static int n;              // 元素數量

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 讀取 heap 型態
        String type = br.readLine().trim();
        isMax = type.equalsIgnoreCase("max");

        // 讀取元素個數
        n = Integer.parseInt(br.readLine().trim());
        heap = new int[n];

        // 讀入序列
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            heap[i] = Integer.parseInt(st.nextToken());
        }

        buildHeap();        // ⬅⬅   Bottom-up 建堆

        // 輸出結果
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                out.append(' ');
            }
            out.append(heap[i]);
        }
        System.out.println(out);
    }

    /**
     * Bottom-up 建堆：從最後一個非葉節點開始 heapifyDown
     */
    private static void buildHeap() {
        for (int i = (n >> 1) - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    /**
     * heapifyDown：將 idx 節點往下沉到正確位置
     */
    private static void heapifyDown(int idx) {
        while (true) {
            int left = (idx << 1) + 1;
            int right = left + 1;
            int best = idx;

            if (left < n && compare(heap[left], heap[best])) {
                best = left;
            }
            if (right < n && compare(heap[right], heap[best])) {
                best = right;
            }

            if (best == idx) {
                break;           // 已符合堆序

            }
            swap(idx, best);
            idx = best;
        }
    }

    /**
     * 依據 Max / Min 模式比較大小
     */
    private static boolean compare(int a, int b) {
        return isMax ? a > b : a < b;
    }

    private static void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆的總比較次數 ≤ n × Σ_{k=1}^{⌊log n⌋} (1 / 2^k) = O(n)。
 */
