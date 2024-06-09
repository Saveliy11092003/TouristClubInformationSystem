package nsu.trushkov.TouristClub.repositories;

import nsu.trushkov.TouristClub.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

}
