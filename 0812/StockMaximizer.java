
import java.util.*;

public class StockMaximizer {

    public int maxProfit(int[] prices, int k) {
        if (prices == null || prices.length == 0 || k == 0) {
            return 0;
        }

        // 儲存所有可能的「獲利區間」利潤
        PriorityQueue<Integer> profitMaxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int n = prices.length;
        int i = 0;

        // 掃描整個價格陣列，找出所有上升趨勢的區間
        while (i < n - 1) {
            // 找買入點（低點）
            while (i < n - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int buy = i;

            // 找賣出點（高點）
            while (i < n - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            int sell = i;

            // 將每段區間的利潤加入 heap
            if (buy < sell) {
                profitMaxHeap.offer(prices[sell] - prices[buy]);
            }
            i++;
        }

        // 從最大利潤開始選，最多選 K 個
        int totalProfit = 0;
        while (k > 0 && !profitMaxHeap.isEmpty()) {
            totalProfit += profitMaxHeap.poll();
            k--;
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        StockMaximizer sm = new StockMaximizer();

        System.out.println(sm.maxProfit(new int[]{2, 4, 1}, 2));        // 預期：2
        System.out.println(sm.maxProfit(new int[]{3, 2, 6, 5, 0, 3}, 2)); // 預期：7
        System.out.println(sm.maxProfit(new int[]{1, 2, 3, 4, 5}, 2));    // 預期：4
        System.out.println(sm.maxProfit(new int[]{5, 4, 3, 2, 1}, 3));    // 預期：0
    }
}
