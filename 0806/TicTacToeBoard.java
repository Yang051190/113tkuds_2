
/**
 * 井字棋棋盤
 * 0 = 空格，1 = X，2 = O
 */
public class TicTacToeBoard {

    private final int[][] board = new int[3][3];

    /* ---------- 1) 重置棋盤 ---------- */
    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
    }

    /* ---------- 2) 落子 ---------- */
    public boolean placeMark(int row, int col, int player) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        if (player != 1 && player != 2) {
            return false;
        }
        if (board[row][col] != 0) {
            return false;   // 已被佔

                }board[row][col] = player;
        return true;
    }

    /* ---------- 3) 判斷某玩家是否勝利 ---------- */
    public boolean checkWin(int p) {
        // 橫列 & 直行
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p) {
                return true;
            }
            if (board[0][i] == p && board[1][i] == p && board[2][i] == p) {
                return true;
            }
        }
        // 兩條對角
        return (board[0][0] == p && board[1][1] == p && board[2][2] == p)
                || (board[0][2] == p && board[1][1] == p && board[2][0] == p);
    }

    /* ---------- 4) 遊戲是否結束 ---------- */
    public boolean isGameOver() {
        if (checkWin(1) || checkWin(2)) {
            return true;
        }
        // 檢查是否還有空格
        for (int[] row : board) {
            for (int v : row) {
                if (v == 0) {
                    return false;
                }
            }
        }
        return true;   // 沒空格 = 平手
    }

    /* ---------- 5) 印出棋盤（方便除錯） ---------- */
    public void show() {
        for (int[] row : board) {
            for (int v : row) {
                System.out.print(" XO".charAt(v) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /* ---------- 6) Demo ---------- */
    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();

        game.placeMark(0, 0, 1); // X
        game.placeMark(1, 1, 1); // X
        game.placeMark(0, 1, 2); // O
        game.placeMark(2, 2, 1); // X -> 形成對角線勝

        game.show();

        System.out.println("X 是否獲勝？ " + game.checkWin(1));
        System.out.println("遊戲是否結束？ " + game.isGameOver());
    }
}
