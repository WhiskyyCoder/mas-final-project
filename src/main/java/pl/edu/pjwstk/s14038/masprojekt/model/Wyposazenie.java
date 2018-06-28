package pl.edu.pjwstk.s14038.masprojekt.model;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Wyposazenie {
    private String name;
    private Date dataZakupu;
    private List<PokojWyposazenie> pokojWyposazenie;
    private Set<Dozorca> opiekujeSie;
    private Set<Dozorca> uprawnienieDoZmiany;

    public List<PokojWyposazenie> getPokojWyposazenie() {
        return pokojWyposazenie;
    }

    public void dodajOpieke(Dozorca dozorca){
        opiekujeSie.add(dozorca);
        dozorca.getOpiekujeSie().add(this);
    }

    public void dajUprawnienie(Dozorca dozorca){
        if(opiekujeSie.contains(dozorca)) {
                uprawnienieDoZmiany.add(dozorca);
                dozorca.getUprawnienieDoZmiany().add(this);
        }
    }
}
