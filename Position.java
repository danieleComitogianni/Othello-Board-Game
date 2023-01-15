public abstract class Position {
	
	protected char piece;
	
	public Position(){
		piece = EMPTY;
	}
	
	public abstract boolean canPlay(); 
	
	
	public void setPiece(char piece) {
		this.piece=piece;
	}
	public char getPiece() {
		return this.piece;
	}
	
	
	
	
	public static final char EMPTY = ' ';
	public static final char BLACK = 'B';
	public static final char WHITE = 'W';
	
}
