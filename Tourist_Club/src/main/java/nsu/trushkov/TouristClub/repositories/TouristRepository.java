package nsu.trushkov.TouristClub.repositories;

import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.entity.enums.TouristType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface TouristRepository extends JpaRepository<Tourist, Long> {
    public List<Tourist> findByGroupNotNull();
}
