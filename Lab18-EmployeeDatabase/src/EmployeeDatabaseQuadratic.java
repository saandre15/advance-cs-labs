public class EmployeeDatabaseQuadratic extends EmployeeDatabase {
  
  public EmployeeDatabaseQuadratic(int entrySize) { super(entrySize); }

  @Override
  public boolean put(int key, Employee val) {
    try {
      int pos = hashcode(key);
      Entry entry = this.entry[pos];
      int counter = 1;
      while(entry != null) {
        if(pos > this.entry.length - 1) return false;
        entry = this.entry[pos+=Math.pow(counter, 2)];
        counter++;
      }
      this.entry[pos] = new Entry(key, val);
      return true;
    }
    catch(IndexOutOfBoundsException e) {
      return false;
    }
  }

  @Override
  public Employee get(int key) {
    try {
      int pos = hashcode(key);
      Entry entry = this.entry[pos];
      int counter = 1;
      while(entry != null) {
        if(pos > this.entry.length - 1) return null;
        if(entry.getId() == key) return entry.getEmployee();
        entry = this.entry[pos+=Math.pow(counter, 2)];
        counter++;
      }
      return null;
    } catch(IndexOutOfBoundsException e) {
      return null;
    }
  }

}
