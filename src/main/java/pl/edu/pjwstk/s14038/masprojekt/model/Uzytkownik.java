package pl.edu.pjwstk.s14038.masprojekt.model;

import pl.edu.pjwstk.s14038.masprojekt.services.UzytkownikService;

import javax.persistence.*;
import java.util.*;

@Entity
public class Uzytkownik {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String haslo;
    private String numerTelefonu;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Uzytkownik_Jezyk", joinColumns = @JoinColumn(name = "idUzytkownik"), inverseJoinColumns = @JoinColumn(name = "idJezyk"))
    private Set<Jezyk> jezyki;
    private String nip;
    private String regon;
    private String imie;
    private String nazwisko;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "RodzajUzytkownika_Uzytkownik", joinColumns = @JoinColumn(name = "idUzytkownik"), inverseJoinColumns = @JoinColumn(name = "idRodzajUzytkownika"))
    private Set<RodzajUzytkownika> rodzajUzytkownika;

    @OneToMany(mappedBy = "wlasciciel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Kwatera> kwatery = new LinkedList<>();


    public Uzytkownik() {
    }

    protected Uzytkownik(UzytkownikService uzytkownikService) {
        this.rodzajUzytkownika = new HashSet<>();
        this.rodzajUzytkownika.add(uzytkownikService.getKindUserById("Uzytkownik"));
    }

    public static Uzytkownik dodajPracownika(UzytkownikService uzytkownikService, String email, String haslo, String numerTelefonu, Set<Jezyk> jezyki) {
        Uzytkownik uzytkownik = new Uzytkownik(uzytkownikService);
        uzytkownik.setJezyki(jezyki);
        uzytkownik.zmienEmail(email);
        uzytkownik.setNumerTelefonu(numerTelefonu);
        uzytkownik.zmienHaslo(haslo);
        uzytkownik.getRodzajUzytkownika().add(uzytkownikService.getKindUserById("Pracownik"));
        uzytkownikService.getUzytkownikRepository().save(uzytkownik);
        return uzytkownik;
    }

    public static Uzytkownik dodajWlasciciela(UzytkownikService uzytkownikService, String nip, String regon, String imie, String nazwisko, String email, String haslo) {
        Uzytkownik uzytkownik = new Uzytkownik(uzytkownikService);
        uzytkownik.zmienEmail(email);
        uzytkownik.setNip(nip);
        uzytkownik.setRegon(regon);
        uzytkownik.setImie(imie);
        uzytkownik.zmienHaslo(haslo);
        uzytkownik.setNazwisko(nazwisko);
        uzytkownik.getRodzajUzytkownika().add(uzytkownikService.getKindUserById("Wlasciciel"));
        uzytkownikService.getUzytkownikRepository().save(uzytkownik);
        return uzytkownik;
    }


    public String getNip() {
        if (nip == null)
            return "Brak nip";
        return nip;
    }

    public Uzytkownik setNip(String nip) {
        this.nip = nip;
        return this;
    }


    public String getRegon() {
        if (regon == null)
            return "Brak regonu";
        return regon;
    }

    public Uzytkownik setRegon(String regon) {
        this.regon = regon;
        return this;
    }


    public String getImie() {
        if (imie == null)
            return "Brak imienia";
        return imie;
    }

    public Uzytkownik setImie(String imie) {
        this.imie = imie;
        return this;
    }


    public String getNazwisko() {
        if (nazwisko == null)
            return "Brak nazwiska";
        return nazwisko;
    }

    public Uzytkownik setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
        return this;
    }

    public void zmienHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void zmienEmail(String email) {
        this.email = email;
    }

    public void zmienNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Set<Jezyk> getJezyki() {
        return jezyki;
    }

    public Uzytkownik setJezyki(Set<Jezyk> jezyki) {
        this.jezyki = jezyki;
        return this;
    }

    public Uzytkownik setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
        return this;
    }

    public Set<RodzajUzytkownika> getRodzajUzytkownika() {
        return rodzajUzytkownika;
    }

    public List<Kwatera> getKwatery() {
        return kwatery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Uzytkownik that = (Uzytkownik) o;

        if (!id.equals(that.id)) return false;
        if (!getEmail().equals(that.getEmail())) return false;
        return haslo != null ? haslo.equals(that.haslo) : that.haslo == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + (haslo != null ? haslo.hashCode() : 0);
        return result;
    }
}
