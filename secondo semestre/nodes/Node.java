
/**
 * Aggiungi qui una descrizione della classe Node
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public class Node
{
    
    
    private final char chr;
    private final int wgt;
    private final Node lft;
    private final Node rgt;
    
    
    public Node(char c, int w){//foglia
        
        chr=c;
        wgt=w;
        lft=null;
        rgt=null;
    }
    
    public Node(Node ln, Node rn){ //intermedio
        
        chr=(char) 0;
        wgt=ln.weight() + rn.weight();
        lft=ln;
        rgt=rn;
    }
    
    public boolean isLeaf(){
        
        return (lft==null);
    }
    
    public char symbol(){
        
        return chr;
    }
    public int weight(){
        
        return wgt;
    }
    public Node left(){
        
        return lft;
    }
    public Node right(){
        
        return rgt;
    }
}
