package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Athlete;
import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.repositories.AthleteRepository;
import nsu.trushkov.TouristClub.repositories.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AthleteService {
    private final AthleteRepository athleteRepository;
    private final TouristRepository touristRepository;

    public void createAthlete(Tourist tourist, Integer yearOfWorkExperience) {
        Athlete athlete = Athlete.builder().tourist(tourist).yearOfWorkExperience(yearOfWorkExperience).build();
        log.info(athlete.toString());
        athleteRepository.save(athlete);
    }

    public void updateAthlete(Long id, Integer year) {
        Athlete athlete = athleteRepository.findById(id).get();
        athlete.setYearOfWorkExperience(year);
        athleteRepository.save(athlete);
    }

    public void deleteAthlete(Long id) {
        Tourist tourist = touristRepository.findById(id).get();
        tourist.setTouristType(null);
        touristRepository.save(tourist);
        athleteRepository.deleteById(id);
    }

    public List<Athlete> getAthlete() {
        return athleteRepository.findAll();
    }


}
