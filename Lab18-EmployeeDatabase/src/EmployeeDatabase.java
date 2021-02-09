import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public abstract class EmployeeDatabase   {

  
  public class Entry {
    private int id;
    private Employee employee;

    public Entry(int id, Employee employee) {
      this.id = id;
      this.employee = employee;
    }

    public Employee getEmployee() {
      return employee;
    }

    public int getId() {
      return id;
    }

    @Override
    public String toString() {
      return "<" + "ID: " + id + ", Employee: " + employee.getName() +">";
    }

  }

  protected Entry[] entry;

  // Adding a higher table size increases the speed however decreases
  public EmployeeDatabase(int entrySize) {
    this.entry = new Entry[entrySize];
  }

  public abstract boolean put(int key, Employee val);

  public abstract Employee get(int key);

  // Good - Equal probablity for each array index.
  protected int hashcode(int key) {
    byte[] digest = getDigest(key);
    Stack<Integer> vals = new Stack<>();
    setDigestStack(vals, digest);
    int size = String.valueOf(this.entry.length)
      .chars()
      .map(cur -> 1)
      .reduce((prev, cur) -> prev + cur)
      .getAsInt();
    String mod = "";
    while(size > 0) {
      if(vals.size() == 1) setDigestStack(vals, getDigest(vals.pop()));
      String str = String.valueOf(vals.pop());
      str = switch(str.length()) {
        case 1 -> 0 + str;
        case 2 -> str;
        case 3 -> str.substring(1);  
        default -> throw new IllegalStateException("Incorrect byte value.");
      };
      mod+=str;
      size-=2;
    }
    
    int code = Integer.valueOf(mod) > this.entry.length 
      ? Integer.valueOf(mod) % this.entry.length 
      : Integer.valueOf(mod) + vals.peek() % this.entry.length;

    return code;
    
  }

  // Bad - Unequal and clustered probablity for each array index.
  protected int hashcode_bad(int key) {
    // TODO
  }

  private byte[] getDigest(int key) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] digest = md.digest(String.valueOf(key).getBytes());
      return digest;
    }
    catch(NoSuchAlgorithmException e) {
      System.err.println("Invalid Digest Algorithm");
      System.exit(-1);
      return null;
    }
  }

  private void setDigestStack(Stack<Integer> stack, byte[] digest) {
    for(int i = 0 ; i < digest.length ; i++) stack.push((int)digest[i]);
  }
  

}
