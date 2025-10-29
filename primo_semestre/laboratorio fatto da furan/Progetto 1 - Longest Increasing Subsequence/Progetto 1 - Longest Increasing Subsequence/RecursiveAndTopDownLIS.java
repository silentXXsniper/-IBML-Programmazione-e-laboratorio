
public class RecursiveAndTopDownLIS
{
    public static int llis( int[] s ) 
    { // s[i] > 0 per i in [0,n-1], dove n = s.length
        return llisRec( s, 0, 0 );
    }

    private static int llisRec( int[] s, int i, int t ) 
    {
        if ( i == s.length ) { // i = n : coda di s vuota
            return 0;
        } else if ( s[i] <= t ) { // x = s[i] ≤ t : x non può essere scelto
            return llisRec( s, i+1, t );
        } else { // x > t : x può essere scelto o meno
            return Math.max( 1+llisRec(s,i+1,s[i]), llisRec(s,i+1,t) );
        }
    }

    /** 1. Applicazione della tecnica top-down in situazioni semplificate - FATTO (VERAMENTE) */
    public static int llisTopDownEZ( int[] s ) 
    {
        int n = s.length;
        int[][] mem = new int[n+1][n+1];
        return llisTopDownRecEZ( s, 0, 0, mem);
    }

    private static int llisTopDownRecEZ( int[] s, int i, int t, int[][] mem)
    { // 0 < s[i] <= n per i in [0,n-1], dove n = s.length
        int n = s.length;

        // array di test: new int[] {1,2,3,2,2,4,6}; expected --> 5 OK
        //                new int[] {7, 8, 9, 10, 4, 5, 6, 2, 3, 1} expected --> 4 OK
        //                new int[] {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3} exp --> 5 OK
        //                new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 6} exp --> 6 OK

        if(mem[i][t] == 0)
        {
            if( i == s.length)
                mem[i][t] = 0;
            else if(s[i] <= t)
                mem[i][t] = llisTopDownRecEZ(s, i+1, t, mem);
            else
                mem[i][t] = Math.max(1+llisTopDownRecEZ(s, i+1, s[i], mem), llisTopDownRecEZ(s, i+1, t, mem));
        }

        return mem[i][t];
    }

    /** 2. Applicazione della tecnica top-down in situazioni generali - FATTO */
    public static int llisTopDown( int[] s ) 
    {
        int n = s.length;
        int[][] mem = new int[n+1][n+1];
        return llisTopDownRec( s, 0, n, mem);
    }

    private static int llisTopDownRec( int[] s, int i, int j, int[][] mem)
    { // 0 < s[i] per i in [0,n-1], dove n = s.length
        // j è un indice che in s identifica il valore prima indicato come t
        // 0 <= j <= n quindi no OutOfBoundsException

        // array di test
        //  1   new int[] {5, 4, 3, 2, 1} --> 1 OK
        //  2   new int[] {2, 7, 5, 7, 4} --> 3 OK
        //  3   new int[] {47, 38, 39, 25, 44} --> 3 OK
        //  4   new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6} --> 4 OK

        int n = s.length;
        int t = (j == n ? 0 : s[j]);

        if(mem[i][j] == 0)
        {
            if( i == s.length )
                mem[i][j] = 0;
            else if( s[i] <= t )
                mem[i][j] = llisTopDownRec(s, i+1, j, mem);
            else
                mem[i][j] = Math.max(
                                    1+llisTopDownRec(s, i+1, i, mem), 
                                    llisTopDownRec(s, i+1, j, mem)
                                    );
        }

        return mem[i][j];
    }
}
