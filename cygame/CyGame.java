package hw2;

/**
 * Model of a Monopoly-like game. Two players take turns rolling dice to move
 * around a board. The game ends when one of the players has at least
 * MONEY_TO_WIN money or one of the players goes bankrupt (has negative money).
 * 
 * @author MakuForchu
 */
public class CyGame {
	/**
	 * Do nothing square type.
	 */
	public static final int DO_NOTHING = 0;
	/**
	 * Pass go square type.
	 */
	public static final int PASS_GO = 1;
	/**
	 * Cyclone square type.
	 */
	public static final int CYCLONE = 2;
	/**
	 * Pay the other player square type.
	 */
	public static final int PAY_PLAYER = 3;
	/**
	 * Get an extra turn square type.
	 */ 
	public static final int EXTRA_TURN = 4;
	/**
	 * Jump forward square type.
	 */
	public static final int JUMP_FORWARD = 5;
	/**
	 * Stuck square type.
	 */
	public static final int STUCK = 6;
	/**
	 * Points awarded when landing on or passing over go.
	 */
	public static final int PASS_GO_PRIZE = 200;
	/**
	 * The amount payed to the other player per unit when landing on a
	 * PAY_PLAYER square.
	 */
	public static final int PAYMENT_PER_UNIT = 20;
	/**
	 * The amount of money required to win.
	 */
	public static final int MONEY_TO_WIN = 400;
	/**
	 * The cost of one unit.
	 */
	public static final int UNIT_COST = 50;
	
	/*
	 * the square player 1 is currently on
	 */
	private int player1Square;
	
	/*
	 * the number of units player 1 has
	 */
	private int player1Units;
	
	/*
	 * the amount of money player 1 has
	 */
	private int player1Money;
	
	/*
	 * the square player 2 is currently on
	 */
	private int player2Square;
	
	/*
	 * the number of units player 2 has
	 */
	private int player2Units;
	
	/*
	 * the amount of money player 2 has
	 */
	private int player2Money;
	
	/*
	 * shows who's turn it is
	 */
	private int currentPlayer;
	
	/*
	 * the number of squares the players pick for the game
	 */
	private int numSquares; 
	
	
	/*
	 * starts a new game 
	 * gets the number of squares the game would have
	 * gets what amount of money each player will start off with
	 * starts of both players on square 0
	 * makes it player 1's turn
	 * @param numSquares
	 * @param startingMoney
	 */
	public CyGame(int numSquares, int startingMoney) {
		currentPlayer = 1;
		this.numSquares = numSquares;
		player1Money = startingMoney;
		player2Money = startingMoney;
		player1Units = 1;
		player2Units = 1;
	}
	
	/*
	 * returns the square player 1 is currently on.
	 * @return player1Square
	 */
	public int getPlayer1Square() {
		return player1Square ;
	}
	
	/*
	 * returns how many units player 1 has
	 * @return player1Units
	 */
	public int getPlayer1Units() {
		return player1Units;
	}
	
	/*
	 * returns how much money player 1 has
	 * @return player1Money
	 */
	public int getPlayer1Money() {
		return player1Money;
	}
	
	/*
	 * returns the square player 2 is currently on
	 * @return player2Square
	 */
	public int getPlayer2Square() {
		return player2Square;	
	}
	
	/*
	 * returns how many units player 2 has
	 * @return player2Units
	 */
	public int getPlayer2Units() {
		return player2Units;
	}
	
	/*
	 * returns how much money player 2 has
	 * @return player2Money
	 */
	public int getPlayer2Money() {
		return player2Money;
	}
	
	/*
	 * returns who's turn it is
	 * @return currentPlayer
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	/*
	 * returns what type of square the player has landed on
	 * @param square
	 */
	public int getSquareType(int square){
		
		if (square == 0) {
			return PASS_GO;
			}
		else if (square == numSquares - 1) {
			return CYCLONE;
			}
		else if ((square % 5) == 0) {
			return PAY_PLAYER;
			}
		else if ((square % 7) == 0 || (square % 11) == 0) {
			return EXTRA_TURN;
			}
		else if (square % 3 == 0) {
			return STUCK;
			}
		else if (square % 2 == 0) {
			return JUMP_FORWARD;
			}
		else {
			return DO_NOTHING;
			}
	}
	
	/*
	 * indicates that the current player has completed their turn
	 * switches the current player
	 */
	public void endTurn() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
		}
		else if (currentPlayer != 1){
			currentPlayer = 1;
		}
	}
	
	/*
	 * checks to see whether or not the game has ended
	 * returns true if the game has ended and false if it hasn't
	 */
	public boolean isGameEnded() {
		
		if (((player1Money >= MONEY_TO_WIN) || (player2Money >= MONEY_TO_WIN)) || ((player1Money < 0) || (player2Money < 0))){
			return true;
		}
		
		else {
			return false;
		}
	}
	
	/*
	 * indicates the dice has been rolled if the game has not ended
	 * advances the player based on the number rolled
	 * depending on what square the player is on or lands on it carries out the action of that square
	 * @param value
	 */
	public void roll(int value) {
		
		if (isGameEnded() == false){
		
			if (currentPlayer == 1) {
				player1Square = player1Square % numSquares;
				
				if (getSquareType(player1Square) == STUCK){
					if (value % 2 == 0) {
						player1Square += value;
						if (player1Square > numSquares) {
							player1Money += PASS_GO_PRIZE;
						}
						player1Square = player1Square% numSquares;
					}
					else {
						endTurn();
						return;
					}
				}
				else {
					player1Square += value;
					if (player1Square > numSquares) {
						player1Money += PASS_GO_PRIZE;
					}
					player1Square = player1Square% numSquares;
				}
				if (getSquareType(player1Square) == PASS_GO){
					player1Money += PASS_GO_PRIZE;
				}
				else if (getSquareType(player1Square) == CYCLONE){
					player1Square = player2Square;
					endTurn();
				}
				else if (getSquareType(player1Square) == PAY_PLAYER){
					player1Money -= (PAYMENT_PER_UNIT * player2Units);
					player2Money += (PAYMENT_PER_UNIT * player2Units);
					endTurn();
				}
				else if (getSquareType(player1Square) == JUMP_FORWARD){
					player1Square += 4;
					if (player1Square > numSquares) {
						player1Money += PASS_GO_PRIZE;
					}
					player1Square = player1Square% numSquares;
					endTurn();
				}
				else if (getSquareType(player1Square) == DO_NOTHING){
					endTurn();
				}
				else if (getSquareType(player1Square) != EXTRA_TURN){
					endTurn();
				}
				else {
					player1Square = player1Square % numSquares;
				}
			}
			else if (currentPlayer != 1) {
				player2Square = player2Square % numSquares;
				
				if (getSquareType(player2Square) == STUCK){
					if (value % 2 == 0) {
						player2Square += value;
						if (player2Square > numSquares) {
							player2Money += PASS_GO_PRIZE;
						}
						player2Square = player2Square % numSquares;
					}
					else {
						endTurn();
						return;
					}
				}
				else {
					player2Square += value;
					if (player2Square > numSquares) {
						player2Money += PASS_GO_PRIZE;
					}
					player2Square = player2Square% numSquares;
				}
				if (getSquareType(player2Square) == PASS_GO){
					player2Money += PASS_GO_PRIZE;
					
				}
				else if (getSquareType(player2Square) == CYCLONE){
					player2Square = player1Square;
					endTurn();
				}
				else if (getSquareType(player2Square) == PAY_PLAYER){
					player2Money -= (PAYMENT_PER_UNIT * player1Units);
					player1Money += (PAYMENT_PER_UNIT * player1Units);
					endTurn();
				}
				else if (getSquareType(player2Square) == JUMP_FORWARD){
					player2Square += 4;
					if (player2Square > numSquares) {
						player2Money += PASS_GO_PRIZE;
					}
					player2Square = player2Square% numSquares;
					endTurn();
				}
				else if (getSquareType(player2Square) == DO_NOTHING){
					endTurn();
				}
				else if (getSquareType(player2Square) != EXTRA_TURN){
					endTurn();
				}
				else {
					player2Square = player2Square % numSquares;
				}
			}
		}
	}
		
	/*
	 * allows the player to buy a unit only if the game has not ended 
	 * and they have enough money to buy a unit
	 * and if they are on the DO_NOTHING square
	 */	
	public void buyUnit() {
		
		if (isGameEnded() == false){
			
			if (currentPlayer == 1){
				if (getSquareType(player1Square) == DO_NOTHING && player1Money >= UNIT_COST){
					player1Money -= UNIT_COST;
					player1Units += 1;
					endTurn();
				}
			}
			
			else if (currentPlayer != 1){
				if (getSquareType(player2Square) == DO_NOTHING && player2Money >= UNIT_COST){
					player2Money -= UNIT_COST;
					player2Units += 1;
					endTurn();
				}
			}
		}
		
	}
	
	/*
	 * allows the player to sell a unit if they have at least one unit and if the game has not ened
	 */
	public void sellUnit() {
		
		if (isGameEnded() == false){
		
			if (currentPlayer == 1) {
				if (player1Units >= 1) {
					player1Money += UNIT_COST;
					player1Units -= 1;
					endTurn();
				}
			}
			else if (currentPlayer != 1) {
				if (player2Units >= 1) {
					player2Money += UNIT_COST;
					player2Units -= 1;
					endTurn();
				}
			}
		}
	}
	
	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player 1*: (0, 0, $0) Player 2: (0, 0, $0)</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which players turn it
	 * is. The numbers (0, 0, $0) indicate which square the player is on, how
	 * many units the player has, and how much money the player has
	 * respectively.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player 1%s: (%d, %d, $%d) Player 2%s: (%d, %d, $%d)";
		String player1Turn = "";
		String player2Turn = "";
		if (getCurrentPlayer() == 1) {
			player1Turn = "*";
		} else {
			player2Turn = "*";
		}
		return String.format(fmt,
				player1Turn, getPlayer1Square(), getPlayer1Units(), getPlayer1Money(),
				player2Turn, getPlayer2Square(), getPlayer2Units(), getPlayer2Money());
	}
}
