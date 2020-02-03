package hangman;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Resources {

    private static final List<String> DATA = Arrays.asList(
            "Ala ma kota",
            "Kot ma Ale",
            "Programowanie JAVA",
            "Test Driven Development",
            "Klasy generyczne",
            "Klasa abstrakcyjna",
            "Software Development Academy",
            "Java Development Kit",
            "Lubie programowac",
            "Jaki piekny kod",
            "Funkcja rekurencyjna",
            "spaghetti code",
            "Kompilator",
            "SOLID",
            "system kontroli wersji GIT",
            "Kompozycja",
            "Polimorfizm",
            "Dziedziczenie",
            "Wyjatki",
            "Typy generyczne",
            "Lambda",
            "Debugowanie",
            "JavaFX",
            "Stream",
            "zlozonosc obliczeniowa",
            "Kolejki",
            "Kopce",
            "Drzewa",
            "Graf",
            "quicksort",
            "bubble sort",
            "Garbage collector",
            "mockowanie",
            "Singleton",
            "Builder",
            "Wzorce projektowe",
            "Baza danych"
    );

    public static String getRandomPuzzle() {
        Random random = new Random();
        int index = random.nextInt(DATA.size());
        return DATA.get(index);
    }
}