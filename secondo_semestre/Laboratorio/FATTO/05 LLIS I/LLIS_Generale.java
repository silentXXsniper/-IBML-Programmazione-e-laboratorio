public class LLIS_Generale {
    public static int llis(int[] s) {
        int n = s.length;
        int[][] memo = new int[n + 1][n + 1]; // j ∈ [0, n] per rappresentare t
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++)
                memo[i][j] = -1;
        return llisRec(s, 0, n, memo); // j = n rappresenta t = 0
    }
    private static int llisRec(int[] s, int i, int j, int[][] memo) {
        if (i == s.length)
            return 0;
        if (memo[i][j] != -1)
            return memo[i][j];

        int t = (j == s.length) ? 0 : s[j];
        int result;
        if (s[i] <= t) {
            result = llisRec(s, i + 1, j, memo);
        } else {
            result = Math.max(
                1 + llisRec(s, i + 1, i, memo), // scegliamo s[i], quindi j = i
                llisRec(s, i + 1, j, memo)      // saltiamo s[i], j resta invariato
            );
        }
        memo[i][j] = result;
        return result;
    }
}
