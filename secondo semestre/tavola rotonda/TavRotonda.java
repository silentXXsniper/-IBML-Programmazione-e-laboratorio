/**
 * 
 * TavRotonda t;
 * int n;
 * 
 *   new TavRotnda(n): TavRotonda
 * 
 * t.numCavalieri()  : int
 * t.cavConBrocca()  : int
 * 
 **/

public class TavRotonda{
    private final int n;
    private final int brocca;
    private final IntSList altri;
    
    
    public TavRotonda(int n){
        this.n=n;
        brocca=1;
        altri= intervallo(2,n);
        
        }
        
        private TavRotonda(int n, int brocca, IntSList altri){
        this.n=n;
        this.brocca=brocca;
        this.altri= altri;
        
        }
        
         private static IntSList intervallo(int inf, int sup)
    {
        // initialise instance variables
        if(inf>sup){
            return IntSList.NULL_INTLIST;
        }else{
            return intervallo (inf+1, sup).con(inf);
        }
    }
        
    public int numCavalieri(){
    
    }
    
    public int cavConBrocca(){}
    
    public TavRotonda serve(){}
    
    public TavRotonda passa(){} 
}  // class TavRotonda
