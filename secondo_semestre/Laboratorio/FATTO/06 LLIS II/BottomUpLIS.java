import java.util.ArrayList;
import java.util.List;
public class BottomUpLIS {
    // Metodo per calcolare la lunghezza della LIS (approccio bottom-up)
    public static int llisDP(int[] s) {
        int n = s.length;
        int[][] dp = new int[n + 1][n + 1]; // dp[j][i]: t = s[j] o 0 se j == n
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= n; j++) {
                int t = (j == n) ? 0 : s[j];
                if (s[i] <= t) {
                    dp[j][i] = dp[j][i + 1]; // s[i] non può essere preso
                } else {
                    int pick = 1 + dp[i][i + 1]; // scegli s[i]
                    int skip = dp[j][i + 1];     // salta s[i]
                    dp[j][i] = Math.max(pick, skip);
                }
            }
        }
        return dp[n][0]; // partenza: i=0, t=0 ⇒ j=n
    }
    // Metodo per ricostruire una LIS
    public static List<Integer> lisDP(int[] s) {
        int n = s.length;
        int[][] dp = new int[n + 1][n + 1];
        // Calcola dp come in llisDP
        for (int i = n - 1; i >= 0; i--) {//conviene i al contrario
            for (int j = 0; j <= n; j++) {//mentre j convenzionale
                int t = (j == n) ? 0 : s[j];
                if (s[i] <= t) {
                    dp[j][i] = dp[j][i + 1];
                } else {
                    int pick = 1 + dp[i][i + 1];
                    int skip = dp[j][i + 1];
                    dp[j][i] = Math.max(pick, skip);
                }
            }
        }
        // Ricostruzione LIS
        List<Integer> lis = new ArrayList<>();
        int i = 0, j = n; // t = 0 all'inizio
        while (i < n) {
            int t = (j == n) ? 0 : s[j];
            if (s[i] <= t) {
                i++; // non si può scegliere s[i]
            } else {
                int pick = 1 + dp[i][i + 1];
                int skip = dp[j][i + 1];
                if (pick >= skip) {
                    lis.add(s[i]);
                    j = i;
                }
                i++;
            }
        }
        return lis;
    }
    // Metodo main per testare il programma
    public static void main(String[] args) {
        int[] s = {2, 7, 5, 7, 4};
        int length = llisDP(s);
        List<Integer> sequence = lisDP(s);
        System.out.println("Lunghezza LIS: " + length);
        System.out.print("Una LIS: ");
        for (int num : sequence){
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
