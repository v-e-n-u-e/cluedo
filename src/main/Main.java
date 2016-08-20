package main;
import java.awt.event.KeyEvent;
import java.util.InputMismatchException;
import java.util.Scanner;

import Board.Game;
import gui.CluedoFrame;

public class Main {
	//THIS PROGRAM WAS MADE BY CONNOR MOOT AND CALLUM CROSBY
	
	private static int numPlayers;
	private static Scanner input;
	static Game game;

	public static void main(String[] args) {
		/*Initialization-----*/
		CluedoFrame cluedoframe = new CluedoFrame();
		numPlayers = cluedoframe.numPlayers();
		game = new Game(numPlayers);
		game.loadAllCards();
		game.generateSolution();
		game.shuffleDeck();
		game.createPlayers();
		game.dealCards();
		game.loadBoard();
		cluedoframe.drawBoard(game.getBoard());



		//game.setPlayers(numPlayers);
		cluedoframe.selectCharacters(game.players);

		/*--------------------*/
		game.running();// rest of operations will take place inside the game class
		
	}

}