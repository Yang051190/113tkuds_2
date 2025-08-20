
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M08_BSTRangedSum {

    // 樹節點
    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 讀 n
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println("Sum: 0");
            return;
        }
        int n = Integer.parseInt(line.trim());

        // 讀 n 個層序值（-1 表 null）
        int[] a = new int[n];
        int cnt = 0;
        while (cnt < n) {
            String row = br.readLine();
            if (row == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(row);
            while (st.hasMoreTokens() && cnt < n) {
                a[cnt++] = Integer.parseInt(st.nextToken());
            }
        }

        // 讀 L, R
        int L, R;
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
        }

        Node root = buildTreeFromLevelArray(a);
        long ans = rangeSum(root, L, R);
        System.out.println("Sum: " + ans);
    }

    /**
     * 由層序陣列建樹（-1 為 null，left=2*i+1, right=2*i+2；跳過 null 的連結）
     */
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

    /**
     * BST 區間和（剪枝）：<L 只走右子樹，>R 只走左子樹，介於其間則左右都走
     */
    private static long rangeSum(Node node, int L, int R) {
        if (node == null) {
            return 0L;
        }
        if (node.val < L) {
            return rangeSum(node.right, L, R);
        }
        if (node.val > R) {
            return rangeSum(node.left, L, R);
        }
        return node.val + rangeSum(node.left, L, R) + rangeSum(node.right, L, R);
    }
}
