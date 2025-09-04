// 題目 20 (II)：防災物資組合總和 II
// 檔名：LC40_CombinationSum2_Procurement.java
// 說明：輸入一組候選物資價格與 target，找出所有組合，每個數字只能用一次，需去重。

import java.util.*;

public class LC40_CombinationSum2_Procurement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        Arrays.sort(candidates);

        List<List<Integer>> res = combinationSum2(candidates, target);
        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                System.out.print(comb.get(i));
                if (i < comb.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue; // 去重

                        }path.add(candidates[i]);
            backtrack(res, path, candidates, target - candidates[i], i + 1); // 每個數字只能用一次
            path.remove(path.size() - 1);
        }
    }
}
