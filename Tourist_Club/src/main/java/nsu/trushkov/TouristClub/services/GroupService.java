package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Coach;
import nsu.trushkov.TouristClub.entity.Group;
import nsu.trushkov.TouristClub.entity.Section;
import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.repositories.CoachRepository;
import nsu.trushkov.TouristClub.repositories.GroupRepository;
import nsu.trushkov.TouristClub.repositories.SectionRepository;
import nsu.trushkov.TouristClub.repositories.TouristRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupService {
    private final GroupRepository groupRepository;
    private final TouristRepository touristRepository;
    private final SectionRepository sectionRepository;
    private final CoachRepository coachRepository;

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public void createGroup(List<Long> idsTourist, Long sectionId,Long coachId, String nameGroup) {
        Group group = new Group();
        group.setNameGroup(nameGroup);
        for (Long id : idsTourist) {
            Tourist tourist = touristRepository.findById(id).get();
            group.addTourist(tourist);
        }

        System.out.println(group);

        Section section = sectionRepository.findById(sectionId).orElse(new Section());
        Coach coach = coachRepository.findById(coachId).orElse(new Coach());
      /*
        System.out.println(section);
        log.info("section");
        log.info(section.toString());

        log.info("group");
        log.info(group.toString());

        log.info("1");
        */

        section.addGroup(group);
        coach.addGroup(group);
        groupRepository.save(group);
        //sectionRepository.save(section);
        log.info("2");

    }

    public void deleteGroup(Long id) {
        Group group = groupRepository.findById(id).get();
        log.info(group.toString());
        Section section = group.getSection();
        Coach coach = group.getCoach();
        log.info(section.toString());
        log.info(coach.toString());

        group.removeTourists();

        section.removeGroup(group);
        coach.removeGroup(group);
        groupRepository.deleteById(id);
    }

    public void updateGroup(Long groupId, List<Long> ids, String nameGroup) {
        log.info("ids " + ids.toString());
        List<Tourist> tourists = new ArrayList<>();
        for (Long id : ids) {
            Tourist tourist = touristRepository.findById(id).get();
            tourists.add(tourist);
        }

        log.info("begin tourist " + tourists);

        Group group = groupRepository.findById(groupId).get();
        group.setNameGroup(nameGroup);

        group.updateTourists(tourists);
        for (Tourist tourist : tourists) {
            tourist.setGroup(group);
        }
        //log.info(group.getTourists().toString());
        groupRepository.save(group);
    }


}
