import java.io.File;
import java.io.IOException;

public class Assignment8 {
    public static String FILE = "Question2_test1.txt";
    public static void main(String[] args) throws IOException{
        //2.
        File file = new File(FILE);
        LyricAnalyzer lyricAnalyzer = new LyricAnalyzer();
        lyricAnalyzer.read(file);
        lyricAnalyzer.displayWords();
        System.out.println("count number = " + lyricAnalyzer.count());
        lyricAnalyzer.writeLyrics(file);
        lyricAnalyzer.mostFrequentWord();
    }
}
