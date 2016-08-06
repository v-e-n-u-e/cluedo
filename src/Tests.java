import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class Tests {

	@Test
	/**
	 * moves player (1) miss scarlett up once on the board to a hallway tile.
	 * 
	 */
	public void testValidMove(){
		
		Game game = new Game(5);
		game.loadAllCards();
		game.generateSolution();
		game.shuffleDeck();
		game.createPlayers();
		game.dealCards();
		game.loadBoard();
		Player currentPlayer = game.players.get(0);
		Point destination = new Point(currentPlayer.getLocation().x,
				currentPlayer.getLocation().y - 1);
		assertTrue(game.board.canMove(currentPlayer, destination));
	}
	
	@Test
	/**
	 * transports player to just outside of door and then enters the room
	 */
	public void testValidEnter(){
		
		Game game = new Game(5);
		game.loadAllCards();
		game.generateSolution();
		game.shuffleDeck();
		game.createPlayers();
		game.dealCards();
		game.loadBoard();
		Player currentPlayer = game.players.get(0);
		Point destination = new Point(7,21);
		Point door = new Point (6,21);
		Point lookAhead = new Point(destination.x - 1, destination.y);
		game.board.move(currentPlayer, destination, new Point(destination.x, destination.y - 1));
		game.board.move(currentPlayer, door, lookAhead);
		assertTrue(currentPlayer.inRoom());
	}
	
	@Test
	/**
	 * tests roll is between 2 and 12
	 */
	public void testValidRollDice(){
		Game game = new Game(5);
		game.loadAllCards();
		game.generateSolution();
		game.shuffleDeck();
		game.createPlayers();
		game.dealCards();
		game.loadBoard();
		assert(game.rollDice() == 2 ||
				game.rollDice() == 3 ||
				game.rollDice() == 4 ||
				game.rollDice() == 5 ||
				game.rollDice() == 6 ||
				game.rollDice() == 7 ||
				game.rollDice() == 8 ||
				game.rollDice() == 9 ||
				game.rollDice() == 10 ||
				game.rollDice() == 11||
				game.rollDice() == 12
				);
	}
	
	@Test
	/**
	 * checks you cant move onto wall or room
	 */
	public void testInvalidMove(){
		
		Game game = new Game(5);
		game.loadAllCards();
		game.generateSolution();
		game.shuffleDeck();
		game.createPlayers();
		game.dealCards();
		game.loadBoard();
		Player currentPlayer = game.players.get(0);
		Point destination = new Point(currentPlayer.getLocation().x - 1,
				currentPlayer.getLocation().y);
		assertFalse(game.board.canMove(currentPlayer, destination));
	}
	
	@Test
	/**
	 * checks you cant move onto a null square
	 */
	public void testInvalidMoveToNull(){
		
		Game game = new Game(5);
		game.loadAllCards();
		game.generateSolution();
		game.shuffleDeck();
		game.createPlayers();
		game.dealCards();
		game.loadBoard();
		Player currentPlayer = game.players.get(0);
		Point destination = new Point(currentPlayer.getLocation().x,
				currentPlayer.getLocation().y + 1);
		assertFalse(game.board.canMove(currentPlayer, destination));
	}
	
	@Test
	/**
	 * checks that you cant leave a room if you arnt in one
	 */
	public void testInvalidLeaveRoom(){
		
		Game game = new Game(5);
		game.loadAllCards();
		game.generateSolution();
		game.shuffleDeck();
		game.createPlayers();
		game.dealCards();
		game.loadBoard();
		Player currentPlayer = game.players.get(0);
		game.board.leaveRoom(currentPlayer);
		assertFalse(currentPlayer.inRoom);
	}
	
	
}
