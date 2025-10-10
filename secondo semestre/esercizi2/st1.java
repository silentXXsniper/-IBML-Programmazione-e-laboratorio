

public class st1
{
// public static long st1(int n, int k){
    
 //   long[][]pippo = new long [n+1][k+1];
 //   pippo [0][0] = 1; 
 //   for(int i=0; i<=n; i=i+1){
 //        for(int j=0; j<=k; j=j+1)
 //       pippo[i][j] = 1;
///    }
//}    

private static final long UNKNOWN = -1;
    
// Altra versione che applica una tecnica di programmazione dinamica:
public static long stirling(int n, int k) {
    long[][] dp = new long[n+1][k+1];

    // Base case
    dp[0][0] = 1;

    // Fill DP table
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= k; j++) {
            dp[i][j] = j * dp[i-1][j] + dp[i-1][j-1];
        }
    }

    return dp[n][k];
}
}
