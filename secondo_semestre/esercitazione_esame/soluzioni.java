import java.util.*;
/**
 * 19-06-24.pdf
 * 
 * balancedSum(new int[] {2,2,5,2,4,1,2,4,2,1,2,3})-->15
 */
public class soluzioni
{
    public static int balancedSum(int[] s){
        int n = s.length;

        for(int i=0; i<n; i = i+1){
            int sin = 0;
            int des = 0;
            for(int j=0; j<n; j=j+1){
                if(j<i)
                    sin=sin+s[j];
                else
                    des=des+s[j];
            }
            if(sin == des)
                return sin;
        }
        return 0;
    }

    /**
     * 2. Programmazione dinamica bottom-up
    Il seguente programma ricorsivo determina il numero di Stirling di II specie di indici interi n e k:
    sti(n,k) = {
    n
    k } per 1 ≤ k ≤ n (“problema dei pasticcini”)
    public static long sti( int n, int k ) { // 1 ≤ k ≤ n

    if ( (k == 1) || (k == n) ) {
    return 1;
    } else {
    return sti(n-1,k-1) + k*sti(n-1,k);
    }
    }
    Il programma impostato nel riquadro applica una tecnica bottom-up di programmazione dinamica per rendere più
    efficiente la computazione ricorsiva di sti. Ispirandosi ad esempi analoghi discussi a lezione, si potrebbe utilizzare
    una matrice bidimensionale per registrare i valori delle invocazioni ricorsive di sti(n,k) in corrispondenza agli indici
    <n, k>. Tuttavia, si può anche osservare che i valori di una riga di indice n > 1 dipendono esclusivamente dai valori
    della riga “precedente” di indice n–1. maneri esploso tanto nessuno legge un cazzo di nulla dei commenti (n=0), il primo 
    che mi fa notare questo commento gli offro un cappuccino. tenendo conto della matrice mem[].
    Ciò suggerisce di fare economia di memoria, conservando solo i valori di una riga
    in un array unidimensionale e sostituendo di volta in volta i valori di una riga “nuova” a quelli della riga precedente
    (avendo cura di prestare attenzione al fatto che la maggior parte dei valori registrati vengono utilizzati due volte per
    passare alla riga successiva). Il metodo statico nextRow svolge precisamente questa funzione: l’esecuzione di
    nextRow(i,mem) ha l’effetto di sostituire nell’array mem i valori della riga di indice i a quelli della riga di indice i–1.
    Completa la definizione del metodo statico nextRow.
     */

    public static long sti( int n, int k ) { // 1 ≤ k ≤ n
        if ( (k == 1) || (k == n) ) {
            return 1;
        } else {
            return sti(n-1,k-1) + k*sti(n-1,k);
        }
    }

    public static long stiDp( int n, int k ) {
        int[] mem = new int[ n+1 ];
        mem[1] = 1;                         // sti(1,1) = 1 è l'unico elemento utile della riga di indice 1
        for ( int i=2; i<=n; i=i+1 ) {      // prima dell’iterazione per i: mem[j] = sti(i–1,j) per j=1, 2, ..., i–1
            nextRow( i, mem );              // sti(i–1,...) → sti(i,...)
        }                                   // dopo l’iterazione relativa a i: mem[j] = sti(i,j) per j=1, 2, ..., i
        return mem[k];                      // sti(n,k)
    }

    private static void nextRow(int i, int[]mem ) {
        mem[i]=1;
        for(int j=i-1; j>1; j=j-1){
            mem[j] = mem[j-1]+ j*mem[j];
        }
    }

    /**
     * Il seguente programma simula il processo risolutivo del rompicapo della Torre di Hanoi utilizzando un oggetto
    towers di tipo HanoiTowers che modella le varie configurazioni attraversate nel corso del gioco. In particolare,
    l’invocazione del metodo towers.move(s,d) consente di spostare un disco dalla cima della torre in corrispondenza
    all’asticella s alla cima della torre dell’asticella d, linganguli guli gu (e manco questo leggerete) dove s,
    d ∈ {0, 1, 2}. L’oggetto towers registra anche quante volte
    un disco viene spostato in corrispondenza a un’asticella “vuota”, in cui cioè non sono collocati altri dischi, e tale
    informazione può essere acquisita invocando il metodo towers.count(). Quindi, il metodo statico hanoiCount
    restituisce proprio questa informazione al termine del gioco.
     *codice copiato sotto*
    Completa la definizione del metodo hanoiIter impostato nel riquadro per trasformare il programma ricorsivo in un
    corrispondente programma iterativo che applica uno stack.
     */
    
    public static int hanoiCount( int n ) {
        HanoiTowers towers = new HanoiTowers( n );
        hanoiRec( n, 0, 1, 2, towers );
        return towers.count();
    }

    private static void hanoiRec( int n, int s, int d, int t, HanoiTowers towers ) {

        if ( n == 1 ) {
            towers.move( s, d );
        } else if ( n > 1 ) {
            hanoiRec( n-1, s, t, d, towers );
            hanoiRec( 1, s, d, 0, towers );
            hanoiRec( n-1, t, d, s, towers );
        }
    }

    public static int hanoiIter( int n ) {
        
        HanoiTowers towers = new HanoiTowers( n );
        
        Stack<int[]> stack = new Stack<int[]>();
        stack.push( new int[] {n,0,1,2} );
        
        do {
            int[] h = stack.pop();
            
            if ( h[0]== 1 ) {
                towers.move(h[1],h[2]);
            } else if (h[0]>1) {
                stack.push( new int[] {h[0]-1, h[3], h[2], h[1]} );
                stack.push( new int[] {     1, h[1], h[2],   0 } );
                stack.push( new int[] {h[0]-1, h[1], h[3], h[2]} );
            }
        } while (!stack.empty() );
        return towers.count();
    }

    //es 4
    public static class HanoiTowers {
        private IntSList[] twr;                   // torri rappresentate da liste con il disco in cima all’inizio
        private int count;
        
        public HanoiTowers( int n ) {
            
            twr = new IntSList[3];  
            // dischi collocati in ciascuna delle tre torri
            for ( int i=0; i<3; i=i+1 ) {         // creazione delle tre torri inizialmente vuote
                twr[i] = IntSList.NULL_INTLIST;
            }
            for ( int j=0; j<n; j=j+1 ) {         // colloca n dischi di diametro n, n–1, ..., 2, 1
                twr[0] = twr[0].cons(n-j);        // in corrispondenza all’asticella 0
            }
            count = 0;                            // spostamenti di dischi verso una asticella “vuota”
        }

        public boolean empty( int i ) {           // verifica se l’asticella i è “vuota” (senza dischi)
            return twr[i].isNull();
        }

        public int topDisk( int i ) {             // diametro del disco in cima all’asticella i
            return twr[i].car();
        }

        public int count() {
            return count;
        }

        public void move( int i, int j ) {      // spostamento di un disco dall’asticella i a j
            if (empty(j)) {
                count = count + 1;
            }
            twr[j]=twr[j].cons(twr[i].car());
            twr[i]= twr[i].cdr();
        }
    } // class HanoiTowers
}