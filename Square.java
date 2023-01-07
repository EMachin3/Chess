public class Square {
	private PieceTypes type;
    private char display;
    private PieceColor color; //idk if this is actually the way to do this but first i'll make functions that move pieces
    //private boolean underAttack (oh god how am i going to implement this)
    public Square(PieceTypes initialType, char initialAppearance, PieceColor initialColor)
    {
        type = initialType;
        display = initialAppearance;
        color = initialColor;
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
    public PieceColor getColor()
    {
        return color;
    }
    public void setColor(PieceColor newColor)
    {
        color = newColor;
    }
}
