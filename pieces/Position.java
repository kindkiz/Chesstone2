package pieces;

public class Position {
	private int x;
	private int y;
	
	public Position(int a, int b)
	{
		x = a;
		y = b;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public static boolean inRange(int x, int y) {
		if (Piece.players == 1)
		{
			if (x < 0 || y < 0 || x > 7 || y > 7) 	return false;
		}
		else if (Piece.players == 2)
		{
			if (x < 0 || x > 13 || y < 0 || y > 13)	return false;
			if ((x < 3 || x > 10) && (y < 3 || y > 10))	return false;			
		}
		return true;
	}

	public boolean inRange() {
		if (Piece.players == 1)
		{
			if (x < 0 || y < 0 || x > 7 || y > 7)
				return false;
		}
		else if (Piece.players == 2)
		{
			if (x < 0 || x > 13 || y < 0 || y > 13)	return false;
			if ((x < 3 || x > 10) && (y < 3 || y > 10))	return false;			
		}
		return true;
	}
    
	@Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position other = (Position) obj;
        return this.x == other.x && this.y == other.y;
    }
	
}
