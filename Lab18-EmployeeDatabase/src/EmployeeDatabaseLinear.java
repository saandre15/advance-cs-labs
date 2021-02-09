public class EmployeeDatabaseLinear extends EmployeeDatabase {

  public EmployeeDatabaseLinear(int entrySize) { super(entrySize); }

  @Override
  public boolean put(int key, Employee val) {
    try {
      int pos = hashcode(key);
      Entry entry = this.entry[pos];
      while(entry != null) {
        if(pos > this.entry.length - 1) return false;
        entry = this.entry[++pos];
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
      while(entry != null) {
        if(pos > this.entry.length - 1) return null;
        if(entry.getId() == key) return entry.getEmployee();
        entry = this.entry[++pos];
      }
      return null;
    } catch(IndexOutOfBoundsException e) {
      return null;
    }
  }
}
