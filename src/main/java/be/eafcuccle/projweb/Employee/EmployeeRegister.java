package be.eafcuccle.projweb.Employee;

import java.util.SortedSet;
import java.util.TreeSet;

public class EmployeeRegister {
    // une liste d'employé pour contenir les employés trié par leur firstName
    private SortedSet<Employee> listEmployees = new TreeSet<Employee>(
            (employee1, employee2) -> employee1.getId().compareTo(employee2.getId()));

    // le contructeur
    public EmployeeRegister() {
        Employee employee1 = new Employee("John", "Doe", "john@gmail.com", "0123456789");
        employee1.setId(1);
        Employee employee2 = new Employee("Jane", "Doe", "jane@gmail.com", "0234567891");
        employee2.setId(2);
        Employee employee3 = new Employee("Alice", "Smith", "alice@gmail.com", "0345678912");
        employee3.setId(3);
        Employee employee4 = new Employee("Bob", "Johnson", "bob@gmail.com", "0456789123");
        employee4.setId(4);
        Employee employee5 = new Employee("Carol", "Williams", "carol@gmail.com", "0567891234");
        employee5.setId(5);
        Employee employee6 = new Employee("David", "Brown", "david@gmail.com", "0678912345");
        employee6.setId(6);
        Employee employee7 = new Employee("Eva", "Wilson", "eva@gmail.com", "0789123456");
        employee7.setId(7);
        Employee employee8 = new Employee("Frank", "Miller", "frank@gmail.com", "0891234567");
        employee8.setId(8);
        Employee employee9 = new Employee("Grace", "Taylor", "grace@gmail.com", "0912345678");
        employee9.setId(9);
        Employee employee10 = new Employee("Henry", "Anderson", "henry@gmail.com", "0123456789");
        employee10.setId(10);
        Employee employee11 = new Employee("Rigo", "Song", "Rigo@gmail.com", "0423456789");
        employee11.setId(11);
        Employee employee12 = new Employee("Aya", "Bernard", "Bernard@gmail.com", "0423456779");
        employee12.setId(12);

        listEmployees.add(employee1);
        listEmployees.add(employee2);
        listEmployees.add(employee3);
        listEmployees.add(employee4);
        listEmployees.add(employee5);
        listEmployees.add(employee6);
        listEmployees.add(employee7);
        listEmployees.add(employee8);
        listEmployees.add(employee9);
        listEmployees.add(employee10);
        listEmployees.add(employee11);
        listEmployees.add(employee12);

    }

    // une fonction pour ajouter des employés à la liste des employés
    public void add(Employee employee) {
        listEmployees.add(employee);
    }

    // afficher la liste des employés triés
    public SortedSet<Employee> getAllEmployees() {
        return listEmployees;
    }

    public void setListEmployees(SortedSet<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }

    public Employee findById(Integer id) {

        for (Employee e : listEmployees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
