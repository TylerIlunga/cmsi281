package dictreenary;

import java.util.ArrayList;

public class Dictreenary implements DictreenaryInterface {

    // Fields
    // -----------------------------------------------------------
    TTNode root;


    // Constructor
    // -----------------------------------------------------------
    Dictreenary () {}


    // Methods
    // -----------------------------------------------------------

    public boolean isEmpty () {
        return isEmpty(root);
    }

    public void addWord (String toAdd) {
        toAdd = normalizeWord(toAdd);
        if (isEmpty(root)) {
          root = new TTNode(toAdd.charAt(0), false);
        }
        addWord(root, toAdd, 0);
    }

    public boolean hasWord (String query) {
        if (isEmpty(root)) return false;
        query = normalizeWord(query);
        TTNode currentNode = root;
        return hasWord(currentNode, query, 0);
    }

    public String spellCheck (String query) {
        if (isEmpty(root)) return null;
        query = normalizeWord(query);
        TTNode currentNode = root;
        if (hasWord(currentNode, query, 0)) {
          return query;
        }
        return spellCheck(currentNode, query.toCharArray(), 0);
    }

    public ArrayList<String> getSortedWords () {
        if (isEmpty(root)) return null;
        ArrayList<String> sortedWords = new ArrayList<String>();
        getSortedWords(root, "", sortedWords);
        return sortedWords;
    }


    // Helper Methods
    // -----------------------------------------------------------

    private String normalizeWord (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }

    /*
     * Returns:
     *   int less than 0 if c1 is alphabetically less than c2
     *   0 if c1 is equal to c2
     *   int greater than 0 if c1 is alphabetically greater than c2
     */
    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }

    // [!] Add your own helper methods here!

    private static boolean isEmpty(TTNode root) {
      return root == null;
    }

    private void addWord (TTNode currentNode, String toAdd, int charLocation) {
      char currentLetter = toAdd.charAt(charLocation);
      if (compareChars(currentLetter, currentNode.letter) < 0) {
        if (currentNode.left == null) {
          currentNode.left = new TTNode(currentLetter, false);
        }
        addWord(currentNode.left, toAdd, charLocation);
      } else if (compareChars(currentLetter, currentNode.letter) > 0) {
        if (currentNode.right == null) {
          currentNode.right = new TTNode(currentLetter, false);
        }
        addWord(currentNode.right, toAdd, charLocation);
      } else {
        if (charLocation + 1 == toAdd.length()) {
          currentNode.wordEnd = true;
          return;
        }
        if (currentNode.mid == null) {
          currentNode.mid = new TTNode(toAdd.charAt(charLocation + 1), false);
        }
        addWord(currentNode.mid, toAdd, charLocation + 1);
      }
    }

    private boolean hasWord (TTNode currentNode, String query, int charLocation) {
      while (currentNode != null) {
        char currentLetter = query.charAt(charLocation);
        if (compareChars(currentLetter, currentNode.letter) < 0) {
          currentNode = currentNode.left;
        } else if (compareChars(currentLetter, currentNode.letter) > 0) {
          currentNode = currentNode.right;
        } else {
          if (charLocation + 1 == query.length()) {
            return currentNode.wordEnd;
          }
          charLocation++;
          currentNode = currentNode.mid;
        }
      }
      return false;
    }

    private void swap(char[] letters, int currentIndex) {
      char temp = letters[currentIndex];
      letters[currentIndex] = letters[currentIndex + 1];
      letters[currentIndex + 1] = temp;
    }

    private String spellCheck(TTNode currentNode, char[] queryLetters, int charLocation) {
        for (int i = 0; i < queryLetters.length - 1; i++) {
          swap(queryLetters, i);
          String finalQuery = String.valueOf(queryLetters);
        	if (hasWord(currentNode, finalQuery, 0)) {
        		return finalQuery;
        	}
        	swap(queryLetters, i);
        }
        return null;
    }

    private void getSortedWords(TTNode currentNode, String word, ArrayList<String> words) {
      if (currentNode == null) {
        return;
      }
      getSortedWords(currentNode.left, word, words);
      if (currentNode.wordEnd) {
        words.add(word + currentNode.letter);
      }
      getSortedWords(currentNode.mid, word + currentNode.letter, words);
      getSortedWords(currentNode.right, word, words);
    }


    // TTNode Internal Storage
    // -----------------------------------------------------------

    /*
     * Internal storage of Dictreenary words
     * as represented using a Ternary Tree with TTNodes
     */
    private class TTNode {

        boolean wordEnd;
        char letter;
        TTNode left, mid, right;

        TTNode (char c, boolean w) {
            letter  = c;
            wordEnd = w;
        }

    }

}
