package ru.yandex.practicum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {
    File fileWord = new File("words_ru.txt");

    public WordleDictionary load() throws DictionaryLoadException {
        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileWord))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim().toLowerCase().replace('ё', 'е');
                if (line.length() == 5 ){
                    words.add(line);
                }
        }
    } catch (IOException e) {
            throw new DictionaryLoadException("Ошибка чтения файла", e);
        }

            if(words.isEmpty()){
                throw new DictionaryLoadException("словарь пуст");
        }
            return new  WordleDictionary(words);
    }
}

