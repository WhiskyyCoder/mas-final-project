package pl.edu.pjwstk.s14038.masprojekt.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Jezyk {

    @Id
    private String nazwa;

    @ManyToMany(mappedBy = "jezyki")
    private Set<Uzytkownik> uzytkownicy;


    public String getNazwa() {
        return nazwa;
    }

    public Jezyk setNazwa(String nazwa) {
        this.nazwa = nazwa;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jezyk jezyk = (Jezyk) o;

        if (getNazwa() != null ? !getNazwa().equals(jezyk.getNazwa()) : jezyk.getNazwa() != null) return false;
        return uzytkownicy != null ? uzytkownicy.equals(jezyk.uzytkownicy) : jezyk.uzytkownicy == null;
    }

    @Override
    public int hashCode() {
        int result = getNazwa() != null ? getNazwa().hashCode() : 0;
        result = 31 * result + (uzytkownicy != null ? uzytkownicy.hashCode() : 0);
        return result;
    }
}
