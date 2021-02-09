public class Runner {

  public static String dictionaryFN = "dictionary.txt";
  public static String inputFN =  "input.txt";
  public static String issueFN = "issue.txt";
  
  public static void main(String[] args) {
    WordLadder w = new WordLadder(inputFN, dictionaryFN);
    System.out.println(w.toString());
    System.out.println("success");
  }
}