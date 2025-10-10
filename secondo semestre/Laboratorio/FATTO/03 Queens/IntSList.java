
public class IntSList {
  public static final IntSList NULL_INTLIST = new IntSList();
  private final boolean empty;             // oggetti immutabili:
  private final int first;                 // variabili di istanza "final"
  private final IntSList rest;
  public IntSList() {                      // creazione di una lista vuota                   // Scheme: null
    empty = true;
    first = 0;                             // valore irrilevante in questo caso
    rest = null;
  }
  public IntSList( int e, IntSList il ) {  // creazione di una lista non vuota:
                                           // Scheme: cons
    empty = false;
    first = e;
    rest = il;
  }
  public boolean isNull() {                // verifica se una lista e' vuota
                                           // Scheme: null?
    return empty;
  }
  public int car() {                       // primo elemento di una lista
                                           // Scheme: car
    return first;                          // si assume: lista non vuota
  }
  public IntSList cdr() {                  // resto di una lista                                     // Scheme: cdr
    return rest;                           // si assume: lista non vuota
  }
  public IntSList cons( int e ) {          // costruzione di nuove liste
                                           // Scheme: cons
    return new IntSList( e, this );
  }
  public int length() {                    // lunghezza di una lista
                                           // Scheme: length
    if ( isNull() ) {                      // oppure: this.isNull()
      return 0;
    } else {
      return ( 1 + cdr().length() );       // oppure:
    }                                      //   ( this.cdr() ).isNull()
  }
  public int listRef( int k ) {            // elemento in posizione k
                                           // Scheme: list-ref
    if ( k == 0 ) {
      return car();                        // oppure: this.car()
    } else {
      return ( cdr().listRef(k-1) );       // oppure:
                                           //   ( this.cdr() ).listRef(k-1)
    }
  }                                        // etc.: this. implicito
  public boolean equals( IntSList il ) {                                     // Scheme: equal?
    if ( isNull() || il.isNull() ) {
      return ( isNull() && il.isNull() );
    } else if ( car() == il.car() ) {
      return cdr().equals( il.cdr() );
    } else {
      return false;
    }
  }
  public IntSList append( IntSList il ) {                                       // Scheme: append
    if ( isNull() ) {
      return il;
    } else {
      // return new IntSList( car(), cdr().append(il) );
      return ( cdr().append(il) ).cons( car() );
    }
  }
  public IntSList reverse() {              // rovesciamento di una lista                                     // Scheme: reverse
    return reverseRec( new IntSList() );
  }
  private IntSList reverseRec( IntSList re ) {
    if ( isNull() ) {                      // metodo di supporto: private!
      return re;
    } else {
      // return cdr().reverseRec( new IntSList(car(),re) );
      return cdr().reverseRec( re.cons(car()) );
    }
  }
  public String toString() {               // ridefinizione del metodo generale
                                           // per la visualizzazione testuale
    if ( isNull() ) {
      return "()";
    } else {
      String rep = "(" + car();
      IntSList r = cdr();
      while ( !r.isNull() ) {
        rep = rep + ", " + r.car();
        r = r.cdr();
      }
      return ( rep + ")" );
    }
  }
}  // class IntSList