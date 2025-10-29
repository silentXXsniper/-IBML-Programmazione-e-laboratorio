import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Puzzle {
    private int n; //gioco nxn
    private int[][] board; //config iniziale
    private int emptyRow, emptyCol; //posizione casella vuota
    //constructor, tavola e casella vuota
    public Puzzle(int n) {
        this.n = n;//dimensione
        board = new int[n][n];//matrice valori
        int val = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (val < n * n) {
                    board[i][j] = val++;//all'inizio ordinata
                } else {
                    board[i][j] = 0; // lacuna
                    emptyRow = i;//x vuota
                    emptyCol = j;//y vuota
                }
            }
        }
    }
    // Mescola la tavoletta con un numero di mosse valide casuali
    public void shuffle(int moves) {
        Random rand = new Random();
        for (int i = 0; i < moves; i++) {
            List<int[]> possibleMoves = new ArrayList<>();
            // Tasselli adiacenti alla lacuna
            if (emptyRow > 0)
            possibleMoves.add(new int[]{emptyRow - 1, emptyCol});
            if (emptyRow < n - 1)
            possibleMoves.add(new int[]{emptyRow + 1, emptyCol});
            if (emptyCol > 0)
            possibleMoves.add(new int[]{emptyRow, emptyCol - 1});
            if (emptyCol < n - 1)
            possibleMoves.add(new int[]{emptyRow, emptyCol + 1});
            Collections.shuffle(possibleMoves, rand);
            int[] move = possibleMoves.get(0);//faccio un tot di mosse possibili a caso
            int tile = board[move[0]][move[1]];
            move(tile);
        }
    }
    // Getter per la configurazione
    public int[][] getBoard() {
        return board;//ritorna la base configurata
    }
    // Verifica se la tavoletta è ordinata
    public boolean isSolved(){
        int val = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) {
                    if (board[i][j] != 0) 
                        return false;
                } else {
                    if (board[i][j] != val) 
                        return false;
                    val++;
                }
            }
        }//vedo se ci sono casi "disordinati"
        return true;//torno vero solo se passo tutti i controlli
    }
    // Verifica se un tassello può essere mosso
    public boolean canMove(int tile) {
        if (tile <= 0 || tile >= n * n) return false;
        int tileRow = -1, tileCol = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == tile) {
                    tileRow = i;
                    tileCol = j;
                    break;
                }
            }
            if (tileRow != -1) break;
        }
        if (tileRow == -1) 
            return false;
        int dr = Math.abs(tileRow - emptyRow);
        int dc = Math.abs(tileCol - emptyCol);
        return ( (dr == 1 && dc == 0) || (dr == 0 && dc == 1) );
    }
    // Sposta il tassello se possibile
    public boolean move(int tile) {
        if (!canMove(tile)) return false;//vedo se posso spostarla
        int tileRow = -1, tileCol = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == tile) {
                    tileRow = i;
                    tileCol = j;
                    break;
                }
            }
            if (tileRow != -1) break;
        }
        board[emptyRow][emptyCol] = tile;
        board[tileRow][tileCol] = 0;
        emptyRow = tileRow;
        emptyCol = tileCol;
        return true;
    }
    // Rappresentazione testuale
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    sb.append("   ");
                } else {
                    sb.append(String.format("%2d ", board[i][j]));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
