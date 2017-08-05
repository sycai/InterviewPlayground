package Hard;

import java.util.*;

/**
 * The similarity of two documents (each with distinct words) is defined to be the size of the intersection divided by
 * the size of the union. For example, if the document consist of integers, the similarity of {1, 5, 3} and {1, 7, 3, 2}
 * is 0.4, because the intersection has size 2 and the union has size 5.
 *
 * We have a long list of documents (with distinct values and each with an associated ID) where the similarity is
 * believed to be "sparse". That is, any two arbitrary selected documents are very likely to have similarity 0. Design
 * an algorithm that returns a list of pairs of document IDs and the associated similarity.
 *
 * Print only the pairs with similarity greater than 0. Empty documents should not be printed at all. For simplicity,
 * you may assume each document is represented as an array of distinct integers.
 *
 * EXAMPLE:
 * Input:
 *    13: {14, 15, 100, 9, 3}
 *    16: {31, 1, 9, 3, 5}
 *    19: {15, 29, 2, 6, 8, 7}
 *    24: {7 ,10}
 * Output:
 *    ID1  ID2 : SIMILARITY
 *    13   19  : 0.1
 *    13   16  : 0.25
 *    19   24  : 0.14285714285714285
 */

public class SparseSimilarity {
    public static class Document {
        private final int id;
        private final List<Integer> words;

        public Document(int id, List<Integer> words) {
            this.id = id;
            this.words = words;
        }

        public Iterable<Integer> words() {
            LinkedList<Integer> res = new LinkedList<>(words);
            return res;
        }

        public int size() {
            return words.size();
        }

        @Override
        public int hashCode() {
            return ((Integer) id).hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Document) {
                Document o = (Document) other;
                return o.id == this.id;
            }
            return false;
        }
    }

    private class Pair {
        Document doc1;
        Document doc2;

        public Pair(Document d1, Document d2) {
            if (d1.id < d2.id) {
                doc1 = d1;
                doc2 = d2;
            } else {
                doc1 = d2;
                doc2 = d1;
            }
        }

        @Override
        public int hashCode() {
            return (doc1.hashCode() * 37) ^ doc2.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Pair) {
                Pair o = (Pair) other;
                return (o.doc1.equals(this.doc1)) && (o.doc2.equals(this.doc2));
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("%d\t%d", doc1.id, doc2.id);
        }
    }

    private List<Document> docs;
    private HashMap<Integer, List<Document>> wordToDoc;

    public SparseSimilarity(List<Document> documents) {
        docs = documents;
        wordToDoc = new HashMap<>();
        for (Document d : docs) {
            for (int w : d.words()) {
                if (wordToDoc.containsKey(w)) {
                    wordToDoc.get(w).add(d);
                } else {
                    wordToDoc.put(w, new LinkedList<>(Arrays.asList(d)));
                }
            }
        }
    }

    public HashMap<int[], Double> getAllSimPair() {
        HashMap<Pair, Integer> unionCount = new HashMap<>();
        for (int w : wordToDoc.keySet()) {
            List<Document> documents = wordToDoc.get(w);
            if (documents.size() == 1)  continue;

            for (int i = 0; i < documents.size() - 1; i++) {
                for (int j = i + 1; j < documents.size(); j++) {
                    Pair p = new Pair(documents.get(i), documents.get(j));
                    unionCount.put(p, unionCount.getOrDefault(p,0) + 1);
                }
            }
        }

        HashMap<int[], Double> res = new HashMap<>();
        for (Pair pair : unionCount.keySet()) {
            int union = unionCount.get(pair);
            int intersection = pair.doc1.size() + pair.doc2.size() - union;
            double similarity = ((double) union) / intersection;
            res.put(new int[] {pair.doc1.id, pair.doc2.id}, similarity);
        }
        return res;
    }

    public static void main(String[] args) {
        Document d1 = new Document(13, Arrays.asList(14, 15, 100, 9, 3));
        Document d2 = new Document(16, Arrays.asList(32, 1, 9, 3, 5));
        Document d3 = new Document(19, Arrays.asList(15, 29, 2, 6, 8, 7));
        Document d4 = new Document(24, Arrays.asList(7, 10));

        SparseSimilarity ss = new SparseSimilarity(Arrays.asList(d1, d2, d3, d4));
        HashMap<int[], Double> res = ss.getAllSimPair();
        for (int[] key : res.keySet()) {
            System.out.println(Arrays.toString(key) + " : " + res.get(key));
        }
    }
}
