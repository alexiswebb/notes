// Challenge: remove duplicates from linked list using a hashmap; assume that 'match' is based only on Employee id
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.HashMap;

public class Challenge9 {

    public static void main(String[] args) {

        LinkedList<Employee> employees = new LinkedList<>();
        employees.add(new Employee("Jane", "Jones", 123));
        employees.add(new Employee("John", "Doe", 5678));
        employees.add(new Employee("Mike", "Wilson", 45));
        employees.add(new Employee("Mary", "Smith", 5555));
        employees.add(new Employee("John", "Doe", 5678));
        employees.add(new Employee("Bill", "End", 3948));
        employees.add(new Employee("Jane", "Jones", 123));

        employees.forEach(e -> System.out.println(e));
        removeDuplicates(employees);
        System.out.println("After removing duplicates...");
        employees.forEach(e -> System.out.println(e));
    }
   
    public static void removeDuplicates(LinkedList<Employee> list) {
        HashMap<Integer, Employee> hashmap = new HashMap<>();
        ListIterator<Employee> iterator = list.listIterator();
        ArrayList<Employee> remove = new ArrayList<>();

        while (iterator.hasNext()) {
             Employee current = iterator.next();
             int hash = current.hashCode();
             if (!hashmap.containsKey(hash)) {
                 hashmap.put(hash, current);
             }
             else { remove.add(current); }
        }

        for (Employee employee : remove) {
             list.remove(employee);
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
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
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
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + id;
        return result;
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
