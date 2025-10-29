
/*
 * Rompicapo delle "n regine"
 *
 * Ultimo aggiornamento: 12/04/2018
 *
 *
 * Dato astratto "configurazione della scacchiera":  Board
 *
 * Operazioni:
 *
 *   new Board( int n )           :  costruttore (scacchiera vuota)
 *
 *   size()                       :  int
 *
 *   queensOn()                   :  int
 *
 *   underAttack( int i, int j )  :  boolean
 *
 *   addQueen( int i, int j )     :  Board
 *
 *   arrangement()                :  String
 *
 *
 * Board b;
 *
 *   new Board(n)           costruttore della scacchiera n x n vuota;
 *   b.size()               dimensione n della scacchiera b;
 *   b.queensOn()           numero di regine collocate nella scacchiera b;
 *   b.underAttack(i,j)     la posizione <i,j> e' minacciata?
 *   b.addQueen(i,j)        scacchiera che si ottiene dalla configurazione b
 *                          aggiungendo una nuova regina in posizione <i,j>
 *                          (si assume che la posizione non sia minacciata);
 *   b.arrangement() :      descrizione "esterna" della configurazione
 *                          (convenzioni scacchistiche).
 */


public class Queens2 {
  public static int numberOfSolutions( int n ) {
    return numberOfCompletions( new Board(n) );
  }
  private static int numberOfCompletions( Board b ) {
    int n = b.size();
    int q = b.queensOn();
    if ( q == n ) {
      return 1;
    } else {
      int i = q + 1;
      int count = 0;
      for ( int j=1; j<=n; j=j+1 ) {
        if ( !b.underAttack(i,j) ) {
          count = count + numberOfCompletions( b.addQueen(i,j) );
      }}
      return count;
    }
  }
  public static void main( String args[] ) {
  
    int n = Integer.parseInt( args[0] );
    
    System.out.println( numberOfSolutions(n) );
  }
}  // class Queens

