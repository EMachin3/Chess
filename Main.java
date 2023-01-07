import java.util.*;
import java.io.IOException;
public class Main {
    public static void printGrid(Square[][] griddy)
    {
        //maybe add legend to the grid?
        for (int i = 8; i >= 1; i--)
        {
            for (int j = 1; j <= 8; j++)
            {
                System.out.print(griddy[i][j].getDisplay() + " ");
            }
            System.out.println();
        }
    }
    //public static boolean isMoveValid(char pieceType, int[] moves {this is moveSummary eric}, griddy);
    //if knight, magnitude of vector between start (delta x and delta y) and end should be sqrt 5
    //if pawn, should only move one up if white or one down if black. if diagonal, should be piece on grid.
    //rook: either delta x or delta y should be 0. if so, check if each square between them is empty.
    //bishop: abs(delta x) and abs(delta y) should be same. also check each square between.
    //queen: do rook and bishop
    //king: neither delta greater than one, also can't move to a square that is underAttack

    public static void main(String[] args) throws IOException, InterruptedException {
        Square[][] grid = new Square[9][9];
        /*for (int i = 1; i < 2; i++)
        {
            for (int j = 1; j < 9; j++)
            {
                grid[i][j] = new Square(PieceTypes.BISHOP, 'B');
            }
        }*/
        for (int i = 1; i < 9; i++)
        {
            for (int j = 1; j < 9; j++)
            {
                grid[i][j] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
            }
        }
        //make white uppercase and black lowercase, maybe make the board rotate 180 degrees inbetween turns
        grid[4][3] = new Square(PieceTypes.QUEEN, 'Q', PieceColor.EMPTY);
        printGrid(grid);
        /*for (int i = 8; i >= 1; i--)
        {
            for (int j = 1; j <= 8; j++)
            {
                System.out.print(grid[i][j].getDisplay() + " ");
            }
            System.out.println();
        }*/
        Scanner scanner = new Scanner(System.in);
        boolean invalidMove = true;
        String move = "";
        while (invalidMove)
        {
            System.out.println("Enter a move in this 5-character format: [PieceLetter][StartCoord][EndCoord]");
            move = scanner.next();
            if (move.length() == 5)
            {
                System.out.println();
                invalidMove = false;
            }
            else
            {
                System.out.println("Enter a valid move.");
            }
        }
        char piece = move.charAt(0); //make a function that takes this as a parameter and determines if the move is valid for that piece
        String start = move.substring(1, 3);
        int startRow = start.charAt(1) - '0';
        int startColumn = start.charAt(0) - 96;
        int endRow = end.charAt(1) - '0';
        int endColumn = end.charAt(0) - 96;
        int[] moveSummary = {startRow, startColumn, endRow, endColumn}; //used for isMoveValid() function
        String end = move.substring(3);
        try
        {
            if (grid[startRow][startColumn].getType() == PieceTypes.EMPTY)
            {
                System.out.println("No piece there.");
            }
            else
            {
                //System.out.println("Cool");
                if (grid[endRow][endColumn].getType() != PieceTypes.EMPTY)
                {
                    System.out.println("Square occupied.");
                }
                else
                {
                    //if (isMoveValid(piece, moveSummary, grid)) {do the stuff below}
                    grid[endRow][endColumn] = grid[startRow][startColumn];
                    grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                    //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                    printGrid(grid);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Make sure the move is in range!");
        }
        System.out.println("Move: " + move);
        scanner.close();
    }
}
