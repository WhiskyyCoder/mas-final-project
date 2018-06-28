package pl.edu.pjwstk.s14038.masprojekt.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.s14038.masprojekt.model.Jezyk;

import java.util.List;

public interface JezykRepository extends CrudRepository<Jezyk,String> {

    @Override
    List<Jezyk> findAll();
}
