package pl.edu.pjwstk.s14038.masprojekt.model;

import java.util.Date;
import java.util.HashSet;

public class WiadomoscSMS {
    private Long id;
    private Date data;
    private String wiadomosc;
    private Rezerwacja rezerwacja;

    public static void wyslijWiadomosc(String wiadomosc,Rezerwacja rezerwacja){

        if(rezerwacja.getWiadomoscEmail()==null){
            if(rezerwacja.getWiadomoscSMS()==null)
                rezerwacja.setWiadomoscSMS(new HashSet<>());

            WiadomoscSMS wiadomoscSMS=new WiadomoscSMS(new Date(),wiadomosc,rezerwacja);
            rezerwacja.getWiadomoscSMS().add(wiadomoscSMS);

        }
    }

    public WiadomoscSMS( Date date, String message, Rezerwacja rezerwacja) {
        this.data = date;
        this.wiadomosc = message;
        this.rezerwacja = rezerwacja;
    }
}
