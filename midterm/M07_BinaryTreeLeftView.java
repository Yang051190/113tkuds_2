
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class M07_BinaryTreeLeftView {

    // 簡單節點結構
    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            this.val = v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 讀 n
        int n = Integer.parseInt(br.readLine().trim());

        // 讀 n 個層序數值（-1 代表 null）
        int[] a = new int[n];
        int cnt = 0;
        while (cnt < n) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens() && cnt < n) {
                a[cnt++] = Integer.parseInt(st.nextToken());
            }
        }

        // 建樹（以完整二元樹索引：left=2*i+1, right=2*i+2；-1 表 null）
        Node root = buildTreeFromLevelArray(a);

        // 計算左視圖（BFS 每層第一個）
        List<Integer> leftView = getLeftView(root);

        // 輸出
        StringBuilder sb = new StringBuilder("LeftView:");
        if (!leftView.isEmpty()) {
            sb.append(' ');
        }
        for (int i = 0; i < leftView.size(); i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(leftView.get(i));
        }
        System.out.println(sb.toString());
    }

    // 依照層序陣列建樹；-1 位置為 null，不連結子樹
    private static Node buildTreeFromLevelArray(int[] a) {
        int n = a.length;
        if (n == 0 || a[0] == -1) {
            return null;
        }

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            if (a[i] != -1) {
                nodes[i] = new Node(a[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) {
                continue;
            }
            int li = 2 * i + 1, ri = 2 * i + 2;
            if (li < n) {
                nodes[i].left = nodes[li];
            }
            if (ri < n) {
                nodes[i].right = nodes[ri];
            }
        }
        return nodes[0];
    }

    // BFS：每層第一個出佇列即為左視圖
    private static List<Integer> getLeftView(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                if (i == 0) {
                    res.add(cur.val);     // 該層第一個

                                }if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
        }
        return res;
    }
}
