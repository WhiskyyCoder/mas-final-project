package pl.edu.pjwstk.s14038.masprojekt.model;

import java.util.Set;

public class Dozorca {
    private Long id;
    private String nazwa;

    private Set<Wyposazenie> opiekujeSie;
    private Set<Wyposazenie> uprawnienieDoZmiany;

    public Dozorca(String nazwa) {
        this.nazwa = nazwa;
    }

    public Set<Wyposazenie> getOpiekujeSie() {
        return opiekujeSie;
    }

    public Set<Wyposazenie> getUprawnienieDoZmiany() {
        return uprawnienieDoZmiany;
    }
}
