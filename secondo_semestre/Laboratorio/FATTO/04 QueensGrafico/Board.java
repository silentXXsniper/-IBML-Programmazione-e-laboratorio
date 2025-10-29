public class Board {
    private static final String ROWS = " 12345789ABCDEF";
    private static final String COLS = " abcdefghijklmno";
    private final int size;
    private final int queens;
    private final SList<SList<Integer>> queensPosition;
    private final String config;
    public Board ( int n ) {
        size = n;
        queens = 0;
        queensPosition = new SList();
        config = " ";
    }
    private Board ( int n, int q, SList<SList<Integer>> qP, String c ) {
        size = n;
        queens = q;
        queensPosition = qP;
        config = c;
    }
    public int size () {
        return size;
    }
    public int queensOn () {
        return queens;
    }
    public boolean underAttack ( int i, int j ) {
        int length = queensPosition.length();
        SList<SList<Integer>> list = queensPosition;
        for ( int count = 0; count < length; count++ ) {
            int u = list.car().car();
            int v = list.car().cdr().car();
            if ( ( i == u ) || ( j == v ) || ( i - j == u - v ) || ( i + j == u + v ) ){
                return true;
            }
            list = list.cdr();
        }
        return false;
    }
    public String arrangement () {
        return config;
    }
    public Board addQueen ( int i, int j ) {
        SList<Integer> newQueen = new SList<Integer>();
        newQueen = newQueen.cons( j );
        newQueen = newQueen.cons( i );
        return new Board ( size, queens + 1, queensPosition.cons( newQueen ), config + COLS.charAt( j ) + ROWS.charAt ( i ) + " " );
    }
    public String toString () {
        return "[" + arrangement() + "]";
    }
}