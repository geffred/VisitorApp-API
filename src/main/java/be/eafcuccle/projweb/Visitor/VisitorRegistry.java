package be.eafcuccle.projweb.Visitor;

import java.util.SortedSet;
import java.util.TreeSet;

public class VisitorRegistry {
    // une liste des visiteurs triée des visiteurs
    private SortedSet<Visitor> listVisitors = new TreeSet<>(
            (visitor1, visitor2) -> visitor1.getId().compareTo(visitor2.getId()));

    // le constructeur
    public VisitorRegistry() {

        Visitor visitor1 = new Visitor("Martin", "Dupont", "Google");
        visitor1.setId(1);
        Visitor visitor2 = new Visitor("Julie", "Lefebvre", "Amazon");
        visitor2.setId(2);
        Visitor visitor3 = new Visitor("Thomas", "Robert", "Microsoft");
        visitor3.setId(3);
        Visitor visitor4 = new Visitor("Amandine", "Martin", "Facebook");
        visitor4.setId(4);
        Visitor visitor5 = new Visitor("Pierre", "Durand", "Apple");
        visitor5.setId(5);
        Visitor visitor6 = new Visitor("Sarah", "Bernard", "Tesla");
        visitor6.setId(6);
        Visitor visitor7 = new Visitor("Lucas", "Roux", "IBM");
        visitor7.setId(7);
        Visitor visitor8 = new Visitor("Clara", "Dubois", "Samsung");
        visitor8.setId(8);
        Visitor visitor9 = new Visitor("Clark", "Dupont", "Nokia");
        visitor9.setId(9);
        Visitor visitor10 = new Visitor("Maxime", "Moreau", "Huawei");
        visitor10.setId(10);
        Visitor visitor11 = new Visitor("Léa", "Petit", "Sony");
        visitor11.setId(11);
        Visitor visitor12 = new Visitor("Gabriel", "Blanc", "Airbus");
        visitor12.setId(12);

        listVisitors.add(visitor1);
        listVisitors.add(visitor2);
        listVisitors.add(visitor3);
        listVisitors.add(visitor4);
        listVisitors.add(visitor5);
        listVisitors.add(visitor6);
        listVisitors.add(visitor7);
        listVisitors.add(visitor8);
        listVisitors.add(visitor9);
        listVisitors.add(visitor10);
        listVisitors.add(visitor11);
        listVisitors.add(visitor12);

    }

    // on ajouter des visiteurs à notre liste de visiteur
    public void add(Visitor visitor) {
        listVisitors.add(visitor);
    }

    // on récupère tous les visiteurs dans une liste ordonnée
    public SortedSet<Visitor> getAllVisitors() {
        return listVisitors;
    }

    public void setListVisitors(SortedSet<Visitor> listVisitors) {
        this.listVisitors = listVisitors;
    }

    public Visitor findById(Integer id) {
        for (Visitor visitor : listVisitors) {
            if (visitor.getId() == id) {
                return visitor;
            }
        }
        return null;
    }

}
