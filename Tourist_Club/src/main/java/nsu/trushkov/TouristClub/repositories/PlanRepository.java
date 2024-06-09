package nsu.trushkov.TouristClub.repositories;

import nsu.trushkov.TouristClub.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
