import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordLadder {

  private Dictionary dictionary;
  private ArrayList<Ladder> ladders;

  public WordLadder(String inputFileName, String dictionaryFileName) {
    dictionary = new Dictionary(dictionaryFileName);
    setLadders(inputFileName);
  }

  private void setLadders(String inputFileName) {
    try {
      Scanner s = new Scanner(new File(inputFileName));
      ladders = new ArrayList<>();
      while(s.hasNext()) {
        String first = s.next();
        String second = s.next();
        System.out.println(first + " " + second);
        ladders.add(new Ladder(dictionary, first, second));
      }
    }
    catch(FileNotFoundException e) {
      System.err.println("Word Ladder input file not found");
      System.exit(-1);
    }
  }

  @Override
  public String toString() {
    return ladders.stream()
      .map(ladder -> ladder.toString() + "\n")
      .reduce((prev, cur) -> prev + cur)
      .orElseThrow(RuntimeException::new);
  }


}