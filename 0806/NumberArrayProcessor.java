
import java.util.*;

/**
 * 數字陣列處理器 功能： 1. removeDuplicates 移除重複 2. mergeSorted 合併已排序陣列 3. mostFrequent
 * 找最高頻元素 4. splitEqual 依總和近似平均切兩半 main() Demo
 */
public class NumberArrayProcessor {

    /* ---------- 1) 去重，保持原順序 ---------- */
    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int v : arr) {
            set.add(v);
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    /* ---------- 2) 合併兩個已排序陣列 ---------- */
    public static int[] mergeSorted(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length || j < b.length) {
            if (j == b.length || (i < a.length && a[i] <= b[j])) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }
        return c;
    }

    /* ---------- 3) 找最高頻元素（若並列取最先出現） ---------- */
    public static int mostFrequent(int[] arr) {
        Map<Integer, Integer> count = new LinkedHashMap<>();
        int best = arr[0], freq = 0;
        for (int v : arr) {
            int c = count.merge(v, 1, Integer::sum);
            if (c > freq) {   // 若更高頻直接取代；並列時保留先出現者
                freq = c;
                best = v;
            }
        }
        return best;
    }

    /* ---------- 4) 依總和 /2 分割成兩段 ---------- */
    public static int[][] splitEqual(int[] arr) {
        int total = Arrays.stream(arr).sum();
        int half = total / 2;
        int cur = 0, idx = 0;
        while (idx < arr.length && cur + arr[idx] <= half) {
            cur += arr[idx];
            idx++;
        }
        return new int[][]{
            Arrays.copyOfRange(arr, 0, idx),
            Arrays.copyOfRange(arr, idx, arr.length)
        };
    }

    /* ---------- 小工具：列印 ---------- */
    private static void print(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    /* ---------- Demo ---------- */
    public static void main(String[] args) {

        int[] nums = {1, 2, 2, 3, 3, 3, 4, 5};
        int[] sortedA = {1, 3, 5, 7};
        int[] sortedB = {2, 4, 6, 8, 10};

        System.out.println("去重：");
        print(removeDuplicates(nums));

        System.out.println("\n合併兩已排序陣列：");
        print(mergeSorted(sortedA, sortedB));

        System.out.println("\n最高頻元素：");
        System.out.println(mostFrequent(nums));

        System.out.println("\n分割為兩段（總和近似）：");
        int[][] sp = splitEqual(new int[]{10, 8, 7, 6});
        print(sp[0]);
        print(sp[1]);
    }
}
