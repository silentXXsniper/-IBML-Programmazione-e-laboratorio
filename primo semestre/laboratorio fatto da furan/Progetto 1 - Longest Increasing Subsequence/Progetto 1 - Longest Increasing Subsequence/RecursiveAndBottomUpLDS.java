public class RecursiveAndBottomUpLDS
{
    // new int[]{2,7,5,7,4} exp --> 7,5,4 quindi 3 OK
    /** 1. Sottosequenza decrescente più lunga - FATTO */
    public static int llds( int[] s ) 
    { // s[i] > 0 per i in [0,n-1], dove n = s.length
        return lldsRec( s, 0, getRoof(s) );
        // uso t come indice per non dover usare la procedura getRoof
    }

    private static int lldsRec( int[] s, int i, int t )
    {
        if ( i == s.length ) { // i = n : coda di s vuota
            return 0;
        } else if ( s[i] >= t ) {
            return lldsRec( s, i+1, t );
        } else { // x < t : x può essere scelto o meno
            return Math.max( 1+lldsRec(s, i+1, s[i]), lldsRec(s, i+1, t) );
        }
    }

    /*
     * s : sequenza di numeri
     * i : indice che scorre la sequenza       [0 all'inizio]
     * j : indice che punta al numero maggiore [0 all'inizio]
     * 
     * val : valore maggiore della sequenza + 1; (+1 così che confrontandolo con se stesso nel programma ricorsivo questo venga contato)
     */
    private static int getRoof( int[] s )
    {
        return getRoofSupp(s, 0, 0);
    }
    
    private static int getRoofSupp( int[] s, int i, int j )
    {
        if( i == s.length)
            return s[j]+1;
        else if( s[j] < s[i] )
            return getRoofSupp(s, i+1, i);
        else
            return getRoofSupp(s, i+1, j);
    }

    /** 2.1. Applicazione della tecnica di programmazione dinamica bottom-up (llds) - FATTA */
    // new int[]{2,7,5,7,4} exp --> 7,5,4 quindi 3 OK
    // new int[] {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3} -->  8,4,1 quindi 3 OK
    // new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 6} --> 2 OK
    // new int[] {1, 2, 3, 2, 2, 4, 6} --> OK
    // new int[] {7, 8, 9, 10, 4, 5, 6, 2, 3, 1} --> 4 OK
    public static int lldsDP( int[] s ) {

        int n = s.length;

        int[][] mem = new int[ n+1 ][ n+1 ];

        // Matrice: valori delle ricorsioni di llisRec
        // relativi a diversi valori degli argomenti

        for ( int j=0; j<=n; j=j+1 ) {

            // --------------------------------------------------
            //  Inserisci qui i comandi per registrare i valori
            //  corrispondenti ai casi base della ricorsione
            // --------------------------------------------------

            mem[n][j] = 0; /** ? */
        }

        int max = 0;
        for( int i = 0; i < n; i++ )
        {
            max = (s[i] > max ? s[i] : max );
        }
        max = max+1;
        
        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {

                // ------------------------------------------------
                //  Inserisci qui le strutture di controllo
                //  appropriate e i comandi per registrare
                //  i valori corrispondenti ai casi ricorsivi
                // ------------------------------------------------

                int t = ( j==n ? max : s[j]);

                if( s[i] >= t )
                    mem[i][j] = mem[i+1][j];
                else 
                {
                    mem[i][j] = Math.max(
                        1+mem[i+1][i],
                        mem[i+1][j]
                    );
                }                       
            }
        }

        // ----------------------------------------------------
        //  Inserisci di seguito l'elemento della matrice
        //  il cui valore corrisponde a llis(s) :

        return mem[0][n];

        // ----------------------------------------------------
    }
    
    // new int[]{2,7,5,7,4} exp --> 7,5,4 quindi 3 OK
    // new int[] {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3} -->  8,4,1 quindi 3 OK
    // new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 6} --> 2 OK
    // new int[] {1, 2, 3, 2, 2, 4, 6} --> OK
    // new int[] {7, 8, 9, 10, 4, 5, 6, 2, 3, 1} --> 4 OK
    
    /** 2. Applicazione della tecnica di programmazione dinamica bottom-up (lds) - FATTA */
    public static int[] ldsDP( int[] s ) {

        int n = s.length;

        int[][] mem = new int[ n+1 ][ n+1 ];

        // Matrice: valori delle ricorsioni di llisRec
        // relativi a diversi valori degli argomenti

        for ( int j=0; j<=n; j=j+1 ) {

            // --------------------------------------------------
            //  Inserisci qui i comandi per registrare i valori
            //  corrispondenti ai casi base della ricorsione
            // --------------------------------------------------

            mem[n][j] = 0; /** ? */
        }

        int max = 0;
        for( int i = 0; i < n; i++ )
        {
            max = (s[i] > max ? s[i] : max );
        }
        max = max+1;
        
        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {

                // ------------------------------------------------
                //  Inserisci qui le strutture di controllo
                //  appropriate e i comandi per registrare
                //  i valori corrispondenti ai casi ricorsivi
                // ------------------------------------------------

                int t = ( j==n ? max : s[j]);

                if( s[i] >= t )
                    mem[i][j] = mem[i+1][j];
                else 
                {
                    mem[i][j] = Math.max(
                        1+mem[i+1][i],
                        mem[i+1][j]
                    );
                }                       
            }
        }

        // ----------------------------------------------------
        //  Inserisci di seguito l'elemento della matrice
        //  il cui valore corrisponde a llis(s) :

        int m = mem[0][n];

        // ----------------------------------------------------
        
        int[] r = new int[m];
        
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
            int t = ( j == n ) ? max : s[j];

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
                if(t > s[i])
                {
                    //System.out.println(" k [0, "+(m-1)+"] = "+k+"\n i [0, "+(n-1)+"] = "+i);
                    r[k] = s[i];
                    k = k+1;
                    j = i;
                }
            }
                
            i = i+1;
        }

        return r;
    }
}
