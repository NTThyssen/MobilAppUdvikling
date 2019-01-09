package com.tt.nicklas.myappbrint;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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

    public ArrayList<String> getPossibleWords(){
        return possibleWords;
    }



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
                System.out.println("-------------------------------" + gameIsWon);
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

    public static String retrieveUrl(String url) throws IOException {
        System.out.println("Henter data fra " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }


    public void retrieveWordsFromDr() throws Exception {
        String data = retrieveUrl("https://dr.dk");
        //System.out.println("data = " + data);

        data = data.substring(data.indexOf("<body")). // fjern headere
                replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
                replaceAll("&#198;", "æ"). // erstat HTML-tegn
                replaceAll("&#230;", "æ"). // erstat HTML-tegn
                replaceAll("&#216;", "ø"). // erstat HTML-tegn
                replaceAll("&#248;", "ø"). // erstat HTML-tegn
                replaceAll("&oslash;", "ø"). // erstat HTML-tegn
                replaceAll("&#229;", "å"). // erstat HTML-tegn
                replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
                replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
                replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

        System.out.println("data = " + data);
        System.out.println("data = " + Arrays.asList(data.split("\\s+")));
        possibleWords.clear();
        possibleWords.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

        System.out.println("muligeOrd = " + possibleWords);
        reset();
    }
}
