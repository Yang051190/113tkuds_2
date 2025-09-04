// 題目：Sudoku Solver
// 將部分填充的 9x9 數獨解出。

class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (backtrack(board)) return true;
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c || board[i][col] == c) return false;
            if (board[3*(row/3)+i/3][3*(col/3)+i%3] == c) return false;
        }
        return true;
    }
}

/*
解題思路：
1. 使用回溯法。
2. 遍歷每個空格，嘗試放 1~9。
3. 若符合 row、col、3x3 區域規則，繼續遞迴。
4. 若成功填滿，返回 true。
5. 時間複雜度高，但數獨範圍有限可接受。
*/
