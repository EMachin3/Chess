public class Square {
	private PieceTypes type;
    private char display;
    public Square(PieceTypes initialType, char initialAppearance)
    {
        type = initialType;
        display = initialAppearance;
    }
    public PieceTypes getType()
    {
        return type;
    }
    public void setType(PieceTypes newType)
    {
        type = newType;
    }
    public char getDisplay()
    {
        return display;
    }
    public void setDisplay(char newAppearance)
    {
        display = newAppearance;
    }
}