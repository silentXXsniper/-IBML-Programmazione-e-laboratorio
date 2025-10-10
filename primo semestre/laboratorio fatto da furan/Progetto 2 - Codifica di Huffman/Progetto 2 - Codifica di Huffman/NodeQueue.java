public class NodeQueue
{
    private Node[] nodeQueue;
    
    public NodeQueue()
    {
        nodeQueue = null;
    }
    
    public int size()
    {
        return (nodeQueue == null? 0 : nodeQueue.length);
    }
    
    public Node peek()
    { return ( size()>0? nodeQueue[0] : null); }
    
    public Node poll()
    {
        if( size() == 1 )
        {
            Node tmp = nodeQueue[0];
            nodeQueue = null;
            return tmp;
        } else if ( size() > 1 ) {
            Node[] a = new Node[nodeQueue.length-1];
            for(int i = 0; i < a.length; i++)
            { a[i] = nodeQueue[i+1]; }
            Node tmp = nodeQueue[0];
            nodeQueue = a;
            return tmp;
        }
        return null;
    }
    
    public void add( Node n )
    {
        Node[] a;
        
        if( size() == 0 )
        {   
            a = new Node[1];
            a[0] = n;
        } else {
            int flag = -1; // finché n = -1 devo continuare a scorrere
            int i = 0;
            
            while(flag == -1 && i < nodeQueue.length)
            {
                Node m = nodeQueue[i];
                flag = compareWeights(m, n);
                i++;
            }
            // se n non va inserito in coda
            if(i < nodeQueue.length)
            {
                a = new Node[nodeQueue.length+1];
                a[i] = n;
                
                for(int j = 0; j < i; j++)
                { a[j] = nodeQueue[j]; }
                
                for(int j = i+1; j < a.length; j++)
                { a[j] = nodeQueue[j-1]; }
            } else { // n va inserito in coda
                a = new Node[nodeQueue.length+1];
                a[a.length-1] = n;
                
                for(int j = 0; j < a.length-1; j++)
                { a[j] = nodeQueue[j]; }
            }
        }
        
        nodeQueue = a;
    }
    
    private int compareWeights(Node m, Node n)
    {
        // -1: minore
        // 0 : uguale
        // +1: maggiore
        if(m.weight() < n.weight())
            return -1;
        else if(m.weight() == n.weight())
            return 0;
        else// (this.wgt > n.wgt)
            return +1;
    }
}
