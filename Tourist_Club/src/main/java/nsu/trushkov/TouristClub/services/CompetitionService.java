package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.*;
import nsu.trushkov.TouristClub.entity.enums.TouristType;
import nsu.trushkov.TouristClub.repositories.AthleteRepository;
import nsu.trushkov.TouristClub.repositories.CompetitionRepository;
import nsu.trushkov.TouristClub.repositories.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final AthleteRepository athleteRepository;
    private final SectionRepository sectionRepository;

    public List<Competition> getCompetitions() {
        return competitionRepository.findAll();
    }

    public void createCompetition(String nameCompetition, String description,List<Long> athleteIds) {
        Competition competition = Competition.builder().nameCompetition(nameCompetition).athletes(new ArrayList<>()).
                description(description).build();

        for (Long id : athleteIds) {
            Athlete athlete = athleteRepository.findById(id).get();
            log.info("1");
            athlete.addCompetition(competition);
            log.info("2");
            log.info(athlete.toString());
        }
        log.info(competition.toString());

        competitionRepository.save(competition);

    }

    public void deleteCompetition(Long id) {
        Competition competition = competitionRepository.findById(id).get();
        competition.setAthletes(null);
        competitionRepository.deleteById(id);
    }


    public void updateCompetition(long idCompetition, List<Long> idsAthletes, String nameCompetition, String description) {
        Competition competition = competitionRepository.findById(idCompetition).get();
        competition.setNameCompetition(nameCompetition);
        competition.setDescription(description);

        List<Athlete> athletes = new ArrayList<>();
        for (Long id : idsAthletes) {
            Athlete athlete = athleteRepository.findById(id).get();
            athletes.add(athlete);
        }

        competition.updateAthletes(athletes);

        competitionRepository.save(competition);

    }

    public List<Competition> getCompetitionsBySection(Long idSection, String allSection) {
        Set<Competition> competitionSet = new HashSet<>();

        if (allSection.equals("All")) {
            for (Section section : sectionRepository.findAll()) {
                for (Group group : section.getGroups()) {
                    for (Tourist tourist : group.getTourists()) {
                        if (tourist.getTouristType() == TouristType.ATHLETE) {
                            Optional<Athlete> athlete = athleteRepository.findById(tourist.getId());
                            if (athlete.isPresent()) {
                                competitionSet.addAll(athlete.get().getCompetitions());
                            }
                        }
                    }
                }
            }
        } else {
            Section section = sectionRepository.findById(idSection).get();
            for (Group group : section.getGroups()) {
                for (Tourist tourist : group.getTourists()) {
                    if (tourist.getTouristType() == TouristType.ATHLETE) {
                        Optional<Athlete> optAthlete = athleteRepository.findById(tourist.getId());
                        if (optAthlete.isPresent()) {
                            competitionSet.addAll(optAthlete.get().getCompetitions());
                        }
                    }
                }
            }
        }

        List<Competition> result = new ArrayList<>(competitionSet);
        return result;
    }


}
