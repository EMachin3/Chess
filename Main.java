import java.util.*;
import java.io.IOException;
import java.lang.Math;
public class Main {
    public static boolean blackHasCastled = false;
    public static boolean whiteHasCastled = false;
    public static void printGridWhite(Square[][] griddy)
    {
        //maybe add legend to the grid?
        for (int i = 8; i >= 1; i--)
        {
            System.out.print(i + " ");
            for (int j = 1; j <= 8; j++)
            {
                System.out.print(griddy[i][j].getDisplay() + " ");
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    public static void printGridBlack(Square[][] griddy)
    {
        for (int i = 1; i < 9; i++)
        {
            System.out.print(i + " ");
            for (int j = 8; j >= 1; j--)
            {
                System.out.print(griddy[i][j].getDisplay() + " ");
            }
            System.out.println();
        }
        System.out.println("  h g f e d c b a");
    }
    public static void initializeGrid(Square[][] griddy) //somehow make a function that loads this from a text file, cuz this is ugly
    {
        griddy[1][1] = new Square(PieceTypes.ROOK, 'R', PieceColor.WHITE);
        griddy[1][2] = new Square(PieceTypes.KNIGHT, 'N', PieceColor.WHITE);
        griddy[1][3] = new Square(PieceTypes.BISHOP, 'B', PieceColor.WHITE);
        griddy[1][4] = new Square(PieceTypes.QUEEN, 'Q', PieceColor.WHITE);
        griddy[1][5] = new Square(PieceTypes.KING, 'K', PieceColor.WHITE);
        griddy[1][6] = new Square(PieceTypes.BISHOP, 'B', PieceColor.WHITE);
        griddy[1][7] = new Square(PieceTypes.KNIGHT, 'N', PieceColor.WHITE);
        griddy[1][8] = new Square(PieceTypes.ROOK, 'R', PieceColor.WHITE);
        for (int i = 1; i < 9; i++)
        {
            griddy[2][i] = new Square(PieceTypes.PAWN, 'P', PieceColor.WHITE);
        }
        for (int i = 3; i < 7; i++)
        {
            for (int j = 1; j < 9; j++)
            {
                griddy[i][j] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
            }
        }
        for (int i = 1; i < 9; i++)
        {
            griddy[7][i] = new Square(PieceTypes.PAWN, 'p', PieceColor.BLACK);
        }
        griddy[8][1] = new Square(PieceTypes.ROOK, 'r', PieceColor.BLACK);
        griddy[8][2] = new Square(PieceTypes.KNIGHT, 'n', PieceColor.BLACK);
        griddy[8][3] = new Square(PieceTypes.BISHOP, 'b', PieceColor.BLACK);
        griddy[8][4] = new Square(PieceTypes.QUEEN, 'q', PieceColor.BLACK);
        griddy[8][5] = new Square(PieceTypes.KING, 'k', PieceColor.BLACK);
        griddy[8][6] = new Square(PieceTypes.BISHOP, 'b', PieceColor.BLACK);
        griddy[8][7] = new Square(PieceTypes.KNIGHT, 'n', PieceColor.BLACK);
        griddy[8][8] = new Square(PieceTypes.ROOK, 'r', PieceColor.BLACK);
    }
    public static boolean isMoveValid(char pieceType, int[] moves, Square[][] griddy)
    {
        try
        {
        int deltaRow = moves[2] - moves[0];
        int deltaColumn = moves[3] - moves[1];
        if ((pieceType == 'P' || pieceType == 'p') && griddy[moves[0]][moves[1]].getType() == PieceTypes.PAWN) //white is uppercase, black is lowercase. this is pawn
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
        else if ((pieceType == 'N' || pieceType == 'n') && griddy[moves[0]][moves[1]].getType() == PieceTypes.KNIGHT) //knight
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
        else if ((pieceType == 'R' || pieceType == 'r') && griddy[moves[0]][moves[1]].getType() == PieceTypes.ROOK) //rook
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
        else if ((pieceType == 'B' || pieceType == 'b') && griddy[moves[0]][moves[1]].getType() == PieceTypes.BISHOP) //bishop
        {
            if (Math.abs(deltaRow) == Math.abs(deltaColumn) && deltaRow != 0 && deltaColumn != 0)
            {
                if (deltaRow > 0 && deltaColumn > 0)
                {
                    for (int i = 1; i < deltaRow; i++) //deltaRow or deltaColumn should work just for first quadrant
                    {
                        if (griddy[moves[0] + i][moves[1] + i].getType() != PieceTypes.EMPTY)
                        {
                            return false;
                        }
                    }
                }
                else if (deltaRow > 0 && deltaColumn < 0)
                {
                    for (int i = 1; i < deltaRow; i++)
                    {
                        if (griddy[moves[0] + i][moves[1] - i].getType() != PieceTypes.EMPTY)
                        {
                            return false;
                        }
                    }
                }
                else if (deltaRow < 0 && deltaColumn < 0)
                {
                    for (int i = 1; i < Math.abs(deltaRow); i++)
                    {
                        if (griddy[moves[0] - i][moves[1] - i].getType() != PieceTypes.EMPTY)
                        {
                            return false;
                        }
                    }
                }
                else //deltaRow < 0 && deltaColumn > 0
                {
                    for (int i = 1; i < deltaColumn; i++)
                    {
                        if (griddy[moves[0] - i][moves[1] + i].getType() != PieceTypes.EMPTY)
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
        else if ((pieceType == 'Q' || pieceType == 'q') && griddy[moves[0]][moves[1]].getType() == PieceTypes.QUEEN) //queen
        {
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
            else if (Math.abs(deltaRow) == Math.abs(deltaColumn) && deltaRow != 0 && deltaColumn != 0)
            {
                if (deltaRow > 0 && deltaColumn > 0)
                {
                    for (int i = 1; i < deltaRow; i++) //deltaRow or deltaColumn should work just for first quadrant
                    {
                        if (griddy[moves[0] + i][moves[1] + i].getType() != PieceTypes.EMPTY)
                        {
                            return false;
                        }
                    }
                }
                else if (deltaRow > 0 && deltaColumn < 0)
                {
                    for (int i = 1; i < deltaRow; i++)
                    {
                        if (griddy[moves[0] + i][moves[1] - i].getType() != PieceTypes.EMPTY)
                        {
                            return false;
                        }
                    }
                }
                else if (deltaRow < 0 && deltaColumn < 0)
                {
                    for (int i = 1; i < Math.abs(deltaRow); i++)
                    {
                        if (griddy[moves[0] - i][moves[1] - i].getType() != PieceTypes.EMPTY)
                        {
                            return false;
                        }
                    }
                }
                else //deltaRow < 0 && deltaColumn > 0
                {
                    for (int i = 1; i < deltaColumn; i++)
                    {
                        if (griddy[moves[0] - i][moves[1] + i].getType() != PieceTypes.EMPTY)
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
        else if ((pieceType == 'K' || pieceType == 'k') && griddy[moves[0]][moves[1]].getType() == PieceTypes.KING) //king
        {
            if (Math.abs(deltaRow) <= 1 && Math.abs(deltaColumn) <= 1)
            {
                if (griddy[moves[0]][moves[1]].getColor() == PieceColor.WHITE)
                {
                    whiteHasCastled = true; //can't castle after moving king
                    return griddy[moves[2]][moves[3]].getColor() != PieceColor.WHITE;
                }
                else //black
                {
                    blackHasCastled = true;
                    return griddy[moves[2]][moves[3]].getColor() != PieceColor.BLACK;
                }
            }
            else if (!whiteHasCastled && griddy[moves[0]][moves[1]].getColor() == PieceColor.WHITE && deltaRow == 0 && Math.abs(deltaColumn) == 2)
            {
                if (deltaColumn == 2)
                {
                    if (griddy[1][8].getType() == PieceTypes.ROOK && griddy[1][8].getColor() == PieceColor.WHITE)
                    {
                        whiteHasCastled = true;
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                    //griddy[moves[2]][moves[3]] = griddy[moves[0]][moves[1]];
                    //griddy[moves[0]][moves[1]] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                }
                else //deltaColumn == -2
                {
                    if (griddy[1][1].getType() == PieceTypes.ROOK && griddy[1][1].getColor() == PieceColor.WHITE)
                    {
                        whiteHasCastled = true;
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
            else if (!blackHasCastled && griddy[moves[0]][moves[1]].getColor() == PieceColor.BLACK && deltaRow == 0 && Math.abs(deltaColumn) == 2)
            {
                if (deltaColumn == 2)
                {
                    if (griddy[8][8].getType() == PieceTypes.ROOK && griddy[8][8].getColor() == PieceColor.BLACK)
                    {
                        blackHasCastled = true;
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                    //griddy[moves[2]][moves[3]] = griddy[moves[0]][moves[1]];
                    //griddy[moves[0]][moves[1]] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                }
                else //deltaColumn == -2
                {
                    if (griddy[8][1].getType() == PieceTypes.ROOK && griddy[8][1].getColor() == PieceColor.BLACK)
                    {
                        blackHasCastled = true;
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
        //for king: later: can't move onto attacked square.
        return false;
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Move not in range.");
            return false;
        }
        //TODO: add an undo(would have to make a deep copy), add promoting and castling, under attack algorithm make some way to initialize a grid from a text file, Fischer random chess?
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
        initializeGrid(grid); //should initialize both empty lines and pieces.
        /*for (int i = 1; i < 9; i++)
        {
            for (int j = 1; j < 9; j++)
            {
                grid[i][j] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
            }
        }*/
        //make white uppercase and black lowercase, maybe make the board rotate 180 degrees inbetween turns
        //grid[7][4] = new Square(PieceTypes.PAWN, 'P', PieceColor.WHITE);
        //grid[2][3] = new Square(PieceTypes.PAWN, 'p', PieceColor.BLACK);
        /*grid[4][4] = new Square(PieceTypes.KING, 'K', PieceColor.WHITE);
        //grid[4][4] = new Square(PieceTypes.KNIGHT, 'N', PieceColor.WHITE);
        //grid[5][6] = new Square(PieceTypes.KNIGHT, 'n', PieceColor.BLACK);
        //grid[4][6] = new Square(PieceTypes.PAWN, 'p', PieceColor.BLACK);
        //grid[2][3] = new Square(PieceTypes.PAWN, 'P', PieceColor.WHITE);
        //grid[5][4] = new Square(PieceTypes.QUEEN, 'q', PieceColor.BLACK);
        grid[7][6] = new Square(PieceTypes.PAWN, 'p', PieceColor.BLACK);*/
        printGridWhite(grid);
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
        boolean printBlackGrid = true;
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
                    if (printBlackGrid) //white to move
                    {
                        if (grid[moveSummary[0]][moveSummary[1]].getColor() == PieceColor.WHITE)
                        {
                            System.out.println("Move accepted.");
                            if (moveSummary[2] == 8 && (piece == 'P' || piece == 'p') && grid[moveSummary[0]][moveSummary[1]].getType() == PieceTypes.PAWN)
                            {
                                boolean chosePromotion = true;
                                char input;
                                System.out.println("Promote to queen or knight? Type Q for queen or N for knight.");
                                while (chosePromotion)
                                {
                                    input = scanner.next().charAt(0);
                                    if (input == 'Q' || input == 'q')
                                    {
                                        grid[endRow][endColumn] = new Square(PieceTypes.QUEEN, 'Q', PieceColor.WHITE);
                                        grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                        //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                                        printGridBlack(grid);
                                        printBlackGrid = !printBlackGrid;
                                        chosePromotion = false;
                                    }
                                    else if (input == 'N' || input == 'n')
                                    {
                                        grid[endRow][endColumn] = new Square(PieceTypes.KNIGHT, 'N', PieceColor.WHITE);
                                        grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                        //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                                        printGridBlack(grid);
                                        printBlackGrid = !printBlackGrid;
                                        chosePromotion = false;
                                    }
                                    else
                                    {
                                        System.out.println("Invalid input.");
                                    }
                                }
                            }
                            else if (Math.abs(endColumn - startColumn) == 2 && (piece == 'K' || piece == 'k') && grid[moveSummary[0]][moveSummary[1]].getType() == PieceTypes.KING)
                            //add system to isMoveValid to check if path between king and rook is clear
                            {
                                if (endColumn - startColumn == 2)
                                {
                                    grid[endRow][endColumn] = grid[startRow][startColumn];
                                    grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                    grid[1][6] = grid[1][8];
                                    grid[1][8] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                    //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                                    printGridBlack(grid);
                                    printBlackGrid = !printBlackGrid;
                                }
                                else //endColumn - startColumn == -2
                                {
                                    grid[endRow][endColumn] = grid[startRow][startColumn];
                                    grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                    grid[1][4] = grid[1][1];
                                    grid[1][1] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                    //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                                    printGridBlack(grid);
                                    printBlackGrid = !printBlackGrid;
                                }
                            }
                            else
                            {
                                grid[endRow][endColumn] = grid[startRow][startColumn];
                                grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                                printGridBlack(grid);
                                printBlackGrid = !printBlackGrid;
                            }
                            /*grid[endRow][endColumn] = grid[startRow][startColumn];
                            grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                            //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                            printGridBlack(grid);
                            printBlackGrid = !printBlackGrid;*/
                        }
                        else
                        {
                            System.out.println("Tried to move black piece as white!");
                        }
                    }
                    else //black to move
                    {
                        if (grid[moveSummary[0]][moveSummary[1]].getColor() == PieceColor.BLACK)
                        {
                            if (moveSummary[2] == 1 && (piece == 'P' || piece == 'p') && grid[moveSummary[0]][moveSummary[1]].getType() == PieceTypes.PAWN)
                            {
                                boolean chosePromotion = true;
                                char input;
                                System.out.println("Promote to queen or knight? Type q for queen or n for knight.");
                                while (chosePromotion)
                                {
                                    input = scanner.next().charAt(0);
                                    if (input == 'Q' || input == 'q')
                                    {
                                        grid[endRow][endColumn] = new Square(PieceTypes.QUEEN, 'q', PieceColor.BLACK);
                                        grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                        //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                                        printGridWhite(grid);
                                        printBlackGrid = !printBlackGrid;
                                        chosePromotion = false;
                                    }
                                    else if (input == 'N' || input == 'n')
                                    {
                                        grid[endRow][endColumn] = new Square(PieceTypes.KNIGHT, 'n', PieceColor.BLACK);
                                        grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                        //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                                        printGridWhite(grid);
                                        printBlackGrid = !printBlackGrid;
                                        chosePromotion = false;
                                    }
                                    else
                                    {
                                        System.out.println("Invalid input.");
                                    }
                                }
                            }
                            else if (Math.abs(endColumn - startColumn) == 2 && (piece == 'K' || piece == 'k') && grid[moveSummary[0]][moveSummary[1]].getType() == PieceTypes.KING)
                            //add system to isMoveValid to check if path between king and rook is clear
                            {
                                if (endColumn - startColumn == 2)
                                {
                                    grid[endRow][endColumn] = grid[startRow][startColumn];
                                    grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                    grid[8][6] = grid[8][8];
                                    grid[8][8] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                    //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                                    printGridWhite(grid);
                                    printBlackGrid = !printBlackGrid;
                                }
                                else //endColumn - startColumn == -2
                                {
                                    grid[endRow][endColumn] = grid[startRow][startColumn];
                                    grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                    grid[8][4] = grid[8][1];
                                    grid[8][1] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                    //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                                    printGridWhite(grid);
                                    printBlackGrid = !printBlackGrid;
                                }
                            }
                            else
                            {
                                System.out.println("Move accepted.");
                                grid[endRow][endColumn] = grid[startRow][startColumn];
                                grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                                //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                                printGridWhite(grid);
                                printBlackGrid = !printBlackGrid;
                            }
                            /*System.out.println("Move accepted.");
                            grid[endRow][endColumn] = grid[startRow][startColumn];
                            grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                            //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                            printGridWhite(grid);
                            printBlackGrid = !printBlackGrid;*/
                        }
                        else
                        {
                            System.out.println("Tried to move white piece as black!");
                        }
                    }
                    /*System.out.println("Move accepted.");
                    grid[endRow][endColumn] = grid[startRow][startColumn];
                    grid[startRow][startColumn] = new Square(PieceTypes.EMPTY, '_', PieceColor.EMPTY);
                    //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear console hopefully
                    if (printBlackGrid)
                    {
                        printGridBlack(grid);
                    }
                    else
                    {
                        printGridWhite(grid);
                    }
                    printBlackGrid = !printBlackGrid;*/
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
