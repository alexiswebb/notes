import java.util.LinkedList;
import java.util.ListIterator;

public class HashTables {
  public static void main(String[] args) {
    ChainedHashTable table = new ChainedHashTable();

    table.put("Jones", new Employee("Jane", "Jones", 123));
    table.put("Doe", new Employee("John", "Doe", 4567));
    // should result in collision
    table.put("Smith", new Employee("Mary", "Smith", 22));
    table.put("Wilson", new Employee("Mike", "Wilson", 3245));
    table.printHashTable();
    System.out.println("Retrieve key Wilson: " + table.get("Wilson"));

    table.remove("Jones");
    table.printHashTable();
  }
}

public class ChainedHashTable {
  private LinkedList<StoredEmployee>[] hashtable;

  public ChainedHashTable() {
    hashtable = new LinkedList[10];
    for (int i = 0; i < hashtable.length; i++) {
      hashtable[i] = new LinkedList<StoredEmployee>();
    }
  }

  public void put(String key, Employee employee) {
    int hashedKey = hashKey(key);
    hashtable[hashedKey].add(new StoredEmployee(key, employee));
  }

  public Employee get(String key) {
    int hashedKey = hashKey(key);
    ListIterator<StoredEmployee> iterator = hashtable[hashedKey].listIterator();
    StoredEmployee employee = null;
    while (iterator.hasNext()) {
      employee = iterator.next();
      if (employee.key.equals(key)) { return employee.employee; }
    }
    return null;
  }

  public Employee remove(String key) {
    int hashedKey = hashKey(key);
    ListIterator<StoredEmployee> iterator = hashtable[hashedKey].listIterator();
    StoredEmployee employee = null;
    int index = 0;
    while (iterator.hasNext()) {
      employee = iterator.next();
      index++;
      if (employee.key.equals(key)) { break; }
    }
    
    if (employee == null || !employee.key.equals(key)) {
      return null;
    }
    else {
      hashtable[hashedKey].remove(index);
      return employee.employee;
    }
  } 

  private int hashKey(String key) {
    return key.length() % hashtable.length;
  }

  public void printHashTable() {
    for (int i = 0; i < hashtable.length; i++) {
      if (hashtable[i] == null) { System.out.println("Empty"); }
      else { 
        System.out.println("position " + i + ": ");
        ListIterator<StoredEmployee> iterator = hashtable[i].listIterator();
        while (iterator.hasNext()) {
          System.out.print(iterator.next().employee);
          System.out.print(" -> ");
        }
        System.out.println("null");
      }
    }
  }
}

public class StoredEmployee{
  public String key;
  public Employee employee;

  public StoredEmployee(String key, Employee employee) {
    this.key = key;
    this.employee = employee;
  }
}

public class Employee {

  private String firstName;
  private String lastName;
  private int id;

  public Employee(String firstName, String lastName, int id) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
  }

  public String getFirstName() {
    return this.firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Employee employee = (Employee) o;

    if (id != employee.id) return false;
    if (!firstName.equals(employee.firstName)) return false;
    return lastName.equals(employee.lastName);
  }

  @Override
  public String toString() {
    return "Employee{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", id=" + id +
      '}';
  }
}
