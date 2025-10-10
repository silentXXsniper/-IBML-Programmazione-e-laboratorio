import java.util.Scanner;
import puzzleboard.PuzzleBoard;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci la dimensione del puzzle (n): ");//inizio
        int n = scanner.nextInt();
        Puzzle puzzle = new Puzzle(n);
        puzzle.shuffle(100); //100 mosse possibili
        PuzzleBoard gui = new PuzzleBoard(n);
        // Inizializza GUI
        int[][] currentBoard = puzzle.getBoard();//tab mescolata
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int t = currentBoard[i][j];
                if (t == 0) gui.clear(i + 1, j + 1);
                else gui.setNumber(i + 1, j + 1, t);
            }
        }
        gui.display();
        System.out.println("start");
        // Ciclo finché non viene risolto
        while (true) {
            int tile = gui.get();
            System.out.println("Hai cliccato sul tassello: " + tile);
            if (puzzle.canMove(tile)) {
                puzzle.move(tile);
                currentBoard = puzzle.getBoard();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int t = currentBoard[i][j];
                        if (t == 0) 
                            gui.clear(i + 1, j + 1);
                        else 
                            gui.setNumber(i + 1, j + 1, t);
                    }
                }
                gui.display();
                if (puzzle.isSolved()) {
                    System.out.println("risolto");//quadro ordinato
                    break;
                }
            } else {
                System.out.println("errore, invalid move" + tile);
                //mossa invalida
            }
        }
        System.out.println("Fine");
    }
}