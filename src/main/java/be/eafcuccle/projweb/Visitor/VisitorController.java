package be.eafcuccle.projweb.Visitor;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class VisitorController {
    // on génère les identifiants des visiteurs
    private Integer id = 12;
    private VisitorRegistry visitorRegistry = new VisitorRegistry();

    // le chemin d'accès à la liste des visiteurs
    @GetMapping("/visitors")
    public ResponseEntity<SortedSet<Visitor>> getVisitors() {
        return ResponseEntity.ok().body(visitorRegistry.getAllVisitors());
    }

    // le chemin pour récuperer une visite via son id
    @GetMapping("/visitors/{id}")
    public ResponseEntity<Visitor> getVisitor(@PathVariable String id) {
        Integer idInt = Integer.valueOf(id);
        return ResponseEntity.ok().body(visitorRegistry.findById(idInt));
    }

    // on ajoute un visiteur à notre liste de visiteur
    @PostMapping("/visitors")
    public ResponseEntity<HttpStatus> postVisitor(@RequestBody @Valid Visitor visitor) {
        id++;
        visitor.setId(id);
        visitorRegistry.add(visitor);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    // Mise à jour des données d'un visiteurs
    @PutMapping("/visitors/{id}")
    public ResponseEntity<HttpStatus> putVisitor(@PathVariable Integer id, @RequestBody @Valid Visitor visitor) {
        SortedSet<Visitor> listVisitors = visitorRegistry.getAllVisitors().stream()
                .filter((v) -> v.getId() != id)
                .collect(Collectors.toCollection(() -> new TreeSet<>((v1, v2) -> v1.getId().compareTo(v2.getId()))));
        listVisitors.add(visitor);
        visitorRegistry.setListVisitors(listVisitors);

        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    // chemin de suppréssion d'un visiteur
    @DeleteMapping("/visitors/{id}")
    public ResponseEntity<HttpStatus> deleteVisitor(@PathVariable Integer id) {
        SortedSet<Visitor> listVisitors = visitorRegistry.getAllVisitors().stream()
                .filter((v) -> v.getId() != id)
                .collect(Collectors.toCollection(() -> new TreeSet<>((v1, v2) -> v1.getId().compareTo(v2.getId()))));

        visitorRegistry.setListVisitors(listVisitors);
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }
}
