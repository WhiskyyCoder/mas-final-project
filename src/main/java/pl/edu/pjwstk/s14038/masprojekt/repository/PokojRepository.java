package pl.edu.pjwstk.s14038.masprojekt.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.s14038.masprojekt.model.Pokoj;
import pl.edu.pjwstk.s14038.masprojekt.model.Uzytkownik;

import java.util.Set;

public interface PokojRepository extends CrudRepository<Pokoj,Long> {

    @Override
    Set<Pokoj> findAll();
}
