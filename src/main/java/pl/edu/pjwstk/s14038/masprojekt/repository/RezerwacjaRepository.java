package pl.edu.pjwstk.s14038.masprojekt.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.s14038.masprojekt.model.Rezerwacja;

import java.util.List;

public interface RezerwacjaRepository extends CrudRepository<Rezerwacja,Long> {

    @Override
    List<Rezerwacja> findAll();
}
