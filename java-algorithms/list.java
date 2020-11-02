import java.util.List;
import java.util.ArrayList;

public class Lists {

  public static void main(String[] args) {
    // ArrayList
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(new Employee("Jane", "Jones", 123));
    employeeList.add(new Employee("John", "Doe", 4567));
    employeeList.add(new Employee("Mary", "Smith", 22));
    employeeList.add(new Employee("Mike", "Wilson", 3245));
    //employeeList.forEach(employee -> System.out.println(employee));


    // Vector
    //List<Employee> employeeVector = new Vector<>();
    //employeeVector.add(new Employee("Jane", "Jones", 123));
    //employeeVector.add(new Employee("John", "Doe", 4567));
    //employeeVector.add(new Employee("Mary", "Smith", 22));
    //employeeVector.add(new Employee("Mike", "Wilson", 3245));

    // Singly Linked Lists
    Employee janeJones = new Employee("Jane", "Jones", 123);
    Employee johnDoe = new Employee("John", "Doe", 4567);
    Employee marySmith =  new Employee("Mary", "Smith", 22);
    Employee mikeWilson =  new Employee("Mike", "Wilson", 3245);

    EmployeeLinkedList list = new EmployeeLinkedList();
    list.addToFront(janeJones);
    list.addToFront(johnDoe);
    list.addToFront(marySmith);
    list.addToFront(mikeWilson);
    list.printList(); 
    System.out.println(list.getSize());
    
    list.removeFromFront();
    list.printList();
    System.out.println(list.getSize());
  }
}

public class EmployeeNode {
  private EmployeeNode next;
  private Employee employee;   
   
  public EmployeeNode(Employee employee) {
    this.employee = employee;
  }

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public EmployeeNode getNext() {
   return this.next;
  }

  public void setNext(EmployeeNode next) {
    this.next = next;
  }
  
  public String toString() {
    return employee.toString();
  }
}

public class EmployeeLinkedList {
  private EmployeeNode head;
  private int size;

  public void addToFront(Employee employee) {
    EmployeeNode node = new EmployeeNode(employee);
    node.setNext(head);
    head = node;
    size++;
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return (this.head == null);
  }

  public EmployeeNode removeFromFront() {
    if (isEmpty()) { return null; }

    EmployeeNode removedNode = head;
    head = head.getNext();
    size--;
    removedNode.setNext(null);
    return removedNode; 
  }

  public void printList() {
    EmployeeNode current = head;
    System.out.print("HEAD -> ");
    while (current != null) {
      System.out.print(current);
      System.out.print("->");
      current = current.getNext();
    }
    System.out.print("null");
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
