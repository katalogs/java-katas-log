package com.adaptionsoft.games.trivia;

import java.util.*;

public class Game {
    private final List<String> players = new ArrayList<>();
    private final int[] places = new int[6];
    private final boolean[] inPenaltyBox  = new boolean[6];
	private final int[] purses  = new int[6];
	private final Map<String, LinkedList<String>> questionListByCategoryMap = new HashMap<>();
    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
		questionListByCategoryMap.put("Pop", new LinkedList<>());
		questionListByCategoryMap.put("Science", new LinkedList<>());
		questionListByCategoryMap.put("Sports", new LinkedList<>());
		questionListByCategoryMap.put("Rock", new LinkedList<>());

    	for (int i = 0; i < 50; i++) {
			questionListByCategoryMap.get("Pop").addLast("Pop Question " + i);
			questionListByCategoryMap.get("Science").addLast("Science Question " + i);
			questionListByCategoryMap.get("Sports").addLast("Sports Question " + i);
			questionListByCategoryMap.get("Rock").addLast("Rock Question " + i);
		}
	}

	public boolean addPlayer(String playerName) {
	    players.add(playerName);
		final int playerNumber = players.size();
	    places[playerNumber] = 0;
	    purses[playerNumber] = 0;
	    inPenaltyBox[playerNumber] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + playerNumber);
		return true;
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (isCurrentPlayerInPenaltyBox()) {
			if (isOdd(roll)) {
				isGettingOutOfPenaltyBox = true;
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				moveCurrentPlayerAndPrintCategory(roll);
				askQuestion();
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
		} else {
			moveCurrentPlayerAndPrintCategory(roll);
			askQuestion();
		}

	}

	private boolean isOdd(int roll) {
		return roll % 2 != 0;
	}

	private void moveCurrentPlayerAndPrintCategory(int roll) {
		places[currentPlayer] = (places[currentPlayer] + roll) % 12;
		System.out.println(players.get(currentPlayer)
				+ "'s new location is "
				+ places[currentPlayer]);
		System.out.println("The category is " + currentCategory());
	}

	private boolean isCurrentPlayerInPenaltyBox() {
		return inPenaltyBox[currentPlayer];
	}

	private void askQuestion() {
		System.out.println(questionListByCategoryMap.get(currentCategory()).removeFirst());
	}
	
	
	private String currentCategory() {
		switch (places[currentPlayer]) {
			case 0:
			case 4:
			case 8:
				return "Pop";
			case 1:
			case 5:
			case 9:
				return "Science";
			case 2:
			case 6:
			case 10:
				return "Sports";
			default:
				return "Rock";
		}

	}

	public boolean wasCorrectlyAnswered() {
		/*if (isCurrentPlayerInPenaltyBox() && !isGettingOutOfPenaltyBox){
			goToNextPlayer();
			return true;
		}

		if (isGettingOutOfPenaltyBox) {
			System.out.println("Answer was correct!!!!");
		} else {
			System.out.println("Answer was correct!!!!");
		}
		return rewardAndGoToNextPlayer();*/

		if (isCurrentPlayerInPenaltyBox()){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				return rewardAndGoToNextPlayer();
			} else {
				goToNextPlayer();
				return true;
			}
		} else {
			System.out.println("Answer was corrent!!!!");
			return rewardAndGoToNextPlayer();
		}
	}

	private boolean rewardAndGoToNextPlayer() {
		rewardCurrentPlayer();
		boolean doesGameContinue = !didPlayerWin();
		goToNextPlayer();

		return doesGameContinue;
	}

	private void rewardCurrentPlayer() {
		purses[currentPlayer]++;
		System.out.println(players.get(currentPlayer)
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");
	}

	private void goToNextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;

		goToNextPlayer();
		return true;
	}


	private boolean didPlayerWin() {
		return purses[currentPlayer] == 6;
	}
}
