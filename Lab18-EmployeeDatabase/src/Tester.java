import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tester {

  private static String samplePath = "samples/employee.db.csv";
  private static EmployeeDatabase.Entry[] employees;
  private static EmployeeDatabase[] databases;

  @BeforeClass
  public static void setUp() {
    try(Scanner s = new Scanner(new File(Tester.samplePath))) {
      Tester.employees = new EmployeeDatabase.Entry[(int)Files.lines(Paths.get(Tester.samplePath)).count()];
      int size = Tester.employees.length * 10;
      Tester.databases = new EmployeeDatabase[]{ new EmployeeDatabaseLinear(size), new EmployeeDatabaseQuadratic(size) };
    } catch(IOException e) {
      System.err.println("Employee Database File Not Found");
      System.exit(-1);
    }
  }


  @Test
  public void test01() {
    
    for(int i = 0 ; i < Tester.databases.length ; i++) {
      EmployeeDatabase db = Tester.databases[i];
      for(int j = 0 ; j < Tester.employees.length ; j++) {
        EmployeeDatabase.Entry employee = Tester.employees[j];
        db.put(employee.getId(), employee.getEmployee());
      }
    }

  }

}
