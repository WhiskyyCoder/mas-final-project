package pl.edu.pjwstk.s14038.masprojekt.model;

import java.util.Date;
import java.util.HashSet;

public class WiadomoscEmail {
    private Long id;
    private String email;
    private Date data;
    private String wiadomosc;
    private Rezerwacja rezerwacja;

    public static void wyslijWiadomosc(String wiadomosc,String email,Rezerwacja rezerwacja){

        if(rezerwacja.getWiadomoscSMS()==null){
            if(rezerwacja.getWiadomoscEmail()==null)
               rezerwacja.setWiadomoscEmail(new HashSet<>());

            WiadomoscEmail wiadomoscEmail=new WiadomoscEmail(email,new Date(),wiadomosc,rezerwacja);
            rezerwacja.getWiadomoscEmail().add(wiadomoscEmail);

        }
    }

    public WiadomoscEmail(String email, Date data, String wiadomosc, Rezerwacja rezerwacja) {
        this.email = email;
        this.data = data;
        this.wiadomosc = wiadomosc;
        this.rezerwacja = rezerwacja;
    }
}
