import java.io.IOException;
import java.util.Scanner;

public class FileCounter { // score 3
    private int characterCount, wordCount, lineCount;
    public FileCounter() {
        this.characterCount = 0;
        this.wordCount = 0;
        this.lineCount = 0;
    }
    public int getWordCount() {
        return this.wordCount;
    }
    public int getCharacterCount() {
        return this.characterCount;
    }
    public int getLineCount() {
        return this.lineCount;
    }
    /**
        Processes an input source and adds its character, word, and line counts to the respective variable.
        @param in the scanner to process
    */
    public void read(Scanner in) throws IOException {
        int countLine = 0;
        int countWord = 0;
        int countCharacter = 0;
        while (in.hasNext()) {
            String line = in.nextLine().trim();
            countLine++;
            String[] words = line.split("\\s+");
            countWord += words.length;
            for (String word : words) {
                countCharacter += word.length();
            }
        }
        this.characterCount =countCharacter;
        this.wordCount = countWord;
        this.lineCount = countLine;
    }
}
