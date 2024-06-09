package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import nsu.trushkov.TouristClub.entity.TypeOfSection;
import nsu.trushkov.TouristClub.repositories.TypeOfSectionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeOfSectionService {
    private final TypeOfSectionRepository typeOfSectionRepository;

    public TypeOfSection findByName(String name) {
        return typeOfSectionRepository.findByName(name);
    }

}
