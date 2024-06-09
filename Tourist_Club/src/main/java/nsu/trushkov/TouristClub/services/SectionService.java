package nsu.trushkov.TouristClub.services;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Section;
import nsu.trushkov.TouristClub.entity.Supervisor;
import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.entity.TypeOfSection;
import nsu.trushkov.TouristClub.entity.enums.TypeOfSport;
import nsu.trushkov.TouristClub.repositories.SectionRepository;
import nsu.trushkov.TouristClub.repositories.SupervisorRepository;
import nsu.trushkov.TouristClub.repositories.TypeOfSectionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SectionService {
    private final SectionRepository sectionRepository;
    private final TypeOfSectionService typeOfSectionService;
    private final SupervisorRepository supervisorRepository;
    private final TypeOfSectionRepository typeOfSectionRepository;


    public List<Section> getSections() {
        for (Section section : sectionRepository.findAll()) {
            log.info(section.toString());
        }
        return sectionRepository.findAll();
    }

    public void removeSection(long id) {
        Optional<Section> optionalSection = sectionRepository.findById(id);
        Section section = optionalSection.get();
        TypeOfSection typeOfSection = section.getTypeOfSection();
        typeOfSection.removeSection(section);
        sectionRepository.deleteById(id);
        //  sectionRepository.deleteById(id);
    }

    public Section getSection(Long idSection) {
        return sectionRepository.findById(idSection).get();
    }

    public void updateSection(long id, Section section) {
        log.info("Update Section begin");

        Section sectionNew = sectionRepository.getById(id);

        Supervisor supervisor = section.getSupervisor();
        sectionNew.setName(section.getName());
        sectionNew.getSupervisor().setName(supervisor.getName());
        sectionNew.getSupervisor().setSurname(supervisor.getSurname());
        sectionNew.getSupervisor().setSalary(supervisor.getSalary());
        sectionNew.getSupervisor().setAge(supervisor.getAge());
        sectionNew.getSupervisor().setDateOfEntry(supervisor.getDateOfEntry());
        sectionNew.getSupervisor().setDateOfBirth(supervisor.getDateOfBirth());

        sectionNew.getTypeOfSection().setName(section.getTypeOfSection().getName());

        sectionRepository.save(sectionNew);
        /*
        TypeOfSection typeOfSection = sectionNew.getTypeOfSection();
        log.info(typeOfSection.toString());
        typeOfSection.removeSection(sectionNew);
        sectionRepository.delete(sectionNew);
        log.info(typeOfSection.toString());

        sectionNew.setTypeOfSection(section.getTypeOfSection());
        sectionNew.setSupervisor(section.getSupervisor());
        log.info("11111111111111111111111111111");
        typeOfSection.addSection(sectionNew);
        log.info(typeOfSection.toString());
        log.info(section.getTypeOfSection().getName().toString());
        sectionNew.setId(null);
        sectionNew.getTypeOfSection().setId(null);
        sectionNew.getSupervisor().setId(null);
        sectionRepository.save(sectionNew);
        log.info("22222222222222222222222222222");

         */
        log.info("Update Section end");
    }

    public void createSection(String typeOfSectionName, String nameSection, String supervisorAge,
                              String supervisorSurname, String supervisorName, String supervisorSalary,
                              String dateOfBirth, String dateOfEntry, String type) {
        log.info(type);
        TypeOfSport typeOfSport = TypeOfSport.FOOTBALL;
        if (type.equals("FOOTBALL")) {
            typeOfSport = TypeOfSport.FOOTBALL;
        } else if (type.equals("SWIMMING")) {
            typeOfSport = TypeOfSport.SWIMMING_SPORT;
        } else if (type.equals("HORSEBACK")) {
            typeOfSport = TypeOfSport.HORSEBACK_RIDING;
        } else if (type.equals("MOUNTAIN")) {
            typeOfSport = TypeOfSport.MOUNTAIN_SPORT;
        }

        log.info("typeOfSport " + typeOfSport.toString());
        TypeOfSection typeOfSection = TypeOfSection.builder().typeOfSport(typeOfSport).name(typeOfSectionName).build();

        Supervisor supervisor = Supervisor.builder().surname(supervisorSurname).name(supervisorName).age(supervisorAge)
                .dateOfEntry(dateOfEntry).dateOfBirth(dateOfBirth).salary(supervisorSalary).build();
        supervisorRepository.save(supervisor);

        Section section = Section.builder().name(nameSection).supervisor(supervisor).build();

        log.info("typeOfSection " + typeOfSection.toString());
        typeOfSectionRepository.save(typeOfSection);
        typeOfSection.addSection(section);
        sectionRepository.save(section);
    }

    public List<Supervisor> supervisorRequest(String salary, String allSalary, Integer yearOfBirth, String allYearsBirth,
                                              Integer yearOfWork, String allYearsWork) {
        Set<Supervisor> supervisors = new HashSet<>();

        for (Supervisor supervisor : supervisorRepository.findAll()) {
            if (allSalary.equals("All")) {
                supervisors.add(supervisor);
            } else {
                if (Integer.parseInt(supervisor.getSalary()) <= Integer.parseInt(salary)) {
                    supervisors.add(supervisor);
                }
            }
        }

        log.info("supervisors " + supervisors.toString());

        List<Supervisor> minus = new ArrayList<>();

        for (Supervisor supervisor : supervisors) {
            if (allYearsBirth.equals("All")) {

            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                int year = LocalDate.parse(supervisor.getDateOfBirth(), formatter).getYear();
                System.out.println("year " + year);
                if (yearOfBirth != year) {
                    minus.add(supervisor);
                }
            }
        }

        log.info("minus " + minus.toString());

        supervisors.removeAll(minus);
        minus.clear();

        System.out.println("yearOfWork "  + yearOfWork);
        for (Supervisor supervisor : supervisors) {
            if (allYearsWork.equals("All")) {

            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                int year = LocalDate.parse(supervisor.getDateOfEntry(), formatter).getYear();
                if (yearOfWork != year) {
                    minus.add(supervisor);
                }
            }
        }

        log.info("minus " + minus.toString());

        supervisors.removeAll(minus);
        minus.clear();


        return new ArrayList<>(supervisors);
    }
}

