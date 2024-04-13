package be.eafcuccle.projweb.Visit;

import org.springframework.web.bind.annotation.RestController;
import be.eafcuccle.projweb.Visitor.Visitor;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class VisitController {
    private Integer id = 8;
    // on défini visitRegistry afin d'enregistrer des visites
    private VisitRegistry visitRegistry = new VisitRegistry();

    // chemin pour accéder à toutes les visits
    @GetMapping("/visits")
    public ResponseEntity<List<SimpleVisitDTO>> getVisit() {
        return ResponseEntity.ok().body(VisitService.toSimpleVisitDTOList(visitRegistry.getListVisit()));
    }

    // chemin pour accéder aux visiteurs présents
    @GetMapping("/visitors/present")
    public ResponseEntity<SortedSet<Visitor>> getPresentVisitor() {
        // on retourne dans le body les visiteur présents via la methode
        // getPresentVisitors présent dans VisitRegister
        return ResponseEntity.ok().body(visitRegistry.getPresentVisitors());
    }

    // chemin pour accéder aux visits attendues
    @GetMapping("/visits/{date}")
    public ResponseEntity<List<SimpleVisitDTO>> getVisitsDay(@PathVariable LocalDate date) {
        List<SimpleVisitDTO> emtyVisit = new ArrayList<SimpleVisitDTO>();
        if (date != null) {
            return ResponseEntity.ok()
                    .body(VisitService.toSimpleVisitDTOList(visitRegistry.getVisitsByDate(date)));
        }
        return ResponseEntity.ok().body(emtyVisit);
    }

    // On récupère la liste des visiteurs prévu pour une date
    @GetMapping("/visitors/attendus")
    public ResponseEntity<SortedSet<Visitor>> getExpectedVisitors() {
        // on retourne dans le body la liste des visiteurs prévu pour la "date
        // d'aujourd'hui"
        return ResponseEntity.ok().body(visitRegistry.getExpectedVisitors(LocalDate.now()));
    }

    // chemin pour récupérer la liste des vistes d'un visiteur
    @GetMapping("/visits/visitors/{id}")
    ResponseEntity<List<SimpleVisitDTO>> getVisitsByVisitor(@PathVariable String id) {

        try {
            // création d'un objet visit
            Visitor visitor = new Visitor();
            // on assigne à cet objet l'id du visiteur que l'on souhaite récupérer ses
            // visites
            visitor.setId(Integer.valueOf(id));

            return ResponseEntity.ok()
                    .body(VisitService.toSimpleVisitDTOList(visitRegistry.getVisitsByVisitor(visitor)));
        }

        catch (NumberFormatException errorFormatException) {

            // En cas d'identifiant erroné on retourne une liste vide
            List<SimpleVisitDTO> emptyVisitList = new ArrayList<>();
            return ResponseEntity.badRequest().body(emptyVisitList);
        }
    }

    // on défini le chemin pour planifier une visit . l'heure actuelle d'arrivée et
    // de depart ne seront pas définis sur le champs du formulaire
    @PostMapping("/visits")
    public ResponseEntity<HttpStatus> postVisit(@RequestBody @Valid Visit visitJSON) {

        SimpleVisitDTO simpleVisitDTO = new SimpleVisitDTO(visitJSON);
        Visit visit = simpleVisitDTO.toVisit();
        // on lui génère un identifiant unique
        id++;
        visit.setId(id);
        // on lui ajoute dans la liste des visits
        Boolean checkAddVisit = visitRegistry.add(visit);

        if (checkAddVisit == false) {
            id -= 1;
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    // on ajoute une heure d'arrivée pour le visiteur
    @PutMapping("/visits/arrival/{id}")
    // on récupère l'id du visiteur
    public ResponseEntity<HttpStatus> putVisitArrival(@PathVariable String id, @RequestBody Visit v) {

        // on fait une boucle pour accéder à la visite correspondants à l'id
        for (Visit visit : visitRegistry.getListVisit()) {
            if (visit.getId() == Integer.valueOf(id)) {
                // on vérifies si l'heure actuel est dans mon bon interval des heures de visite
                // 7h00 à 20:45
                if (v.getActualArrivalTime().isBefore(LocalTime.parse("07:00:00"))
                        || v.getActualArrivalTime().isAfter(LocalTime.parse("20:45:00")))
                    return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);

                // on modifie la date d'arrivé du visiteur pour mettre la date récupéré dans v
                visit.setActualArrivalTime(LocalTime.parse(v.getActualArrivalTime().toString()));

                // si l'arrive n'est pas inférieur au départ on met le départ à null afin qu'il
                // soit entreé de nouveau dans l'ui

                break;
            }
        }
        if (v.getActualArrivalTime() == null)
            return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    // on ajoute une heure de départ pour le visiteur
    @PutMapping("/visits/departure/{id}")
    // on récupère l'id du visiteur qui part de la visit
    public ResponseEntity<HttpStatus> putVisitDeparture(@PathVariable String id, @RequestBody Visit v) {

        // on fait une boucle pour accéder à la visite correspondants à l'id
        for (Visit visit : visitRegistry.getListVisit()) {
            if (visit.getId() == Integer.parseInt(id)) {
                // on modifie l'heure actuel de départ du visiteur pour mettre l'heure récupéré
                // dans v
                visit.setActualDepartureTime(LocalTime.parse(v.getActualDepartureTime().toString()));

                if (!visit.checkArrivalDeparture()) {
                    visit.setActualDepartureTime(null);
                    return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);
                }

                break;
            }
        }
        if (v.getActualDepartureTime() == null) {

            return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    @PutMapping("/visits/licensePlateNumber/{id}")
    public ResponseEntity<HttpStatus> putLicensePlateNumber(@PathVariable String id, @RequestBody Visit v) {

        if (v.getLicensePlateNumber() == ""
                || v.getLicensePlateNumber().matches("^[1-7]-[a-zA-Z]{3}-[0-9]{2}[1-9]$") == false)
            return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);

        for (Visit visit : visitRegistry.getListVisit()) {
            if (visit.getId() == Integer.parseInt(id)) {
                visit.setLicensePlateNumber(v.getLicensePlateNumber());

            }
        }

        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/visits/{id}")
    public ResponseEntity<HttpStatus> deleteVisit(@PathVariable Integer id) {

        visitRegistry.getListVisit().removeIf((v) -> v.getId() == id);

        return ResponseEntity.ok().body((HttpStatus.ACCEPTED));
    }

}
