package be.eafcuccle.projweb.Visit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import be.eafcuccle.projweb.Employee.Employee;
import be.eafcuccle.projweb.Visitor.Visitor;

public class SimpleVisitDTO {

    private Integer id;
    // le visiteur
    private Visitor visitor;
    // l'employé / le serveur
    private Employee host;
    // la date
    private LocalDate date;
    // l'heure d'arrivé prévue
    private LocalTime plannedArrivalTime;
    // la durée prévue
    private Long plannedDuration;
    // l'heure d'arrivée
    private LocalTime actualArrivalTime;
    // l'heure de départ
    private LocalTime actualDepartureTime;
    // Numéro de la plaque d'immatriculation
    private String licensePlateNumber;

    // le contructeur qui transforme les attributs d'une visit d'un type en un autre
    // type (String/Integer)
    public SimpleVisitDTO(Visit visit) {
        this.id = visit.getId();
        this.visitor = visit.getVisitor();
        this.host = visit.getHost();
        this.date = LocalDate.parse(visit.getDate().toString());
        this.plannedArrivalTime = LocalTime.parse(visit.getPlannedArrivalTime().toString());
        this.plannedDuration = visit.getPlannedDuration().toHours();
        this.actualArrivalTime = visit.getActualArrivalTime();
        this.actualDepartureTime = visit.getActualDepartureTime();
        this.licensePlateNumber = visit.getLicensePlateNumber();

    }

    public SimpleVisitDTO() {
    }

    // méthode pour convertir un simpleVisitDTO en un objet Visit
    public Visit toVisit() {

        Visit visit = new Visit(this.visitor, this.host, this.date, this.plannedArrivalTime,
                Duration.ofHours(this.plannedDuration));
        visit.setId(id);
        visit.setActualArrivalTime(this.actualArrivalTime);
        visit.setActualDepartureTime(this.actualDepartureTime);
        visit.setLicensePlateNumber(this.licensePlateNumber);
        return visit;
    }

    // recuperation des visteurs
    public Visitor getVisitor() {
        return visitor;
    }

    // modification d'un visiteur
    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    // recupération d'un employé/serveur
    public Employee getHost() {
        return host;
    }

    // modification du serveur
    public void setHost(Employee host) {
        this.host = host;
    }

    // recupération de la date
    public LocalDate getDate() {
        return date;
    }

    // Modification de la date
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // recupération de la date d'arrivée
    public LocalTime getActualArrivalTime() {
        return actualArrivalTime;
    }

    // modification de la date d'arrivée
    public void setActualArrivalTime(LocalTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    // recupérationde la date de départ
    public LocalTime getActualDepartureTime() {
        return actualDepartureTime;
    }

    // modificationde la date de depart
    public void setActualDepartureTime(LocalTime actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    // recupération de son numéro de plaque
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    // mise à jour de son numéro de plaque
    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    // recupération de l'heure d'arrivée prévue
    public LocalTime getPlannedArrivalTime() {
        return plannedArrivalTime;
    }

    // modification de l'heure d'arrivée
    public void setPlannedArrivalTime(LocalTime plannedArrivalTime) {
        this.plannedArrivalTime = plannedArrivalTime;
    }

    // récupération de l'id
    public Integer getId() {
        return id;
    }

    // ajout de l'id
    public void setId(Integer id) {
        this.id = id;
    }

    // récupération de la durée prévue
    public Long getPlannedDuration() {
        return plannedDuration;
    }

    // mise à jour de la durée prévue
    public void setPlannedDuration(Long plannedDuration) {
        this.plannedDuration = plannedDuration;
    }

}
