package ru.yandex.practicum;

import java.util.List;
import java.util.Random;



public class WordleDictionary {

    private List<String> words;

    public WordleDictionary(List<String> words) {
        this.words = words;
    }

    public String getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    public boolean contains(String word) {
        return words.contains(word);
    }

    public List<String> getWords() {
        return words;
    }

    public static String checkWord(String answer, String guess) {
        char[] result = new char[5];
        boolean[] used = new boolean[5];


        for (int i = 0; i < 5; i++) {
            if (answer.charAt(i) == guess.charAt(i)) {
                result[i] = '+';
                used[i] = true;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (result[i] == '+') {
                continue;
            }

            boolean found = false;
            for (int j = 0; j < 5; j++) {
                if (!used[j] && answer.charAt(j) == guess.charAt(i)) {
                    result[i] = '^';
                    used[j] = true;
                    found = true;
                    break;
                }
            }

            if (!found) {
                result[i] = '-';
            }
        }
        return new String(result);
    }

}


