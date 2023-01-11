public class Square {
	private PieceTypes type;
    private char display;
    private PieceColor color;
    private boolean whiteAttacking;
    private boolean blackAttacking;
    public Square(PieceTypes initialType, char initialAppearance, PieceColor initialColor)
    {
        type = initialType;
        display = initialAppearance;
        color = initialColor;
        whiteAttacking = false;
        blackAttacking = false;
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
    public boolean getWhiteAttacking()
    {
        return whiteAttacking;
    }
    public void setWhiteAttacking(boolean condition)
    {
        whiteAttacking = condition;
    }
    public boolean getBlackAttacking()
    {
        return blackAttacking;
    }
    public void setBlackAttacking(boolean condition)
    {
        blackAttacking = condition;
    }
}
