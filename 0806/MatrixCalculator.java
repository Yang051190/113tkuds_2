
import java.util.Arrays;

/**
 * 矩陣運算器 功能： 1. add ─ 矩陣加法 2. multiply ─ 矩陣乘法 3. transpose ─ 轉置 4. maxMin ─ 找最大值
 * / 最小值 5. main() ─ Demo 測試
 */
public class MatrixCalculator {

    /* ---------- 1) 矩陣加法 ---------- */
    public static int[][] add(int[][] a, int[][] b) {
        int n = a.length, m = a[0].length;
        int[][] c = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    /* ---------- 2) 矩陣乘法 ---------- */
    public static int[][] multiply(int[][] a, int[][] b) {
        int n = a.length;          // a 的列數
        int k = a[0].length;       // a 的行數 / b 的列數
        int m = b[0].length;       // b 的行數
        int[][] c = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int t = 0; t < k; t++) {
                    c[i][j] += a[i][t] * b[t][j];
                }
            }
        }
        return c;
    }

    /* ---------- 3) 矩陣轉置 ---------- */
    public static int[][] transpose(int[][] a) {
        int n = a.length, m = a[0].length;
        int[][] t = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                t[j][i] = a[i][j];
            }
        }
        return t;
    }

    /* ---------- 4) 找最大值 / 最小值 ---------- */
    public static int[] maxMin(int[][] a) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int[] row : a) {
            for (int v : row) {
                if (v > max) {
                    max = v;
                }
                if (v < min) {
                    min = v;
                }
            }
        }
        return new int[]{max, min};   // [0]=max, [1]=min
    }

    /* ---------- 工具：列印矩陣 ---------- */
    private static void print(int[][] m) {
        for (int[] row : m) {
            System.out.println(Arrays.toString(row));
        }
    }

    /* ---------- 5) Demo ---------- */
    public static void main(String[] args) {

        int[][] m1 = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int[][] m2 = {
            {7, 8, 9},
            {10, 11, 12}
        };

        System.out.println("=== 加法 m1 + m2 ===");
        print(add(m1, m2));

        /* 為了示範乘法，準備 2×3 乘 3×2 */
        int[][] a = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int[][] b = {
            {7, 8},
            {9, 10},
            {11, 12}
        };
        System.out.println("\n=== 乘法 a × b ===");
        print(multiply(a, b));

        System.out.println("\n=== m1 的轉置 ===");
        print(transpose(m1));

        int[] mm = maxMin(b);
        System.out.printf("%n矩陣 b 的最大值 = %d，最小值 = %d%n", mm[0], mm[1]);
    }
}
