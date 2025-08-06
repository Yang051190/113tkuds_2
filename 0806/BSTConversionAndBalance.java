
import java.util.*;

/**
 * 練習 3.9 ─ BST 轉換與平衡（重寫版）
 * ------------------------------------------------------- 1) bstToDoublyList —
 * 以「線性」雙向串列回傳 (不首尾相連) 2) sortedArrayToBST — 遞增陣列 → 平衡 BST 3) isBalanced —
 * 是否平衡；balanceFactor(root) 4) greaterSumTree — 反向中序累加，改寫節點值
 *
 * Demo 流程： ① 建平衡 BST → 檢查平衡 ② 做 greater-sum → 印中序驗證 ③ 最後才把 BST 改成 Doubly List
 * 並列印 （改裝後不再呼叫樹的遞迴函式，避免混用）
 */
public class BSTConversionAndBalance {

    /* ======== 節點 ======== */
    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    /* --------------------------------------------------
       1) BST → 線性雙向串列 (head, tail)
       -------------------------------------------------- */
    public static Node bstToDoublyList(Node root) {
        Node[] pair = inorderDLL(root, new Node[]{null}); // [head, tail]
        return pair[0];
    }

    private static Node[] inorderDLL(Node n, Node[] prev) {
        if (n == null) {
            return new Node[]{null, prev[0]};
        }
        Node[] left = inorderDLL(n.left, prev);
        if (prev[0] != null) {           // 串接 prev ↔ n
            prev[0].right = n;
            n.left = prev[0];
        }
        prev[0] = n;
        Node[] right = inorderDLL(n.right, prev);
        Node head = (left[0] != null) ? left[0] : n;
        Node tail = (right[1] != null) ? right[1] : n;
        return new Node[]{head, tail};
    }

    /* --------------------------------------------------
       2) 遞增陣列 → 平衡 BST
       -------------------------------------------------- */
    public static Node sortedArrayToBST(int[] a) {
        return build(a, 0, a.length - 1);
    }

    private static Node build(int[] a, int l, int r) {
        if (l > r) {
            return null;
        }
        int m = (l + r) >>> 1;
        Node n = new Node(a[m]);
        n.left = build(a, l, m - 1);
        n.right = build(a, m + 1, r);
        return n;
    }

    /* --------------------------------------------------
       3) 平衡檢查與平衡因子
       -------------------------------------------------- */
    public static boolean isBalanced(Node root) {
        return height(root) != -1;
    }

    public static int balanceFactor(Node root) {
        return h(root.left) - h(root.right);
    }

    private static int height(Node n) {
        if (n == null) {
            return 0;
        }
        int l = height(n.left);
        if (l == -1) {
            return -1;
        }
        int r = height(n.right);
        if (r == -1) {
            return -1;
        }
        return Math.abs(l - r) > 1 ? -1 : Math.max(l, r) + 1;
    }

    private static int h(Node n) {
        return n == null ? 0 : Math.max(h(n.left), h(n.right)) + 1;
    }

    /* --------------------------------------------------
       4) Greater-Sum Tree
       -------------------------------------------------- */
    public static void greaterSumTree(Node root) {
        int[] acc = {0};
        revIn(root, acc);
    }

    private static void revIn(Node n, int[] acc) {
        if (n == null) {
            return;
        }
        revIn(n.right, acc);
        acc[0] += n.val;
        n.val = acc[0];
        revIn(n.left, acc);
    }

    /* ----------------------- Demo ----------------------- */
    public static void main(String[] args) {
        /* 建立平衡 BST: 1-7 */
        Node bst = sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7});

        /* ① 檢查平衡 */
        System.out.println("Balanced? " + isBalanced(bst));
        System.out.println("Root balance factor = " + balanceFactor(bst));

        /* ② greater-sum & 印中序 */
        greaterSumTree(bst);
        System.out.println("InOrder after greater-sum: " + inorder(bst));

        /* ③ 轉成線性 DLL 並列印 */
        Node head = bstToDoublyList(bst);
        System.out.print("Doubly list: ");
        for (Node cur = head; cur != null; cur = cur.right) {
            System.out.print(cur.val + (cur.right == null ? "" : " <-> "));
        }
    }

    private static List<Integer> inorder(Node n) {
        List<Integer> r = new ArrayList<>();
        in(n, r);
        return r;
    }

    private static void in(Node n, List<Integer> r) {
        if (n == null) {
            return;

        }
        in(n.left, r);
        r.add(n.val);
        in(n.right, r);
    }
}
