import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class CountingWords {

    private final String src;

    CountingWords(String src) {
        this.src = src;
    }


    Map<String, Entry> getAllWordsWithoutRepeats() {
        Map<String, Entry> records = new TreeMap<>(Comparator.comparing(String::toLowerCase));
        String[] lines = getTheResultOfGettingAllWords(src).split("\n");
        for (String line : lines) {
            // I wasn't sure if we are looking for index of line or for index of whole file, so i decided to apply index of line.
            int index = 1;
            for (String word : line.split(" ")) {
                if (!records.containsKey(word)) {
                    records.put(word, new Entry(index));
                }
                else{
                    records.get(word).addNewRecord(index);
                }
                ++index;
            }
        }
        records.remove("");
        return records;
    }

    private static String getTheResultOfGettingAllWords(String filePath)
    {
        StringBuilder sb = new StringBuilder();
        File file = new File(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(filePath)).getPath());
        try (BufferedReader br = new BufferedReader(new FileReader(file.toPath().toString())))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                if (!sCurrentLine.isEmpty()){
                    sb.append(sCurrentLine.replaceAll("\\p{Punct}|–|„|”|\\p{Digit}", "")).append("\n");
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }

    static class Entry {
        private int counting;
        private List<Integer> indexes;

        Entry(int index) {
            counting = 1;
            indexes = new ArrayList<>();
            indexes.add(index);
        }
        void addNewRecord(int index) {
            ++counting;
            indexes.add(index);
        }

        @Override
        public String toString() {
            return " - " + counting + " - pozycje -> " + indexes;
        }

        int getCounting() {
            return counting;
        }

        List<Integer> getIndexes() {
            return indexes;
        }
    }
}
