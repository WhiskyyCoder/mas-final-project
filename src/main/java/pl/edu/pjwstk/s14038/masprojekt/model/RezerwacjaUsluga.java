package pl.edu.pjwstk.s14038.masprojekt.model;

import java.util.Date;

public class RezerwacjaUsluga {
    private Long id;
    private Date dataRealizacji;
    private Usluga usluga;
    private Rezerwacja rezerwacja;


    public RezerwacjaUsluga(Usluga usluga, Rezerwacja rezerwacja,Date dataRealizacji) {
        this.usluga = usluga;
        this.rezerwacja = rezerwacja;
        this.dataRealizacji=dataRealizacji;
        usluga.rezerwacjaUsluga.add(this);
        rezerwacja.rezerwacjaUsluga.add(this);

    }
}
