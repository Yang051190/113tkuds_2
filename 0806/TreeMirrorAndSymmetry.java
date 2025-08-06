
/**
 * 練習 3.3 ─ 樹的鏡像與對稱
 * ------------------------------------------------------
 *  1) isSymmetric   判斷一棵樹是否對稱
 *  2) mirror        將樹就地轉換為鏡像
 *  3) areMirror     比較兩棵樹是否互為鏡像
 *  4) isSubtree     判斷 subtree 是否為 root 的子樹
 */
public class TreeMirrorAndSymmetry {

    /* ---------- 節點定義 ---------- */
    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    /* ====================================================
       1) 判斷對稱樹：比較左右子樹是否鏡像
       ==================================================== */
    public static boolean isSymmetric(Node root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(Node a, Node b) {
        if (a == null || b == null) {
            return a == b;
        }
        return a.val == b.val
                && isMirror(a.left, b.right)
                && isMirror(a.right, b.left);
    }

    /* ====================================================
       2) 將樹轉換為鏡像（就地交換左右子樹）
       ==================================================== */
    public static void mirror(Node root) {
        if (root == null) {
            return;
        }
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        mirror(root.left);
        mirror(root.right);
    }

    /* ====================================================
       3) 判斷兩棵樹是否互為鏡像
       ==================================================== */
    public static boolean areMirror(Node a, Node b) {
        return isMirror(a, b);
    }

    /* ====================================================
       4) 判斷一棵樹是否為另一棵的子樹
       ==================================================== */
    public static boolean isSubtree(Node root, Node sub) {
        if (sub == null) {
            return true;      // 空樹視為子樹

                }if (root == null) {
            return false;
        }
        if (isSame(root, sub)) {
            return true;
        }
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }

    private static boolean isSame(Node a, Node b) {
        if (a == null || b == null) {
            return a == b;
        }
        return a.val == b.val
                && isSame(a.left, b.left)
                && isSame(a.right, b.right);
    }

    /* ===================== Demo & 測試 ===================== */
    public static void main(String[] args) {

        /* 範例 1：對稱樹 */
        Node sym = new Node(1);
        sym.left = new Node(2);
        sym.right = new Node(2);
        sym.left.left = new Node(3);
        sym.left.right = new Node(4);
        sym.right.left = new Node(4);
        sym.right.right = new Node(3);

        System.out.println("sym 是否對稱？ " + isSymmetric(sym));  // true

        /* 範例 2：產生 sym 的鏡像 copy，並比較 */
        Node mirrorCopy = cloneTree(sym);
        mirror(mirrorCopy);
        System.out.println("sym 與其鏡像 copy 是否互為鏡像？ "
                + areMirror(sym, mirrorCopy));                    // true

        /* 範例 3：非對稱樹 */
        Node nonSym = new Node(1);
        nonSym.left = new Node(2);
        nonSym.right = new Node(3);
        System.out.println("nonSym 是否對稱？ " + isSymmetric(nonSym)); // false

        /* 範例 4：子樹判斷 */
        Node sub = new Node(2);
        sub.left = new Node(3);
        sub.right = new Node(4);
        System.out.println("sub 是否為 sym 的子樹？ "
                + isSubtree(sym, sub));                           // true
    }

    /* 輔助：複製樹 */
    private static Node cloneTree(Node n) {
        if (n == null) {
            return null;
        }
        Node copy = new Node(n.val);
        copy.left = cloneTree(n.left);
        copy.right = cloneTree(n.right);
        return copy;
    }
}
