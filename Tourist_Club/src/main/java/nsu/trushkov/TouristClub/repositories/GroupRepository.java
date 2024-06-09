package nsu.trushkov.TouristClub.repositories;

import nsu.trushkov.TouristClub.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
