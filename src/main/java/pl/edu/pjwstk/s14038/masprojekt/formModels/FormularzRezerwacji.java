package pl.edu.pjwstk.s14038.masprojekt.formModels;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


public class FormularzRezerwacji {
    @NotEmpty(message = "Pole to jest wymagane")
    private String nazwisko;
    @NotEmpty(message = "Pole to jest wymagane")
    @Pattern( regexp = "\\+48[0-9]+", message = "Tylko telefon z polski +48")
    private String telefonKontaktowy;
    @NotNull(message = "Pole to jest wymagane")
    private Integer cenaZaOsobe;
    @NotNull(message = "Pole to jest wymagane")
    private Integer iloscOsob;
    private String od;
    private String doo;
    private List<Integer> idPokoje;

    public String getNazwisko() {
        return nazwisko;
    }

    public FormularzRezerwacji setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
        return this;
    }

    public String getTelefonKontaktowy() {
        return telefonKontaktowy;
    }

    public FormularzRezerwacji setTelefonKontaktowy(String telefonKontaktowy) {
        this.telefonKontaktowy = telefonKontaktowy;
        return this;
    }

    public Integer getCenaZaOsobe() {
        return cenaZaOsobe;
    }

    public FormularzRezerwacji setCenaZaOsobe(Integer cenaZaOsobe) {
        this.cenaZaOsobe = cenaZaOsobe;
        return this;
    }

    public Integer getIloscOsob() {
        return iloscOsob;
    }

    public FormularzRezerwacji setIloscOsob(Integer iloscOsob) {
        this.iloscOsob = iloscOsob;
        return this;
    }

    public String getOd() {
        return od;
    }

    public FormularzRezerwacji setOd(String od) {
        this.od = od;
        return this;
    }

    public String getDoo() {
        return doo;
    }

    public FormularzRezerwacji setDoo(String doo) {
        this.doo = doo;
        return this;
    }

    public List<Integer> getIdPokoje() {
        return idPokoje;
    }

    public FormularzRezerwacji setIdPokoje(List<Integer> idPokoje) {
        this.idPokoje = idPokoje;
        return this;
    }
}
