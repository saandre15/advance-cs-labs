import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Ladder {

  private Dictionary dictionary;
  private String from;
  private String to;
  private ArrayList<String> steps;

  public Ladder(Dictionary d, String from, String to) {
    this(d, from, to, true);
  }
  
  public Ladder(Dictionary d, String from, String to, boolean isDebug) {
    dictionary = d;
    this.from = from.toUpperCase();
    this.to = to.toUpperCase();
    this.steps = new ArrayList<>();
    setSteps(isDebug);
    // if(setSteps(isDebug))
    //   System.err.println("[WARNING]: Unable to find the ladder between " + from + " " + to);;
  }

  // private void setSteps(boolean isDebug) {
  //   steps = new ArrayList<>();
  //   // Get the starting word and search through dictionary to find all words differ by one
  //   List<String> wordList = dictionary.getAllDifferByOne(from);
  //   List<Stack<String>> stacks = new ArrayList<>();
  //   Queue<Stack<String>> queue = new LinkedList<>();
  //   for(String word: wordList) {
  //     // Creates a stacks for each of the words and pushes them into the stack with the first word at the front
  //     Stack<String> s = new Stack<>();
  //     s.push(from);
  //     s.push(word);
  //     // Enqueue each of the stacks into the queue
  //     queue.offer(s);
  //   }
  //   // Dequeue the first item
  //   Stack<String> s = queue.poll();
  //   // Compare it to its top word to the ending word
  //   if(!s.peek().equals(s.peek().equals(s.elementAt(0)))) {
  //     // Find all word one different letter
  //     List<String> a = dictionary.getAllDifferByOne(s.peek());
  //     // For each of the words
  //     for(String word: a) {
  //       Stack<String> copy = new Stack<>();
  //       for(int i = 0 ;  i < s.size() ; i++) {
  //         copy.push(copy.get(i));
  //       }
  //       copy.push(word);
  //       queue.offer(copy);
  //     }
      
  //   }
  // }

  // Student optimized code
  private void setSteps(boolean isDebug) {
    // Dictionary final word doesn't exit. Don't waste time searching for something that doesn't exist.
    if(!dictionary.contains(to)) {
      if(isDebug) System.out.println("[]" + "\n\n\n\n");
      return;
    }
    
    Queue<Stack<String>> paths = new LinkedList<>();
    Stack<String> start = new Stack<>();
    start.add(from);
    steps = new ArrayList<>(findPath(start));
    if(isDebug) System.out.println(steps + "\n\n\n\n");
  }
  /**
   * Uses a recursive tree algo to solve this problem.
   * @param lastPath stores the last path state so we know where to look if we need to go back
   * @param paths stores all the paths that get to the last word
   */
  private Stack<String> findPath(Stack<String> lastPath) {
    if(lastPath.peek().contains(to)){
      return lastPath; 
    }

    List<String> possiblePaths = dictionary
      .getAllDifferByOne(lastPath.peek())
      .stream()
      // Removes all word that the path already explored
      .filter(cur -> !lastPath.contains(cur))
      .collect(Collectors.toList());
    // Tries to reduce to the least different word.
    int leastDiff = dictionary.getLeastDiff(possiblePaths, to);
    possiblePaths.removeIf(cur -> dictionary.getDiff(cur, to) != leastDiff);
    // Too big to find
    if(lastPath.size() > 150)
      return new Stack<>();

    
    for(int i = 0 ; i < possiblePaths.size() ; i++) {
      Stack<String> s = (Stack<String>)lastPath.clone();
      s.push(possiblePaths.get(i));
      Stack<String> path = findPath(s);
      if(path != null) return path;
    }
    return null;
  }

  public boolean isEmpty() {
    return steps.isEmpty();
  }

  
  
  @Override
  public String toString() {
    return isEmpty() 
      ? "No ladder between " 
        + from 
        + " & " 
        + to 
      : "Found a ladder >>>" 
        + " ["
        + from + " -> "
        + steps.toString().substring(1, steps.toString().length() - 1) + " -> " 
        + to
        + "]";
  }

  // Unit Test
  public static void main(String[] args) {
    Ladder ladder = new Ladder(new Dictionary(Runner.dictionaryFN), "HOBBLED", "MODERNS", true);
    System.out.println(ladder.toString());
  }

}