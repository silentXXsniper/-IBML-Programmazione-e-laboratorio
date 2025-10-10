public class IntSList
{
    /* static final = costante, public quindi condivisa, utilizzabile a prescindere da che si abbia un'istanza della classe o meno */
    public static final IntSList NULL_INTSLIST = new IntSList();
    
    /* final = oggetti immutabili caratterizzati da variabili final */
    private final boolean empty;
    private final int car;
    private final IntSList cdr;
    
    /** 
     * Costruttori 
     * */
    public IntSList()
    {
        empty = true;
        car = 0;
        cdr = null;
    }
    
    private IntSList(int n, IntSList t)
    {
        empty = false;
        car = n;
        cdr = t;
    }
    /**---------------------------------------------------------------------*/
    
    /** 
     * Informazioni sulla configurazione 
     * */
    public int car()
    { return car; }
    
    public IntSList cdr()
    { return cdr; }
    
    public boolean isNull()
    { return empty; }

    public int length()
    {
        if(isNull())
            return 0;
        else
            return 1 + cdr().length();
    }

    public int listRef(int i)
    {
        if(i == 0)
            return car();
        else 
            return cdr().listRef(i-1);
    }
    
    public boolean equals(IntSList il)
    {
        if(this.isNull() && il.isNull())
            return true;
        else if(car() == il.car())
            return this.cdr().equals(il.cdr());
        else
            return false;
    }
    /**---------------------------------------------------------------------*/    
    
    /** 
     * Metodi per la creazione di nuove configurazioni 
     * */
    public IntSList cons(int n)
    { return new IntSList(n, this); }
    
    public IntSList append(IntSList il)
    {
        if(isNull())
            return il;
        else
        {
            return cdr().append(il).cons(car());
        }
    }
    
    public IntSList reverse()
    {
        return reverseTree(NULL_INTSLIST);
    }
    
    private IntSList reverseTree(IntSList il)
    {
        if(isNull())
            return il;
        else
            return cdr().reverseTree(il.cons(car()));
    }
    
    public static IntSList range(int inf, int sup)
    {
        if( inf > sup )
            return new IntSList();
        else
            return range(inf+1, sup).cons(inf);
    }
    /**---------------------------------------------------------------------*/
    
    /** 
     * Override 
     * */
    public String toString()
    {
        if(isNull())  
            return "'()";
        else
        {
            String rep = "(" + car();
            IntSList r = cdr();
            while(! r.isNull() )
            {
                rep += ", " + r.car();
                r = r.cdr();
            }
            return rep + ")";
        }
    }
    /**---------------------------------------------------------------------*/
    
    /*
    public int listRef(int i)
    {
        return listRefSupp(i, 0);
    }
    
    private int listRefSupp(int i, int k)
    {
        if(k <= i)
        {
            if(k == i)
                return car();
            else
                return cdr().listRefSupp(i, k+1);
        } else {
            return 0;
        }
    }
    */
}
