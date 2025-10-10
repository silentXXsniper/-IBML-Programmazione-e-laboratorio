
/**
 * Soluzioni del rompicapo delle N regine
 */
public class Queens
{   
    // Configurazioni
    public static int numeroDiSoluzioni(int n)
    { return numeroDiCompletamenti(new BoardMod(n)); }
    
    private static int numeroDiCompletamenti(BoardMod b)
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
}
