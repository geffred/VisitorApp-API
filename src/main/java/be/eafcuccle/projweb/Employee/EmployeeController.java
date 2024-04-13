package be.eafcuccle.projweb.Employee;

import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EmployeeController {
    // on génère les identifiants des employés
    private Integer id = 12;
    private EmployeeRegister employeeRegister = new EmployeeRegister();

    // chemin pour enregistrer un employé
    @PostMapping("/employees")
    public ResponseEntity<HttpStatus> addEmployee(@RequestBody @Valid Employee employee) {
        for (Employee e : employeeRegister.getAllEmployees()) {
            if (employee.getEmailAddress().equals(e.getEmailAddress())) {
                return ResponseEntity.badRequest().body(HttpStatus.NOT_ACCEPTABLE);
            }
        }
        id++;
        employee.setId(id);
        employeeRegister.add(employee);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    // chemin d'accés à liste des employés
    @GetMapping("/employees")
    public ResponseEntity<SortedSet<Employee>> listEmployees() {

        return ResponseEntity.ok().body(employeeRegister.getAllEmployees());
    }

    // recupérer un employée via son id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> listEmployees(@PathVariable String id) {
        Integer employeeId = Integer.valueOf(id);
        return ResponseEntity.ok().body(employeeRegister.findById(employeeId));
    }

    // Supprimer un employé via son id
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployees(@PathVariable String id) {
        SortedSet<Employee> list = new TreeSet<Employee>(
                (employee1, employee2) -> employee1.getFirstName().compareTo(employee2.getFirstName()));
        for (Employee e : employeeRegister.getAllEmployees()) {

            if (e.getId() != Integer.valueOf(id)) {

                list.add(e);
            }

        }
        employeeRegister.setListEmployees(list);
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> putEmployee(@PathVariable Integer id, @RequestBody @Valid Employee employeeJSON) {
        employeeRegister.getAllEmployees().removeIf((employee) -> employee.getId() == id);
        employeeJSON.setId(id);
        employeeRegister.add(employeeJSON);
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

}
