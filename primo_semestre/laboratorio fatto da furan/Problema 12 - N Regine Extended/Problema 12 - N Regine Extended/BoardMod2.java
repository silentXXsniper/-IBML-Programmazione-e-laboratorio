/**
 * Protocollo
 * 
 * BoardMod2 b = new BoardMod2(int n);
 * 
 * b.size()                     : int
 * 
 * b.queensOn()                 : int
 * 
 * b.underAttack(int i, int j)  : boolean
 * 
 * b.arrangement()              : String
 * 
 * b.addQueen(int i, int j)     : BoardMod2
 */
import queens.*;
import java.util.function.BiPredicate;

public class BoardMod2
{
    private static final String ROWS = " 123456789ABCDEF";
    private static final String COLS = " abcdefghijklmno";
    
    private final int                           size;
    private final int                           queens;
    private final String                        config;
    private final SList<Integer>                rowsIndexes;
    private final SList<Integer>                colsIndexes;
    private final SList<Integer>                dUpRightIndexes;
    private final SList<Integer>                dUpLeftIndexes;
    
    // Costruttori
    public BoardMod2(int n)
    {
        size     = n;
        queens   = 0;
        config   = " ";
        rowsIndexes     = new SList();
        colsIndexes     = new SList();
        dUpRightIndexes = new SList();
        dUpLeftIndexes  = new SList();
    }
    
    private BoardMod2(int n, int q, String cfg, SList<Integer> rsI, SList<Integer> csI, SList<Integer> drI, SList<Integer> dlI)
    {
        size            = n;
        queens          = q;
        config          = cfg;
        rowsIndexes     = rsI;
        colsIndexes     = csI;
        dUpRightIndexes = drI;
        dUpLeftIndexes  = dlI;
    }
    
    // Dati sulla configurazione
    public int size()
    { return size; }
    
    public int queensOn()
    { return queens; }

    private String arrangement()
    {
        // descrive le posizioni delle regine descrivendone (col, row)
        return config;
    }
    
    public String toString()
    {
        return "" + arrangement();
    }
  
    // Metodi per nuove configurazioni
    public boolean underAttack(int i, int j)
    {
        return (
                underAttackSupp(i, rowsIndexes) || 
                underAttackSupp(j, colsIndexes) || 
                underAttackSupp(i-j, dUpRightIndexes) || 
                underAttackSupp(i+j, dUpLeftIndexes)
               );
    }

    private boolean underAttackSupp(int value, SList<Integer> dimension)
    {
        if( dimension.isNull() )
            return false;
        else {
            if( !dimension.cdr().isNull() )
            {
                if(value == dimension.car())
                    return true;
                else
                    return underAttackSupp(value, dimension.cdr());
            } else {
                return value == dimension.car();
            }
        }
    }
    
    public BoardMod2 addQueen(int i, int j)
    {
        SList<Integer> tmp = new SList();
        
        return new BoardMod2(
                            size, 
                            queens+1, 
                            config + COLS.charAt(j) + ROWS.charAt(i) + " ",
                            rowsIndexes.append(tmp.cons(i)),
                            colsIndexes.append(tmp.cons(j)),
                            dUpRightIndexes.append(tmp.cons(i-j)),
                            dUpLeftIndexes.append(tmp.cons(i+j))                                
                           );
    }
    
    public String extendedToString()
    {
        return "(" + size + ", " + queens + ", " + rowsIndexes.toString() + ", " + colsIndexes.toString() + ", " + dUpRightIndexes.toString() + ", " + dUpLeftIndexes + ", " + arrangement();
    }
    
    public void seeConfig()
    {
        ChessboardView gui = new ChessboardView(size());
        
        gui.setQueens(arrangement());
    }
}