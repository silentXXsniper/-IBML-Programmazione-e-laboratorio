
/**
 * Write a description of class StringSList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StringSList
{
    private final boolean empty;
    private final String first;
    private final StringSList rest;

    

    public StringSList(){
        empty=true;
        first="";
        rest=null;
    }

    public StringSList(String first, StringSList r){
        empty=false;
        this.first=first;
        rest=r;
    }

    public String car(){
        return first;
    }

    public boolean isNull(){
        return empty;
    }

    public StringSList cdr(){
        return rest;
    }

    public StringSList cons(String e){
        return new StringSList(e,this);
    }

    public int length(){
        if (isNull()){
            return 0;
        }else{
            return 1+ cdr().length();
        }
    }

    public boolean equal(StringSList e){
        if (isNull() || (e.isNull())){
            return (isNull()) && (e.isNull());
        }else{
            if(car()==e.car()){
                return this.cdr().equal(e.cdr());
            }else{
                return false;
            }

        }
    }

    public String stringRef(int n){
        if(!(n==0)){ //n != 0 
            return cdr().stringRef(n-1);
        }else{
            return this.car();
        }
    }

    public StringSList append(StringSList sl){
    if ( isNull() ) {
      return sl;
    } else {
      // return new IntSList( car(), cdr().append(il) );
      return ( cdr().append(sl) ).cons( car() );
    }
    }

    public StringSList reverse(){
        return reverseRec(new StringSList());       
    }

    public StringSList reverseRec(StringSList ric){
        if(isNull()){
            return ric;
        }else{
            return cdr().reverseRec(rest.cons(car()) );
            //manca return e .stringslist non e un metodo, cons puo prendere solo un String non una lista
        }
    }

    
    public String toString() {               // ridefinizione del metodo generale
                                                   // per la visualizzazione testuale
    if ( isNull() ) {
      return "()";
    } else {
      String rep = "(" + car();
      StringSList r = cdr();
      while ( !r.isNull() ) {
        rep = rep + ", " + r.car();
        r = r.cdr();
      }
      return ( rep + ")" );
    }
  }
  
  
  

}


