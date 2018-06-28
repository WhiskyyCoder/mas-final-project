package pl.edu.pjwstk.s14038.masprojekt.model;

public class PokojWyposazenie {

    private Integer ilosc;
    private Pokoj pokoj;
    private Wyposazenie wyposazenie;


    public PokojWyposazenie(Pokoj pokoj, Wyposazenie wyposazenie, Integer ilosc) {
        pokoj.getPokojWyposazenie().add(this);
        wyposazenie.getPokojWyposazenie().add(this);
        this.ilosc = ilosc;
        this.pokoj = pokoj;
        this.wyposazenie = wyposazenie;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public Pokoj getPokoj() {
        return pokoj;
    }

    public Wyposazenie getWyposazenie() {
        return wyposazenie;
    }
}
