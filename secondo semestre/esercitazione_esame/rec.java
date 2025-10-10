/**
 * esercitazione d'esame
 */
public class rec
{
    public static long rec(int x, int y, int z){
        if((x ==1 ) || (y == z)){
            return 1;
        }else {return rec ( x-1, y , z) + x * rec(x, y+1, z);
        }
    }

    /**
     * con valori rec(8,5,12) ci si chiede:
     * valore più piccolo di x  =  1
     * valore più piccolo di y  =  5
     * valore più piccolo di z  = 12
     * valore più grande di x   =  8
     * valore più grande di y   = 12
     * valore più grande di z   = 12x
     * 
     * converti il codice per renderlo più efficente
     */

    public static long recPD(int x, int y, int z){
        long[][] mem = new long[x+1][z+2];   //chiedere se sarebbe più efficente scrivere [z-y+1] per evitare tutti i valori minori di y non utilizz
        for (int col= y; col<=z-y+1; col=col+1){
            mem[1][col]=1;
        }
        for(int row=2;row<=y;row=row+1){
            mem[row][x]=1;
        }
        
        for(int row=2;row<=x;row=row+1){
            for(int col=z-1; col>=y;col=col-1){
                mem[row][col]=mem[row-1][col]+row*mem[row][col+1];
            }
        }
        return mem[x][y];
    }
}

