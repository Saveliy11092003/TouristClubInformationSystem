package nsu.trushkov.TouristClub.repositories;

import nsu.trushkov.TouristClub.entity.TypeOfSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfSectionRepository extends JpaRepository<TypeOfSection, Long> {

    TypeOfSection findByName(String name);

}
