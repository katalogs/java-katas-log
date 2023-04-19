package com.adaptionsoft.games.trivia;

import java.util.*;

public class Game {
    private final List<String> players = new ArrayList<>();
    private final int[] places = new int[6];
    private final boolean[] inPenaltyBox  = new boolean[6];
    
    private final LinkedList<String> popQuestionList = new LinkedList<>();
    private final LinkedList<String> scienceQuestionList = new LinkedList<>();
	private final LinkedList<String> sportQuestionList = new LinkedList<>();
	private final LinkedList<String> rockQuestionList = new LinkedList<>();

	private final Map<String, LinkedList<String>> questionListByCategoryMap = new HashMap<>();
    
    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestionList.addLast("Pop Question " + i);
			scienceQuestionList.addLast("Science Question " + i);
			sportQuestionList.addLast("Sports Question " + i);
			rockQuestionList.addLast("Rock Question " + i);
		}
		questionListByCategoryMap.put("Pop", popQuestionList);
		questionListByCategoryMap.put("Science", scienceQuestionList);
		questionListByCategoryMap.put("Sports", sportQuestionList);
		questionListByCategoryMap.put("Rock", rockQuestionList);

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
			if (roll % 2 != 0) {
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

	int[] purses  = new int[6];

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
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
