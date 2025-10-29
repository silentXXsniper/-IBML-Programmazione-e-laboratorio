
public class TavRotonda { // n>0
    private int n;                   // numero di cavalieri a tavola
    private int b;                   // etichetta del cavaliere con la brocca
    private int[] cav;               // lista degli altri cavalieri (numerati) (indice = posizione reciproca)

    // ----- Costruttore pubblico
    public TavRotonda( int n ) {             // creazione di una tavola con n cavalieri
        this.n = n;
        b = 0;

        cav = new int[2*n-1];
        for(int k=1; k<=n; k++){
            cav[k-1] = k;
        }
    }

    // ----- Metodi del protocollo: acquisizione di informazioni sulla configurazione
    public int numCavalieri() {
        return n;
    }

    public int cavConBrocca() { 
        return cav[b];
    }

    // ----- Metodi del protocollo: generazione di configurazioni successive
    public void serve() {     // serve il commensale vicino a sinistra, il servito lascia la tavola
        if ( n > 1 ){
            //return new TavRotonda(n-1, brocca, altri.cdr());
            cav[b+1] = cav[b];
            b++;
            n--;
        }    
    }

    public void passa() {            // passa la brocca al commensale vicino (a sinistra)
        if ( n > 1 ) {
            cav[b+n] = cav[b]; //prima posizione vuota, ci copio il primo cav che passa la brocca e finisce quindi per ultimo
            b++;
        }
    }


}  // class TavRotonda
