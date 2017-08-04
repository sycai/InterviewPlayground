package Hard;

import Trees.Trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * CCI 17.25 Given a list of millions of words, design an algorithm to create the largest possible rectangle of letters
 * such that every row forms a word (reading left to right) and every column forms a word (reading top to bottom). The
 * words need not be chose consecutively from the list, but all rows must be the same length and all columns must be
 * the same height.
 */

public class WordRectangle {
    private Trie validWords;
    private List<String>[] wordsOfLen;

    private class Rectangle {
       final int len; // All the word in this rectangle should have this length
       List<String> words;

       public Rectangle(int len) {
           this.len = len;
           words = new LinkedList<>();
       }

       // Copy constructor
       public Rectangle(Rectangle other) {
           len = other.len;
           words = new LinkedList<>(other.words);
       }

       public void append(String word) {
           if (word.length() != len) {
               throw new IllegalArgumentException("The length of the given word is not " + len);
           }
           words.add(word);
       }

       public void removeLastWord() {
           if (words.size() != 0) {
               words.remove(words.size() - 1);
           }
       }

       public int height() {
           return words.size();
       }

       private String getColumn(int c) {
           StringBuilder sb = new StringBuilder();
           for (String row : words) {
               sb.append(row.charAt(c));
           }
           return sb.toString();
       }

       public boolean isPartialComplete(Trie validWords) {
           for (int c = 0; c < len; c++) {
               String column = getColumn(c);
               if (!validWords.containsPrefix(column)) {
                   return false;
               }
           }
           return true;
       }

       public boolean isComplete(Trie validWords) {
           for (int c = 0; c < len; c++) {
               String column = getColumn(c);
               if (!validWords.contains(column)) {
                   return false;
               }
           }
           return true;
       }

       public List<String> getWords() {
           return words;
       }

       public int size() { return len * words.size(); }

       @Override
       public String toString() {
           return words.stream().collect(Collectors.joining("\n"));
       }
    }

    private final int maxWordLen;

    public WordRectangle(File f) {
        LinkedList<String> allWords = new LinkedList<>();
        int maxLen = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ( (line = br.readLine()) != null) {
                line = line.trim();
                allWords.add(line);
                maxLen = Math.max(maxLen, line.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        maxWordLen = maxLen;

        validWords = new Trie();
        wordsOfLen = (List<String>[]) new List[maxLen + 1];
        for (int i = 0; i < wordsOfLen.length; i++) {
            wordsOfLen[i] = new LinkedList<>();
        }
        for (String word : allWords) {
            int len = word.length();
            validWords.insert(word);
            wordsOfLen[len].add(word);
        }
    }

    public List<String> getMaxRectangle() {
        int maxSizeSoFar = 0;
        List<String> max = new LinkedList<>();
        for (int len = maxWordLen; len > 0; len--) {
            System.out.println("Trying matrix with length: " + len);
            Rectangle base = new Rectangle(len);
            Rectangle maxRec = findMaxRecWithLen(base);
            if (maxSizeSoFar < maxRec.size()) {
                maxSizeSoFar = maxRec.size();
                max = maxRec.getWords();
            }
        }
        return max;
    }

    private Rectangle findMaxRecWithLen(Rectangle base) {
        int h = base.height();
        int l = base.len;

        // There is no need to validate a matrix whose height is larger than length,
        // because if such matrix M exists, then the transpose of M is also a valid matrix.
        if (h == l) {
            if (base.isComplete(validWords)) {
                return new Rectangle(base);
            } else {
                return new Rectangle(l); // Rectangle of height 0 means a failed search
            }
        }

        if (!base.isPartialComplete(validWords)) {
            return new Rectangle(l);
        } else {
            Rectangle maxSoFar = base.isComplete(validWords) ? new Rectangle(base) : new Rectangle(l);
            for (String word : wordsOfLen[l]) {
                base.append(word);
                Rectangle nextMax = findMaxRecWithLen(base);
                if (nextMax.height() > maxSoFar.height()) {
                    maxSoFar = nextMax;
                }
                base.removeLastWord();
            }
            return maxSoFar;
        }
    }

    public static void main(String[] args) {
        File file = new File("words_alpha.txt");
        WordRectangle wr = new WordRectangle(file);
        List<String> res = wr.getMaxRectangle();
        for (String s : res) {
            System.out.println(s);
        }
    }
}
