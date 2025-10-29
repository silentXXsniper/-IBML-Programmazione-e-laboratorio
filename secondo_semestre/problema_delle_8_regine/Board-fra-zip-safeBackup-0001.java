
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
    private final int n;
    private final int q;
    private final BiPredicate<Integer,Integer>attack;
    private final String config;
    
    public Board(int n){
       this.n=n; 
       q = 0; 
       attack=(x,y)->false;
       
       config="";
    }
    
    private Board(int i, int j, Board b){
        
       n=b.size; 
       q=b.queensOn() + 1; 
       attack=(x,y)->((x==i)|(y==j))||(x-y);
       
       config=b.arrangment();
    }
    
    
    public int size(){
        return n;
    }
    
    public int queensOn(){
        return q;
    
    }
    
    public boolean underAttack(int i, int j){
        
        return attack.test(i,j);
    }
    
    public Board addQueen(int i, int j){
        
        return new Board(i,j,this);
    }
    
    public String arrangment(){
    
        return config;
    }
        
}// class Board
    
    