
/**
 * Requisiti funzionali:
 *  T + public StringSList()
 *  T + public StringSList(String e, StringSList sl)
 *  T + public boolean isNull()
 *  T + public String car()
 *  T + public StringSList cdr()
 *  T + public StringSList cons(String e)
 *  T + public int length()
 *  T + public String listRef(int k)
 *  T + public boolean equals(StringSList sl)
 *  T + public StringSList append(StringSList sl)
 *  T + public StringSList reverse()
 *  T + public String toString()
 */
public class StringSList
{
    // instance variables - replace the example below with your own
    private final String car;
    private final String cdr;
    
    public static final StringSList NULL_STRINGSLIST = new StringSList();
    
    public StringSList()
    {
        this.car = "";
        this.cdr = "";
    }
    
    public StringSList(String e, StringSList sl)
    {
        if(e.equals(""))
        {
            if(sl.equals(NULL_STRINGSLIST))
            {
                this.car = "";
                this.cdr = "";
            } 
            else
            {
                this.car = sl.car();
                this.cdr = sl.cdr();
            }
        }
        else
        {
            this.car = e.substring(0, 1);
            this.cdr = e.substring(1) + sl.car + sl.cdr;
        }
    }
    
    public boolean isNull()
    {
        if((this.car.equals("")) && (this.cdr.equals("")))
            return true;
        else 
            return false;
    }
    
    public String car()
    {
        if(!this.isNull())
            return this.car;
        else
            return "";
    }
    
    public String cdr()
    {
        if(!this.isNull())
            return this.cdr;
        else
            return "";
    }
    
    public StringSList cons(String e)
    {
        return new StringSList(this.toString(), (new StringSList(e, NULL_STRINGSLIST)));
    }
    
    public String toString()
    {
        if(this.isNull())
            return null;
        else
            return this.car+this.cdr;
    }
    
    public int length()
    {
        if(this.isNull())
            return 0;
        else
            return this.toString().length();
    }
    
    public String listRef(int k)
    {
        // Se è this is Null o k va fuori dagli indici possibili, return "";
        if(this.isNull() || k > (this.length()-1))
            return "";
        else
            return this.listRefSupp(k, 0);
            
        /* Variante imperativa (no listRefSupp e ricorsione)
         * 
         * int i = 0;
         * StringSList nextChar = this;
         * 
         * while(i != k)
         * {
         *     nextChar = new StringSList(nextcChar.cdr(), NULL_STRINGSLIST);
         *     i++;
         * }
         * 
         * return nextChar.car();
         }
           */
    }
    
    private String listRefSupp(int k, int i)
    {
        if(k == i)
            return this.car();
        else
            // chiamata ricorsiva sulla StringSList che va da cdr in poi
            return new StringSList(this.cdr(), NULL_STRINGSLIST).listRefSupp(k, (i+1));
    }
    
    public boolean equals(StringSList sl)
    {
        if(this.length() != sl.length())
            return false;
        else
            return this.equalsSupp(sl);
            
        /* Variante imperativa
         * 
         * nextCharThis = this;
         * nextCharSl = sl;
         * 
         * while(nextCharThis.length() != 0)
         * {
         *     if(nextCharThis.car().equals(nextCharSl.car()))
         *     {
         *         nextCharThis = new StringSList(nextCharThis.cdr(), NULL_STRINGSLIST);
         *         nextCharSl = new StringSList(nextCharSl.cdr(), NULL_STRINGSLIST);
         *     }
         *     else
         *         return false;
         }
         * }
         * 
         * return true;
           */
    }
    
    private boolean equalsSupp(StringSList sl)
    {
        if(this.cdr().equals("") && sl.cdr().equals(""))
            return true;
        else
        {
            if(this.car().equals(sl.car()))
                return new StringSList(this.cdr(), NULL_STRINGSLIST).equals(new StringSList(sl.cdr(), NULL_STRINGSLIST));
            else
                return false;
        }
    }
    
    public StringSList append(StringSList app)
    {
        return new StringSList(this.toString(), app);
    }
    
    public StringSList reverse()
    {
        StringSList rv = new StringSList();
        
        if(this.isNull())
            return this;
        else
            return this.reverseSupp(rv);
            
        /* Variante imperativa
         * 
         * int i = this.length();
         * while(i != 0)
         * {
         *      rv = new StringSList(this.listRef(i), rv);
         *      i--;
         * }
         * 
         * return rv;
         }
           */
    }
    
    private StringSList reverseSupp(StringSList rv)
    {
        if(this.length() == 0)
            return rv;
        else
            return new StringSList(this.cdr(), NULL_STRINGSLIST).reverseSupp(new StringSList(this.car(), rv));
            // new StringSList(this.car(), rv)
    }
}
