
/**
 * Board b;
 * int n;
 * 
 * b = new Board(n)
 * 
 * b.size()             :  int
 * b.queensOn()         :  int
 * 
 * n.unerAttack(i,j)    : boolean
 * 
 * b.addQueen (i,j)    :  Board
 * 
 * b.arrangment         : String
 * 
 */
public class Board
{
    private final int i;
    private final int q;
    private final BiPredicate<Integer,Integer> attack;
    private final String config;
    
    public Board(int n){
       this.n = n; 
       q = 0; 
       attack= (x, y) ->false;
       config="";
    }
    
    privae Board(int i, int j, Board b){
       n=b.size(); 
       q=b.queensOn()+1; 
       attack=(x,y)->((x==1)||(y==j)||
                      (x-y==i-j)||(x+y==i+j)||
                      b.underAttack(x,y));
                      
       config=b.config();
    }
    
    public int size(){
        return size;
    }
    
    public int queensOn(){
        return queens;
    
    }
    
    public boolean underAttack(int i, int j){
        
        return (attack.test(i,j));
    }
    
    public Board addQueen(int i, int j){
        return new Board(this, i, j);
    }
    
    public String arrangment(){
    
        return config;
    }
    
    public String toString(){
    return"[" + config + "]";
    }
}// class Board
    
    