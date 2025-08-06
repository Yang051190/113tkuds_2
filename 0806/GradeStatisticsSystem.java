
/**
 * 成績統計系統
 * 功能：
 *  1. 平均、最高、最低
 *  2. 各等第(A~F)人數
 *  3. 高於平均的人數
 *  4. 列印報表
 */
public class GradeStatisticsSystem {

    // A=0, B=1, C=2, D=3, F=4
    private static int gradeIndex(int score) {
        if (score >= 90) {
            return 0;   // A

        }
        if (score >= 80) {
            return 1;   // B

        }
        if (score >= 70) {
            return 2;   // C

        }
        if (score >= 60) {
            return 3;   // D

        }
        return 4;                    // F
    }

    public static void main(String[] args) {
        // 測試資料
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        /* ---------- 1) 平均、最高、最低 ---------- */
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int s : scores) {
            sum += s;
            if (s > max) {
                max = s;
            }
            if (s < min) {
                min = s;
            }
        }
        double avg = (double) sum / scores.length;

        /* ---------- 2) 各等第人數 ---------- */
        int[] gradeCnt = new int[5];             // A~F
        for (int s : scores) {
            gradeCnt[gradeIndex(s)]++;
        }

        /* ---------- 3) 高於平均人數 ---------- */
        int aboveAvg = 0;
        for (int s : scores) {
            if (s > avg) {
                aboveAvg++;
            }
        }

        /* ---------- 4) 列印報表 ---------- */
        System.out.println("========== 成績報表 ==========");
        System.out.printf("平均分：%.1f\t最高分：%d\t最低分：%d%n", avg, max, min);
        System.out.printf("等第統計：A:%d  B:%d  C:%d  D:%d  F:%d%n",
                gradeCnt[0], gradeCnt[1], gradeCnt[2], gradeCnt[3], gradeCnt[4]);
        System.out.printf("高於平均分的人數：%d%n", aboveAvg);
    }
}
