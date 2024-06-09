package nsu.trushkov.TouristClub.repositories;

import nsu.trushkov.TouristClub.entity.Amateur;
import nsu.trushkov.TouristClub.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}
