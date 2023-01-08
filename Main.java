import java.util.*;
import java.io.IOException;
import java.lang.Math;
public class Main {
    public static void printGrid(Square[][] griddy) //make a print func for white and one for black
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
    public static boolean isMoveValid(char pieceType, int[] moves, Square[][] griddy)
    {
        try
        {
        int deltaRow = moves[2] - moves[0];
        int deltaColumn = moves[3] - moves[1];
        if (pieceType == 'P' || pieceType == 'p') //white is uppercase, black is lowercase. this is pawn
        {
            if (griddy[moves[0]][moves[1]].getColor() == PieceColor.WHITE)
            {
                if (deltaColumn == 0 && deltaRow == 1)
                {
                    return griddy[moves[2]][moves[3]].getType() == PieceTypes.EMPTY;
                }
                else if (deltaColumn == 0 && deltaRow == 2 && moves[0] == 2)
                {
                    return griddy[moves[0] + 1][moves[1]].getType() == PieceTypes.EMPTY && griddy[moves[2]][moves[3]].getType() == PieceTypes.EMPTY;
                }
                else if (Math.abs(deltaColumn) == 1 && deltaRow == 1)
                {
                    return griddy[moves[2]][moves[3]].getType() != PieceTypes.EMPTY && griddy[moves[2]][moves[3]].getColor() == PieceColor.BLACK;
                }
                else
                {
                    return false;
                }
            }
            else //black piece
            {
                if (deltaColumn == 0 && deltaRow == -1)
                {
                    return griddy[moves[2]][moves[3]].getType() == PieceTypes.EMPTY;
                }
                else if (deltaColumn == 0 && deltaRow == -2 && moves[0] == 7)
                {
                    return griddy[moves[0] - 1][moves[1]].getType() == PieceTypes.EMPTY && griddy[moves[2]][moves[3]].getType() == PieceTypes.EMPTY;
                }
                else if (Math.abs(deltaColumn) == 1 && deltaRow == -1)
                {
                    return griddy[moves[2]][moves[3]].getType() != PieceTypes.EMPTY && griddy[moves[2]][moves[3]].getColor() == PieceColor.WHITE;
                }
                else
                {
                    return false;
                }
            } //1 down, 5 to go.
        }
        else if (pieceType == 'N' || pieceType == 'n') //knight
        {
            //if (griddy[moves[0]][moves[1]].getColor() == PieceColor.WHITE)
            //{
            if ((Math.abs(deltaRow) == 1 && Math.abs(deltaColumn) == 2) || (Math.abs(deltaRow) == 2 && Math.abs(deltaColumn) == 1))
            {
                if (griddy[moves[0]][moves[1]].getColor() == PieceColor.WHITE)
                {
                    return griddy[moves[2]][moves[3]].getColor() != PieceColor.WHITE;
                }
                else //black
                {
                    return griddy[moves[2]][moves[3]].getColor() != PieceColor.BLACK;
                }
            }
            else
            {
                return false;
            }
            //}
            //else //black
            //{
                /*if ((Math.abs(deltaRow) == 1 && Math.abs(deltaColumn) == 2) || (Math.abs(deltaRow) == 2 && Math.abs(deltaColumn) == 1))
                {
                    return griddy[moves[2]][moves[3]].getColor() != PieceColor.BLACK;
                }
                else
                {
                    return false;
                }*/
            //}
        }
        else if (pieceType == 'R' || pieceType == 'r') //rook
        {
            //if (griddy[moves[0]][moves[1]].getColor() == PieceColor.WHITE)
            if (deltaRow == 0 && deltaColumn != 0)
            {
                if (deltaColumn > 0)
                {
                    for (int i = 1; i < deltaColumn; i++)
                    {
                        if (griddy[moves[0]][moves[1] + i].getType() != PieceTypes.EMPTY)
                        {
                            return false;
                        }
                    }
                }
                else //deltaColumn < 0
                {
                    for (int i = -1; i > deltaColumn; i--)
                    {
                        if (griddy[moves[0]][moves[1] + i].getType() != PieceTypes.EMPTY)
                        {
                            return false;
                        }
                    }
                }
            }
            else if (deltaColumn == 0 && deltaRow != 0)
            {
                if (deltaRow > 0)
                {
                    for (int i = 1; i < deltaRow; i++)
                    {
                        if (griddy[moves[0] + i][moves[1]].getType() != PieceTypes.EMPTY)
                        {
                            return false;
                        }
                    }
                }
                else //deltaRow < 0
                {
                    for (int i = -1; i > deltaRow; i--)
                    {
                        if (griddy[moves[0] + i][moves[1]].getType() != PieceTypes.EMPTY)
                        {
                            return false;
                        }
                    }
                }
            }
            else
            {
                return false;
            }
            if (griddy[moves[0]][moves[1]].getColor() == PieceColor.WHITE)
            {
                return griddy[moves[2]][moves[3]].getColor() != PieceColor.WHITE;
            }
            else //black
            {
                return griddy[moves[2]][moves[3]].getColor() != PieceColor.BLACK;
            }
        }
        return false;
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Move not in range.");
            return false;
        }
        //TODO: add the remaining pieces, add an undo, make it so black gets a flipped print, add promoting and castling, make some way to initialize a grid from a text file
    }
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
        grid[4][4] = new Square(PieceTypes.ROOK, 'R', PieceColor.WHITE);
        //grid[4][4] = new Square(PieceTypes.KNIGHT, 'N', PieceColor.WHITE);
        //grid[5][6] = new Square(PieceTypes.KNIGHT, 'n', PieceColor.BLACK);
        grid[4][6] = new Square(PieceTypes.PAWN, 'p', PieceColor.BLACK);
        //grid[2][3] = new Square(PieceTypes.PAWN, 'P', PieceColor.WHITE);
        //grid[5][4] = new Square(PieceTypes.QUEEN, 'q', PieceColor.BLACK);
        //grid[7][6] = new Square(PieceTypes.PAWN, 'p', PieceColor.BLACK);
        printGrid(grid);
        /*for (int i = 8; i >= 1; i--)
        {
            for (int j = 1; j <= 8; j++)
            {
                System.out.print(grid[i][j].getDisplay() + " ");
            }
            System.out.println();
        }*/
        boolean firstTime = true;
        boolean finished = true;
        Scanner scanner = new Scanner(System.in);
        while (finished)
        {
        String move = "";
        boolean invalidMove = true;
        while (invalidMove)
        {
            System.out.println("Enter a move in this 5-character format: [PieceLetter][StartCoord][EndCoord]");
            if (firstTime)
            {
                System.out.println("Enter \"Finished\" when you are done.");
                firstTime = false;
            }
            move = scanner.next();
            if (move.length() == 5)
            {
                System.out.println();
                invalidMove = false;
            }
            else if (move.equals("Finished"))
            {
                finished = false;
                break;
            }
            else
            {
                System.out.println("Enter a valid move.");
            }
        }
        char piece = move.charAt(0); //make a function that takes this as a parameter and determines if the move is valid for that piece
        String start = move.substring(1, 3);
        String end = move.substring(3);
        int startRow = start.charAt(1) - '0';
        int startColumn = start.charAt(0) - 96;
        int endRow = end.charAt(1) - '0';
        int endColumn = end.charAt(0) - 96;
        int[] moveSummary = {startRow, startColumn, endRow, endColumn}; //used for isMoveValid() function
        try
        {
            if (grid[startRow][startColumn].getType() == PieceTypes.EMPTY)
            {
                System.out.println("No piece there.");
            }
            else
            {
                //System.out.println("Cool");
                //eventually remove this and deal with it in isMoveValid
                // replace if else with this: if (isMoveValid(piece, moveSummary, grid)) move the piece, else say move invalid
                if (isMoveValid(piece, moveSummary, grid))
                {
                    System.out.println("Move accepted.");
                    grid[endRow][endColumn] = grid[startRow][startColumn];
                    grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                    //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                    printGrid(grid);
                }
                else
                {
                    System.out.println("Invalid move.");
                }
                /*if (grid[endRow][endColumn].getType() != PieceTypes.EMPTY) 
                {
                    System.out.println("Square occupied.");
                }*/
                /*else
                {
                    grid[endRow][endColumn] = grid[startRow][startColumn];
                    grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                    //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                    printGrid(grid);
                }*/
            }
        } catch (ArrayIndexOutOfBoundsException e)
        {
            if (finished)
            {
                System.out.println("Make sure the move is in range!");
            }
        }
        System.out.println("Move: " + move);
        }
        scanner.close();
    }
}
