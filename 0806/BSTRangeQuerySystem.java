
import java.util.*;

/**
 * 練習 3.2 ─ BST 範圍查詢系統
 * ----------------------------------------------------------- 功能： 1)
 * rangeSearch 取回 [min,max] 之間的所有節點 (遞增排序) 2) rangeCount 回傳落在範圍內的節點數 3) rangeSum
 * 回傳落在範圍內的節點值總和 4) closest 找出最接近 target 的節點值
 *
 * 範例樹 (插入順序)： 15 / \ 10 25 / \ / \ 8 12 20 30
 */
public class BSTRangeQuerySystem {

    /* ---------- 節點定義 ---------- */
    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    /* ---------- BST 建樹 ---------- */
    public static Node insert(Node root, int v) {
        if (root == null) {
            return new Node(v);
        }
        if (v < root.val) {
            root.left = insert(root.left, v); 
        }else {
            root.right = insert(root.right, v);
        }
        return root;
    }

    /* -------------------------------------------------------
       1) rangeSearch：取回所有在 [min,max] 的值 (中序)
       ------------------------------------------------------- */
    public static List<Integer> rangeSearch(Node root, int min, int max) {
        List<Integer> res = new ArrayList<>();
        inOrderRange(root, min, max, res);
        return res;
    }

    private static void inOrderRange(Node n, int min, int max, List<Integer> res) {
        if (n == null) {
            return;
        }
        if (n.val > min) {
            inOrderRange(n.left, min, max, res);
        }
        if (n.val >= min && n.val <= max) {
            res.add(n.val);
        }
        if (n.val < max) {
            inOrderRange(n.right, min, max, res);
        }
    }

    /* -------------------------------------------------------
       2) rangeCount：範圍內節點數
       ------------------------------------------------------- */
    public static int rangeCount(Node root, int min, int max) {
        if (root == null) {
            return 0;
        }
        if (root.val < min) {
            return rangeCount(root.right, min, max);
        }
        if (root.val > max) {
            return rangeCount(root.left, min, max);
        }
        return 1 + rangeCount(root.left, min, max)
                + rangeCount(root.right, min, max);
    }

    /* -------------------------------------------------------
       3) rangeSum：範圍內節點值總和
       ------------------------------------------------------- */
    public static long rangeSum(Node root, int min, int max) {
        if (root == null) {
            return 0;
        }
        if (root.val < min) {
            return rangeSum(root.right, min, max);
        }
        if (root.val > max) {
            return rangeSum(root.left, min, max);
        }
        return root.val + rangeSum(root.left, min, max)
                + rangeSum(root.right, min, max);
    }

    /* -------------------------------------------------------
       4) closest：找最接近 target 的節點值
       ------------------------------------------------------- */
    public static int closest(Node root, int target) {
        int best = root.val;
        Node cur = root;
        while (cur != null) {
            if (Math.abs(cur.val - target) < Math.abs(best - target)) {
                best = cur.val;
            }
            cur = (target < cur.val) ? cur.left : cur.right;
        }
        return best;
    }

    /* --------------------------- Demo --------------------------- */
    public static void main(String[] args) {
        /*  建立範例 BST  */
        int[] nums = {15, 10, 25, 8, 12, 20, 30};
        Node root = null;
        for (int v : nums) {
            root = insert(root, v);
        }

        /* 範圍 [10, 25] 測試 */
        int lo = 10, hi = 25;
        System.out.println("範圍搜尋 " + lo + "~" + hi + " → " + rangeSearch(root, lo, hi));
        System.out.println("範圍計數             → " + rangeCount(root, lo, hi));
        System.out.println("範圍總和             → " + rangeSum(root, lo, hi));

        /* 最接近查詢 */
        int target = 23;
        System.out.printf("最接近 %d 的節點值   → %d%n", target, closest(root, target));
    }
}
