import java.util.Random;

public class TextGenerator {
    private static Random random = new Random();
    private static final int QUANTITY_OF_TEXTS = 100_000;
    private static final String LETTER_SET = "abc";
    private static final int DIAPASON_OF_TEXT_LENGTH_CHANGING = 3;

    // генерация текста
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    // генерация набора текстов
    static String [] generateTexts() {
        String[] texts = new String[QUANTITY_OF_TEXTS];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = TextGenerator.generateText(LETTER_SET, LETTER_SET.length() + random.nextInt(DIAPASON_OF_TEXT_LENGTH_CHANGING));
        }
        return texts;
    }
}
