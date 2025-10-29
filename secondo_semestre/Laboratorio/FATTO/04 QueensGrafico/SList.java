public class SList<T> {
    private final T first;
    private final SList<T> rest;
    public SList () {
        first = null;
        rest = null;
    }
    public SList ( T e, SList<T> sl ) {
        first = e;
        rest = sl;
    }
    public boolean isNull () {
        return ( first == null );
    }
    public T car () {
        return first;
    }
    public SList<T> cdr () {
        return rest;
    }
    public SList<T> cons ( T e ) {
        return new SList<T> ( e, this );
    }
    public int length () {
        if ( isNull() ) {
            return 0;
        } else {
            return 1 + cdr().length();
        }
    }
    public boolean equals ( SList<T> sl ) {
        if ( sl == null ) {
            return false;
        } else if ( sl.isNull() ) {
            return isNull();
        } else if ( isNull() ) {
            return false;
        } else if ( sl.car().equals( car() ) ) {
            return cdr().equals( sl.cdr() );
        } else {
            return false;
        }
    }
    public SList<T> append ( SList<T> sl ) {
        if ( isNull() ) {
            return sl;
        } else {
            return ( cdr().append( sl ) ).cons( car() );
        }
    }
    public SList<T> reverse () {
        SList<T> rl = new SList<T> ();
        SList<T> sl = this;
        while ( ! sl.isNull() ) {
            rl = rl.cons( sl.car() );
            sl = sl.cdr();
        }
        return rl;
    }
    public String toString () {
        if ( isNull() ) {
            return "()";
        } else {
            String txt = "( " + car();
            SList<T> r = cdr();
            while ( ! r.isNull() ) {
                txt = txt + ", " + r.car();
                r = r.cdr();
            }
            return txt + " )";
        }
    }
}
