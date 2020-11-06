
public class HashTables {
  public static void main(String[] args) {
    SimpleHashTable table = new SimpleHashTable();

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

public class SimpleHashTable {
  private StoredEmployee[] hashtable;

  public SimpleHashTable() {
    hashtable = new StoredEmployee[10];
  }

  private int hashKey(String key) {
    return key.length() % hashtable.length;
  }

  public void put(String key, Employee employee) {
    int hashedKey = hashKey(key);
    // use linear probing to increment index
    if (occupied(hashedKey)) {
      int stopIndex = hashedKey;
      if (hashedKey == hashtable.length - 1) { hashedKey = 0; }
      else { hashedKey++; } 
      while (occupied(hashedKey) && hashedKey != stopIndex) {
	hashedKey = (hashedKey + 1) % hashtable.length;
      }
    }
    if (occupied(hashedKey)) { System.out.println("No room at position " + hashedKey); }
    else { hashtable[hashedKey] = new StoredEmployee(key, employee); }
  }

  public Employee get(String key) {
    int hashedKey = findKey(key);
    if (hashedKey == -1) { return null; }
    return hashtable[hashedKey].employee;
  }

  private int findKey(String key) {
    int hashedKey = hashKey(key);
    if (hashtable[hashedKey] != null &&
          hashtable[hashedKey].key.equals(key)) { 
      return hashedKey;
    }

    int stopIndex = hashedKey;
    if (hashedKey == hashtable.length - 1) { hashedKey = 0; }
    else { hashedKey++; } 
    while (hashedKey != stopIndex &&
          hashtable[hashedKey] != null &&
          !hashtable[hashedKey].key.equals(key)) {
      hashedKey = (hashedKey + 1) % hashtable.length;
    }
    if (hashtable[hashedKey] != null &&
            hashtable[hashedKey].key.equals(key)) { return hashedKey; }
    else { return -1; }
  }

  public Employee remove(String key) {
    int hashedKey = hashKey(key);
    if (hashedKey == -1) { return null; }
    
    Employee employee = hashtable[hashedKey].employee;
    StoredEmployee[] oldHashtable = hashtable;
    hashtable = new StoredEmployee[hashtable.length];
    for (int i = 0; i < hashtable.length; i++) {
      if (oldHashtable[i] != null) { 
        put(oldHashtable[i].key, oldHashtable[i].employee);
      }
    }
    return employee;
  }

  private boolean occupied(int key) {
    return hashtable[key] != null;
  }

  public void printHashTable() {
    for (int i = 0; i < hashtable.length; i++) {
      if (hashtable[i] == null) { System.out.println("Empty"); }
      else { System.out.println(hashtable[i].employee); }
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
