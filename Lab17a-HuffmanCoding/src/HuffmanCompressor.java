import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HuffmanCompressor {

  public static void compress(String filename) {
    try (Scanner scanner = new Scanner(new File(filename + ".txt"))) {
      // 0-255 ASCII Character
      // 10 New Line Character
      // 257 Termination Character
      int[] frequencies = new int[256 + 1 + 1];
      while(scanner.hasNextLine()) {
        String line = scanner.nextLine();
        for(int i = 0 ; i < line.length() ; i++) {
          int index = line.charAt(i);
          frequencies[index]+=1;
        }
        frequencies[10]+=1;
      }
      frequencies[4]+=1;
      HuffmanTree tree = new HuffmanTree(frequencies);
      // tree.print();
      tree.write(filename + ".code");
      tree.encode(filename + ".txt", new BitOutputStream(filename + ".short"));
    } catch(FileNotFoundException e) {
      System.out.println("File that requires compression cannot be found.");
      System.exit(-1);
    }
  }

  public static void expand(String codeFile, String fileName) {
    HuffmanTree tree = new HuffmanTree(codeFile + ".code");
    // tree.print();
    tree.decode(new BitInputStream(fileName + ".short"), fileName + ".new");
  }

}