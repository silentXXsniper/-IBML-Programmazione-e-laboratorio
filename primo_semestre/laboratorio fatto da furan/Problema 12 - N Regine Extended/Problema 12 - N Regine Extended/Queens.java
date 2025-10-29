import queens.*;
/**
 * Soluzioni del rompicapo delle N regine
 */
public class Queens
{   
    // Configurazioni
    public static int numeroDiSoluzioni(int n)
    { return numeroDiCompletamenti(new BoardMod2(n)); }
    
    private static int numeroDiCompletamenti(BoardMod2 b)
    { 
        int n = b.size();
        int q = b.queensOn();
        
        if(q == n)
            return 1; // ultima configurazione possibile: quella attuale
        else // q < n
        {
            // riga q+1esima, ossia per cui non ho ancora trovato soluzione
            int i = q + 1;
            int count = 0;
            
            // tutte le colonne
            for(int j = 1; j <= n; j++)
            {
                if(! b.underAttack(i, j) )
                    count += numeroDiCompletamenti( b.addQueen(i, j) );
            }
            
            return count;
        }
    }
    
    public static SList<BoardMod2> listaDiSoluzioni(int n)
    { return listaDiCompletamenti(new BoardMod2(n)); }
    
    private static SList<BoardMod2> listaDiCompletamenti(BoardMod2 b)
    { 
        int n = b.size();
        int q = b.queensOn();
        
        if(q == n)
            return new SList<BoardMod2>().cons(b); // ultima configurazione possibile: quella attuale
        else // q < n
        {
            // riga q+1esima, ossia per cui non ho ancora trovato soluzione
            int        i    = q + 1;
            SList<BoardMod2> list = new SList<BoardMod2>();
            
            // tutte le colonne
            for(int j = 1; j <= n; j++)
            {
                if(! b.underAttack(i, j) )
                    list = list.append(listaDiCompletamenti( b.addQueen(i, j) ));
            }
            
            return list;
        }
    }
    
    public static void seeAllConfigs(SList<BoardMod2> bl)
    {
        if( !bl.isNull() ) 
        {   
            int size = bl.car().size();
            ChessboardView gui = new ChessboardView(size);
            
            while( !bl.isNull() )
            {
                gui.setQueens(bl.car().toString());
                
                bl = bl.cdr();
            }
        }
    }
}
