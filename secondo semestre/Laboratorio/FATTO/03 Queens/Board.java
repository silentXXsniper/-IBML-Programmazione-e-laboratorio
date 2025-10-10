public class Board {
    // Variabili di istanza
    private final int size;              // Dimensione della scacchiera
    private final int queens;            // Numero di regine collocate
    private final IntSList rows;         // Righe minacciate
    private final IntSList cols;         // Colonne minacciate
    private final IntSList upDiags;      // Diagonali ascendenti minacciate (x - y)
    private final IntSList downDiags;    // Diagonali discendenti minacciate (x + y)
    private final String arrangement;    // Codifica testuale (es: " d1 c3 ")
    // Costruttore per scacchiera vuota
    public Board(int n) {
        size = n; //la scacchiera sarà nxn
        queens = 0; //regine iniziali =0
        rows = IntSList.NULL_INTLIST; //inizializziamo tutte le liste come nulle
        cols = IntSList.NULL_INTLIST;
        upDiags = IntSList.NULL_INTLIST;
        downDiags = IntSList.NULL_INTLIST;
        arrangement = "";//ovviamente essendo all'inizio la lista vuota
        //all inizio la config è vuota
    }
    // Costruttore privato per configurazioni successive
    private Board(int n, int q, IntSList r, IntSList c, IntSList up, IntSList down, String arr) {
        size = n;
        queens = q;
        rows = r;
        cols = c;
        upDiags = up;
        downDiags = down;
        arrangement = arr;
    }
    // Dimensione della scacchiera
    public int size() {
        return size;//ritorna la grandezza n della scacchiera
    }
    // Numero di regine collocate
    public int queensOn() {
        return queens;
    }
    // Verifica se una posizione è minacciata
    public boolean underAttack(int i, int j) {
        return contains(rows, i) ||
               contains(cols, j) ||
               contains(upDiags, i - j) ||
               contains(downDiags, i + j);
               //controlla orizzontali verticali e diagonali
    }
    // Agginge una regina e ritorna una nuova Board
    public Board addQueen(int i, int j) {
        String colLetter = "" + (char)('a' + j - 1);  // Colonne da 'a' a 'z'
        String newArrangement = arrangement + " " + colLetter + i;
        return new Board(
            size,
            queens + 1,
            rows.cons(i),
            cols.cons(j),
            upDiags.cons(i - j),
            downDiags.cons(i + j),
            newArrangement
        );
    }
    //classe per rimuovere una regina
    public Board removeQueen(int i, int j){
        String colLetter = "" + (char)('a' + j - 1);  // Colonne da 'a' a 'z'
        String newArrangement = arrangement + " " + colLetter + i;
        return new Board(
            size,
            queens + 1,
            rows.cons(i),
            cols.cons(j),
            upDiags.cons(i - j),
            downDiags.cons(i + j),
            newArrangement
        );
    }
    // Codifica testuale della configurazione
    public String arrangement() {
        return arrangement;
    }
    // Metodo ausiliario per controllare se una lista contiene un elemento
    private boolean contains(IntSList list, int value) {
        while (!list.isNull()) {//controllo su tutti gli elementi
            if (list.car() == value) return true;//se lo trova e vero
            list = list.cdr();//aktrimenti passa al prossimo elemento
        }
        return false;//se ha scorso tutta la lista e falso
    }
}
