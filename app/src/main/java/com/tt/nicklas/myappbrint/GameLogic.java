package com.tt.nicklas.myappbrint;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    ArrayList<String> possibleWords = new ArrayList<String>();
    private String wordToGuess;
    private ArrayList<String> usedLetters = new ArrayList<String>();
    private String visibleWord;
    private int wrongGuesses;
    private String word;
    private boolean lastLetterWasCorrect;
    private boolean gameIsWon;
    private boolean gameIsLost;

    public ArrayList<String> getUsedLetters() {
        return usedLetters;
    }

    public String getVisibleWord() {
        return visibleWord;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public boolean wasLastLetterCorrect() {
        return lastLetterWasCorrect;
    }

    public boolean isGameIsLost() {
        return gameIsLost;
    }

    public boolean isGameIsWon() {
        return gameIsWon;
    }

    public GameLogic() {
        possibleWords.add("car");
        possibleWords.add("computer");
        possibleWords.add("programming");
        possibleWords.add("highway");
        possibleWords.add("busroute");
        possibleWords.add("sidewalk");
        possibleWords.add("snail");
        possibleWords.add("bird");
        possibleWords.add("sixteen");
        possibleWords.add("seventeen");
        possibleWords.add("eigthteen");
        reset();
    }

    private void updateVisibleWord() {
        visibleWord = "";
        gameIsWon = true;
        for (int i = 0; i < wordToGuess.length(); i++) {
            String letter = wordToGuess.substring(i, i + 1);
            if (usedLetters.contains(letter)) {
                visibleWord = visibleWord + letter;
            } else {
                visibleWord = visibleWord + "*";
                gameIsWon = false;
            }
        }
    }

    public void reset() {
        usedLetters.clear();
        wrongGuesses = 0;
        gameIsLost = false;
        gameIsWon = false;
        wordToGuess = possibleWords.get(new Random().nextInt(possibleWords.size()));
        updateVisibleWord();
    }

    public void guessLetter(String letter) {
        if (letter.length() != 1) return;
        System.out.println("The letter of choice was: " + letter);
        if (usedLetters.contains(letter)) return;
        if (gameIsWon || gameIsLost) return;

        usedLetters.add(letter);

        if (wordToGuess.contains(letter)) {
            lastLetterWasCorrect = true;
            System.out.println("The letter was correct: " + letter);
        } else {
            lastLetterWasCorrect = false;
            System.out.println("The letter was wrong: " + letter);
            wrongGuesses++;
            if (wrongGuesses > 5) {
                gameIsLost = true;
                visibleWord = wordToGuess;

            }
        }
        updateVisibleWord();
    }
}
