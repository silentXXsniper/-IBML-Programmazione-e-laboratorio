/**
 * Protocollo
 * 
 * BoardMod b = new BoardMod(int n);
 * 
 * b.size()                     : int
 * 
 * b.queensOn()                 : int
 * 
 * b.underAttack(int i, int j)  : boolean
 * 
 * b.arrangement()              : String
 * 
 * b.addQueen(int i, int j)     : BoardMod
 */

import java.util.function.BiPredicate;

public class BoardMod
{
    private static final String ROWS = " 123456789ABCDEF";
    private static final String COLS = " abcdefghijklmno";
    
    private final int                           size;
    private final int                           queens;
    private final String                        config;
    private final IntSList                      rowsIndexes;
    private final IntSList                      colsIndexes;
    private final IntSList                      dUpRightIndexes;
    private final IntSList                      dUpLeftIndexes;
    
    // Costruttori
    public BoardMod(int n)
    {
        size     = n;
        queens   = 0;
        config   = " ";
        rowsIndexes     = IntSList.NULL_INTSLIST;
        colsIndexes     = IntSList.NULL_INTSLIST;
        dUpRightIndexes = IntSList.NULL_INTSLIST;
        dUpLeftIndexes  = IntSList.NULL_INTSLIST;
    }
    
    private BoardMod(int n, int q, String cfg, IntSList rsI, IntSList csI, IntSList drI, IntSList dlI)
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

    private boolean underAttackSupp(int value, IntSList dimension)
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
    
    public BoardMod addQueen(int i, int j)
    {
        IntSList tmp = IntSList.NULL_INTSLIST;
        
        return new BoardMod(
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
}