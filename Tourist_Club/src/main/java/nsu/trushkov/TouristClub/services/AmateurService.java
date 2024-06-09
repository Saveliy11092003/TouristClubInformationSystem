package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Amateur;
import nsu.trushkov.TouristClub.entity.Coach;
import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.repositories.AmateurRepository;
import nsu.trushkov.TouristClub.repositories.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AmateurService {
    private final AmateurRepository amateurRepository;
    private final TouristRepository touristRepository;

    public void createAmateur(Tourist tourist, Integer yearOfWorkExperience) {
        Amateur amateur = Amateur.builder().tourist(tourist).yearOfWorkExperience(yearOfWorkExperience).build();
        log.info(amateur.toString());
        amateurRepository.save(amateur);
    }

    public void updateAmateur(Long id, Integer year) {
        Amateur amateur = amateurRepository.findById(id).get();
        amateur.setYearOfWorkExperience(year);
        amateurRepository.save(amateur);
    }

    public void deleteAmateur(Long id) {
        Tourist tourist = touristRepository.findById(id).get();
        tourist.setTouristType(null);
        touristRepository.save(tourist);
        amateurRepository.deleteById(id);
    }

    public List<Amateur> getAmateurs() {
        return amateurRepository.findAll();
    }


}
