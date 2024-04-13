package be.eafcuccle.projweb.Visit;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import be.eafcuccle.projweb.Employee.Employee;
import be.eafcuccle.projweb.Visitor.Visitor;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public class Visit {

    private Integer id;
    // le visiteur
    @NotNull
    private Visitor visitor;
    // l'employé / le serveur
    @NotNull
    private Employee host;
    // la date
    @NotNull
    // @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    // l'heure d'arrivé prévue
    private LocalTime plannedArrivalTime;
    // la durée prévue ( nous avons choisi de travailler avec les secondes )
    @NotNull
    private Duration plannedDuration;
    // l'heure d'arrivée
    private LocalTime actualArrivalTime;
    // l'heure de départ
    private LocalTime actualDepartureTime;
    // Numéro de la plaque d'immatriculation
    private String licensePlateNumber;

    // le constructeur qui prend le visiteur , le serveur , la date du jour ,
    // l'heure d'arrivée et la durée prévue
    public Visit(Visitor visitor, Employee host, LocalDate date, LocalTime plannedArrivalTime,
            Duration plannedDuration) {
        this.visitor = visitor;
        this.host = host;
        this.date = date;
        this.plannedArrivalTime = plannedArrivalTime;
        this.plannedDuration = plannedDuration;
    }

    public Visit() {
    }

    // on enregistre l'heure d'arrivée actuelle
    public void registerArrival(LocalTime actualTime) {
        this.actualArrivalTime = actualTime;
    }

    // on enregistre l'heure de depart actuelle
    public void registerDeparture(LocalTime actualTime) {
        this.actualDepartureTime = actualTime;
    }

    // on recupère la date d'arrive
    public LocalDate getDate() {
        return date;
    }

    // on récupère l'heure d'arrivé prévue
    public LocalTime getPlannedArrivalTime() {
        return plannedArrivalTime;
    }

    // on récupère la durée prévu
    public Duration getPlannedDuration() {
        return plannedDuration;
    }

    public void setPlannedDuration(Duration plannedDuration) {
        this.plannedDuration = plannedDuration;
    }

    // on récupère l'heure d'arriver réélle
    public LocalTime getActualArrivalTime() {
        return actualArrivalTime;
    }

    // on modifie la date d'arrivée
    public void setActualArrivalTime(LocalTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    // on récupère l'heure de départ réelle
    public LocalTime getActualDepartureTime() {
        return actualDepartureTime;
    }

    // on modifie la date la date de départ
    public void setActualDepartureTime(LocalTime actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    // on récupère la plaque d'immatriculation du visiteur
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    // on modifie la plaque d'immatriculation du visiteur
    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    // on verifie si un visiteur est présent à la visite
    public Boolean isPresent() {
        // on calcule l'heure de départ prévu
        LocalTime plannedDepartureTime = plannedArrivalTime.plus(plannedDuration);
        // un visiteur est présent si l'heure à laquelle il arrive est inférieur à
        // l'heure à laquelle il avait prévu de rentrer
        if ((actualArrivalTime != null && actualArrivalTime != null)
                && actualArrivalTime.isBefore(plannedDepartureTime)) {
            return true;
        }
        return false;
    }

    // on verifie si il y'a une visite pour une date donnée en argument
    public Boolean isExpected(LocalDate date) {
        if (this.date.toString().equals(date.toString())) {
            return true;
        }
        return false;
    }

    public Boolean checkArrivalDeparture() {
        if (this.actualArrivalTime != null && this.actualDepartureTime != null
                && this.actualArrivalTime.isBefore(actualDepartureTime)) {
            return true;
        }
        return false;
    }

    // on recupère le visiteur
    public Visitor getVisitor() {
        return visitor;
    }

    // recupération de l'id
    public Integer getId() {
        return id;
    }

    // modifiaction de l'id
    public void setId(Integer id) {
        this.id = id;
    }

    // recupération du serveur
    public Employee getHost() {
        return host;
    }

    // modification du serveur
    public void setHost(Employee host) {
        this.host = host;
    }

}
