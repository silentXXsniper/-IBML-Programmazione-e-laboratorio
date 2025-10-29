public class Queens {
    public static int numeroDiSoluzioni ( int n ) {
        return numeroDiCompletamenti ( new Board( n ) );
    }
    private static int numeroDiCompletamenti ( Board b ) {
        int n = b.size();
        int q = b.queensOn();
        if ( q == n ) {
            return 1;
        } else {
            int i = q + 1;
            int count = 0;
            for ( int j = 1; j <= n; j++ ) {
                if ( ! b.underAttack( i, j ) ) {
                    count = count + numeroDiCompletamenti ( b.addQueen ( i, j ) );
                }
            }
            return count;
        }
    }
    public static SList<Board> listaDiSoluzioni ( int n ) {
        return listaDiCompletamenti ( new Board( n ) );
    }
    private static SList<Board> listaDiCompletamenti ( Board b ) {
        int n = b.size();
        int q = b.queensOn();
        if ( q == n ) {
            return new SList().cons( b );
        } else {
            int i = q + 1;
            SList<Board> list = new SList();
            for ( int j = 1; j <= n; j++ ) {
                if ( ! b.underAttack ( i, j ) ) {
                    list = list.append( listaDiCompletamenti( b.addQueen( i, j ) ) );
                }
            }
            return list;
        }
    }
    public static void visualizzaSoluzioni ( int n ) {
        soluzioniGrafiche( listaDiSoluzioni( n ), n );
    }
    private static void soluzioniGrafiche ( SList<Board> soluzioni, int n) {
        queens.ChessboardView view = new queens.ChessboardView( n );
        while ( ! soluzioni.isNull() ) {
            view.setQueens( soluzioni.car().arrangement() );
            soluzioni = soluzioni.cdr();
        }
    }
}
