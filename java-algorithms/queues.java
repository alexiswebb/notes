import java.util.NoSuchElementException;

public class Queue {
  public static void main(String[] args) {

    ArrayQueue queue = new ArrayQueue(5);
    queue.add(new Employee("Jane", "Jones", 123));
    queue.add(new Employee("John", "Doe", 4567));
    queue.circularRemove();
    queue.circularAdd(new Employee("James", "Bond", 007));
    queue.circularRemove();
    queue.circularAdd(new Employee("Mary", "Smith", 22));
    queue.circularRemove();
    queue.circularAdd(new Employee("Mike", "Wilson", 3245));
    queue.circularRemove();
    queue.circularAdd(new Employee("Will", "End", 76));

    queue.printQueue();
  }
}

public class ArrayQueue {
  private Employee[] queue;
  private int front;
  private int back;

  public ArrayQueue(int capacity) {
    queue = new Employee[capacity];
  }

  public void circularAdd(Employee employee) {
    if (size() == queue.length - 1) {
      Employee[] newArray = new Employee[2* queue.length];
      int queueSize = size();
      // unwrap the queue
      System.arraycopy(queue, front, newArray, 0, queue.length - front);
      System.arraycopy(queue, 0, newArray, queue.length - front, back);
      queue = newArray;

      front = 0;
      back = queueSize;
    }
    queue[back] = employee;
    if (back < queue.length - 1) { back++; }
    // if there is room in the queue, but it's 'before' front point, set back to 0
    else { back = 0; }
  }

  public void add(Employee employee) {
    if (back == queue.length) {
      Employee[] newArray = new Employee[2* queue.length];
      System.arraycopy(queue, 0, newArray, 0, queue.length);
      queue = newArray;
    }
    queue[back] = employee;
    back++;
  }

  public Employee circularRemove() {
    if (size() == 0) {
      throw new NoSuchElementException();
    }

    Employee employee = queue[front];
    queue[front] = null;
    front++;
    if (size() == 0) {
      front = 0;
      back = 0;
    }
    else if (front == queue.length) {
      front = 0;
    }

    return employee;
  }

  public Employee remove() throws Exception {
    if (size() == 0) {
      throw new Exception("Empty queue");
    }

    Employee employee = queue[front];
    queue[front] = null;
    front++;
    if (size() == 0) {
      front = 0;
      back = 0;
    }
    return employee;
  }
  
  public Employee peek() throws Exception {
    if (size() == 0) {
      throw new Exception("Empty queue");
    }
    return queue[front];
   }

  public void printQueue() {
    if (front <= back) {
      for (int i = front; i < back; i++){
	System.out.println(queue[i]);
      }
    }
    else {
      for (int i = front; i < queue.length; i++) {
        System.out.println(queue[i]);
      }
      for(int i = 0; i < back; i++) {
        System.out.println(queue[i]);
      }
    }
  }

  public int size() {
    if (front <= back) { return back - front; }
    return back - front + queue.length;
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
