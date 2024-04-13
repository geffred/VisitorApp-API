package be.eafcuccle.projweb.Employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Employee {
    // l'identifiant unique de l'employé
    private Integer id;
    // le prénom de l'employé
    @NotEmpty(message = "Le champs prénom ne doit pas etre vide")
    @NotNull(message = "le champs prénom ne doit pas etre null")
    @Pattern(regexp = "^[a-zA-Zàâäéèêëîïôöùûüÿç ]{2,20}$", message = "votre prénom est trop cour ou utilise des symboles spéciaux")
    private String firstName;
    // le nom de l'employé
    @NotEmpty(message = "Le champs nom ne doit pas etre vide")
    @NotNull(message = "le champs nom ne doit pas etre null")
    @Pattern(regexp = "^[a-zA-Zàâäéèêëîïôöùûüÿç ]{2,20}$", message = "votre nom est trop cour ou utilise des symboles spéciaux")
    private String lastName;
    // l'adresse email de l'employé
    @NotEmpty(message = "Ce champs ne doit pas etre vide ")
    @NotNull(message = "l'email ne soit pas etre null ")
    @Email(message = "Votre adresse email n'est pas valide")
    private String emailAddress;
    // le numéro de téléphone de l'employé
    @Pattern(regexp = "^0[0-9]{9}$", message = "votre numéro de téléphone n'est pas sous le bon format le format , il doit contenir 10 chiffres commençant par 0 ")
    private String phoneNumber;
    // le constructeur qui prend le prénom, le nom, l'adresse email et le numéro de
    // téléphone

    public Employee(String firstName, String lastName, String emailAddress, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    // on récupère le prénom
    public String getFirstName() {
        return firstName;
    }

    // modification du firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // on récupère le nom
    public String getLastName() {
        return lastName;
    }

    // modification du lastName
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // on récupère l'adresse email
    public String getEmailAddress() {
        return emailAddress;
    }

    // modification de l'adress email
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // on récupère le numéro de téléphone
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // modication du numéro de téléphone
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // on recupère l'id de l'employé
    public Integer getId() {
        return id;
    }

    // ajout d'un identifiant
    public void setId(Integer id) {
        this.id = id;
    }

}
