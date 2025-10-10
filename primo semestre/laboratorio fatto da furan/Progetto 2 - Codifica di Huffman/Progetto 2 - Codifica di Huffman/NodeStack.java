public class NodeStack
{
    // First in is last out
    // Last in is first out
    private Node[] nodeStack;
    
    public NodeStack()
    { nodeStack = null; }
    
    public boolean empty()
    { return nodeStack == null; }
    
    public Node peek()
    { return (empty()? null : nodeStack[nodeStack.length-1]); }
    
    public Node pop()
    {
        if(empty()) // Lo stack è vuoto, return null
            return null;
        else if(nodeStack.length == 1) // Ho un solo elemento, perciò dopo il pop lo stack è vuoto (== null)
        {
            Node firstIn = nodeStack[0];
            nodeStack = null;
            return firstIn; // lastOut
        } else {
            Node lastIn = nodeStack[nodeStack.length-1];
            
            Node[] a = new Node[nodeStack.length-1];            
            for(int i = 0; i < a.length; i++)
            { a[i] = nodeStack[i]; }
            nodeStack = a;
            
            return lastIn; // firstOut
        }
    }
    
    public void push( Node n )
    {
        Node[] a = new Node[(empty()? 1 : nodeStack.length+1)];
        
        int i;
        for(i = 0; i < a.length-1; i++) // fino all'ultimo elemento di nodeStack riempio a
        { a[i] = nodeStack[i]; }
        // poi riempio l'ultimo elemento di a (corrisponde all'indice i, perché i viene incrementato dopo ogni ciclo)
        a[i] = n;
        
        nodeStack = a;
    }
}
