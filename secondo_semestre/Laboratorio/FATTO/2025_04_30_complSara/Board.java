
/*
 * Rompicapo delle "n regine"
 * Realizzazione del dato astratto "configurazione della scacchiera"
 *
 * Protocollo della classe Board:
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
 *   
 *   b.removeQueen(i,j)
 *   b.arrangement() :      descrizione "esterna" della configurazione
 *                          (convenzioni scacchistiche).
 */

public class Board {
    // Codifica secondo le convenzioni scacchistiche (massima dimensione: 15 x 15)
    private static final String ROWS = " 123456789ABCDEF";
    private static final String COLS = " abcdefghijklmno";

    // Realizzazione del dato astratto "Scacchiera": stato interno
    private final int n;                             // 1) dimensione scacchiera
    private int q;                           // 2) numero regine collocate

    private String config;                        // 3) disposizione delle regine: rappresentazione testuale
    private int[] rowAttack, colAttack, dg1Attack, dg2Attack;

    // Costruttori:

    public Board( int n ) {                             // scacchiera vuota

        this.n = n;                                         // scacchiera n x n
        q = 0;                                       // nessuna regina
        config = " ";

        rowAttack = new int[n];
        colAttack = new int[n];
        dg1Attack = new int[2*n-1];
        dg2Attack = new int[2*n-1];

        for(int k=0; k<n; k++){
            rowAttack[k] = 0;
            colAttack[k] = 0;
        }
        for(int k=0; k<n; k++){
            dg1Attack[k] = 0;
            dg2Attack[k] = 0;
        }
    }


    /*private Board( Board board, int i, int j ) {        // <-- board.addQueen(i,j)

    size = board.size();                              // stessa dimensione di board
    queens = board.queensOn() + 1;                    // ma una nuova regina che...

    attack = ( u, v ) -> ( (u == i) ||                // minaccia la riga i
    (v == j) ||                // minaccia la colonna j
    (u-v == i-j) ||            // minaccia le diagonali i-j e i+j
    (u+v == i+j) ||
    board.underAttack(u,v)     // minaccia delle regine in board
    );

    config = board.arrangement() + COLS.charAt(j) + ROWS.charAt(i) + " ";
    }*/

    public int size() {                                 // dimensione della scacchiera
        return n;
    }

    public int queensOn() {                             // numero di regine collocate
        return q;
    }

    public boolean underAttack( int i, int j ) {        // posizione <i,j> minacciata?
        return ((rowAttack[i-1] > 0) || (colAttack[j-1] > 0) || (dg1Attack[i-j+n-1] > 0) || (dg2Attack[i+j-2] > 0));
    }

    public Board addQueen( int i, int j ) {             // nuova scacchiera
        // con una regina anche in <i,j>
        q++;
        config = config+ COLS.substring(j, j+1) + ROWS.substring(i, i+1) + " ";

        rowAttack [i-1]=rowAttack[i-1]-1;
        colAttack [j-1]=colAttack[j-1]-1;
        dg1Attack [i-j+n-1]=dg1Attack[i-j+n-1]-1;
        dg2Attack [i+j-2]=dg2Attack[i+j-2]-1;
    }

    public void removeQueen(int i, int j){
        // con una regina anche in <i,j>
        q--;
        String pair=COLS.substring(j,j+1)+ROWS.substring(i,i+1);
        int k=config.indexOf(pair);
        config = config.substring(0,k)+ config.substring(k+3);

        rowAttack [i-1]=rowAttack[i-1]-1;
        colAttack [j-1]=colAttack[j-1]-1;
        dg1Attack [i-j+n-1]=dg1Attack[i-j+n-1]-1;
        dg2Attack [i+j-2]=dg2Attack[i+j-2]-1;
    }

    public String arrangement() {                       // descrizione testuale
        return "[" + config + "]";
    }

    public String toString() {                          // rappresentazione standard per Java
        return arrangement();
    }

}  // class Board
