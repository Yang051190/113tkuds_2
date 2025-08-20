
import java.io.*;
import java.util.*;

/**
 * 紅黑樹性質檢查（層序，-1 表 null；color 為 B/R，null 視為黑） 檢查順序： 1) RootNotBlack 2)
 * RedRedViolation at index i （i 為 0-based 陣列索引，先遇到者先回報） 3) BlackHeightMismatch
 * 皆通過則輸出：RB Valid
 */
public class M10_RBPropertiesCheck {

    static int n;
    static int[] val;
    static char[] col; // 'B' or 'R'（對於 val==-1 一律視為 'B'）

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());

        // 讀入 2n 個 token：val1 color1 val2 color2 ...
        ArrayList<String> tokens = new ArrayList<>(2 * n);
        while (tokens.size() < 2 * n) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                tokens.add(st.nextToken());
            }
        }
        val = new int[n];
        col = new char[n];
        for (int i = 0, t = 0; i < n; i++) {
            int v = Integer.parseInt(tokens.get(t++));
            char c = tokens.get(t++).toUpperCase().charAt(0);
            val[i] = v;
            col[i] = (v == -1) ? 'B' : (c == 'R' ? 'R' : 'B'); // null 一律黑
        }

        // 1) 根為黑（空樹視為黑）
        if (n > 0 && val[0] != -1 && col[0] != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 2) 紅紅相鄰（按索引由小到大檢查，先遇到先回報）
        int badIdx = firstRedRedViolation();
        if (badIdx != -1) {
            System.out.println("RedRedViolation at index " + badIdx);
            return;
        }

        // 3) 黑高度一致（從根做後序，-1 表不合法；空樹 OK）
        int bh = blackHeight(0);
        if (bh == -1) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    // 找第一個紅紅相鄰違規的索引（0-based）；沒有則回傳 -1
    private static int firstRedRedViolation() {
        for (int i = 0; i < n; i++) {
            if (i >= n || val[i] == -1) {
                continue;
            }
            if (col[i] == 'R') {
                int li = 2 * i + 1, ri = 2 * i + 2;
                if (li < n && val[li] != -1 && col[li] == 'R') {
                    return i;
                }
                if (ri < n && val[ri] != -1 && col[ri] == 'R') {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 回傳以 index 為根的「黑高度」；若任一路徑黑高度不一致則回傳 -1。 規則：NIL（越界或 val==-1）黑高度為 1；非空節點若為黑
     * +1。
     */
    private static int blackHeight(int idx) {
        if (idx >= n || val[idx] == -1) {
            return 1; // NIL 為黑
        }
        int li = 2 * idx + 1, ri = 2 * idx + 2;
        int lh = blackHeight(li);
        if (lh == -1) {
            return -1;
        }
        int rh = blackHeight(ri);
        if (rh == -1) {
            return -1;
        }

        if (lh != rh) {
            return -1; // 左右路徑黑高度不一致
        }
        return lh + (col[idx] == 'B' ? 1 : 0);
    }
}
