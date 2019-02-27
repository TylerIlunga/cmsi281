import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UniqueWords {
  public static void main(String[] args) {
    int uniqueWordsCount = 0;
    Scanner input = new Scanner(System.in);
    System.out.println("Type and [ENTER] words below to see how many unique words are within the input!");

    String inputString = input.nextLine().toLowerCase();
    if (inputString.equals("")) {
      throw new IllegalArgumentException("You must input at least one word to run this program!");
    }

    Pattern lettersOnly = Pattern.compile("[^a-zA-Z ]+");
    Matcher lettersOnlyMatcher = lettersOnly.matcher(inputString);
    if (lettersOnlyMatcher.find()) {
      throw new IllegalArgumentException("You must input ONLY words!");
    }

    HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
    for (String word : inputString.split(" ")) {
      if (wordMap.containsKey(word)) {
        continue;
      } else {
        wordMap.put(word, 1);
        uniqueWordsCount = uniqueWordsCount + 1;
      }
    }
    System.out.println(uniqueWordsCount);
  }
}
