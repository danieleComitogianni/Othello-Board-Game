public class UnplayablePosition extends Position{
	
	public final char UNPLAYABLE = '*';
	
	@Override
	public char getPiece() {
		return UNPLAYABLE;
	}
	
	@Override
	public boolean canPlay() {
		return false;
	}
	
}
