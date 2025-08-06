
import java.util.*;

/**
 * 練習 3.7 ─ 樹的路徑問題 ------------------------------------------------------------
 * 1) allRootToLeafPaths 列出根→葉的所有路徑 2) hasPathSum 判斷是否存在根→葉路徑和 = target 3)
 * maxRootToLeafPath 找到和最大的根→葉路徑 4) maxPathSumAnyTwoNodes 任意兩節點最大路徑和（樹徑）
 */
public class TreePathProblems {

    /* ==== 節點定義 ==== */
    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    /* ----------------------------------------------------------
       1) 根→葉所有路徑（DFS）
       ---------------------------------------------------------- */
    public static List<List<Integer>> allRootToLeafPaths(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        dfsPaths(root, new ArrayList<>(), res);
        return res;
    }

    private static void dfsPaths(Node n, List<Integer> path, List<List<Integer>> res) {
        if (n == null) {
            return;
        }
        path.add(n.val);
        if (n.left == null && n.right == null) // 到葉子
        {
            res.add(new ArrayList<>(path)); 
        }else {
            dfsPaths(n.left, path, res);
            dfsPaths(n.right, path, res);
        }
        path.remove(path.size() - 1);                    // 回溯
    }

    /* ----------------------------------------------------------
       2) 是否存在根→葉路徑和 = target
       ---------------------------------------------------------- */
    public static boolean hasPathSum(Node n, int target) {
        if (n == null) {
            return false;
        }
        if (n.left == null && n.right == null) {
            return target == n.val;
        }
        return hasPathSum(n.left, target - n.val)
                || hasPathSum(n.right, target - n.val);
    }

    /* ----------------------------------------------------------
       3) 最大根→葉路徑
       ---------------------------------------------------------- */
    public static List<Integer> maxRootToLeafPath(Node root) {
        List<Integer> bestPath = new ArrayList<>();
        dfsMaxPath(root, 0, new ArrayList<>(), new int[]{Integer.MIN_VALUE}, bestPath);
        return bestPath;
    }

    private static void dfsMaxPath(Node n, int sum, List<Integer> path,
            int[] bestSum, List<Integer> bestPath) {
        if (n == null) {
            return;
        }
        path.add(n.val);
        sum += n.val;
        if (n.left == null && n.right == null) {         // 葉
            if (sum > bestSum[0]) {
                bestSum[0] = sum;
                bestPath.clear();
                bestPath.addAll(path);
            }
        } else {
            dfsMaxPath(n.left, sum, path, bestSum, bestPath);
            dfsMaxPath(n.right, sum, path, bestSum, bestPath);
        }
        path.remove(path.size() - 1);
    }

    /* ----------------------------------------------------------
       4) 任意兩節點最大路徑和（樹徑，含節點值）
       ---------------------------------------------------------- */
    public static int maxPathSumAnyTwoNodes(Node root) {
        int[] best = {Integer.MIN_VALUE};
        dfsMaxAny(root, best);
        return best[0];
    }

    /**
     * 回傳：從當前節點往下延伸的「單支」最大和
     */
    private static int dfsMaxAny(Node n, int[] best) {
        if (n == null) {
            return 0;
        }
        int left = Math.max(0, dfsMaxAny(n.left, best));  // 不取負和
        int right = Math.max(0, dfsMaxAny(n.right, best));
        best[0] = Math.max(best[0], left + right + n.val); // 左+右+本身
        return Math.max(left, right) + n.val;
    }

    /* ==========================================================
       Demo
       ----------------------------------------------------------
             10
            /  \
           5    -3
          / \     \
         3   2     11
        / \   \
       3  -2   1
    ========================================================== */
    public static void main(String[] args) {
        Node root = n(10,
                n(5,
                        n(3, n(3), n(-2)),
                        n(2, null, n(1))),
                n(-3, null, n(11)));

        /* 1) 所有根→葉路徑 */
        System.out.println("所有根→葉路徑：");
        for (List<Integer> p : allRootToLeafPaths(root)) {
            System.out.println(p);
        }

        /* 2) 是否存在和 = 18 的根→葉路徑 */
        System.out.println("\n存在和為 18 路徑？ " + hasPathSum(root, 18)); // true (10,5,3)

        /* 3) 最大根→葉路徑 */
        List<Integer> maxPath = maxRootToLeafPath(root);
        int maxSum = maxPath.stream().mapToInt(i -> i).sum();
        System.out.println("\n最大根→葉路徑 = " + maxPath + " (sum=" + maxSum + ")");

        /* 4) 任意兩節點最大路徑和 */
        System.out.println("\n任意兩節點最大路徑和 = " + maxPathSumAnyTwoNodes(root)); // 21
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
}
