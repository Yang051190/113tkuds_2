
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M06_PalindromeClean {

    private static boolean isAsciiLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();              // 讀一整行（長度 ≤ 1e5）
        if (s == null) {
            System.out.println("Yes");
            return;
        }

        int i = 0, j = s.length() - 1;
        while (i < j) {
            // 跳過非英文字母
            while (i < j && !isAsciiLetter(s.charAt(i))) {
                i++;
            }
            while (i < j && !isAsciiLetter(s.charAt(j))) {
                j--;
            }

            if (i >= j) {
                break;
            }

            char a = s.charAt(i), b = s.charAt(j);
            // 忽略大小寫
            if (a >= 'A' && a <= 'Z') {
                a = (char) (a - 'A' + 'a');
            }
            if (b >= 'A' && b <= 'Z') {
                b = (char) (b - 'A' + 'a');
            }

            if (a != b) {
                System.out.println("No");
                return;
            }
            i++;
            j--;
        }
        System.out.println("Yes");
    }
}
