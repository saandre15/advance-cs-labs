import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.PrintWriter;
import java.lang.Comparable;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;


public class HuffmanTree {

  /**
   * -1 represents an empty node
   * this.frequencies.length represents termination node 
   */
  public class Node implements Comparable<Node> {

    private int val;
    public Node left,right;
    private int frequency;

    public Node(int val, int frequency) {
      this.val = val;
      this.frequency = frequency;
    }

    public Node(int val) {
      this(val, 1);
    }

    public Node() {
      this(-1);
    }

    public void add(int val, String treePos) {

      try {
        if(treePos.length() == 1) {
          Node node = getChild(Integer.parseInt(treePos));
          if(node != null) throw new DuplicateNodeException("A duplicate node indicates an invalid parsing.");
          setChild(Integer.parseInt(treePos), val);
          return;
        }
        
        int bin = Integer.parseInt(treePos.substring(0, 1));
        Node node = getChild(bin);
        if(node == null) setChild(bin, -1);
        node = getChild(bin);
        node.add(val, treePos.substring(1));
      }
      catch(NotSerializableException e) {
        System.out.println(e);
        System.exit(-1);
      } catch(DuplicateNodeException e) {
        System.out.println(e);
        print();
        System.exit(-1);
      }
    }

    private Node getChild(int binary) throws NotSerializableException {
      if(binary != 0 && binary != 1) throw new NotSerializableException();
      if(binary == 1) return right;
      return left;
    } 

    private void setChild(int binary, int val) throws NotSerializableException {
      if(binary != 0 && binary != 1) throw new NotSerializableException();
      if(binary == 1) right = new Node(val);
      else left = new Node(val);
    }

    public void setLeft(Node node) {
      left = node;
    }

    public void setRight(Node node) {
      right = node;
    }
    /**
     * Gets Total Amounts of Node
     */
    public int totalSize() {
      int total = 1;
      if(left != null) total += left.totalSize();
      if(right != null) total += right.totalSize();
      return total;
    }

    /**
     * Gets the frequency for this node
     */
    public int totalWeight() {
      // ignore empty nodes
      int total = this.val == -1 ? 0 : this.frequency;
      if(left != null) total += left.totalWeight();
      if(right != null) total += right.totalWeight();
      return total;
    }

    @Override
    public int compareTo(Node other) {
      int weight = totalWeight();
      int otherWeight = other.totalWeight();
      if(weight > otherWeight) return 1;
      else if (weight < otherWeight) return -1;
      return 0;
    }

    public String toEncodedString(String next) {
      String s =  val + "\n" + next + "\n"; 
      if(left == null && right == null) return s;
      else if(right == null && left != null) return left.toEncodedString(next + "0");
      else if (right != null && left == null) return right.toEncodedString(next + "1");
      else return left.toEncodedString(next + "0") + right.toEncodedString(next + "1");
    }
    
    public String toEncodedString() {
      return toEncodedString("");
    }

    public String valAtAsBinary(int val) {
      Stack<String> bin = new Stack<>();
      if(!valAtAsBinaryHelper(val, bin)) throw new NoSuchElementException();
      return bin.stream().reduce((prev, cur) -> prev + cur).get();
    }

    private boolean valAtAsBinaryHelper(int val, Stack<String> bin) {
      if(right != null) {
        bin.push("1");
        if(right.valAtAsBinaryHelper(val, bin)) return true;
        bin.pop();
      }

      if(left != null) {
        bin.push("0");
        if(left.valAtAsBinaryHelper(val, bin)) return true;
        bin.pop();
      }
      if(val == this.val) return true;
      return false;
    }

    @Override
    public String toString() {
      return this.val + "";
    }

  }

  private class DuplicateNodeException extends Exception {
    DuplicateNodeException(String msg) { super(msg); }
  }

  private PriorityQueue<Node> forest;
  private int[] frequencies;

  public HuffmanTree(int[] frequencies) {
    this.forest = new PriorityQueue<>();
    this.frequencies = frequencies;

    for(int i = 0 ; i < frequencies.length ; i++) {
      if(frequencies[i] != 0) forest.add(new Node(i, frequencies[i]));
    }

    while(forest.size() > 1) {
      Node node = new Node();
      Node node1 = forest.poll();
      Node node2 = forest.poll();
      //System.out.println("Node 1 NodeCount " + node1.totalSize() + " Val " + node1.val);
      //System.out.println("Node 2 NodeCount " + node2.totalSize() + " Val " + node1.val);
      node.setLeft(node1);
      node.setRight(node2);
      forest.add(node);
    }
    
  }

  public HuffmanTree(String codeFile) {
    this(new int[256]);
    try(Scanner parser = new Scanner(new File(codeFile))) {
      while(parser.hasNextLine()) {
        add(Integer.parseInt(parser.nextLine().trim()), String.valueOf(parser.nextLine().trim()));
      }
      this.forest.add(new Node(frequencies.length));
    } catch (FileNotFoundException e) {
      System.out.println("The encoding file was not found.");
      System.exit(-1);
    }
  }

  private void add(int val, String treePos) {
    if(val < -1 && val > frequencies.length) throw new IndexOutOfBoundsException("Cannot add an index that is greater than the available frequencies.");
    if(forest.peek() == null) forest.add(new Node());
    Node root = forest.peek();
    root.add(val, treePos);
  }

  public void write(String fileName) {
    try(FileWriter writer = new FileWriter(fileName)) {
      Node root = forest.peek();
      writer.write(root.toEncodedString());
    } catch(IOException e) {
      System.out.println("Unable to write encoding code to an unavailable file.");
      System.exit(-1);
    }
  }


  public void print() {
    System.out.println("Forest View");
    forest.stream().forEach(tree -> TreePrinter.printTree(tree));
  }

  public void decode(BitInputStream in, String outFile) {
    try (PrintWriter writer = new PrintWriter(outFile)) {
      forest.poll();
      Node node = forest.peek();
      Stack<Integer> binsOne = new Stack<>();
      Stack<Integer> binsTwo = new Stack<>();

      for(int i = 0 ; i < 8 ; i++) binsOne.push(in.readBit());
      for(int i = 0 ; i < 8 ; i++) binsTwo.push(in.readBit());


      int bit = binsOne.pop();

      while(bit != -1) {
        // System.out.println("BIT");
        // System.out.println(bit);
        if(bit != 0 && bit != 1) throw new Error("BitInputStream failed");
        Node temp = bit == 1 ? node.right : node.left;
        if(temp == null) {
          // System.out.println("NODE VAL");
          // System.out.println(node.val);
          writer.append((char) node.val);
          // Terminate Parsing
          // System.out.println("BINARY STRING");
          // System.out.println(Integer.toBinaryString(node.val));
          // System.out.println("INT VAL");
          // System.out.println(node.val);
          if(node.val == 4) return;
          node = bit == 1 ? forest.peek().right : forest.peek().left;
        } else node = temp;
        if(binsOne.size() == 1) {
          binsTwo.push(binsOne.pop());
          binsOne = binsTwo;
          binsTwo = new Stack<>();
          for(int i = 0 ; i < 8 ; i++) binsTwo.push(in.readBit());
        }
        bit = binsOne.pop();
      }
      
      // while(bit != -1) {
      //   if(bit != 0 && bit != 1) throw new Error("BitInputStream failed");
      //   Node temp = bit == 1 ? node.right : node.left;
      //   if(temp == null) {
      //     writer.append((char)node.val);
      //     node = forest.peek();
      //   } else node = temp;
      //   bit = in.readBit();
      // }
      in.finalize();
    } catch(IOException e) {
      System.out.println("Failed to access output file decoding.");
      System.exit(-1);
    }
  }

  /**
   * @return byte interpretation of an input character
   */
  public void encode(String inFile, BitOutputStream out) {
    try(Scanner scanner = new Scanner(new File(inFile))) {
      while(scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        for(int i = 0 ; i < nextLine.length() ; i++) {
          char c = nextLine.charAt(i);
          eachEncode((int)c, out);
        }
        // TODO: Add Nextline encoding
        eachEncode(10, out);
      }

      // TODO: Add Termination Encoding
      eachEncode(4, out);
      
      out.finalize();
      
    } catch(FileNotFoundException e) {
      System.out.println("Unable to write encoding information to an unavailable file.");
      System.exit(-1);
    } catch (NoSuchElementException e) {
      System.out.println("Unable to find an element within the HuffmanTree. Please make sure the tree is correct or that the file added is using the correct encoding.");
      System.exit(-1);
    }
  }
  
  private void eachEncode(int index, BitOutputStream out) {
    Node root = forest.peek();
    String binary = root.valAtAsBinary(index);
    if(binary == null) throw new NoSuchElementException();
    for(int j = 0 ; j < binary.length() ; j++) {
      int bin = Integer.parseInt(binary.charAt(j) + "");
      out.writeBit(bin);
    }
    if(index == 4) for(int i = 0 ; i < 8 ; i++) out.writeBit(0);
  }

}