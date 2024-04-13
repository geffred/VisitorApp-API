package be.eafcuccle.projweb.Visitor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Visitor {

    private Integer id;

    @NotEmpty(message = "Le champs prénom ne doit pas etre vide")
    @NotNull(message = "le champs prénom ne doit pas etre null")
    @Pattern(regexp = "^[a-zA-Zàâäéèêëîïôöùûüÿç ]{2,20}$", message = "votre prénom est trop cour ou utilise des symboles spéciaux")
    // prenom du visiteur
    private String firstName;
    // nom du visiteur
    @NotEmpty(message = "Le champs nom ne doit pas etre vide")
    @NotNull(message = "le champs nom ne doit pas etre null")
    @Pattern(regexp = "^[a-zA-Zàâäéèêëîïôöùûüÿç ]{2,20}$", message = "votre nom est trop cour ou utilise des symboles spéciaux")
    private String lastName;
    // nom de l'entreprise
    @NotEmpty(message = "Le champs companyName ne doit pas etre vide")
    @NotNull(message = "le champs companyName ne doit pas etre null")
    @Pattern(regexp = "^[a-zA-Z0-9àâäéèêëîïôöùûüÿç ]{2,20}$", message = "votre companyName est trop cour ou utilise des symboles spéciaux")
    private String companyName;

    // le constructeur qui prend le prénom, le nom et le nom de l'entreprise
    public Visitor(String firstName, String lastName, String companyName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
    }

    public Visitor() {
    }

    // on récupère le prénom
    public String getFirstName() {
        return firstName;
    }

    // on modifie le prénom
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // on récupère le nom
    public String getLastName() {
        return lastName;
    }

    // on modifie le nom
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // on récupère le nom de l'entreprise
    public String getCompanyName() {
        return companyName;
    }

    // on modifie le nom de l'entreprise
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
