// Approccio imperativo: Tecniche di memoization e programmazione dinamica
import java.math.*;  // BigInteger

public class Memoization {
    /* Numeri di Fibonacci (tree recursion)
    (define fibonacci
    (lambda (n)      ; n naturale
    (if (< n 2)
    1
    (+ (fibonacci (- n 2)) (fibonacci (- n 1)))
    )
    ))
     */

    // Traduzione in Java:

    public static long fibonacci( int n ) {  // n >= 0
        if ( n < 2 ) 
            return  1;
        else 
            return  fibonacci( n-2 ) + fibonacci( n-1 );      
    }

    // Nuova versione che applica la tecnica di memoization:
    public static long fibonacciMem( int n ) {  // n >= 0
        long[] mem = new long[ n+1 ];
        for ( int i=0; i<=n; i=i+1 ) 
            mem[i] = UNKNOWN;       
        return fibonacciRec( n, mem );
    }

    private static long fibonacciRec( int n, long[] mem ) {
        if ( mem[n] == UNKNOWN ) {
            if ( n < 2 ) 
                mem[n] = 1;
            else 
                mem[n] = fibonacciRec( n-2, mem ) + fibonacciRec( n-1, mem );
        }
        return mem[n];
    }

    private static final long UNKNOWN = -1;

    // Superamento dei limiti delle rappresentazioni numeriche a parola fissa:
    // int:         risultato rappresentabile per n <= 45
    // long:        risultato rappresentabile per n <= 91
    // BigInteger:  rappresentazione intera equiparabile a quella di Scheme

    public static BigInteger fibonacciBig( int n ) {  // n >= 0
        BigInteger[] mem = new BigInteger[ n+1 ];
        for ( int i=0; i<=n; i=i+1 ) 
            mem[i] = BIG_UNKNOWN;    
        return fibonacciBigRec( n, mem );
    }

    private static BigInteger fibonacciBigRec( int n, BigInteger[] mem ) {
        if ( mem[n] == BIG_UNKNOWN ) {
            if ( n < 2 )
                mem[n] = new BigInteger( "1" );
            else 
                mem[n] = fibonacciBigRec( n-2, mem ).add( fibonacciBigRec(n-1,mem) );
        }
        return mem[n];
    }

    private static final BigInteger BIG_UNKNOWN = null;

    // Diversa versione che applica una tecnica di programmazione dinamica:
    public static long fibonacciDp( int n ) {  // n >= 0
        long[] mem = new long[ n+1 ];
        mem[0] = 1;
        if ( n > 0 ) 
            mem[1] = 1;       
        for ( int k=2; k<=n; k=k+1 ) 
            mem[k] = mem[k-2] + mem[k-1];      
        return mem[n];
    }

    /* Problema dei "Percorsi di Manhattan"
    (define manhattan
    (lambda (i j)    ; i, j naturali
    (if (or (= i 0) (= j 0))
    1
    (+ (manhattan (- i 1) j) (manhattan i (- j 1)))
    )
    ))
     */

    // Traduzione in Java:

    public static long manhattan( int i, int j ) {  // i, j >= 0
        if ( (i == 0) || (j == 0) ) 
            return  1;
        else 
            return  manhattan( i-1, j ) + manhattan( i, j-1 );   
    }

    // Nuova versione che applica la tecnica di memoization:
    public static long manhattanMem( int i, int j ) {  // i, j >= 0
        long[][] mem = new long[ i+1 ][ j+1 ];
        for ( int u=0; u<=i; u=u+1 ) {
            for ( int v=0; v<=j; v=v+1 ) 
                mem[u][v] = UNKNOWN;
        }
        return manhattanRec( i,j, mem );
    }

    private static long manhattanRec( int i, int j, long[][] mem ) {
        if ( mem[i][j] == UNKNOWN ) {
            if ( (i == 0) || (j == 0) ) 
                mem[i][j] = 1;
            else 
                mem[i][j] = manhattanRec( i-1, j, mem ) + manhattanRec( i, j-1, mem );
        }
        return mem[i][j];
    }

    // Altra versione che applica una tecnica di programmazione dinamica:
    public static long manhattanDp( int i, int j ) {  // i, j >= 0
        long[][] mem = new long[i+1][j+1];       // Economia di spazio di memoria
        for ( int y=0; y<=j; y=y+1 )     // manhattan(0,v), v in [0,j]
            mem[0][y] = 1;   
        for ( int x=0; x<=j; x=x+1 )     // manhattan(0,v), v in [0,j]
            mem[x][0] = 1;
        for ( int x=1; x<=i; x=x+1 ) {
            for ( int y=1; y<=j; y=y+1 )  // manhattan(u,v), v in [0,j]
                mem[x][y] = mem[x-1][y] + mem[x][y-1];
        }
        return mem[i][j];                    // manhattan(i,j)
    }

    //lscs
    /**
     * (define llcs    ;val: int
     *  (lambda (u,v)  ;u,v : stringhe
     *   (cond ((or(string=? u "")(string=? v ""))
     *         0
     *         )
     *         ((char=? (string-ref u 0)(string-ref v 0))
     *         (+ 1 (llcs (substring u 1)(substring v 1))))
     *         )
     *         (else
     *          (max (llcs (substring u 1) v)(llcs u (substring v 1))))
     *          )
     *         )
     *   ))
     */

    private static final int UNKNOWN_INT = -1;

    public static int llcs( String u, String v) {

        int m=u.length();
        int n=v.length();
        int[][] mem = new int[m+1][n+1];

        for ( int i=0; i<=m; i=i+1 ) {
            for ( int j=0; j<=n; j=j+1 ) 
                mem[i][j] = UNKNOWN_INT;
        }
        return llcsRec( u,v, mem );
    }

    private static int llcsRec( String u, String v, int[][] mem ) {
        int i=u.length();
        int j=v.length();
        if ( mem[i][j] == UNKNOWN ) {
            if ( (i == 0) || (j == 0) ) 
                mem[i][j] = 0;          
            else if(u.charAt(0)==v.charAt(0))
                mem[i][j]=1+llcsRec(u.substring(1),v.substring(1),mem);   
            else{
                mem[i][j] = Math.max(llcsRec(u.substring(1),v,mem),
                    llcsRec(u,v.substring(1),mem));
            }
        }
        return mem[i][j];
    }

    //lcs con le stringhe
    public static String lcs( String u, String v) {

        int m=u.length();
        int n=v.length();
        String[][] mem = new String[m+1][n+1];

        for ( int i=0; i<=m; i=i+1 ) {
            for ( int j=0; j<=n; j=j+1 ) 
                mem[i][j] = null;
        }
        return lcsRec( u,v, mem );
    }

    private static String lcsRec( String u, String v, String[][] mem ) {
        int i=u.length();
        int j=v.length();
        if ( mem[i][j] == null ) {
            if ( (i == 0) || (j == 0) ) 
                mem[i][j] = "";
            else if(u.charAt(0)==v.charAt(0))
                mem[i][j]=u.substring(0,1)+lcsRec(u.substring(1),v.substring(1),mem);
            else{
                mem[i][j] = longer(lcsRec(u.substring(1),v,mem),
                    lcsRec(u,v.substring(1),mem));
            }
        }
        return mem[i][j];
    }

    private static String longer(String u, String v){
        int m=u.length();
        int n=v.length();
        if(m<n)
            return v;
        else if (m>n)
            return u;
        else if(Math.random()<0.5)
            return v;
        else
            return u;
    }

    //llcsDP versione migliore
    public static String llcsDP( String u, String v) {

        int m=u.length();
        int n=v.length();
        int[][] mem = new int[m+1][n+1];

        for ( int j=0; j<=n; j++ ) 
            mem[0][j] =0;           
        for ( int i=1; i<=m; i++ )         
            mem[i][0] =0;                  
        for ( int i=1; i<=m; i++) {
            for ( int j=1; j<=n; j++ ) {
                if(u.charAt(m-i)==v.charAt(n-j))
                    mem[i][j] =1+mem[i-1][j-1];
                else
                    mem[i][j] =Math.max(mem[i-1][j],mem[i][j-1]);      
            }
        }

        String s = ""; //uguale a String s= new String("")
        int i=m;
        int j=n;

        while(mem[i][j]>0){
            if(u.charAt(m-i)==v.charAt(n-j)){
                s=s+u.charAt(m-i); //è uguale all'append
                i--;
                j--;
            }else if(mem[i-1][j]<mem[i][j-1])
                j--;                               
            else if(mem[i-1][j]<mem[i][j-1])
                i--;                
            else if(Math.random()<0.5)
                j--;               
            else
                i--;                             
        }      
        return s;
    }
}   // class Memoization