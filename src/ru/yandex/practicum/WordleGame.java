package ru.yandex.practicum;

import java.util.*;

public class WordleGame {

    private char[] exactLetters = new char[5];

    private Set<Character> absentLetters = new HashSet<>();

    private Set<Character> presentLetters = new HashSet<>();

    private String answer;

    private int steps;

    private WordleDictionary dictionary;


    public WordleGame(WordleDictionary dictionary, int steps) {
        this.dictionary = dictionary;
        this.steps = steps;
        this.answer = dictionary.getRandomWord();
        for (int i = 0; i < 5; i++) {
            exactLetters[i] = '_';
        }
    }

    public String makeMove(String guess) throws WordNotFoundInDictionary {

        if (!dictionary.contains(guess)) {
            throw new WordNotFoundInDictionary("Слово " + guess + " не найдено в словаре.");
        }
        steps--;
        String hint = WordleDictionary.checkWord(answer, guess);

        for (int i = 0; i < 5; i++) {
            if (hint.charAt(i) == '+') {
                exactLetters[i] = guess.charAt(i);
            } else if (hint.charAt(i) == '-') {
                absentLetters.add(guess.charAt(i));
            } else if (hint.charAt(i) == '^') {
                presentLetters.add(guess.charAt(i));
            }
        }

        return hint;
    }

    public String getHint() {
        List<String> allWords = dictionary.getWords();
        List<String> suitable = new ArrayList<>();

        for (String word : allWords) {
            boolean matches = true;


            for (int i = 0; i < 5; i++) {
                if (exactLetters[i] != '_' && word.charAt(i) != exactLetters[i]) {
                    matches = false;
                    break;
                }
            }

            for (char c : absentLetters) {
                if (word.indexOf(c) != -1) {  // буква нашлась в слове — плохо
                    matches = false;
                    break;
                }
            }

            for (char c : presentLetters) {
                if (word.indexOf(c) == -1) {  // буквы нет в слове — плохо
                    matches = false;
                    break;
                }
            }

            if (matches) {
                suitable.add(word);
            }
        }

        Random random = new Random();
        return suitable.get(random.nextInt(suitable.size()));
    }

    public int getSteps() { return steps; }
    public String getAnswer() { return answer; }

}
