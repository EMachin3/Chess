import java.util.*;
import java.io.IOException;
public class Main {
    public static void printGrid(Square[][] griddy)
    {
        for (int i = 8; i >= 1; i--)
        {
            for (int j = 1; j <= 8; j++)
            {
                System.out.print(griddy[i][j].getDisplay() + " ");
            }
            System.out.println();
        }
    }
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
                grid[i][j] = new Square(PieceTypes.EMPTY, '_');
            }
        }
        grid[4][3] = new Square(PieceTypes.QUEEN, 'Q');
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
        String end = move.substring(3);
        try
        {
            if (grid[start.charAt(1) - '0'][start.charAt(0) - 96].getType() == PieceTypes.EMPTY)
            {
                System.out.println("No piece there.");
            }
            else
            {
                //System.out.println("Cool");
                if (grid[end.charAt(1) - '0'][end.charAt(0) - 96].getType() != PieceTypes.EMPTY)
                {
                    System.out.println("Square occupied.");
                }
                else
                {
                    grid[end.charAt(1) - '0'][end.charAt(0) - 96] = grid[start.charAt(1) - '0'][start.charAt(0) - 96];
                    grid[start.charAt(1) - '0'][start.charAt(0) - 96] = new Square(PieceTypes.EMPTY, '_');
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
