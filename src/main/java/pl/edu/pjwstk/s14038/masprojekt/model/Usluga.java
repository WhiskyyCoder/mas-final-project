package pl.edu.pjwstk.s14038.masprojekt.model;

import java.util.List;

public class Usluga {

    private Long id;
    private String nazwa;
    private int koszt;

    public List<RezerwacjaUsluga> rezerwacjaUsluga;

    public Usluga(String nazwa, int cena) {
        this.nazwa = nazwa;
        this.koszt= cena;
    }

}
