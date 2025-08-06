
import java.util.*;

/**
 * 練習 3.6 ─ BST 驗證與修復
 * ------------------------------------------------------------- 1) isValidBST
 * 檢查整棵樹是否為合法 BST 2) findViolations 找出所有違反 BST 中序順序的節點 3) recoverBST 修復「僅 2
 * 個節點值互換」的 BST 4) minRemovalToBST 最少移除多少節點可使其成為合法 BST
 */
public class BSTValidationAndRepair {

    /* ====== 節點定義 ====== */
    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    /* =========================================================
       1) 驗證 BST：遞迴傳遞範圍
       ========================================================= */
    public static boolean isValidBST(Node root) {
        return validate(root, null, null);
    }

    private static boolean validate(Node n, Integer low, Integer high) {
        if (n == null) {
            return true;
        }
        if ((low != null && n.val <= low) || (high != null && n.val >= high)) {
            return false;
        }
        return validate(n.left, low, n.val)
                && validate(n.right, n.val, high);
    }

    /* =========================================================
       2) 找違規節點：中序 + 前驅比較
       ========================================================= */
    public static List<Node> findViolations(Node root) {
        List<Node> bad = new ArrayList<>();
        inOrderFind(root, new Node[]{null}, bad);
        return bad;
    }

    private static void inOrderFind(Node n, Node[] prev, List<Node> bad) {
        if (n == null) {
            return;
        }
        inOrderFind(n.left, prev, bad);
        if (prev[0] != null && prev[0].val >= n.val) {
            // 可能有 1 或 2 個違規點：前一個或當前
            bad.add(prev[0]);
            bad.add(n);
        }
        prev[0] = n;
        inOrderFind(n.right, prev, bad);
    }

    /* =========================================================
       3) 修復「兩節點互換」的 BST
       ========================================================= */
    public static void recoverBST(Node root) {
        Node[] arr = new Node[3];      // first, middle, last
        inorderRecover(root, new Node[]{null}, arr);
        if (arr[0] != null && arr[2] != null) {
            swap(arr[0], arr[2]); 
        }else if (arr[0] != null && arr[1] != null) {
            swap(arr[0], arr[1]);
        }
    }

    private static void inorderRecover(Node n, Node[] prev, Node[] arr) {
        if (n == null) {
            return;
        }
        inorderRecover(n.left, prev, arr);
        if (prev[0] != null && prev[0].val > n.val) {
            if (arr[0] == null) {           // 第一次違規
                arr[0] = prev[0];           // first
                arr[1] = n;                 // middle
            } else {
                arr[2] = n;                 // second (last)
            }
        }
        prev[0] = n;
        inorderRecover(n.right, prev, arr);
    }

    private static void swap(Node a, Node b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    /* =========================================================
       4) 最少移除節點 → 總節點數 - 最大 BST 子樹大小
       ========================================================= */
    public static int minRemovalToBST(Node root) {
        int total = count(root);
        int maxBST = largestBST(root).size;
        return total - maxBST;
    }

    /* count nodes */
    private static int count(Node n) {
        if (n == null) {
            return 0;
        }
        return 1 + count(n.left) + count(n.right);
    }

    /* 封裝子樹資訊 */
    private static class Info {

        boolean isBST;
        int size, min, max;

        Info(boolean b, int s, int mn, int mx) {
            isBST = b;
            size = s;
            min = mn;
            max = mx;
        }
    }

    private static Info largestBST(Node n) {
        if (n == null) {
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Info L = largestBST(n.left);
        Info R = largestBST(n.right);

        if (L.isBST && R.isBST && n.val > L.max && n.val < R.min) {
            return new Info(true,
                    L.size + R.size + 1,
                    Math.min(n.val, L.min),
                    Math.max(n.val, R.max));
        }
        return new Info(false, Math.max(L.size, R.size), 0, 0);
    }

    /* ================= Demo / 測試用 ================= */
    public static void main(String[] args) {
        /* 建立合法 BST:      6
                           /   \
                          3     8
                         / \   / \
                        2  4  7   9           */
        Node bst = n(6,
                n(3, n(2), n(4)),
                n(8, n(7), n(9))
        );
        System.out.println("合法 BST? " + isValidBST(bst));          // true
        System.out.println("違規節點: " + findVals(findViolations(bst))); // []

        /* 產生「兩節點互換」的錯誤 BST：把 2 與 8 值交換 */
        swap(bst.left.left, bst.right);
        System.out.println("\n交換 2 與 8 後合法? " + isValidBST(bst)); // false
        System.out.println("違規節點: " + findVals(findViolations(bst))); // 8,6,2

        /* 修復 */
        recoverBST(bst);
        System.out.println("修復後合法? " + isValidBST(bst));         // true

        /* 再造一棵更亂的樹，測 minRemovalToBST */
        Node bad = n(10,
                n(5, n(1), n(8)),
                n(15, n(12), n(7)));    // 7 放錯
        System.out.println("\n移除最少節點數 = " + minRemovalToBST(bad)); // 1
    }

    /* 快速建樹 */
    private static Node n(int v) {
        return new Node(v);
    }

    private static Node n(int v, Node l, Node r) {
        Node n = new Node(v);
        n.left = l;
        n.right = r;
        return n;
    }

    private static List<Integer> findVals(List<Node> list) {
        List<Integer> r = new ArrayList<>();
        for (Node x : list) {
            r.add(x.val);
        
        }return r;
    }
}
