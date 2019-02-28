package sentinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sentinal implements SentinalInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------

    private PhraseHash posHash, negHash;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    Sentinal (String posFile, String negFile) throws FileNotFoundException {
        Scanner posRead = new Scanner(new File(posFile));
        Scanner negRead = new Scanner(new File(negFile));
        posHash = new PhraseHash();
        negHash = new PhraseHash();

        while (posRead.hasNext()) {
          String posStr = posRead.nextLine();
          posHash.put(posStr);
        }

        while (negRead.hasNext()) {
          String negStr = negRead.nextLine();
          negHash.put(negStr);
        }
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------

    public void loadSentiment (String phrase, boolean positive) {
        PhraseHash hash = positive ? posHash : negHash;
        hash.put(phrase);
    }

    public void loadSentimentFile (String filename, boolean positive) throws FileNotFoundException {
        PhraseHash hash = positive ? posHash : negHash;
        handlePhraseStorage(new Scanner(new File(filename)), hash);
    }

    public String sentinalyze (String filename) throws FileNotFoundException {
        String result = "neutral";
      	File file = new File(filename);
      	Scanner scanner = new Scanner(file);
      	int lineRank = 0;

      	while (scanner.hasNextLine()) {
      	    String[] line = scanner.nextLine().split("\\s+");
            lineRank += rankCurrentLine(posHash, line);
            lineRank -= rankCurrentLine(negHash, line);
      	}

        result = lineRank > 0 ? "positive" : result;
        result = lineRank < 0 ? "negative" : result;
        return result;
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    private void handlePhraseStorage(Scanner reader, PhraseHash hash) {
        while (reader.hasNext()) {
          hash.put(reader.nextLine());
        }
    }

    private int rankCurrentLine(PhraseHash current, String[] line) {
        int count = 0;
      	int longest = current.longestLength();
        int lineLength = line.length;

      	for (int i = 0; i < lineLength; i++) {
      		for (int j = 1; i + j - 1 < lineLength && j < longest + 1; j++) {
      			String toCheck = String.join(" ", Arrays.copyOfRange(line, i, i + j));
      			if (current.get(toCheck) != null) { count++; }
      		}
      	}
      	return count;
    }

}
