 
/**
 * Write a description of class Queens here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Queens
{
    public static int  numeroSoluzioni(int n){
        
    return numeroCompletamenti( new Board(n));
    }
    
    private static int numeroCompletamenti(Board b){
    int n=b.size();
    int q=b.queensOn();
    
    if(q == n){
    return 1;
    }else{
     int count = 0;
     int i = q + 1;
     for(int j=1; j<=n; j=j+1){
        if(!b.underAttack(i,j)){
        count= count+ numeroCompletamenti(b.addQueen(i,j));
        }
        }
        return count;
    }
    }
    
    
    //Boardlist:esercizio
    
    public static int  listaSoluzioni(int n){
        
    return numeroCompletamenti( new Board(n));
    }
    
    private static int listaCompletamenti(Board b){
    int n=b.size();
    int q=b.queensOn();
    
    if(q == n){
    return BoardSList.NULL_BOARDLIST.cons(b);
    }else{
     BoardSList lista = BoardSList.NULL_BOARDKLIST;
     int i = q + 1;
     for(int j=1; j<=n; j=j+1){
        if(!b.underAttack(i,j)){
        lista= lista.append(listaCompletamenti(b.addQueen(i,j)));
        }
        }
        return lista;
    }
    }
    
    
    
    

}// class Queens
