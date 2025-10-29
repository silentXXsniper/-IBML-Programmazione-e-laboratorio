public class Queens {
  //ritorna il numero di completamenti effettuati
  public static int numberOfSolutions( int n ) {
    return numberOfCompletions( new Board(n) );
  }
  private static int numberOfCompletions( Board b ) {
    int n = b.size();//come sopra
    int q = b.queensOn();//numero regine sulla scacchiera
    if ( q == n ) {
      return 1;//caso base
    } else {
      int i = q + 1;
      int count = 0;
      for ( int j=1; j<=n; j=j+1 ) {
        if ( !b.underAttack(i,j) ) {//se non è sotto attacco aggiungo regina
          count = count + numberOfCompletions( b.addQueen(i,j) );
      }
    }
      return count;//ritorno count
    }
  }
}  // class Queens
