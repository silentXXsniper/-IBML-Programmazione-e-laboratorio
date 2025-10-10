
/*
 * Modulo Josephus:
 *
 * Programma per risolvere il problema ispirato da Giuseppe Flavio
 * nella rielaborazione di Donald Knuth (metodo statico)
 *
 * Ultimo aggiornamento: 25/03/2021
 */

public class josephus {

    public static int josephus( int n ) {

        RoundTable rt = new RoundTable( n );     // configurazione iniziale

        while ( rt.numberOfKnights() > 1 ) {     // finche' c'e' piu' di un commensale a tavola

            RoundTable ext = rt.serveNeighbour();  // un commensale viene servito ed esce
            rt = ext.passJug();                    // la brocca viene passata di mano
        }
        return rt.knightWithJug();               // alla fine rimane un cavaliere con la brocca
    }

    public static void main( String args[] ) {
        int n = Integer.parseInt( args[0] );
        for ( int k=1; k<=n; k=k+1 ) {
            System.out.println( josephus(k) );
        }
    }

}  // class Josephus
