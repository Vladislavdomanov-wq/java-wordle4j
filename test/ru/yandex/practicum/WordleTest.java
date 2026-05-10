package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {

    @Test
    void checkWord_ShouldReturnCorrectPattern() {

        String answer = "город";
        String guess = "голод";


        String result = WordleDictionary.checkWord(answer, guess);


        assertEquals("++-++", result);
    }

    @Test
    void checkWord_WhenGuessHasDuplicateLetter_ReturnsCorrectPattern() {
        String answer = "каска";
        String guess = "казак";

        String result = WordleDictionary.checkWord(answer, guess);

        assertEquals("++-^^", result);
    }

    @Test
    void checkWord_AllLettersMatch_ShouldReturnAllPlus() {
        String answer = "абзац";
        String guess = "абзац";

        String result = WordleDictionary.checkWord(answer, guess);

        assertEquals("+++++", result);
    }

}
