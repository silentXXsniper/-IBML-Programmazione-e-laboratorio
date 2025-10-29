import java.util.Random;
import puzzleboard.PuzzleBoard;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Protocollo
 * 
 *  + Costruttore/i
 *     Bar b = new Bar(int n);
 * 
 *  + Metodi con info sulla configurazione
 *     b.isSorted()               : boolean
 *     b.canBeMoved(int i, int j
 *                  int x, int y) : boolean
 *     b.arrangement()            : String
 *  
 *  + Metodi per nuova configurazione
 *     b.moveTile(int i, int j
 *                int x, int y)   : Bar
 *     
 */
public class Bar
{
    // Variabili d'istanza
    private final int     size;
    private       int[][] tiles;
    private       String  config;

    // Costruttore/i
    public Bar(int n)
    {
        size   = n;
        tiles  = createRandomTiling(n);
        config = computeConfiguration();
    }

    // Metodi di appoggio al costruttore
    private int[][] createRandomTiling(int n)
    {
        int[][] a = new int[n+1][n+1];    
        int k = 1;
        int roof = (int) Math.pow(size, 2)-1;
        
        for (int i = 1; i < a.length; i++)
        {
            for(int j = 1; j < a.length; j++)
            {
                a[i][j] = (k > roof ? -1 : k);
                k++;
            }
        }
        a[a.length-1][a.length-1] = 0;
        // System.out.println("Prima dello shuffle: " + Arrays.deepToString(a));
        
        a = shuffle(a);
        
        return a;
    }

    private int[][] shuffle(int[][] a)
    {
        Random rgen1 = new Random();
        Random rgen2 = new Random();
        
        for (int i=1; i < a.length; i++) 
        {
            for (int j=1; j < a.length; j++)
            {
                int randomPosition1 = rgen1.nextInt(a.length);
                int randomPosition2 = rgen2.nextInt(a.length);
                
                while(randomPosition1 == 0 || randomPosition2 == 0)
                {
                    rgen1 = new Random();
                    rgen2 = new Random();
                    
                    randomPosition1 = rgen1.nextInt(a.length);
                    randomPosition2 = rgen2.nextInt(a.length);
                }
                
                int temp = a[i][j];
                
                a[i][j] = a[randomPosition1][randomPosition2];
                a[randomPosition1][randomPosition2] = temp;
            }
        }
        // System.out.println("Dopo lo shuffle: " + Arrays.deepToString(a));
        
        return a;
    }
    
    // Metodi per informazioni sulla configurazione
    public int size()
    {
        return size;
    }

    public String arrangement()
    {
        return config;   
    }

    public int getElementAt(int i, int j)
    {
        if(size() > 0)
            return tiles[i][j];
        else
            return -1;
    }
    
    public void setElementAt(int i, int j, int k)
    {
        if(size() > 0)
            tiles[i][j] = k;
    }
    
    public boolean isSorted()
    {
        int k = 1;
        int roof = (int) Math.pow(size(), 2)-1;

        if(roof <= 0)
            return true;
        
        for(int i = 1; i < tiles.length; i++)
        {
            for(int j = 1; j < tiles.length; j++)
            {
                if( k != roof )
                {
                    if( tiles[i][j] != k )
                        return false;
                    else
                        k++;
                } else {
                    return (tiles[i][j] == k ? true : false);
                }
            }
        }
        
        return true; 
    }

    private boolean canBeMoved(int i, int j,
                               int x, int y)
    {
        if(tiles[x][y] == 0)
        {
            if( (Math.abs(x-i) <= 1 && Math.abs(y-j) == 0) || (Math.abs(x-i) == 0 && Math.abs(y-j) <= 1) )
            {
                return true;
            }
        }
        return false;
    }

    // Metodi per modificare configurazione
    public void moveTile(int i, int j,
                         int x, int y)
    {
        if( canBeMoved( i, j, x, y ) )
        {
            int tmp = tiles[i][j];
            tiles[i][j] = -1;
            tiles[x][y] = tmp;
        }

        config = computeConfiguration();
    }

    private String computeConfiguration()
    {
        String s = "";

        for(int i = 1; i < tiles.length; i++)
        {
            s += "[";
            for(int j = 1; i < tiles.length; i++)
            {
                s+=tiles[i][j]+"][";
            }
            s += "] \n";
        }

        return s;
    }

    // Metodo per giocare
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in); 
        System.out.print("Enter the size of the bar: "); 
        Bar b = new Bar(sc.nextInt());
        
        /** Generazione situazione iniziale */
        PuzzleBoard gui = new PuzzleBoard(b.size());
        for(int i = 1; i <= b.size(); i++)
        {
            for(int j = 1; j <= b.size(); j++)
            {
                int k = b.getElementAt(i,j);
                gui.setNumber(i, j, k);
                gui.display();
            }
        }
        
        /** GameLoop - finché b non è ordinato -> gioca */
        while( !b.isSorted() )
        {
            /** Acquisizione tassello da spostare*/
            int toMove = gui.get();
            int[] from = new int[2];
            
            from = getTileNumber(toMove, b);
            // System.out.println("from["+from[0]+"]["+from[1]+"] : "+toMove);
            
            /** Acquisizione posizione di arrivo */
            int toReach = gui.get();
            int[] to = new int[2];
            
            to = getTileNumber(toReach, b);
            // System.out.println("toReach="+toReach+"\n to["+to[0]+"]["+to[1]+"] : "+toReach);
            
            /** Spostamento tile (o niente, se il tile non è spostabile) */
            newScenario(gui, b, from, to);
            // System.out.println("is b sorted? : " + b.isSorted());
        }
        
        
    }
    
    // Metodi di appoggio al gioco
    private static int[] getTileNumber(int k, Bar b)
    {
        for(int i = 1; i <= b.size(); i++)
        {
            for(int j = 1; j <= b.size(); j++)
            {
                if(k == b.getElementAt(i,j))
                {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0,0};
    }
    
    private static void newScenario(PuzzleBoard gui, Bar b, int[] from, int[] to)
    {
        int fromRow = from[0];
        int fromCol = from[1];
        int toRow = to[0];
        int toCol = to[1];
        
        System.out.println("can be moved? : "+b.canBeMoved(fromRow, fromCol, toRow, toCol));  
        
        if(b.canBeMoved(fromRow, fromCol, toRow, toCol))
        { 
            gui.clear(fromRow, fromCol);
            b.moveTile(fromRow, fromCol, toRow, toCol);
            b.setElementAt(fromRow, fromCol, 0);
            gui.setNumber(toRow, toCol, b.getElementAt(toRow,toCol));
            gui.display();
        }
    }
}