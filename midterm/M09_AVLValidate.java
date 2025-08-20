
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M09_AVLValidate {

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
        int n = Integer.parseInt(br.readLine().trim());

        // 讀 n 個層序值（-1 代表 null），可跨行
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

        Node root = buildTreeFromLevelArray(a);

        // 先檢查 BST
        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }

        // 再檢查 AVL（回傳 -1 代表不合法）
        int h = checkAVLHeight(root);
        if (h == -1) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    /**
     * 由層序陣列建樹；-1 為 null，left=2*i+1, right=2*i+2
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
     * 驗證 BST：使用嚴格界 (min < val < max)
     */
    private static boolean isBST(Node node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false; // 嚴格不允許重複

                }return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    /**
     * 檢查 AVL：回傳子樹高度；若任一處 |BF|>1 則回傳 -1 表不合法。 空樹高度 0；葉子高度 1。
     */
    private static int checkAVLHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int lh = checkAVLHeight(node.left);
        if (lh == -1) {
            return -1;
        }
        int rh = checkAVLHeight(node.right);
        if (rh == -1) {
            return -1;
        }

        if (Math.abs(lh - rh) > 1) {
            return -1;  // 失衡

                }return Math.max(lh, rh) + 1;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：建樹 O(n)；BST 驗證每節點訪問一次 O(n)；
 *      AVL 檢查以後序一次遍歷計算高度與平衡 O(n)；總計線性時間。
 */
