// 題目：Integer to Roman
// 將整數轉換為羅馬數字，範圍 1 <= num <= 3999。

class Solution {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }
}

/*
解題思路：
1. 羅馬數字由大到小組成，特殊情況用減法表示，例如 900 = CM。
2. 建立數值與符號對照表，從大到小依序減去數字，並加上對應符號。
3. 重複直到 num 減為 0。
4. 時間複雜度 O(1)，因為最大值固定為 3999。
5. 空間複雜度 O(1)。
*/
