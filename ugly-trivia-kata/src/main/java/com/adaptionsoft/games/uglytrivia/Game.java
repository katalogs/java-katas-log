package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	final static int FIVE=6;

    ArrayList players = new ArrayList();


	int[] places = new int[6];
    int[] purses  = new int[6];

	boolean[] inPenaltyBox  = new boolean[FIVE];
    
    LinkedList q1s = new LinkedList();
    LinkedList _Q2s = new LinkedList();

    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			q1s.addLast("Pop Question " + i);
			_Q2s.addLast(("Science Question " + i));
			q3s.addLast(("Sports Question " + i));
			q4.addLast(createRockQuestion(i));
    	}
		// shuffle();
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	private void shuffle()
	{
		var shufpower = q1s
				.stream()
				.mapToInt(q -> q.hashCode())
				.map(h -> h > 42 ? 43 : 0)
				.sorted();
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	LinkedList q3s = new LinkedList();
	LinkedList q4 = new LinkedList();

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
		if (roll % 2 != 0) {
			//User is getting out of penalty box
			isGettingOutOfPenaltyBox = true;

			//Write that user is getting out
			System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");

			// add roll to place
places[currentPlayer] = places[currentPlayer] + roll;
if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				System.out.println(players.get(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
					} else {
						System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
						isGettingOutOfPenaltyBox = false;
						}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
							if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			
			System.out.println(players.get(currentPlayer) 
					+ "'s new location is " 
					+ places[currentPlayer]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(q1s.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(_Q2s.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(q3s.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(q4.removeFirst());
	}
	
	
	private String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
	}

	/**
	 * Call on right ansewr
	 * @return a boolean
	 */
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

	/**
	 * Call on right ansewr
	 * @return a boolean
	 */
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
