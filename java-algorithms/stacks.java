import java.util.LinkedList;
import java.util.ListIterator;

public class Stacks {

  public static void main(String[] args) {
    // Stack using array
    ArrayStack stack = new ArrayStack(10);
    stack.push(new Employee("Jane", "Jones", 123));
    stack.push(new Employee("John", "Doe", 4567));
    stack.push(new Employee("Mary", "Smith", 22));
    stack.push(new Employee("Mike", "Wilson", 3245));
    stack.push(new Employee("Will", "End", 76));

    stack.printStack();
 
    // Stack using linked list
    ListStack stackList = new ListStack();
    stackList.push(new Employee("Jane", "Jones", 123));
    stackList.push(new Employee("John", "Doe", 4567));
    stackList.push(new Employee("Mary", "Smith", 22));
    stackList.push(new Employee("Mike", "Wilson", 3245));
    stackList.push(new Employee("Will", "End", 76));

    stack.printStack();
  }
}

public class ListStack {
  private LinkedList<Employee> stack;

  public ListStack() {
    stack = new LinkedList<Employee>();
  }
 
  // LinkedList has a push, pop and peek; just call
  public void push(Employee employee) {
    stack.push(employee);
  }

  public Employee pop() {
    return stack.pop();
  }

  public Employee peek() {
    return stack.peek();
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }

  public void printList() {
    ListIterator<Employee> iterator = stack.listIterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}

public class ArrayStack {
  private Employee[] stack;
  private int top;

  public ArrayStack(int capacity){
    stack = new Employee[capacity];
  }

  public void push(Employee employee){
    if (top == stack.length) {
       // resize array
       Employee[] newArray = new Employee[2*stack.length];
       System.arraycopy(stack, 0, newArray, 0, stack.length);
       stack = newArray;
    }
    stack[top++] = employee;
  }

  public Employee pop() throws Exception {
    if (isEmpty()) {
      throw new Exception("Stack is Empty");
    }

    Employee employee = stack[--top];
    stack[top] = null;
    return employee;
  }

  public Employee peek() throws Exception {
    if (isEmpty()) {
      throw new Exception("Stack is Empty");
    }
    return stack[top-1];
  }
  
  public int size(){
    return top;
  }

  public boolean isEmpty() {
    return top == 0;
  }
  
  public void printStack() {
    for(int i = top; i >= 0; i--){
      System.out.println(stack[i]);
    }
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
