public class PlayablePosition extends Position{

	
	@Override
	public boolean canPlay() {
		if(piece==Position.BLACK||piece==Position.WHITE) {
			return false;
		}
		return true;
	}

}
