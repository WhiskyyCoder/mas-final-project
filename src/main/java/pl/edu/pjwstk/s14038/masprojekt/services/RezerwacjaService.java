package pl.edu.pjwstk.s14038.masprojekt.services;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.s14038.masprojekt.repository.PokojRepository;
import pl.edu.pjwstk.s14038.masprojekt.repository.RezerwacjaRepository;

@Service
public class RezerwacjaService {
    private RezerwacjaRepository rezerwacjaRepository;
    private PokojRepository pokojRepository;

    public RezerwacjaService(RezerwacjaRepository rezerwacjaRepository, PokojRepository pokojRepository) {
        this.rezerwacjaRepository = rezerwacjaRepository;
        this.pokojRepository = pokojRepository;

    }

    public RezerwacjaRepository getRezerwacjaRepository() {
        return rezerwacjaRepository;
    }

    public PokojRepository getPokojRepository() {
        return pokojRepository;
    }
}
