// 題目：Valid Sudoku
// 判斷一個 9x9 的數獨是否有效。
// 數字 1-9 在每行、每列、每個 3x3 子方格中都不能重複。

import java.util.*;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    if (!seen.add(c + " in row " + i) ||
                        !seen.add(c + " in col " + j) ||
                        !seen.add(c + " in box " + i/3 + "-" + j/3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

/*
解題思路：
1. 使用 HashSet 記錄每個數字出現的位置：
   - row, col, box。
2. 若任意位置有重複，回傳 false。
3. 否則回傳 true。
*/
