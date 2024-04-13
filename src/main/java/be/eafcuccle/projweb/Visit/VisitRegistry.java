package be.eafcuccle.projweb.Visit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.SortedSet;
import java.util.TreeSet;

import be.eafcuccle.projweb.Employee.Employee;
import be.eafcuccle.projweb.Visitor.Visitor;

public class VisitRegistry {
    // une liste des visites
    private SortedSet<Visit> listVisit = new TreeSet<>((v1, v2) -> v1.getId().compareTo(v2.getId()));

    // le constructeur
    public VisitRegistry() {

        // création des visiteurs
        Visitor visitor1 = new Visitor("Martin", "Dupont", "Google");
        visitor1.setId(1);
        Visitor visitor2 = new Visitor("Julie", "Lefebvre", "Amazon");
        visitor2.setId(2);
        Visitor visitor3 = new Visitor("Thomas", "Robert", "Microsoft");
        visitor3.setId(3);
        Visitor visitor4 = new Visitor("Amandine", "Martin", "Facebook");
        visitor4.setId(4);
        // création des employés
        Employee employee1 = new Employee("John", "Doe", "john@gmail.com", "0123456789");
        employee1.setId(1);
        Employee employee2 = new Employee("Jane", "Doe", "jane@gmail.com", "0234567891");
        employee2.setId(2);
        Employee employee3 = new Employee("Alice", "Smith", "alice@gmail.com", "0345678912");
        employee3.setId(3);
        Employee employee4 = new Employee("Bob", "Johnson", "bob@gmail.com", "0456789123");
        employee4.setId(4);

        // création des visites
        Visit visit1 = new Visit(visitor1, employee1, LocalDate.parse("2024-04-01"), LocalTime.of(7, 30, 0),
                Duration.ofHours(2));
        visit1.setId(1);

        Visit visit2 = new Visit(visitor2, employee2, LocalDate.parse("2024-04-01"), LocalTime.of(10, 30, 0),
                Duration.ofHours(1));
        visit2.setId(2);

        Visit visit3 = new Visit(visitor3, employee3, LocalDate.parse("2024-04-01"), LocalTime.of(12, 0, 0),
                Duration.ofHours(3));
        visit3.setId(3);

        Visit visit4 = new Visit(visitor3, employee2, LocalDate.parse("2024-04-02"), LocalTime.of(9, 15, 0),
                Duration.ofHours(3));
        visit4.setId(4);

        Visit visit5 = new Visit(visitor1, employee1, LocalDate.parse("2024-04-02"), LocalTime.of(8, 30, 0),
                Duration.ofHours(4));
        visit5.setId(5);

        Visit visit6 = new Visit(visitor1, employee4, LocalDate.parse("2024-04-02"), LocalTime.of(15, 00, 0),
                Duration.ofHours(2));
        visit6.setId(6);

        Visit visit7 = new Visit(visitor4, employee3, LocalDate.parse("2024-04-01"), LocalTime.of(16, 45, 0),
                Duration.ofHours(1));
        visit7.setId(7);

        Visit visit8 = new Visit(visitor4, employee4, LocalDate.parse("2024-04-01"), LocalTime.of(17, 00, 0),
                Duration.ofHours(1));
        visit8.setId(8);

        listVisit.add(visit1);
        listVisit.add(visit2);
        listVisit.add(visit3);
        listVisit.add(visit4);
        listVisit.add(visit5);
        listVisit.add(visit6);
        listVisit.add(visit7);
        listVisit.add(visit8);

    }

    // on ajoute des visites à notre liste de visite
    public Boolean add(Visit visit) {
        for (Visit v : listVisit) {
            if (visit.getPlannedArrivalTime().isBefore(v.getPlannedArrivalTime().plus(v.getPlannedDuration()))
                    && v.getDate().toString().equals(visit.getDate().toString())
                    && v.getVisitor().getId() == visit.getVisitor().getId()) {
                return false;
            }
        }
        listVisit.add(visit);
        return true;
    }

    // on recupère et rajoute les visites dans la nouvelle liste ordonnée "visits"
    // par rapport à la date "date
    public SortedSet<Visit> getVisitsByDate(LocalDate date) {
        SortedSet<Visit> visits = new TreeSet<>((visit1, visit2) -> visit1.getId().compareTo(visit2.getId()));
        for (Visit visit : listVisit) {
            if (visit.getDate().toString().equals(date.toString())) {
                visits.add(visit);
            }
        }
        return visits;
    }

    // on recupère et rajoute les visites dans la nouvelle liste ordonnée "visits"
    // par rapport au visiteur "visitor"
    public SortedSet<Visit> getVisitsByVisitor(Visitor visitor) {
        SortedSet<Visit> visits = new TreeSet<>((visit1, visit2) -> visit1.getId().compareTo(visit2.getId()));
        for (Visit visit : listVisit) {
            if (visit.getVisitor().getId() == visitor.getId()) {
                visits.add(visit);
            }
        }
        return visits;
    }

    // on recupère et rajoute les visites dans la nouvelle liste ordonnée "visits"
    // par rapport à la "date" de la visite prévue
    public SortedSet<Visitor> getExpectedVisitors(LocalDate date) {
        SortedSet<Visitor> visitors = new TreeSet<>(
                (visitor1, visitor2) -> visitor1.getId().compareTo(visitor2.getId()));
        for (Visit visit : listVisit) {
            if (visit.isExpected(date)) {
                visitors.add(visit.getVisitor());
            }
        }
        return visitors;
    }

    // on recupère et rajoute les visiteurs presents dans la nouvelle liste ordonnée
    // "visits"
    public SortedSet<Visitor> getPresentVisitors() {
        // on crée une liste ordonnée par de visiteur. cette liste est ordonnée via le
        // firsName des visiteurs
        SortedSet<Visitor> visitors = new TreeSet<>(
                (visitor1, visitor2) -> visitor1.getId().compareTo(visitor2.getId()));
        for (Visit visit : listVisit) {
            if (visit.isPresent()) {
                visitors.add(visit.getVisitor());
            }
        }
        return visitors;
    }

    // on recupère la liste de toutes les visites
    public SortedSet<Visit> getListVisit() {
        return listVisit;
    }

}
