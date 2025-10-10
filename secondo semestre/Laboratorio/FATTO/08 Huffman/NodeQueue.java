public class NodeQueue {
    private Node[] queue;      // Array che rappresenta la coda
    private int size;          // Numero di elementi attualmente nella coda
    public NodeQueue () {
        queue = new Node[10000]; // Inizializza la coda con capacità massima di 10000 elementi
        size = 0;                 // Inizializza la dimensione a 0
    }
    public int size () {
        return size; // Restituisce il numero di elementi nella coda
    }
    public Node peek () {
        int min = lower(); // Trova il valore minimo del peso tra tutti i nodi
        for ( int i = 0; i < size; i++ ) {
            if ( queue[i].weight() == min ) {
                return queue[i]; // Restituisce il primo nodo con peso minimo
            }
        }
        return null; // Se non trova alcun nodo (caso improbabile), restituisce null
    }
    private int lower () {
        int min = queue[0].weight(); // Assume che il primo nodo abbia il peso minimo
        for ( int i = 0; i < size; i++ ) {
            if ( queue[i].weight() < min ) {
                min = queue[i].weight(); // Aggiorna il minimo se trova un peso inferiore
            }
        }
        return min; // Restituisce il peso minimo trovato
    }
    public Node poll () {
        Node minNode = peek(); // Trova il nodo con il peso minimo
        boolean find = false;
        int i = 0;
        do {
            if ( minNode.weight() == queue[i].weight() ) {
                find = true; // Trova la prima occorrenza del nodo con peso minimo
            } else {
                i++;
            }
        } while ( ! find );
        size--; // Decrementa la dimensione della coda
        for ( ; i < size; i++ ) {
            queue[i] = queue[i + 1]; // Sposta tutti gli elementi a sinistra per rimuovere il nodo
        }
        return minNode; // Restituisce il nodo rimosso
    }
    public void add ( Node n ) {
        queue[size] = n; // Aggiunge un nuovo nodo in coda
        size++;          // Incrementa la dimensione
    }
}