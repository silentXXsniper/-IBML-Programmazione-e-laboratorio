
public class BottomUpLIS {

    // Length of Longest Increasing Subsequence (LLIS):
    // Programmazione dinamica bottom-up

    // new int[]{2,7,5,7,4} --> exp {2,5,7}

    /** 1. Applicazione della tecnica di programmazione dinamica bottom-up - FATTO */
    public static int llisDP( int[] s ) {

        int n = s.length;

        int[][] mem = new int[ n+1 ][ n+1 ];

        // Matrice: valori delle ricorsioni di llisRec
        // relativi a diversi valori degli argomenti

        for ( int j=0; j<=n; j=j+1 ) {

            // --------------------------------------------------
            //  Inserisci qui i comandi per registrare i valori
            //  corrispondenti ai casi base della ricorsione
            // --------------------------------------------------

            mem[n][j] = 0;
        }

        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {

                // ------------------------------------------------
                //  Inserisci qui le strutture di controllo
                //  appropriate e i comandi per registrare
                //  i valori corrispondenti ai casi ricorsivi
                // ------------------------------------------------

                int t = ( j==n ? 0 : s[j]);

                if( s[i]<=t )
                    mem[i][j] = mem[i+1][j];
                else 
                {
                    mem[i][j] = Math.max(
                        1+mem[i+1][i],
                        mem[i+1][j]
                    );
                }                       
            }}

        // ----------------------------------------------------
        //  Inserisci di seguito l'elemento della matrice
        //  il cui valore corrisponde a llis(s) :

        return mem[0][n];

        // ----------------------------------------------------
    }

    // Longest Increasing Subsequence (LIS):
    // Programmazione dinamica bottom-up
    /** 2. Ricostruzione di una sottosequenza crescente più lunga seguendo un percorso 
     * * * attraverso la matrice - FATTO */
    public static int[] lisDP( int[] s ) {

        int n = s.length;

        int[][] mem = new int[ n+1 ][ n+1 ];

        // 1. Matrice: valori delle ricorsioni di llisRec
        //    calcolati esattamente come per llisDP

        // ------------------------------------------------
        //  Replica qui il codice del corpo di llisDP
        //  che registra nella matrice i valori
        //  corrispondenti alle ricorsioni di llisRec
        // ------------------------------------------------

        for ( int j=0; j<=n; j=j+1 ) {
            mem[n][j] = 0;
        }

        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {
                int t = ( j==n ? 0 : s[j]);

                if( s[i]<=t )
                    mem[i][j] = mem[i+1][j];
                else 
                {
                    mem[i][j] = Math.max(
                        1+mem[i+1][i],
                        mem[i+1][j]
                    );
                }                       
            }}

        // 2. Cammino attraverso la matrice per ricostruire
        //    un esempio di Longest Increasing Subsequence

        // ----------------------------------------------------
        //  Inserisci di seguito l'elemento della matrice
        //  il cui valore corrisponde a llis(s) :

        int m = mem[0][n]; /* elemento appropriato della matrice */;

        // ----------------------------------------------------

        int[] r = new int[ m ];  // per rappresentare una possibile LIS

        // ----------------------------------------------------
        //  Introduci e inizializza qui gli indici utili
        //  per seguire un cammino attraverso la matrice e
        //  per assegnare gli elementi della sottosequenza r
        // ----------------------------------------------------
        int i = 0;
        int j = n;
        int k = 0;

        while ( mem[i][j] > 0 ) {
            System.out.println("while #"+i);
            int t = ( j == n ) ? 0 : s[j];

            // --------------------------------------------------
            //  Inserisci qui strutture di controllo e comandi
            //  per scegliere e seguire un percorso appropriato
            //  attraverso la matrice in modo da ricostruire in
            //  r una possibile LIS relativa alla sequenza s
            // --------------------------------------------------

            // new int[]{2,7,5,7,4} --> 2,5,7 OK
            // new int[] {47, 38, 39, 25, 44} --> 38,39,44 OK
            // new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6} --> 7,29,49,53 OK

            if(1+mem[i+1][i] > mem[i+1][j])
            {
                if(t < s[i])
                {
                    System.out.println(" k [0, "+(m-1)+"] = "+k+"\n i [0, "+(n-1)+"] = "+i);
                    r[k] = s[i];
                    k = k+1;
                    j = i;
                }
            }

            i = i+1;
        }

        return r;                // = LIS relativa alla sequenza s
    }
}  // class BottomUpLIS
