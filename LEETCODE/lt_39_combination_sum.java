// 題目：Combination Sum
// 給定一組不重複的整數 candidates 與目標 target，
// 找出所有可以使數字和等於 target 的組合（數字可重複）。

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) return;

        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            backtrack(res, path, candidates, target - candidates[i], i);
            path.remove(path.size() - 1);
        }
    }
}

/*
解題思路：
1. 使用回溯法。
2. 每次選擇一個數字，遞迴減去 target。
3. 當 target == 0，加入結果。
4. 可以重複使用數字，所以遞迴傳入 i（而不是 i+1）。
*/
