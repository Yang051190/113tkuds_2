
import java.util.*;

/**
 * 練習 2.5 ─ 遞迴樹狀結構預習 ----------------------------------------------------------
 * 1) countFiles : 遞迴計算資料夾的檔案總數 2) printMenu : 遞迴列印多層選單 3) flattenArray :
 * 遞迴展平成一維陣列 4) maxDepth : 遞迴計算巢狀清單最大深度
 */
public class RecursiveTreePreview {

    /* ======================================================
       1) 模擬檔案系統：FileNode
       ====================================================== */
    static class FileNode {

        String name;
        boolean isFile;
        List<FileNode> children = new ArrayList<>();

        FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
        }

        FileNode add(FileNode child) {
            children.add(child);
            return this;
        }
    }

    /**
     * 遞迴計算檔案數
     */
    public static int countFiles(FileNode node) {
        if (node.isFile) {
            return 1;
        }
        int sum = 0;
        for (FileNode ch : node.children) {
            sum += countFiles(ch);
        }
        return sum;
    }

    /* ======================================================
       2) 多層選單：MenuItem
       ====================================================== */
    static class MenuItem {

        String label;
        List<MenuItem> sub = new ArrayList<>();

        MenuItem(String label) {
            this.label = label;
        }

        MenuItem add(MenuItem m) {
            sub.add(m);
            return this;
        }
    }

    /**
     * 遞迴列印選單
     */
    public static void printMenu(MenuItem m) {
        printMenu(m, 0);
    }

    private static void printMenu(MenuItem m, int indent) {
        System.out.printf("%s- %s%n", "  ".repeat(indent), m.label);
        for (MenuItem s : m.sub) {
            printMenu(s, indent + 1);
        }
    }

    /* ======================================================
       3) 巢狀陣列展平
       ====================================================== */
    /**
     * flattenArray([1,[2,[3,4]],5]) → [1,2,3,4,5]
     */
    public static List<Object> flattenArray(Object[] arr) {
        List<Object> res = new ArrayList<>();
        for (Object o : arr) {
            if (o instanceof Object[]) {
                res.addAll(flattenArray((Object[]) o)); 
            }else {
                res.add(o);
            }
        }
        return res;
    }

    /* ======================================================
       4) 巢狀清單最大深度
       ====================================================== */
    /**
     * maxDepth([1,[2,[3]],4]) → 3
     */
    public static int maxDepth(List<?> lst) {
        int depth = 1;                       // 自身層
        for (Object o : lst) {
            if (o instanceof List) {
                depth = Math.max(depth, 1 + maxDepth((List<?>) o));
            }
        }
        return depth;
    }

    /* ======================================================
       Demo
       ====================================================== */
    public static void main(String[] args) {

        /* ---------- 1) FileNode demo ---------- */
        FileNode root = new FileNode("root", false)
                .add(new FileNode("fileA.txt", true))
                .add(new FileNode("sub", false)
                        .add(new FileNode("fileB.txt", true))
                        .add(new FileNode("deep", false)
                                .add(new FileNode("fileC.txt", true))));
        System.out.println("檔案總數 = " + countFiles(root));   // 3

        /* ---------- 2) Menu demo ---------- */
        MenuItem menu = new MenuItem("首頁")
                .add(new MenuItem("產品")
                        .add(new MenuItem("手機"))
                        .add(new MenuItem("筆電")))
                .add(new MenuItem("關於我們")
                        .add(new MenuItem("團隊"))
                        .add(new MenuItem("歷史")));
        System.out.println("\n--- 選單 ---");
        printMenu(menu);

        /* ---------- 3) flattenArray demo ---------- */
        Object[] nested = {1, new Object[]{2, new Object[]{3, 4}}, 5};
        System.out.println("\n展平結果 = " + flattenArray(nested)); // [1,2,3,4,5]

        /* ---------- 4) maxDepth demo ---------- */
        List<?> nestedList = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, 4)), 5);
        System.out.println("\n最大深度 = " + maxDepth(nestedList)); // 3
    }
}
