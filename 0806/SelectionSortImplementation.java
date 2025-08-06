
import java.util.Arrays;

/**
 * 選擇排序實作 & 與氣泡排序比較
 */
public class SelectionSortImplementation {

    /**
     * 用來統計比較/交換次數
     */
    static class Stat {

        int cmp = 0, swp = 0;
    }

    /* ---------- 1) 選擇排序 ---------- */
    public static int[] selectionSort(int[] src, Stat st) {
        int[] a = src.clone();                // 不修改原陣列
        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                st.cmp++;
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int t = a[i];
                a[i] = a[min];
                a[min] = t;
                st.swp++;
            }
            System.out.printf("第 %d 輪：%s%n", i + 1, Arrays.toString(a));
        }
        return a;
    }

    /* ---------- 2) 氣泡排序（簡版，只為比較） ---------- */
    public static int[] bubbleSort(int[] src, Stat st) {
        int[] a = src.clone();
        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                st.cmp++;
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                    st.swp++;
                }
            }
        }
        return a;
    }

    /* ---------- 3) Demo ---------- */
    public static void main(String[] args) {

        int[] data = {64, 34, 25, 12, 22, 11, 90};

        /* --- 選擇排序 --- */
        Stat ss = new Stat();
        System.out.println("=== 選擇排序過程 ===");
        int[] selResult = selectionSort(data, ss);
        System.out.printf("選擇排序結果：%s%n比較 %d 次，交換 %d 次%n%n",
                Arrays.toString(selResult), ss.cmp, ss.swp);

        /* --- 氣泡排序 --- */
        Stat bs = new Stat();
        bubbleSort(data, bs);
        System.out.printf("氣泡排序比較 %d 次，交換 %d 次%n", bs.cmp, bs.swp);
    }
}
