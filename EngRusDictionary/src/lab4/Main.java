package lab4;

import lab4.exceptions.FileReadException;
import lab4.exceptions.InvalidFileFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String dictionaryFileName = "dictionary.txt";
        String inputFileName = "input.txt";
        File dictionaryFile = new File(dictionaryFileName);
        File textFile = new File(inputFileName);
        try {
            if (!dictionaryFile.exists() || !dictionaryFile.canRead()) {
                throw new FileReadException("Ошибка с файлом словаря");
            }
            TreeMap<String, String> dictionary = new TreeMap<>();
            BufferedReader dictionaryReader = new BufferedReader(new FileReader(dictionaryFileName));
            String line = dictionaryReader.readLine();
            while (line != null) {
                String[] parts = line.split("\\|", 2);
                try {
                    if (parts.length != 2 || !Pattern.matches("^[a-zA-Z]+[a-zA-Z\\s]*$", parts[0].trim()) || !Pattern.matches("^[а-яА-Я]+[а-яА-Я\\s]*$", parts[1].trim())) {
                        throw new InvalidFileFormatException("Неверный формат файла словаря");
                    }
                    dictionary.put(parts[0].trim().toLowerCase(), parts[1].trim().toLowerCase());
                } catch (InvalidFileFormatException e) {
                    System.out.println(e.getMessage());
                }
                line = dictionaryReader.readLine();
            }
            if (!textFile.exists() || !textFile.canRead()) {
                throw new FileReadException("Ошибка со входным файлом");
            }
            BufferedReader inputReader = new BufferedReader(new FileReader(inputFileName));
            line = inputReader.readLine();
            while (line != null) {
                line = line.trim();
                String[] words = line.split(" ");
                for (int i = 0; i < words.length; i++) {
                    ArrayList<String> suitableKeys = new ArrayList<>();
                    for (String key : dictionary.keySet()) {
                        if (key.startsWith(words[i].toLowerCase())) {
                            suitableKeys.add(key);
                        }
                    }
                    suitableKeys.sort(new SortByWordsAmount());
                    boolean translated = false;
                    for (String suitableKey: suitableKeys) {
                        StringBuilder currentWord = new StringBuilder(words[i].toLowerCase());
                        String[] partsOfKey = suitableKey.split(" ");
                        int suitableKeyLength = partsOfKey.length;
                        if (suitableKeyLength > words.length - i) {
                            continue;
                        }
                        for (int j = 1; j < suitableKeyLength; j++) {
                            currentWord.append(" ").append(words[i + j]);
                        }
                        if (suitableKey.contentEquals(currentWord)) {
                            System.out.print(dictionary.get(suitableKey) + " ");
                            i += suitableKeyLength - 1;
                            translated = true;
                            break;
                        }
                    }
                    if (!translated) {
                        System.out.print(words[i] + " ");
                    }
                }
                System.out.println();
                line = inputReader.readLine();
            }
        } catch (IOException | FileReadException e) {
        System.out.println(e.getMessage());
        }
    }
}
