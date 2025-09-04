// 題目：Combination Sum II
// candidates 可能有重複，每個數字只能用一次。
// 找出所有不重複組合，使得和等於 target。

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
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
            if (i > start && candidates[i] == candidates[i - 1]) continue; // 避免重複
            path.add(candidates[i]);
            backtrack(res, path, candidates, target - candidates[i], i + 1);
            path.remove(path.size() - 1);
        }
    }
}

/*
解題思路：
1. 與 Combination Sum 類似，但每個數字只能用一次。
2. 先排序，避免重複組合。
3. 遞迴時從 i+1 開始，確保每個數字只使用一次。
4. 若相同數字已經用過，跳過（i > start && nums[i]==nums[i-1]）。
*/
