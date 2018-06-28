package pl.edu.pjwstk.s14038.masprojekt.model;

import pl.edu.pjwstk.s14038.masprojekt.services.UzytkownikService;

import javax.persistence.Entity;

@Entity
public class WłascicielPortalu extends Uzytkownik {
    private int pin;

    private WłascicielPortalu(UzytkownikService uzytkownikService) {
        super(uzytkownikService);
    }

    public static Uzytkownik dodajWlascicielaPortalu(UzytkownikService uzytkownikService, int pin, String email , String haslo) {
        WłascicielPortalu user=new WłascicielPortalu(uzytkownikService);
        user.zmienEmail(email);
        user.setPin(pin);
        user.zmienHaslo(haslo);
        user.getRodzajUzytkownika().add(uzytkownikService.getKindUserById("WłascicielPortalu"));
        uzytkownikService.getUzytkownikRepository().save(user);
        return user;
    }

    public int getPin() {
        return pin;
    }

    public WłascicielPortalu setPin(int pin) {
        this.pin = pin;
        return this;
    }
}
