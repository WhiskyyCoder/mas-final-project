package pl.edu.pjwstk.s14038.masprojekt.model;

import javax.persistence.*;

@Entity
public class RodzajUzytkownika {

    @Id
    private String name;

    public String getName() {
        return name;
    }


    public RodzajUzytkownika(String name) {
        this.name = name;
    }

    public RodzajUzytkownika() {
    }

    public RodzajUzytkownika setName(String name) {
        this.name = name;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RodzajUzytkownika rodzajUzytkownika = (RodzajUzytkownika) o;

        return getName() != null ? getName().equals(rodzajUzytkownika.getName()) : rodzajUzytkownika.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
