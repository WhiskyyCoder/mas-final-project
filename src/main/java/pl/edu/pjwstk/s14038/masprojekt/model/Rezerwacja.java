package pl.edu.pjwstk.s14038.masprojekt.model;

import pl.edu.pjwstk.s14038.masprojekt.services.RezerwacjaService;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Rezerwacja {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dataStworzenia;
    @Enumerated(EnumType.STRING)
    private StanRezerwacji stanRezerwacji;
    private String nazwisko;
    private String telefonKontaktowy;
    private Date od;
    private Date doo;
    private Integer cenaZaOsobe;
    private Integer iloscOsob;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Pokoj> pokoje;
    @Transient
    public List<RezerwacjaUsluga> rezerwacjaUsluga;
    @Transient
    private Set<WiadomoscSMS> wiadomoscSMS;
    @Transient
    private Set<WiadomoscEmail> wiadomoscEmail;


    public Rezerwacja(){
        pokoje=new HashSet<>();
    }

    public static List<Rezerwacja> szukajRezerwacji(RezerwacjaService rezerwacjaService, String surname) {

        List<Rezerwacja> rezerwacjas = rezerwacjaService.getRezerwacjaRepository().findAll();
        List<Rezerwacja> limitedRezerwacjas = rezerwacjas.stream().filter(r -> !r.nazwisko.equalsIgnoreCase(surname)).collect(Collectors.toList());
        return limitedRezerwacjas;
    }

    public static List<Rezerwacja> pokazRezerwacje(RezerwacjaService rezerwacjaService, int limit) {

        List<Rezerwacja> rezerwacjas = rezerwacjaService.getRezerwacjaRepository().findAll();
        List<Rezerwacja> limitedRezerwacjas = rezerwacjas.stream().sorted(Comparator.comparing(rezerwacja -> rezerwacja.dataStworzenia)).limit(limit).collect(Collectors.toList());

        return limitedRezerwacjas;
    }

    public static List<Rezerwacja> pokazRezerwacje(RezerwacjaService rezerwacjaService, int limit, Date od, Date doo) {

        List<Rezerwacja> rezerwacjas = rezerwacjaService.getRezerwacjaRepository().findAll();
        List<Rezerwacja> limitedRezerwacjas = rezerwacjas.stream().sorted(Comparator.comparing(rezerwacja -> rezerwacja.dataStworzenia)).filter(r -> r.od.after(od) && r.doo.before(doo)).limit(limit).collect(Collectors.toList());

        return limitedRezerwacjas;
    }

    public static Rezerwacja rezerwuj(RezerwacjaService rezerwacjaService, Date od, Date doo, String telefonKontaktowy, String nazwisko,Integer cenaZaOsobe, Integer iloscOsob) {
        Rezerwacja rezerwacja = new Rezerwacja();
        rezerwacja.od = od;
        rezerwacja.doo = doo;
        rezerwacja.telefonKontaktowy = telefonKontaktowy;
        rezerwacja.nazwisko = nazwisko;
        rezerwacja.iloscOsob = iloscOsob;
        rezerwacja.dataStworzenia=new Date();
        rezerwacja.cenaZaOsobe=cenaZaOsobe;
        rezerwacja.stanRezerwacji=StanRezerwacji.Stworzona;
        rezerwacjaService.getRezerwacjaRepository().save(rezerwacja);
        return rezerwacja;
    }

    public double getzadatek(){
        return Math.round(0.3*getfinalnyKoszt());
    }

    public Long getfinalnyKoszt(){
        LocalDate date = od.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = doo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long dni = ChronoUnit.DAYS.between(date, date2);
        return (iloscOsob*Kwatera.oplataKlimatyczna*dni)+(iloscOsob*cenaZaOsobe*dni);

    }
    public static Set<Pokoj> pokazDostepnePokoje(RezerwacjaService rezerwacjaService, Set<Pokoj> pokoje, Date od, Date doo) {
        return pokoje.stream().filter(pokoj -> {
            for (Rezerwacja r : pokoj.getRezerwacje()) {
                if (doo.after(r.getOd()) && doo.before(r.getDoo())) {
                    return false;
                }
                if (od.after(r.getOd()) && od.before(r.getDoo())) {
                    return false;
                }
            }

            return true;
        }).collect(Collectors.toSet());

    }

    public void odwolaj() {
        stanRezerwacji = StanRezerwacji.Odwolana;
    }

    public void potwierdz() {
        stanRezerwacji = StanRezerwacji.Potwierdzona;
    }

    public Date getOd() {
        return od;
    }

    public Date getDoo() {
        return doo;
    }

    public Set<WiadomoscSMS> getWiadomoscSMS() {
        return wiadomoscSMS;
    }

    public Set<WiadomoscEmail> getWiadomoscEmail() {
        return wiadomoscEmail;
    }

    public Long getId() {
        return id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Rezerwacja setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
        return this;
    }

    public String getTelefonKontaktowy() {
        return telefonKontaktowy;
    }

    public Rezerwacja setTelefonKontaktowy(String telefonKontaktowy) {
        this.telefonKontaktowy = telefonKontaktowy;
        return this;
    }

    public Rezerwacja setOd(Date od) {
        this.od = od;
        return this;
    }

    public Rezerwacja setDoo(Date doo) {
        this.doo = doo;
        return this;
    }

    public Integer getIloscOsob() {
        return iloscOsob;
    }

    public Rezerwacja setIloscOsob(Integer iloscOsob) {
        this.iloscOsob = iloscOsob;
        return this;
    }

    public Rezerwacja setId(Long id) {
        this.id = id;
        return this;
    }



    public Integer getCenaZaOsobe() {
        return cenaZaOsobe;
    }

    public Rezerwacja setCenaZaOsobe(Integer cenaZaOsobe) {
        this.cenaZaOsobe = cenaZaOsobe;
        return this;
    }

    public Rezerwacja setWiadomoscSMS(Set<WiadomoscSMS> wiadomoscSMS) {
        this.wiadomoscSMS = wiadomoscSMS;
        return this;
    }

    public Rezerwacja setWiadomoscEmail(Set<WiadomoscEmail> wiadomoscEmail) {
        this.wiadomoscEmail = wiadomoscEmail;
        return this;
    }

    public Set<Pokoj> getPokoje() {
        return pokoje;
    }

    public Rezerwacja setPokoje(Set<Pokoj> pokoje) {
        this.pokoje = pokoje;
        return this;
    }
}
