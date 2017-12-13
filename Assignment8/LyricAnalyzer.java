import java.io.*;
import java.util.*;

public class LyricAnalyzer { // score 4

    private HashMap<String, ArrayList<Integer>> map;
    public LyricAnalyzer() {
        this.map = new HashMap<String, ArrayList<Integer>>();
    }
    //read the lyrics from file and adds to the map
    public void read(File file) throws IOException {

        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        int count = 0;
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] words = line.trim().split("\\s+");
            for (int i = 0; i < words.length; i++) {
                String word = words[i].toUpperCase();
                count++;
                int newCount = count;
                if (i == words.length - 1) {
                    newCount = -newCount;
                }
                add(word, newCount);
            }
        }
        br.close();
        reader.close();

    }
    private void add(String lyricWord, int wordPosition) {
        lyricWord = lyricWord.toUpperCase();
        if (map.containsKey(lyricWord)) {
            ArrayList<Integer> arr = map.get(lyricWord);
            arr.add(wordPosition);
            this.map.put(lyricWord, arr);
        } else {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(wordPosition);
            this.map.put(lyricWord, arr);
        }
    }
    public void displayWords() {
        List<Map.Entry<String, ArrayList<Integer>>> list = new ArrayList<Map.Entry<String, ArrayList<Integer>>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
            @Override
            public int compare(Map.Entry<String, ArrayList<Integer>> o1, Map.Entry<String, ArrayList<Integer>> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for (Map.Entry<String, ArrayList<Integer>> word : list) {
            System.out.print(word.getKey() + ": ");
            ArrayList<Integer> positions = word.getValue();
            for (int position : positions) {
                System.out.print(position + ", ");
            }
            System.out.println();
        }
    }
    public void writeLyrics(File file) throws IOException{
        int len = this.count();
        String[] lyrics = new String[len + 1];
        List<Map.Entry<String, ArrayList<Integer>>> list = new ArrayList<>(map.entrySet());
        for (Map.Entry<String, ArrayList<Integer>> word : list) {
            ArrayList<Integer> positions = word.getValue();
            for (int position : positions) {
                if (position < 0) {
                    position = -position;
                    lyrics[position] =word.getKey() + "\n";
                } else {
                    lyrics[position] = word.getKey();
                }

            }
        }
        FileWriter writer = new FileWriter(file);
        PrintWriter pw = new PrintWriter(writer);
        for (int i = 1; i < lyrics.length; i++) {
            String word = lyrics[i];
            if (i == lyrics.length - 1) {
                word = word.substring(0,word.length() - 1);
                pw.print(word);
            } else if (lyrics[i].contains("\n")) {
                pw.print(lyrics[i]);
            } else {
                pw.print(lyrics[i] + " ");
            }
        }
        pw.close();
        writer.close();
    }
    public int count() {
        if(this.map == null) {
            return 0;
        }
        int count = 0;
        List<Map.Entry<String, ArrayList<Integer>>> list = new ArrayList<>(map.entrySet());
        for (Map.Entry<String, ArrayList<Integer>> word : list) {
            ArrayList<Integer> positions = word.getValue();
            for (int position : positions) {
                count++;
            }
        }
        return count;
    }
    public String mostFrequentWord() {
        int freqNum = 0;
        String freLyric = "";
        List<Map.Entry<String, ArrayList<Integer>>> list = new ArrayList<>(map.entrySet());
        for (Map.Entry<String, ArrayList<Integer>> word : list) {
            if (freqNum < word.getValue().size()) {
                freqNum = word.getValue().size();
                freLyric = word.getKey();
            }
            if (freqNum == word.getValue().size()) {
                if (freLyric.compareTo(word.getKey()) > 0) {
                    freLyric = word.getKey();
                }
            }
        }
        System.out.println(freLyric);
        return freLyric;
    }
}
