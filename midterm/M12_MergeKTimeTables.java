
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class M12_MergeKTimeTables {

    // 小根堆的節點：時間、來自哪個列表、該列表中的索引
    static class Node implements Comparable<Node> {

        int time, li, idx;

        Node(int t, int li, int idx) {
            this.time = t;
            this.li = li;
            this.idx = idx;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }

    // 讀取工具：跨行讀 token
    static class FastScanner {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws Exception {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) {
                    return null;
                }
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
    }

    private static boolean seenHHMM = false;

    private static int toMinutes(String s) {
        if (s.indexOf(':') >= 0) {
            seenHHMM = true;
            int p = s.indexOf(':');
            int h = Integer.parseInt(s.substring(0, p));
            int m = Integer.parseInt(s.substring(p + 1));
            return h * 60 + m;
        } else {
            return Integer.parseInt(s);
        }
    }

    private static String fmt(int minutes) {
        if (!seenHHMM) {
            return Integer.toString(minutes);
        }
        int h = minutes / 60, m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // 讀 K
        String tokK = fs.next();
        if (tokK == null) {
            return;
        }
        int K = Integer.parseInt(tokK);

        // 讀 K 條已排序列表
        List<int[]> lists = new ArrayList<>(K);
        for (int i = 0; i < K; i++) {
            int len = Integer.parseInt(fs.next());
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                arr[j] = toMinutes(fs.next());
            }
            lists.add(arr);
        }

        // Min-Heap 合併
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            int[] a = lists.get(i);
            if (a.length > 0) {
                pq.offer(new Node(a[0], i, 0));
            }
        }

        StringBuilder out = new StringBuilder();
        boolean first = true;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!first) {
                out.append(' ');
            }
            out.append(fmt(cur.time));
            first = false;

            int[] a = lists.get(cur.li);
            int ni = cur.idx + 1;
            if (ni < a.length) {
                pq.offer(new Node(a[ni], cur.li, ni));
            }
        }

        System.out.println(out.toString());
    }
}
