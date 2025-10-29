
/**
 * Codifica di Huffman
 * 
 * Classe Node
 * nodi albero Huffman
 * 
 * implements Comparable<Node>:
 * le istanze di Node possono essere ordinate
 * in una coda con priorità
 * 
 * Procollo:
 * 
 *   Node n = new Node(char c, int w);
 *   Node u = new Node(Node l, Node r);
 *   
 *     ... n.symbol() : char
 *     ... n.weight() : int
 *     ... u.left()   : Node
 *     ... u.right()  : Node
 *     
 *     ... n.isLeaf() : boolean
 * 
 */

import java.util.*;

public class Node implements Comparable<Node>
{
    // Oggetti immutabili
    private final char chr;
    private final int  wgt;
    private final Node lft;
    private final Node rgt;
    
    // Costruttori
    public Node(char c, int w)
    {
        chr = c;
        wgt = w;
        lft = null;
        rgt = null;
    }
    
    public Node(Node l, Node r)
    {
        chr = (char) 0;
        wgt = l.weight() + r.weight();
        lft = l;
        rgt = r;
    }
    
    // per determinare l'usabilità dei metodi per info
    public boolean isLeaf()
    { return ( lft == null ); }
    
    // Metodi per informazioni relative ai nodi
    public char character()
    { return chr; }
    
    public int weight()
    { return wgt; }
    
    public Node left()
    { return lft; }
    
    public Node right()
    { return rgt; }
    
    // Uso dell'interfaccia Comparable per poter poi creare
    // una "coda di priorità" e ridefinizione del metodo
    // compareTo.
    public int compareTo(Node n)
    {
        // -1: minore
        // 0 : uguale
        // +1: maggiore
        if(this.wgt < n.wgt)
            return -1;
        else if(this.wgt == n.wgt)
            return 0;
        else// (this.wgt > n.wgt)
            return +1;
    }
}
