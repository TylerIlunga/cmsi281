package sentinal;

import java.util.Arrays;

public class PhraseHash implements PhraseHashInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------

    private final static int BUCKET_START = 1000;
    private final static double LOAD_MAX = 0.7;
    private int size, longest;
    private String[] buckets;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    PhraseHash () {
        size = 0;
        longest = 0;
        buckets = new String[BUCKET_START];
    }


    // -----------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------

    public int size () {
        return size;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public void put (String s) {
        buckets[hash(s)] = s;
        longest = evaluateLongestPhrase(s);
        size++;
    }

    public String get (String s) {
        return buckets[hash(s)];
    }

    public int longestLength () {
        return longest;
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    private int hash (String s) {
        checkAndGrow();
        int total = 7;
        for (int i = 0; i < s.length(); i++) {
        	total = (total * 31) + (int)s.charAt(i);
        }
        return Math.abs(total % buckets.length);
    }

    private void checkAndGrow () {
        if ((size / buckets.length) < 0.7) { return; }

        String[] newBuckets = new String[buckets.length * 2];
        for (int i = 0; i < buckets.length; i++) {
           newBuckets[i] = buckets[i];
        }

        buckets = newBuckets;
    }

    private int evaluateLongestPhrase(String s) {
        if (size == 0) {
          return s.split("\\s+").length;
        }

        if (s.split("\\s+").length < longest) {
          return longest;
        }

        return s.split("\\s+").length;
    }
}
