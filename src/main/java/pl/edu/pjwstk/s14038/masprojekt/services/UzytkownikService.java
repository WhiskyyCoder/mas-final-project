package pl.edu.pjwstk.s14038.masprojekt.services;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.s14038.masprojekt.model.RodzajUzytkownika;
import pl.edu.pjwstk.s14038.masprojekt.model.Uzytkownik;
import pl.edu.pjwstk.s14038.masprojekt.repository.RodzajUzytkownikaRepository;
import pl.edu.pjwstk.s14038.masprojekt.repository.KwateraRepository;
import pl.edu.pjwstk.s14038.masprojekt.repository.JezykRepository;
import pl.edu.pjwstk.s14038.masprojekt.repository.UzytkownikRepository;

@Service
public class UzytkownikService {
    private RodzajUzytkownikaRepository rodzajUzytkownikaRepository;
    private JezykRepository jezykRepository;
    private UzytkownikRepository uzytkownikRepository;
    private KwateraRepository kwateraRepository;

    public UzytkownikService(RodzajUzytkownikaRepository rodzajUzytkownikaRepository, JezykRepository jezykRepository, KwateraRepository kwateraRepository, UzytkownikRepository uzytkownikRepository) {
        this.rodzajUzytkownikaRepository = rodzajUzytkownikaRepository;
        this.jezykRepository = jezykRepository;
        this.uzytkownikRepository = uzytkownikRepository;
        this.kwateraRepository=kwateraRepository;
    }

    public RodzajUzytkownikaRepository getRodzajUzytkownikaRepository() {
        return rodzajUzytkownikaRepository;
    }

    public JezykRepository getJezykRepository() {
        return jezykRepository;
    }

    public UzytkownikRepository getUzytkownikRepository() {
        return uzytkownikRepository;
    }

    public KwateraRepository getKwateraRepository() {
        return kwateraRepository;
    }

    public Uzytkownik getUserById(Long id){
        return uzytkownikRepository.findById(id).orElse(new Uzytkownik());

    }

    public RodzajUzytkownika getKindUserById(String name){
        return rodzajUzytkownikaRepository.findById(name).orElse(new RodzajUzytkownika(name));

    }


}
