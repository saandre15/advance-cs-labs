import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Dictionary  {
  
  private ArrayList<String> words;
  private String filename;
  
  public Dictionary(String filename) {
    setWords(filename);
    this.filename = filename;
    System.out.println("Dictionary initalized");
  }

  private void setWords(String filename) {
    try {
      words = new ArrayList<>();
      Scanner s = new Scanner(new File(filename));
      while(s.hasNext()) {
        words.add(s.next());
      }
    }
    catch(FileNotFoundException e) {
      System.err.println("Invalid dictionary filename");
      System.exit(-1);
    }
  }

  public List<String> getAllDifferByOne(String s) {
    return words.stream()
      .filter(word -> differByOne(s, word))
      .collect(Collectors.toList());
    
  }

  private boolean differByOne(String a, String b) {
    if(a.length() != b.length())
      return false;
    for(int i = 0 ; i < a.length() ; i++) {
      if(a.charAt(i) != b.charAt(i)) {
        return new StringBuilder(a)
          .deleteCharAt(i)
          .toString()
          .equals(
            new StringBuilder(b)
            .deleteCharAt(i)
            .toString()
          );
      }
    }
    return false;
  }

  public boolean contains(String s) {
    return words.contains(s);
  }
  
  public boolean remove(String s) {
    return words.remove(s);
  }

  public int getLeastDiff(List<String> source, String target) {
    int diff = Integer.MAX_VALUE;
    for(int i = 0 ; i < source.size() ; i++) {
      int d = getDiff(source.get(i), target);
      if(d < diff) diff = d;
    }
    return diff;
  }

  public int getDiff(String source, String target) {
    int diff = Math.abs(source.length() - target.length());
    String smallest = source.length () > target.length() ? target : source;
    for(int i = 0 ; i < smallest.length() ; i++) if(source.charAt(i) != target.charAt(i)) diff++;
    return diff;
  }

  // Unit Test
  public static void main(String[] args) {
    try {
      Dictionary d = new Dictionary(Runner.dictionaryFN);
      assert d.differByOne("sdabus", "sdcbus") == true : "differByOne() failed";
      ArrayList<String> differByOne = new ArrayList<>();
      differByOne.add("HOAXER");
      differByOne.add("HOAXES");
      assert d.getAllDifferByOne("HOAXED").equals(differByOne) : "getAllDifferByOneFailed";
    }
    catch(AssertionError e) {
      System.err.println("Dictionary unit test failed!");
      System.exit(-1);
    }
    finally {
      System.out.println("Dictionary unit test succeeded!");
    }
  }

}