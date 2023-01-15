import java.io.*;
import java.util.Scanner;

public class Game {

	private Board board;
	private static Player p1;
	private static Player p2;
	private static Player current;
	
	
	
	public Game() {
		
	}
	//load method
	public static Board load() {
		Scanner sc = new Scanner(System.in);
		System.out.println("File Name: ");
		String name = sc.next();
		try {
			File file = new File(name);
			Scanner key = new Scanner(file);
			if(key.hasNextLine()) {
				p1 = new Player(key.nextLine());
				p1.setSymbolB();;
			}
			if(key.hasNextLine()) {
				p2 = new Player(key.nextLine());
				p2.setSymbolW();
			}
			
			if(key.hasNext()) {
				String c=key.nextLine();
				if(c.equals(p1.getName())) {
					current=p1;
				}else {
					current=p2;
				}
			}
			if(key.hasNextLine()) {
				Board board = new Board(key.nextLine());
				return board;
			}
		} catch (IOException e) {
			System.out.println("An Error Has Occured");
		}
		return null;
	}
	//start method
	public void start() {
		try {
		Scanner key = new Scanner(System.in);
		int menu=-1;
		String name1,name2;
		boolean bool = true;
		System.out.println("Othello!\n\n1. Start Game\n2. Load Game\n3.Quit");	
			if(key.hasNextInt()) {
				menu = key.nextInt();
				if(menu<1||menu>3) {
					System.out.println("Wrong Input Try Again!");
				} else {
					bool = false;
				}
			} else {
				System.out.println("Restart And Enter a Digit Please");
			}
		switch(menu){
			case 1:
				System.out.println("Player 1 Name: ");
				name1 = key.next();
				Player p1 = new Player(name1);
				System.out.println("Player 2 Name: ");
				name2 = key.next();
				Player p2 = new Player(name2);
				Game game = new Game(p1,p2);
				int num = boardMenuChoice();
				switch(num) {
					case 1:
						game.board.setBoard1();
						break;
					case 2:
						game.board.setBoard2();
						break;
				}
				game.play();
				break;
			case 2:
				board = load();
				play();
				break;
				
			case 3:
				System.out.println("The Game has Ended");
				break;
		}
		}catch (Exception e) {
			System.out.println("An Error Has Occured, Try Again!");
		}
		
	}
	
	

	public int boardMenuChoice(){
		boolean bool = false;
		int n = -1;
		while(!bool) {
			Scanner key = new Scanner(System.in);
			System.out.println("Chose A Starting Position\n1.\n2.");
			if(key.hasNextInt()) {
				n=key.nextInt();
				if(n<1||n>2) {
					System.out.println("Wrong Input. Try Again!");
				} else {
					bool = true;
				}
			} else {
				System.out.println("Wrong Input. Try Again!");
			}
		}
		return n;
	}
	
	public int menuChoice() {
		boolean bool = false;
		int n=-1;
		while(!bool) {
		Scanner key = new Scanner(System.in);
		System.out.println("\nPlayer: " +current+"\n\n1. Make a Move\n2. Save\n3. Concede");
		if(key.hasNextInt()) {
			n = key.nextInt();
			if(n<1||n>3) {
				System.out.println("Wrong Input. Try Again!");
			} else {
				bool = true;
			}
		} else {
			System.out.println("Wrong Input. Try Again!");
		}
	}
		return n;
	}
	
	
	
	public void play() {
		while(!board.checkWin(current)) {
			board.drawBoard();
			int choice = menuChoice();
			switch(choice) {
				case 1:
					takeTurn(current);
					break;
				case 2:
					Scanner sc = new Scanner(System.in);
					System.out.println("File Name: ");
					String fileName = sc.next();
					File file = new File(fileName);
					try {
						file.createNewFile();
					} catch(IOException e) {
						System.out.println("An Error Occured");
					}
					
					try {
						FileWriter write = new FileWriter(fileName);
						write.write(p1.getName()+"\n"+p2.getName()+"\n"+current.getName()+"\n");
						for(int i=0;i<board.getBoard().length;i++) {
							for(int j=0;j<board.getBoard()[i].length;j++) {
								write.write(board.getBoard()[i][j].getPiece());
							}
						}
						write.close();
						
					} catch (IOException e) {
						System.out.println("An Error Has Occured");
					}
					System.out.println("Game Saved");
					System.exit(0);
					break;
				case 3:
					System.out.println(current.getName()+" Conceded");
					System.exit(0);
					break;
			}
		}
		board.drawBoard();
		board.win(current);
	}
	
	public void takeTurn(Player current) {
		
		try{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Row:");
			int row=sc.nextInt();
			System.out.println("Enter Column:");
			int col = sc.nextInt();
			if(board.getBoard()[row-1][col-1].canPlay()) {
				if(board.validMove(row,col,current)) {
					board.setBoard(row-1, col-1, current.getSymbol());
					swapCurrent();
				} else {
					System.out.println("\nCant go there");
				}
			} else {
				System.out.println("\nUnplayable Position!");
			}
		} catch (Exception e) {
			System.out.println("An Error Has Occured\nEnter A Correct Position!\n");
		}
	}

	public void swapCurrent() {
		if(current.getSymbol()==p1.getSymbol()) {
			current=p2;
		} else {
			current=p1;
		}
	}
	
	public Game(Player p1,Player p2) {
		board = new Board();
		this.p1=p1;
		p1.setSymbolB();
		this.p2=p2;
		p2.setSymbolW();
		board.initBoard();
		current = p1;
	}
	
	

	
	
}

