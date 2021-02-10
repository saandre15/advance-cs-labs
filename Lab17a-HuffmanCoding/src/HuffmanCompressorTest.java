import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HuffmanCompressorTest
{
  // NO EXTENSIONS
  private static String filename;

  @BeforeClass
  public static void setUp() throws FileNotFoundException {
    filename = "Hamlet";

    PrintStream output = new PrintStream(new File("test.output"));
    PrintStream console = System.out;
    
    System.setOut(output);
    
    File file = new File(filename + ".txt");
    if(!file.exists()) throw new FileNotFoundException();
    
    new File(filename + ".code").delete();
    new File(filename + ".short").delete();
    new File(filename + ".new").delete();
  }

  @Test 
  public void test01_compress() { 
    HuffmanCompressor.compress(filename);
  }

  @Test
  public void test02_expand() {
    HuffmanCompressor.expand(filename, filename);
  }


}
