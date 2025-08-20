
import java.io.*;
import java.util.*;

/**
 * 便利商店 Top-K 熱銷（小型資料） 規則： 1) 若輸入中同一品名出現多次，先彙總其銷量（加總）。 2) 取 Top-K：用「大小為 K
 * 的最小堆」維護目前前 K 名。 3) 輸出時依「銷量由高到低」，同量時依「品名字典序由小到大」。
 *
 * 堆的比較（用於淘汰）：(qty 升冪, name 降冪) —— 這樣同量時，字典序較大的會先被淘汰，保留較小者。
 */
public class M03_TopKConvenience {

    static class Item {

        String name;
        int qty;

        Item(String n, int q) {
            name = n;
            qty = q;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 讀 n, K
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 彙總銷量（若同名多筆出現則加總）
        Map<String, Integer> sum = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();      // 題目保證品名不含空白
            int qty = Integer.parseInt(st.nextToken());
            sum.put(name, sum.getOrDefault(name, 0) + qty);
        }

        // 最小堆（維持 Top-K）
        // 比較：qty 升冪；若 qty 相同，name 降冪（讓較不想留的在堆頂，易被淘汰）
        PriorityQueue<Item> minHeap = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) {
                    return Integer.compare(a.qty, b.qty);
                }
                return b.name.compareTo(a.name); // name 反向，確保同量時字典序小的被保留
            }
        });

        for (Map.Entry<String, Integer> e : sum.entrySet()) {
            Item it = new Item(e.getKey(), e.getValue());
            if (minHeap.size() < K) {
                minHeap.offer(it);
            } else {
                // 只在 it 比堆頂更「好」時替換（更大 qty；或同 qty 且 name 更小）
                Item top = minHeap.peek();
                if (it.qty > top.qty || (it.qty == top.qty && it.name.compareTo(top.name) < 0)) {
                    minHeap.poll();
                    minHeap.offer(it);
                }
            }
        }

        // 取出並排序為輸出順序：qty 由高到低，name 由小到大
        List<Item> ans = new ArrayList<>(minHeap);
        ans.sort((a, b) -> {
            if (a.qty != b.qty) {
                return Integer.compare(b.qty, a.qty); // qty 降冪

                        }return a.name.compareTo(b.name);                          // name 升冪
        });

        // 輸出（不足 K 則全列）
        StringBuilder sb = new StringBuilder();
        for (Item it : ans) {
            sb.append(it.name).append(' ').append(it.qty).append('\n');
        }
        System.out.print(sb.toString());
    }
}

/*
 * Time Complexity: O(n + U log K)
 * 說明：先以 HashMap 彙總為 O(n)，其中 U 為不同品項數；
 *      維護大小為 K 的最小堆，對每個品項做 O(log K)，合計 O(U log K)；
 *      最終將至多 K 筆排序輸出 O(K log K)，被 O(U log K) 吸收。
 */
