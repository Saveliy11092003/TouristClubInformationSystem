package nsu.trushkov.TouristClub.repositories;

import nsu.trushkov.TouristClub.entity.PlannedCamping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannedCampingRepository extends JpaRepository<PlannedCamping, Long> {
}
