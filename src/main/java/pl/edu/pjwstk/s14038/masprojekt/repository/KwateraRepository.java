package pl.edu.pjwstk.s14038.masprojekt.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.s14038.masprojekt.model.Kwatera;

import java.util.Set;

public interface KwateraRepository extends CrudRepository<Kwatera,Long> {

    @Override
    Set<Kwatera> findAll();
}
