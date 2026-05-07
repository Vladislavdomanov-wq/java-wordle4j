package ru.yandex.practicum;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import java.util.Scanner;

public class Wordle  {

    public static void main(String[] args) {
        PrintWriter log = null;

        try {
            log = new PrintWriter(new FileWriter("game.log", StandardCharsets.UTF_8));
            log.println("Программа запущена");

            WordleDictionaryLoader loader = new WordleDictionaryLoader();
            WordleDictionary dictionary = loader.load();
            WordleGame game = new WordleGame(dictionary, 6);

            System.out.println("Добро пожаловать в Wordle!");
            System.out.println("Угадайте слово из 5 букв. У вас 6 попыток.");
            System.out.println("Нажмите Enter для подсказки.");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Введите слово: ");
                String word = scanner.nextLine().trim().toLowerCase();


                if (word.isEmpty()) {
                    String hint = game.getHint();
                    System.out.println("Подсказка: " + hint);
                    continue;
                }


                if (word.length() != 5) {
                    System.out.println("Слово должно состоять из 5 букв!");
                    continue;
                }


                try {
                    String result = game.makeMove(word);
                    System.out.println(result);


                    if (word.equals(game.getAnswer())) {
                        System.out.println("Поздравляем! Вы угадали слово!");
                        log.println("Победа! Загаданное слово: " + game.getAnswer());
                        break;
                    }


                    if (game.getSteps() == 0) {
                        System.out.println("Попытки закончились. Загаданное слово: " + game.getAnswer());
                        log.println("Поражение. Загаданное слово: " + game.getAnswer());
                        break;
                    }

                    System.out.println("Осталось попыток: " + game.getSteps());

                } catch (WordNotFoundInDictionary e) {
                    System.out.println("Такого слова нет в словаре. Попробуйте другое.");
                    log.println("Игрок ввёл несуществующее слово: " + word);
                }
            }

            scanner.close();

        } catch (DictionaryLoadException e) {
            System.out.println("Ошибка загрузки словаря: " + e.getMessage());
            if (log != null) {
                log.println("Ошибка загрузки словаря: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка. Смотрите лог-файл.");
            if (log != null) {
                log.println("Непредвиденная ошибка: " + e.getMessage());
                e.printStackTrace(log);
            }
        } finally {
            if (log != null) {
                log.println("Программа завершена");
                log.close();
            }
        }
    }
}


