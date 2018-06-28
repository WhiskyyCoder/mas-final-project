package pl.edu.pjwstk.s14038.masprojekt.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.s14038.masprojekt.model.RodzajUzytkownika;

import java.util.List;

public interface RodzajUzytkownikaRepository extends CrudRepository<RodzajUzytkownika,String> {

    @Override
    List<RodzajUzytkownika> findAll();
}
