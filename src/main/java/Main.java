import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger wordLength_3 = new AtomicInteger(0);
    static AtomicInteger wordLength_4 = new AtomicInteger(0);
    static AtomicInteger wordLength_5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final String [] nickNames = TextGenerator.generateTexts();

        Thread analyzeByPalindrome = new Thread(() -> {
            StringBuilder reversS = new StringBuilder();
            for (String s : nickNames) {
                reversS.delete(0, reversS.length()).append(s).reverse();
                if (s.equals(reversS.toString())) {
                    switchOfLength(s.length());
                }
            }
        });

        Thread analyzeByEqualLetters = new Thread(() -> {
            for (String s : nickNames) {
                if (s.chars().filter(x -> x == s.charAt(0)).count() == s.length()) {
                    switchOfLength(s.length());
                }
            }
        });

        Thread analyzeBySorting = new Thread(() -> {
            StringBuilder sortedS = new StringBuilder();
            for (String s : nickNames) {
                sortedS.delete(0,sortedS.length());
                Arrays.stream(s.split("")).sorted().forEach(sortedS::append);
                if (s.equals(sortedS.toString())) {
                    switchOfLength(s.length());
                }
            }
        });

        analyzeByPalindrome.start();
        analyzeByEqualLetters.start();
        analyzeBySorting.start();

        analyzeByPalindrome.join();
        analyzeByEqualLetters.join();
        analyzeBySorting.join();

        System.out.println("Красивых слов с длиной 3: " + wordLength_3.get() + " шт\n" +
                           "Красивых слов с длиной 4: " + wordLength_4.get() + " шт\n" +
                           "Красивых слов с длиной 5: " + wordLength_5.get() + " шт");
        System.out.println(wordLength_3.get() + wordLength_4.get() + wordLength_5.get());
    }

    static void switchOfLength(int length) {
        switch (length) {
            case (3):
                wordLength_3.incrementAndGet();
            case (4):
                wordLength_4.incrementAndGet();
            case (5):
                wordLength_5.incrementAndGet();
        }
    }
}
