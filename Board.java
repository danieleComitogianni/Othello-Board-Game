
import java.util.Arrays;
import java.util.Scanner;

public class Board  {
	
	private Position[][] board = new Position[8][8];
	UnplayablePosition unplay = new UnplayablePosition();
	PlayablePosition play= new PlayablePosition();
	
	
	public Board(){
		
	}
	
	public Board copy(Board x) { //created a copy board to check if theres any valid moves left
		Board y = new Board();
		y.initBoard();
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				y.board[i][j].setPiece(x.board[i][j].getPiece());
			}
		}
		return y;
	}
	
	public void setBoard(int x,int y, char a) {
		board[x][y].setPiece(a);
	}
	
	public Board(String position) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				switch(position.charAt(i*8+j)) {
					case  ' ','B','W':
						board[i][j]=new PlayablePosition();
						board[i][j].setPiece(position.charAt(i*8+j));
						break;
					case '*':
						board[i][j]= new UnplayablePosition();
				}
			}
		}
	}
	
	public Position[][] getBoard(){
		return this.board;
	}
	
	public void initBoard() { // Inititalize the board
		int num='1';
		int num2='1';
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j]=new PlayablePosition();
			}
				
				
		}
		board[0][3]= new UnplayablePosition();
		board[0][4]= new UnplayablePosition();
	}
	
	public void setBoard1() { // set the first Board
		board[2][2]= new PlayablePosition();
		board[2][2].setPiece(Position.WHITE);
		board[2][3]= new PlayablePosition();
		board[2][3].setPiece(Position.WHITE);
		board[3][2]= new PlayablePosition();
		board[3][2].setPiece(Position.WHITE);
		board[3][3]= new PlayablePosition();
		board[3][3].setPiece(Position.WHITE);
		
		
		board[4][2]=new PlayablePosition();
		board[4][2].setPiece(Position.BLACK);
		board[5][2] = new PlayablePosition();
		board[5][2].setPiece(Position.BLACK);
		board[4][3] = new PlayablePosition();
		board[4][3].setPiece(Position.BLACK);
		board[5][3]=new PlayablePosition();
		board[5][3].setPiece(Position.BLACK);
		
		board[4][4] = new PlayablePosition();
		board[4][4].setPiece(Position.WHITE);
		board[4][5] = new PlayablePosition();
		board[4][5].setPiece(Position.WHITE);
		board[5][4] = new PlayablePosition();
		board[5][4].setPiece(Position.WHITE);
		board[5][5] = new PlayablePosition();
		board[5][5].setPiece(Position.WHITE);
		
		board[2][4] = new PlayablePosition();
		board[2][4].setPiece(Position.BLACK);
		board[3][4] = new PlayablePosition();
		board[3][4].setPiece(Position.BLACK);
		board[3][5] = new PlayablePosition();
		board[3][5].setPiece(Position.BLACK);
		board[2][5] = new PlayablePosition();
		board[2][5].setPiece(Position.BLACK);
	}
	
	
	public void setBoard2() { //set the 2nd Board
		board[3][3]=new PlayablePosition();
		board[3][3].setPiece(Position.WHITE);
		board[4][3]=new PlayablePosition();
		board[4][3].setPiece(Position.BLACK);
		board[3][4]=new PlayablePosition();
		board[3][4].setPiece(Position.BLACK);
		board[4][4] = new PlayablePosition();
		board[4][4].setPiece(Position.WHITE);
	}
	
	

	public void drawBoard() { // Method to draw the Board
		System.out.println(" 12345678");
		for(int i=0;i<board.length;i++) {
			System.out.print(i+1);
			for(int j=0;j<board[i].length;j++) {
				System.out.print(board[i][j].getPiece());
			}
			System.out.println();
		}
	}
	

	
	
	
	
	
	
	public boolean checkRight(int row, int col, Player current) {
			boolean bool=false;
			row=row-1;col=col-1;
			if(row>=0&&col>=0&&row<8&&col<6) {
				if(board[row][col+1].getPiece()!=current.getSymbol() && board[row][col+1].getPiece()!=Position.EMPTY) {
					if(board[row][col+2].getPiece()==current.getSymbol()){
						char color= current.getSymbol();
						board[row][col+1].setPiece(color);
						bool = true;
					} else {
						bool = checkRight(row+1,col+2,current);
						if (bool) {
							char color= current.getSymbol();
							board[row][col+1].setPiece(color);
						}
					}
				}else if(board[row][col+1].getPiece()==Position.EMPTY) {
					bool = false;
				} else if(board[row][col+1].getPiece()==current.getSymbol()) {
					bool = false;
				}
			}
			return bool;
	}
	
	public boolean checkLeft(int row, int col, Player current) {
		boolean bool = false;
		row = row-1; col=col-1;
		if(row>=0&&col>1&&row<8&&col<8) {
			if(board[row][col-1].getPiece()!=current.getSymbol() && board[row][col-1].getPiece()!=Position.EMPTY) {
				if(board[row][col-2].getPiece()==current.getSymbol()){
					char color= current.getSymbol();
					board[row][col-1].setPiece(color);
					bool = true;
				} else {
					bool = checkLeft(row+1,col,current);
					if (bool) {
						char color= current.getSymbol();
						board[row][col-1].setPiece(color);
					}
				}
			} else if(board[row][col-1].getPiece()==Position.EMPTY) {
				bool = false;
			} else if(board[row][col-1].getPiece()==current.getSymbol()) {
				bool = false;
			}
		}
		return bool;
	}
	
	public boolean checkUp(int row, int col, Player current) {
		boolean bool = false;
		row=row-1;col=col-1;
		if(row>1&&col>=0&&row<8&&col<8) {
			if(board[row-1][col].getPiece()!=current.getSymbol() && board[row-1][col].getPiece() != Position.EMPTY) {
				if(board[row-2][col].getPiece()==current.getSymbol()) {
					char color= current.getSymbol();
					board[row-1][col].setPiece(color);
					bool=true;
				} else {
					bool = checkUp(row,col+1,current);
					if (bool) {
						char color= current.getSymbol();
						board[row-1][col].setPiece(color);
					}
				}
			} else if(board[row-1][col].getPiece()==Position.EMPTY) {
				bool=false;
			} else if(board[row-1][col].getPiece()==current.getSymbol()) {
				bool = false;
			}
		}
		return bool;
	}
	
	public boolean checkDown(int row, int col, Player current) {
		boolean bool = false;
		row = row-1; col = col-1;
		if(row >=0 && col >=0&&row<6&&col<8) {
			if(board[row+1][col].getPiece() != current.getSymbol() && board[row+1][col].getPiece() != Position.EMPTY) {
				if(board[row+2][col].getPiece()==current.getSymbol()) {
					char color= current.getSymbol();
					board[row+1][col].setPiece(color);
					bool = true;
				} else {
					bool = checkDown(row+2,col+1,current);
					if (bool) {
						char color= current.getSymbol();
						board[row+1][col].setPiece(color);
					}
				}
			} else if(board[row+1][col].getPiece()==Position.EMPTY) {
				bool = false;
			} else if(board[row+1][col].getPiece()==current.getSymbol()) {
				bool = false;
			}
		}
		return bool;
	}
	
	public boolean checkUpRightDiag(int row, int col, Player current) {
		boolean bool = false;
		row = row-1; col = col-1;
		if(row>1 && col>=0 && row<8 && col<6) {
			if(board[row-1][col+1].getPiece() != current.getSymbol() && board[row-1][col+1].getPiece() != Position.EMPTY) {
				if(board[row-2][col+2].getPiece()==current.getSymbol()) {
					char color= current.getSymbol();
					board[row-1][col+1].setPiece(color);
					bool = true;
				} else {
					bool = checkUpRightDiag(row,col+2,current);
					if (bool) {
						char color= current.getSymbol();
						board[row-1][col+1].setPiece(color);
					}
				}
			} else if(board[row-1][col+1].getPiece()==Position.EMPTY) {
				bool=false;
			} else if(board[row-1][col+1].getPiece()==current.getSymbol()) {
				bool = false;
			}
		}		
		return bool;
	}
	
	public boolean checkUpLeftDiag(int row, int col, Player current) {
		boolean bool = false;
		row = row-1; col = col-1;
		if(row>1&&col>1 && row<8 && col<8) {	
			if(board[row-1][col-1].getPiece() != current.getSymbol() && board[row-1][col-1].getPiece() != Position.EMPTY) {	
				if(board[row-2][col-2].getPiece() == current.getSymbol()) {
						char color= current.getSymbol();
						board[row-1][col-1].setPiece(color);
						bool = true;
					} else {
						bool = checkUpLeftDiag(row, col, current);
						if (bool) {
							char color= current.getSymbol();
							board[row-1][col-1].setPiece(color);
						}
					}
				} else if(board[row-1][col-1].getPiece() == Position.EMPTY) {
					bool = false;
				} else if(board[row-1][col-1].getPiece()==current.getSymbol()) {
					bool=false;
				}
		}
		return bool;
	}
	
	public boolean checkDownRightDiag(int row, int col, Player current) {
		boolean bool = false;
		row = row-1; col = col-1;
		if(row>=0 && col>=0 &&row<6&&col<6) {
			if(board[row+1][col+1].getPiece() != current.getSymbol() && board[row+1][col+1].getPiece() != Position.EMPTY) {
				if(board[row+2][col+2].getPiece() == current.getSymbol()) {
					char color= current.getSymbol();
					board[row+1][col+1].setPiece(color);
					bool=true;
				} else {
					bool = checkDownRightDiag(row+2,col+2,current);
					if (bool) {
						char color= current.getSymbol();
						board[row+1][col+1].setPiece(color);
					}
				}
			} else if(board[row+1][col+1].getPiece() == Position.EMPTY) {
				bool=false;
			} else if(board[row+1][col+1].getPiece() == current.getSymbol()) {
				bool = false;
			}
		}
		return bool;
	}
	
	public boolean checkDownLeftDiag(int row, int col, Player current) {
		boolean bool=false;
		row=row-1; col=col-1;
		if(row>=0&&col>1&&row<6&&col<8) {
		if(board[row+1][col-1].getPiece() != current.getSymbol() && board[row+1][col-1].getPiece() != Position.EMPTY) {
			if(board[row+2][col-2].getPiece()== current.getSymbol()) {
				char color= current.getSymbol();
				board[row+1][col-1].setPiece(color);
				bool=true;
			} else {
				bool = checkDownLeftDiag(row+2,col,current);
				if (bool) {
					char color= current.getSymbol();
					board[row+1][col-1].setPiece(color);
				}
			}
		} else if(board[row+1][col-1].getPiece()==Position.EMPTY) {
			bool=false;
		} else if(board[row+1][col-1].getPiece()== current.getSymbol()) {
			bool=false;
		}
		}
		return bool;
	}
	
	/*The logic behind all of my check methods is, check if there is the opposite piece in a position around you
	 * if there is, check if theres the same piece after it, if there is, flip, if there isnt, call the method again to climb 
	 * up the ladder. Took me a good 10 hours to make it all work. */
	
	public boolean validMove(int row, int col, Player current) {
		boolean bool = false;
		if(!board[row-1][col-1].canPlay()) {
			return false;
		}
		if(checkRight(row,col,current)||checkLeft(row,col,current)||checkUp(row,col,current)||checkDown(row,col,current)||checkUpRightDiag(row,col,current)||checkUpLeftDiag(row,col,current)||checkDownRightDiag(row,col,current)||checkDownLeftDiag(row,col,current)) {
			checkRight(row,col,current);
			checkLeft(row,col,current);
			checkUp(row,col,current);
			checkDown(row,col,current);
			checkUpRightDiag(row,col,current);
			checkUpLeftDiag(row,col,current);
			checkDownLeftDiag(row,col,current);
			checkDownRightDiag(row,col,current);
			bool = true;
		}
		return bool;
	}
	
	public boolean checkWin(Player current) {
		boolean bool = true;
		Board copy = copy(this);
		for(int i=1;i<=copy.board.length;i++) {
			for(int j=1;j<=copy.board[i-1].length;j++){
				if(copy.validMove(i, j, current)) {
					return false;
				}
			}
		}
		return bool;
	}
	
	
	public void win(Player current) {
		int B=0,W=0;
		if(checkWin(current)) {
			for(int i=0;i<board.length;i++) {
				for(int j=0;j<board[i].length;j++) {
					if(board[i][j].getPiece()==Position.BLACK) {
						B++;
					}
					if(board[i][j].getPiece()==Position.WHITE) {
						W++;
					}
				}
			}
		}
		if(B>W) {
			System.out.println("\nBlack Wins!\nCongratulations!");
		} else {
			System.out.println("\nWhite Wins!\nCongratulations!");
		}
	}
	
	
}
	