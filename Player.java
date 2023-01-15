public class Player {

	private String name;
	private char symbol;
	
	Player(String name){
		setName(name);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setSymbolB() {
		this.symbol=Position.BLACK;
	}
	public void setSymbolW() {
		this.symbol=Position.WHITE;
	}
	public char getSymbol() {
		return this.symbol;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
