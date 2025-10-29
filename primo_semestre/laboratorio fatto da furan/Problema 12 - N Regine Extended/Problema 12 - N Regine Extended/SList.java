/**
 * Classe SList<T>: Scheme-like Lists di elementi di classe/tipo T
 * 
 *   SList<T> s, t, u:
 *   T x;
 *   
 *      s = new SList();
 *      t = new SList(T x, SList s);
 *      
 *      boolean b = t.isNull();
 *      x = t.car()
 *      u = t.cdr();
 *      u = t.cons(x);
 *   
 * */

public class SList<T>
{
    /* static final = costante, public quindi condivisa, utilizzabile a prescindere da che si abbia un'istanza della classe o meno */
    // public static final SList NULL_SList = new SList();
    /* non posso staticamente fare riferimento ad un tipo che viene
       definito solo in fase di esecuzione. Prima dell'esecuzione
       il tipo T di SList resta generico e indefinito, e non può 
       essere ridefinito una volta noto il tipo T in runtime.
       */
    
    /* final = oggetti immutabili caratterizzati da variabili final */
    private final boolean  empty;
    private final T        car;
    private final SList<T> cdr;
    
    public SList()
    {
        empty = true;
        car   = null;
        cdr   = null;
    }
    
    private SList(T x, SList<T> t)
    {
        empty = false;
        car   = x;
        cdr   = t;
    }
    
    public SList<T> cons(T x)
    { return new SList<T>(x, this); }
    
    public T car()
    { return car; }
    
    public SList<T> cdr()
    { return cdr; }
    
    public boolean isNull()
    { return empty; }

    public String toString()
    {
        if(isNull())  
            return "'()";
        else
        {
            String rep = "'(" + car();
            SList<T> r = cdr();
            while(! r.isNull() )
            {
                rep += ", " + r.car();
                r = r.cdr();
            }
            return rep + ")";
        }
    }
    
    public int length()
    {
        if(isNull())
            return 0;
        else
            return 1 + cdr().length();
    }
    
    /*
        Alternativa mia, peggiore
    public T listRef(int i)
    {
        return listRefSupp(i, 0);
    }
    
    private T listRefSupp(int i, int k)
    {
        if(k <= i)
        {
            if(k == i)
                return car();
            else
                return cdr().listRefSupp(i, k+1);
        } else {
            return null;
        }
    }*/
    
    /* 
        Alternativa professore (migliore) */
    public T listRef(int i)
    {
        if(i == 0)
            return car();
        else 
            return cdr().listRef(i-1);
    }
    
    public boolean equals(SList<T> sl)
    {
        if(this.isNull() || sl.isNull())
            return this.isNull() && sl.isNull();
        else if(car().equals(sl.car()))
            return this.cdr().equals(sl.cdr());
        else
            return false;
    }
    
    public SList<T> append(SList<T> sl)
    {
        if(isNull())
            return sl;
        else
        {
            return cdr().append(sl).cons(car());
        }
    }
    
    public SList<T> reverse()
    {
        return reverseTree(new SList());
    }
    
    private SList<T> reverseTree(SList sl)
    {
        if(isNull())
            return sl;
        else
            return cdr().reverseTree(sl.cons(car()));
    }
    /* ? c'è un modo di definire range ?
    public static SList range(int inf, int sup)
    {
        if( inf > sup )
            return new SList();
        else
            return range(inf+1, sup).cons(inf);
    }
    */
}
