
/**
 * Write a description of class IntSList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IntSList
{
    public static final IntSList NULL_INTSLIST = new IntSList();
    
    private final boolean empty;
    private final int first;
    private final IntSList rest;
    
    public IntSList(){
        empty = true;
        first = 0;
        rest = null;
    }
    
    public IntSList(int el, IntSList rest){
        first = el;
        this.rest = rest;
        empty = false;
    }
    
    public boolean isNull(){
        return empty;
    }
    
    public int car(){
        return first;
    }
    public IntSList cdr(){
        return rest;
    }
    public IntSList cons(int el){
        return new IntSList(el, this);
    }
    public int length(){
       int len = 0;
       IntSList r = this;
       while(! r.isNull()){
           len++;
           r =  r.cdr();
       }
       return len;
    }
    public String toString(){
        if(isNull()){ //implicito come fosse this.isNull()
            return "()";            
        }else{
            String s = "(" + car();
            IntSList r = cdr();
            
            while(!r.isNull()){
                s+= " " + r.car();
                r = r.cdr();
            }
            return s+=")";
        }
    }
    public IntSList append(IntSList t){
        if(isNull()){
            return t;
        }else{
            return ( cdr().append(t)).cons(car());
        }
    
    }
    public IntSList reverse(){
        IntSList rev = NULL_INTSLIST;
        IntSList s = this;
        while(!s.isNull()){
               rev = rev.cons(s.car());
               s = s.cdr();
            }
        return rev;
    }
    
}
