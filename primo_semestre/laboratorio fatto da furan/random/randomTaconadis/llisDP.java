public class llisDP {

    // Length of Longest Increasing Subsequence (LLIS):
    // Programmazione dinamica bottom-up

    //private static final int UNKNOWN = -1;
    public static int llisDP( int[] s ) {
        int n = s.length;
        int[][] mem = new int[ n+1 ][ n+1 ];
        /*for(int i = 0; i < n+1; i = i + 1) {
        for(int k = 0; k < n+1; k = k + 1) {
        mem[i][k] = UNKNOWN;         }     } */
        for ( int j=0; j<=n; j=j+1 ) {
            mem[j][n] = 0;     }
            
        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {
                int t;
                if(j == n) {
                    t = 0;
                } else {
                    t = s[j];         
                }

                if ( s[i] <= t ) { // x = s[i] ≤ t : x non può essere scelto
                    mem[j][i] = mem[j][i+1];             
                } else { // x > t : x può essere scelto o meno 
                    mem[j][i] = Math.max( 1 + mem[i][i+1],mem[j][i+1] );             
                }              
            }
        }
        return  mem[n][0];
    }


    // Longest Increasing Subsequence (LIS):
    // Programmazione dinamica bottom-up

    public static int[] lisDP( int[] s ) {
        
        int n = s.length;
        int[][] mem = new int[ n+1 ][ n+1 ];
        
        for ( int j=0; j<=n; j=j+1 ) {
            mem[j][n] = 0;
        }
        
        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {
                int t;
                if(j == n) {
                    t = 0;             
                } 
                else {
                    t = s[j];         
                }

                if ( s[i] <= t ) { // x = s[i] ≤ t : x non può essere scelto
                    mem[j][i] = mem[j][i+1];             
                } 
                else { // x > t : x può essere scelto o meno 
                    mem[j][i] = Math.max( 1 + mem[i][i+1],mem[j][i+1]  );             
                }
            }
        }
        
        int m =  mem[n][0];
        
        // ----------------------------------------------------

        int[] r = new int[ m ];  // per rappresentare una possibile LIS

        // ----------------------------------------------------
        //  Introduci e inizializza qui gli indici utili
        //  per seguire un cammino attraverso la matrice e
        //  per assegnare gli elementi della sottosequenza r
        // ----------------------------------------------------
        int i = n;
        int j = 0;
        int c = 0;
        
        while ( mem[i][j] > 0 ) {
            int t = ( j == n ) ? 0 : s[j];
            
            // --------------------------------------------------
            //  Inserisci qui strutture di controllo e comandi
            //  per scegliere e seguire un percorso appropriato
            //  attraverso la matrice in modo da ricostruire in
            //  r una possibile LIS relativa alla sequenza s
            // --------------------------------------------------
            int x = mem[j][j+1];
            int y = mem[i][j+1] + 1;
            
            if(y > x && mem[i][j] > t)
            {
                r[c] = t;
                i = j;
            }
            j++;
            c++;
        }

        return r;                // = LIS relativa alla sequenza s
    }


}  // class BottomUpLIS