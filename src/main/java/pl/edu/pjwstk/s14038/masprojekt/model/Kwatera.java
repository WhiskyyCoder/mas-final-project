package pl.edu.pjwstk.s14038.masprojekt.model;

import pl.edu.pjwstk.s14038.masprojekt.services.RezerwacjaService;
import pl.edu.pjwstk.s14038.masprojekt.services.UzytkownikService;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Kwatera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazwa;
    private String adres;
    private Integer maxPokoi;
    public static Integer oplataKlimatyczna = 2;
    @ManyToOne(cascade = CascadeType.ALL)
    private Uzytkownik wlasciciel;
    @OneToMany(mappedBy = "kwatera")
    @MapKey(name="id")
    private Map<Long, Pokoj> pokoje = new HashMap<>();

    public Kwatera(String nazwa, String adres, Integer maxPokoi, Uzytkownik wlasciciel) {
        this.nazwa = nazwa;
        this.adres = adres;
        this.maxPokoi = maxPokoi;
        this.wlasciciel = wlasciciel;
    }

    public Kwatera(){}
    public void dodajPokoj(RezerwacjaService rezerwacjaService, Pokoj pokoj) {
        pokoj.setKwatera(this);
        rezerwacjaService.getPokojRepository().save(pokoj);
    }

    public void usunPokoj(UzytkownikService uzytkownikService, Pokoj pokoj) {
        pokoje.remove(pokoj.getId());
        uzytkownikService.getKwateraRepository().save(this);
    }

    public long podajOblozenie(UzytkownikService uzytkownikService, Date from, Date to) {
        long count = 0;
        for (Long key : pokoje.keySet()) {
            Pokoj pokoj = pokoje.get(key);
            count += pokoj.getRezerwacje().stream().filter(r -> from.after(r.getOd()) && to.before(r.getDoo())).count();
        }
        return count;
    }

    public static Set<Kwatera> pokazKwatery(UzytkownikService uzytkownikService, Uzytkownik uzytkownik) {
        Set<Kwatera> kwatery = uzytkownikService.getKwateraRepository().findAll();
        return kwatery.stream().filter(kwatera -> kwatera.wlasciciel.equals(uzytkownik)).collect(Collectors.toSet());
    }

    public Map<Long, Pokoj> getPokoje() {
        return pokoje;
    }

    public Long getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public Integer getMaxPokoi() {
        return maxPokoi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kwatera kwatera = (Kwatera) o;

        if (!id.equals(kwatera.id)) return false;
        return nazwa.equals(kwatera.nazwa);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nazwa.hashCode();
        return result;
    }
}
