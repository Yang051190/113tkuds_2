
import java.util.*;

/**
 * 練習 3.1 ─ 二元樹基本操作 ---------------------------------------------------- 1)
 * sumAndAverage 計算總和與平均 2) findMinMax 找最大值與最小值 3) maxWidth 計算最大層寬 4)
 * isCompleteBinaryTree 判斷是否完全二元樹
 */
public class BinaryTreeBasicOperations {

    /* ====== 節點定義 ====== */
    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    /* -------------------------------------------------
       1) 總和與平均：一次 DFS 回傳 [sum, count]
       ------------------------------------------------- */
    public static double[] sumAndAverage(TreeNode root) {
        long[] res = dfsSumCnt(root);
        return new double[]{res[0], res[1] == 0 ? 0 : (double) res[0] / res[1]};
    }

    private static long[] dfsSumCnt(TreeNode n) {
        if (n == null) {
            return new long[]{0, 0};
        }
        long[] L = dfsSumCnt(n.left);
        long[] R = dfsSumCnt(n.right);
        return new long[]{L[0] + R[0] + n.val, L[1] + R[1] + 1};
    }

    /* -------------------------------------------------
       2) 最大值 / 最小值（DFS）
       ------------------------------------------------- */
    public static int[] findMinMax(TreeNode root) {
        int[] mm = dfsMinMax(root);
        return new int[]{mm[0], mm[1]}; // [min, max]
    }

    private static int[] dfsMinMax(TreeNode n) {
        if (n == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        int[] L = dfsMinMax(n.left);
        int[] R = dfsMinMax(n.right);
        int mn = Math.min(n.val, Math.min(L[0], R[0]));
        int mx = Math.max(n.val, Math.max(L[1], R[1]));
        return new int[]{mn, mx};
    }

    /* -------------------------------------------------
       3) 最大層寬（BFS，每層數量取最大）
       ------------------------------------------------- */
    public static int maxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int best = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            best = Math.max(best, size);
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        return best;
    }

    /* -------------------------------------------------
       4) 是否完全二元樹（BFS：遇到 null 後不得再有非 null）
       ------------------------------------------------- */
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean seenNull = false;
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == null) {
                seenNull = true;
            } else {
                if (seenNull) {
                    return false;        // 之前出現 null，後面又有非空 -> 非 CBT

                                }q.offer(cur.left);
                q.offer(cur.right);
            }
        }
        return true;
    }

    /* =================================================
       Demo：建立一棵示範樹
              10
             /  \
            6    15
           / \     \
          4   8     20
       ================================================= */
    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(20);

        /* 1) 總和與平均 */
        double[] sa = sumAndAverage(root);
        System.out.printf("總和 = %.0f，平均 = %.2f%n", sa[0], sa[1]);

        /* 2) 最大值 / 最小值 */
        int[] mm = findMinMax(root);
        System.out.printf("最小值 = %d，最大值 = %d%n", mm[0], mm[1]);

        /* 3) 最大層寬 */
        System.out.println("最大層寬 = " + maxWidth(root));

        /* 4) 是否完全二元樹 */
        System.out.println("是否完全二元樹？ " + isCompleteBinaryTree(root));
    }
}
