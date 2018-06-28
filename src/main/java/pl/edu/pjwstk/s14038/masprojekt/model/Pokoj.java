package pl.edu.pjwstk.s14038.masprojekt.model;

import pl.edu.pjwstk.s14038.masprojekt.services.UzytkownikService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
public class Pokoj {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer maxOsoby;
    private String wymiar;
    @Enumerated(EnumType.STRING)
    private Toaleta toaleta;
    private Integer balkony;
    @Enumerated(EnumType.STRING)
    private WsparcieDlaNiepelnosprawnych wsparcieDlaNiepelnosprawnych;
    @Enumerated(EnumType.STRING)
    private StandardPokoju standardPokoju;

    @ManyToOne(cascade = CascadeType.ALL)
    private Kwatera kwatera;

    @ManyToMany(mappedBy = "pokoje")
    private Set<Rezerwacja> rezerwacje;

    @Transient
    private Set<Zdjecie> zdjecia = new HashSet<>();

    @Transient
    private Set<PokojWyposazenie> pokojWyposazenie;


    public Set<Zdjecie> getZdjecia() {
        return zdjecia;
    }

    public Pokoj(Integer maxOsoby, String wymiar) {
        this.maxOsoby = maxOsoby;
        this.wymiar = wymiar;
    }

    public Pokoj(){
    }

    public Set<Wyposazenie> pokazWyposazenie() {
        Set<Wyposazenie> wyposazenie = new HashSet<>();
        pokojWyposazenie.forEach(pokojWyposazenie1 -> {
            if (pokojWyposazenie1.getPokoj().equals(this)) {
                wyposazenie.add(pokojWyposazenie1.getWyposazenie());
            }

        });
        return wyposazenie;
    }

    public void dodajWyposazenie(Wyposazenie wyposazenie, Integer ilosc) {
        PokojWyposazenie pokojWyposazenie = new PokojWyposazenie(this, wyposazenie, ilosc);
    }

    public Set<Zdjecie> pokazZdjecia() {

        Set<Zdjecie> zdj = new HashSet<Zdjecie>();
        zdj.addAll(zdjecia);
        return zdj;

    }

    public String pokazInformacje() {
        return "Pokoj{" +
                "id=" + id +
                ", maxOsoby=" + maxOsoby +
                ", wymiar='" + wymiar + '\'' +
                ", toaleta=" + toaleta +
                ", balkony=" + balkony +
                '}';
    }

    public static Set<Pokoj> pokazPokoje( Kwatera kwatera) {

        return kwatera.getPokoje().values().stream().collect(Collectors.toSet());
    }

    public Pokoj setZdjecia(Set<Zdjecie> zdjecia) {
        this.zdjecia = zdjecia;
        return this;
    }

    public Pokoj setKwatera(Kwatera kwatera) {
        this.kwatera = kwatera;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Integer getMaxOsoby() {
        return maxOsoby;
    }

    public String getWymiar() {
        return wymiar;
    }

    public Set<Rezerwacja> getRezerwacje() {
        return rezerwacje;
    }

    public Set<PokojWyposazenie> getPokojWyposazenie() {
        return pokojWyposazenie;
    }
}
