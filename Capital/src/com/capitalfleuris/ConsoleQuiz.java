package com.capitalfleuris;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleQuiz {
	
	private int score;
	private long timeElapsed;
	private boolean done = false;
	private int nbreQuestions;
	Scanner clavier = new Scanner(System.in);
	
	public ConsoleQuiz(int nbreQuestion) {
		nbreQuestions = nbreQuestion;
	}
	
	public void start () {
		try {
			long startTime = System.currentTimeMillis();
			for (Question question : generate(nbreQuestions)) {
				System.out.println(question.getText());
				String userAnswer = clavier.nextLine();
				
				if (userAnswer.equalsIgnoreCase(question.getResponse())){
					score++;
					System.out.println("Bonne Reponse");
				}else{
					System.out.println("Mauvaise Reponse");
					System.out.println("la bonne reponse etait: " +question.getResponse());
				}
			}
				done = true;
			long endTime = System.currentTimeMillis();
			timeElapsed = endTime - startTime;
		} catch(IllegalArgumentException e){
			done = false;
			System.out.println(e.getMessage());
		}
		
	}
	private int getTimeElapsedInSeconds(long timeInMilliSeconds) {
		// TODO Auto-generated method stub
		return (int) (timeInMilliSeconds / 1000);
	}
	public void displayResultats(){
		if(done){
			displayScore();
			displayTimeElapsed();
		}
	}
	
	private void displayTimeElapsed() {
		System.out.printf("Il vous a fallu environ %d secondes pour repondre aux questions", getTimeElapsedInSeconds(timeElapsed), nbreQuestions);
		
	}
	private void displayScore() {
		System.out.printf("votre score final est de: %d/%d\n ", score, nbreQuestions);
		
	}
	public ArrayList<Question> generate(int nbreQuestions){
		String[][] data = getData();
		
		if (nbreQuestions > data.length) {
			throw new IllegalArgumentException("On ne peut generer que: " + data.length + " question maximun");
		}
		ArrayList<Question> questions = new ArrayList<Question>();
		int index;
		ArrayList<Integer> indexesAlreadyTaken = new ArrayList<Integer>();
		indexesAlreadyTaken.clear();
		
		for (int i = 0; i < nbreQuestions; i++){
			do {
				Random random = new Random();
				index = random.nextInt(data.length);
			} while (indexesAlreadyTaken.contains(index));
			indexesAlreadyTaken.add(index);
			String pays = data[index][0];
			String capitale = data[index][1];
			String questionText = String.format("Quelle est la capitale de ce pays: %s? ", pays);
			questions.add(new Question(questionText, capitale));
		}
		
		return questions;
		
	}
	
	private static String[][] getData(){
		String[][] data  = { {"Senegal", "Dakar"} , {"France", "Paris"},{"Togo","Lome"},
				{"Burkina-Faso","Ouagedougou"},
				{"Haiti", "Paup"} , {"Argemtine", "Brenos.A"}, {"Brasil", "brasilia"},
				{"Itali", "Rome"} ,{"Benin","Porto-Novo"}, {"Perou", "Lima"} , {"Almangne", "berlin"} , {"Canada", "Otawa"}};
		return data;
		}

}
